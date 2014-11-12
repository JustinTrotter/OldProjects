module RegExp where

import Data.List (foldl1)

type RegExp = String -> Bool

epsilon :: RegExp
epsilon = (=="")

char :: Char -> RegExp
char ch = (==[ch])

(|||) :: RegExp -> RegExp -> RegExp
e1 ||| e2 = 
   \x -> e1 x || e2 x

(<*>) :: RegExp -> RegExp -> RegExp
e1 <*> e2 =  \x -> or [e1 y && e2 z | (y,z) <- splits x]

splits :: String -> [(String, String)]
splits xs = [splitAt n xs | n <- [0..len]]
    where
      len = length xs

(<**>) :: RegExp -> RegExp -> RegExp
e1 <**> e2 = \x -> or [e1 y && e2 z | (y,z) <- fsplits x]

star :: RegExp -> RegExp
star p = epsilon ||| (p <**> star p)
--	 epislon ||| (p <~> star p)

fsplits :: String -> [(String, String)]
fsplits xs = tail (splits xs)

a,b :: RegExp
a = char 'a'
b = char 'b'
zero = char '0'
one = char '0'

option, plus :: RegExp -> RegExp
option e = epsilon ||| e
plus e = e <*> star e

digits :: RegExp
digits = (char '1' ||| char '2' ||| char '3' ||| char '4'  ||| char '5' ||| char '6' ||| char '7' ||| char '8'  ||| char '9') <*> star ((char '0' ||| char '1' ||| char '2' ||| char '3' ||| char '4'  ||| char '5' ||| char '6' ||| char '7' ||| char '8'  ||| char '9'))

floats :: RegExp
floats  = digits <*> char '.' <*> star ((char '0' ||| char '1' ||| char '2' ||| char '3' ||| char '4'  ||| char '5' ||| char '6' ||| char '7' ||| char '8'  ||| char '9')) <*> (char '1' ||| char '2' ||| char '3' ||| char '4'  ||| char '5' ||| char '6' ||| char '7' ||| char '8'  ||| char '9')

test_all =
	do
		test_ex1214
		test_ex1215
		test_ex1216
		test_ex1217
		test_ex1218


test_ex1214 =
	do
		putStrLn "STAR TEST"
		putStr "star ((a ||| b ) <*> (a ||| b)) \"\" = "
                putStrLn (show (star ((a ||| b ) <*> (a ||| b)) ""))
		putStr "star ((a ||| b ) <*> (a ||| b)) \"a\" = "
		putStrLn (show (star ((a ||| b ) <*> (a ||| b)) "a"))
		putStr "star ((a ||| b ) <*> (a ||| b)) \"b\" = "
                putStrLn (show (star ((a ||| b ) <*> (a ||| b)) "b"))
		putStr "star ((a ||| b ) <*> (a ||| b)) \"ab\" = "
                putStrLn (show (star ((a ||| b ) <*> (a ||| b)) "ab"))
		putStr "star ((a ||| b ) <*> (a ||| b)) \"bba\" = "
                putStrLn (show (star ((a ||| b ) <*> (a ||| b)) "bba"))
		putStr "star ((a ||| b ) <*> (a ||| b)) \"aaab\" = "
                putStrLn (show (star ((a ||| b ) <*> (a ||| b)) "aaab"))
		putStr "star ((a ||| b ) <*> (a ||| b)) \"abababababab\" = "
                putStrLn (show (star ((a ||| b ) <*> (a ||| b)) "abababababab"))
		putStr "star ((a ||| b ) <*> (a ||| b)) \"aaaaaaaa\" = "
                putStrLn (show (star ((a ||| b ) <*> (a ||| b)) "aaaaaaaa"))
		putStrLn ""

test_ex1215 =
	do
		putStrLn "STAR STAR TEST"
		putStr "star (star ((a ||| b ) <*> (a ||| b))) \"\" = "
                putStrLn (show (star (star ((a ||| b ) <*> (a ||| b))) ""))
		putStr "star (star ((a ||| b ) <*> (a ||| b))) \"a\" = "
                putStrLn (show (star (star ((a ||| b ) <*> (a ||| b))) "a"))
		putStr "star (star ((a ||| b ) <*> (a ||| b))) \"b\" = "
                putStrLn (show (star (star ((a ||| b ) <*> (a ||| b))) "b"))
		putStr "star (star ((a ||| b ) <*> (a ||| b))) \"ab\" = "
                putStrLn (show (star (star ((a ||| b ) <*> (a ||| b))) "ab"))
		putStr "star (star ((a ||| b ) <*> (a ||| b))) \"baa\" = "
                putStrLn (show (star (star ((a ||| b ) <*> (a ||| b))) "baa"))
		putStr "star (star ((a ||| b ) <*> (a ||| b))) \"aaab\" = "
                putStrLn (show (star (star ((a ||| b ) <*> (a ||| b))) "aaab"))
		putStr "star (star ((a ||| b ) <*> (a ||| b))) \"abbab\" = "
                putStrLn (show (star (star ((a ||| b ) <*> (a ||| b))) "abbab"))
		putStr "star (star ((a ||| b ) <*> (a ||| b))) \"bababa\" = "
                putStrLn (show (star (star ((a ||| b ) <*> (a ||| b))) "bababa"))
		putStr "star (star ((a ||| b ) <*> (a ||| b))) \"aaabbba\" = "
                putStrLn (show (star (star ((a ||| b ) <*> (a ||| b))) "aaabbba"))
		putStrLn ""

test_ex1216 =
	do
		putStrLn "OPTION TEST"
		putStr "option (a<*>b) \"ab\" = "
		putStrLn (show (option (a <*> b) "ab"))
		putStr "option (a<*>b) \"a\" = "       
                putStrLn (show (option (a <*> b) "a"))
		putStr "option (a<*>b) \"\" = "       
                putStrLn (show (option (a <*> b) ""))
		putStr "option (a<*>b) \"ba\" = "       
                putStrLn (show (option (a <*> b) "ba"))
		putStr "option (a<*>b) \"abab\" = "       
                putStrLn (show (option (a <*> b) "abab"))
		putStrLn ""

		putStrLn "PLUS TEST"
		putStr "plus (a<*>b) \"ab\" = "
                putStrLn (show (plus (a <*> b) "ab"))
                putStr "plus (a<*>b) \"a\" = "
                putStrLn (show (plus (a <*> b) "a"))
                putStr "plus (a<*>b) \"\" = "
                putStrLn (show (plus (a <*> b) ""))
                putStr "plus (a<*>b) \"ba\" = "
                putStrLn (show (plus (a <*> b) "ba"))
                putStr "plus (a<*>b) \"abab\" = "
                putStrLn (show (plus (a <*> b) "abab"))
		putStrLn ""

test_ex1217 =
	do
		putStrLn "DIGITS FORMAT"
		putStr "digits \"123456789\" = "
		putStrLn (show (digits "123456789"))
		putStr "digits \"987654321\" = "
                putStrLn (show (digits "987654321"))
		putStr "digits \"112233445566778899\" = "
                putStrLn (show (digits "12233445566778899"))
		putStr "digits \"2648310575316\" = "
                putStrLn (show (digits "2648310575316"))
		putStr "digits \"\" = "
                putStrLn (show (digits ""))
		putStr "digits \"0123456789\" = "
                putStrLn (show (digits "0123456789"))
		putStr "digits \"1234567890\" = "
                putStrLn (show (digits "1234567890"))
		putStr "digits \"000123456789\" = "
                putStrLn (show (digits "000123456789"))
		putStrLn ""
		
		putStrLn "DECIMAL FORMAT"
		putStr "floats \"1234.5678\" = "
		putStrLn (show (floats "1234.5678"))
		putStr "floats \"090412.4678\" = "
                putStrLn (show (floats "090412.4678"))
		putStr "floats \"\" = "
                putStrLn (show (floats ""))
		putStr "floats \"3865.029\" = "
                putStrLn (show (floats "3865.029"))
		putStr "floats \"1111.1000\" = "
                putStrLn (show (floats "1111.1000"))
		putStr "floats \"22002.4004\" = "
                putStrLn (show (floats "22002.4004"))
		putStrLn ""


test_ex1218 =
	do
		putStrLn "AT MOST TWO A's"
		putStr "((star b <*> a <*> star b <*> a <*> star b) ||| (star b <*> a <*> star b) ||| star b) \"bbbaabb\" = "
		putStrLn (show (((star (b) <*> a <*> star (b) <*> a <*> star (b)) ||| (star (b) <*> a <*> star (b)) ||| (star (b))) "bbbaabb"))
		putStr "((star b <*> a <*> star b <*> a <*> star b) ||| (star b <*> a <*> star b) ||| star b) \"bbbaaabb\" = "
                putStrLn (show (((star (b) <*> a <*> star (b) <*> a <*> star (b)) ||| (star (b) <*> a <*> star (b)) ||| (star (b))) "bbbaaabb"))
		putStr "((star b <*> a <*> star b <*> a <*> star b) ||| (star b <*> a <*> star b) ||| star b) \"bbbabb\" = "
                putStrLn (show (((star (b) <*> a <*> star (b) <*> a <*> star (b)) ||| (star (b) <*> a <*> star (b)) ||| (star (b))) "bbbabb"))
		putStr "((star b <*> a <*> star b <*> a <*> star b) ||| (star b <*> a <*> star b) ||| star b) \"bbbbb\" = "
                putStrLn (show (((star (b) <*> a <*> star (b) <*> a <*> star (b)) ||| (star (b) <*> a <*> star (b)) ||| (star (b))) "bbbbb"))
		putStr "((star b <*> a <*> star b <*> a <*> star b) ||| (star b <*> a <*> star b) ||| star b) \"\" = "
                putStrLn (show (((star (b) <*> a <*> star (b) <*> a <*> star (b)) ||| (star (b) <*> a <*> star (b)) ||| (star (b))) ""))
		putStr "((star b <*> a <*> star b <*> a <*> star b) ||| (star b <*> a <*> star b) ||| star b) \"aa\" = "
                putStrLn (show (((star (b) <*> a <*> star (b) <*> a <*> star (b)) ||| (star (b) <*> a <*> star (b)) ||| (star (b))) "aa"))
		putStrLn ""

		putStrLn "EXACTLY TWO A's"
		putStr "(star b <*> a <*> star b <*> a <*> star b) \"bbbaabb\" = "
                putStrLn (show ((star (b) <*> a <*> star (b) <*> a <*> star (b)) "bbbaabb"))
		putStr "(star b <*> a <*> star b <*> a <*> star b) \"bbbabb\" = "
                putStrLn (show ((star (b) <*> a <*> star (b) <*> a <*> star (b)) "bbbabb"))
		putStr "(star b <*> a <*> star b <*> a <*> star b) \"bbbaaabb\" = "
                putStrLn (show ((star (b) <*> a <*> star (b) <*> a <*> star (b)) "bbbaaabb"))
		putStr "(star b <*> a <*> star b <*> a <*> star b) \"bbbbb\" = "
                putStrLn (show ((star (b) <*> a <*> star (b) <*> a <*> star (b)) "bbbbb"))
		putStr "(star b <*> a <*> star b <*> a <*> star b) \"\" = "
                putStrLn (show ((star (b) <*> a <*> star (b) <*> a <*> star (b)) ""))


