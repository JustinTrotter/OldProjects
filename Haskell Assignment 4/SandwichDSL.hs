module SandwichDSL
where

-- Used functions from these modules in my implementation
import Data.Maybe
import Data.List

{- Haskell data type definitions from "Building the DSL" -}

data Platter   = Platter [Sandwich] 
                 deriving Show

data Sandwich  = Sandwich [Layer]
                 deriving Show

data Layer     = Bread Bread         | Meat Meat           |
                 Cheese Cheese       | Vegetable Vegetable | 
                 Condiment Condiment
                 deriving (Eq,Show)

data Bread     = White | Wheat | Rye
                 deriving (Eq,Show)

data Meat      = Turkey | Chicken | Ham | RoastBeef | Tofu
                 deriving (Eq,Show)

data Cheese    = American | Swiss | Jack | Cheddar
                 deriving (Eq,Show)

data Vegetable = Tomato | Onion | Lettuce | BellPepper
                 deriving (Eq,Show)

data Condiment = Mayo | Mustard | Ketchup | Relish | Tabasco
                 deriving (Eq,Show)

-- Function type signatures given in section
newSandwich :: Bread -> Sandwich
newSandwich x = Sandwich [Bread x]

addLayer :: Sandwich -> Layer -> Sandwich
addLayer (Sandwich ([x])) y = Sandwich ([x] ++ [y])

newPlatter ::  Platter
newPlatter = Platter []

addSandwich :: Platter -> Sandwich -> Platter
addSandwich (Platter ([x])) y = Platter ([x] ++ [y])

-- Query Functions
isBread :: Layer -> Bool
isBread (Bread _) = True
isBread _ = False

isMeat :: Layer -> Bool
isMeat (Meat _) = True
isMeat _ = False

isCheese :: Layer -> Bool
isCheese (Cheese _) = True
isCheese _ = False

isVegetable :: Layer -> Bool
isVegetable (Vegetable _) = True
isVegetable _ = False

isCondiment :: Layer -> Bool
isCondiment (Condiment _) = True
isCondiment _ = False

noMeat :: Sandwich -> Bool
noMeat (Sandwich (x:xs)) = if isMeat (x) then False else noMeat (Sandwich xs)
noMeat (Sandwich []) = True

noBread :: Sandwich -> Bool
noBread (Sandwich (x:xs)) = if isBread (x) then False else noBread (Sandwich xs)
noBread (Sandwich []) = True

-- INOSO

inOSO :: Sandwich -> Bool
inOSO (Sandwich (x:xs)) = if isBread (x) then inOSOMeat (Sandwich xs) x else False
inOSO (Sandwich []) = False

inOSOMeat :: Sandwich -> Layer -> Bool
inOSOMeat (Sandwich (x:xs)) y = if isMeat (x) then inOSOMeat (Sandwich xs) y else inOSOCheese (Sandwich (x:xs)) y
inOSOMeat (Sandwich [])_ = False

inOSOCheese :: Sandwich -> Layer -> Bool
inOSOCheese (Sandwich (x:xs)) y = if isCheese (x) then inOSOCheese (Sandwich xs) y else inOSOVegetable (Sandwich (x:xs)) y
inOSOCheese (Sandwich [])_ = False

inOSOVegetable :: Sandwich -> Layer -> Bool
inOSOVegetable (Sandwich (x:xs)) y = if isVegetable (x) then inOSOVegetable (Sandwich xs) y else inOSOCondiment (Sandwich (x:xs)) y
inOSOVegetable (Sandwich []) _ = False

inOSOCondiment :: Sandwich -> Layer -> Bool
inOSOCondiment (Sandwich (x:xs)) y = if isCondiment (x) then inOSOCondiment (Sandwich xs) y else inOSOBread (Sandwich (x:xs)) y
inOSOCondiment (Sandwich []) _ = False

inOSOBread :: Sandwich -> Layer -> Bool
inOSOBread (Sandwich (x:xs)) y = if x == y then inOSOFinal (Sandwich (xs)) else False
inOSOBread (Sandwich [])_ = False

inOSOFinal :: Sandwich -> Bool
inOSOFinal (Sandwich []) = True
inOSOFinal _ = False

-- INTOOSO

intoOSO :: Sandwich -> Bread -> Sandwich
intoOSO x y = if noBread x then intoOSODefault x y else intoOSODefault x y

intoOSODefault :: Sandwich -> Bread -> Sandwich
intoOSODefault (Sandwich x) y = Sandwich ([Bread y] ++ intoOSOStart x ++ [Bread y])

intoOSOStart :: [Layer] -> [Layer]
intoOSOStart x = getMeat x ++ getCheese x ++ getVegetable x ++ getCondiment x

getMeat :: [Layer] -> [Layer]
getMeat (x:xs) = if isMeat x then [x] ++ getMeat xs else getMeat xs
getMeat [] = []

getCheese :: [Layer] -> [Layer]
getCheese (x:xs) = if isCheese x then [x] ++ getCheese xs else getCheese xs
getCheese [] = []

getVegetable :: [Layer] -> [Layer]
getVegetable (x:xs) = if isVegetable x then [x] ++ getVegetable xs else getVegetable xs
getVegetable [] = []

getCondiment :: [Layer] -> [Layer]
getCondiment (x:xs) = if isCondiment x then [x] ++ getCondiment xs else getCondiment xs
getCondiment [] = []

-- Price Sandwich
prices = [(Bread White,20),(Bread Wheat,30),(Bread Rye,30), 
          (Meat Turkey,100),(Meat Chicken,80),(Meat Ham,120),
          (Meat RoastBeef,140),(Meat Tofu,50),
          (Cheese American,50),(Cheese Swiss,60),
          (Cheese Jack,60),(Cheese Cheddar,60),
          (Vegetable Tomato,25),(Vegetable Onion,20),
          (Vegetable Lettuce,20),(Vegetable BellPepper,25),
          (Condiment Mayo,5),(Condiment Mustard,4),
          (Condiment Ketchup,4),(Condiment Relish,10),
          (Condiment Tabasco,5) 
         ]

priceSandwich :: Sandwich -> Int
priceSandwich (Sandwich (x:xs)) = fromJust'(lookup x prices) + priceSandwich (Sandwich xs)
priceSandwich (Sandwich []) = 0

fromJust' :: Maybe a -> a
fromJust' Nothing = error ("Maybe.fromJust: Nothing")
fromJust' (Just x) = x


{- Haskell data type definitions from 
   "Compiling the Program for the SueChef Controller"
-}

data SandwichOp = StartSandwich    | FinishSandwich |
                  AddBread Bread   | AddMeat Meat   |
                  AddCheese Cheese | AddVegetable Vegetable | 
                  AddCondiment Condiment |
                  StartPlatter | MoveToPlatter | FinishPlatter 
                  deriving (Eq, Show) 

complieSandwich :: Sandwich -> [SandwichOp]
complieSandwich (Sandwich x) = [StartSandwich] ++ complieIngredients x  ++ [FinishSandwich] ++ [MoveToPlatter]

complieIngredients :: [Layer] -> [SandwichOp]
complieIngredients (x:xs)
	| x == Bread White = [AddBread White] ++ complieIngredients xs
	| x == Bread Rye = [AddBread Rye] ++ complieIngredients xs
	| x == Bread Wheat = [AddBread Wheat] ++ complieIngredients xs
	| x == Meat Turkey = [AddMeat Turkey] ++ complieIngredients xs
	| x == Meat Chicken = [AddMeat Chicken] ++ complieIngredients xs
	| x == Meat Ham = [AddMeat Ham] ++ complieIngredients xs
	| x == Meat RoastBeef = [AddMeat RoastBeef] ++ complieIngredients xs
        | x == Meat Tofu = [AddMeat Tofu] ++ complieIngredients xs
        | x == Cheese American = [AddCheese American] ++ complieIngredients xs
	| x == Cheese Swiss = [AddCheese Swiss] ++ complieIngredients xs
        | x == Cheese Jack = [AddCheese Jack] ++ complieIngredients xs
        | x == Cheese Cheddar = [AddCheese Cheddar] ++ complieIngredients xs
	| x == Vegetable Tomato = [AddVegetable Tomato] ++ complieIngredients xs
        | x == Vegetable Onion = [AddVegetable Onion] ++ complieIngredients xs
        | x == Vegetable Lettuce = [AddVegetable Lettuce] ++ complieIngredients xs
	| x == Vegetable BellPepper = [AddVegetable BellPepper] ++ complieIngredients xs
        | x == Condiment Mayo = [AddCondiment Mayo] ++ complieIngredients xs
        | x == Condiment Mustard = [AddCondiment Mustard] ++ complieIngredients xs
	| x == Condiment Ketchup = [AddCondiment Ketchup] ++ complieIngredients xs
        | x == Condiment Relish = [AddCondiment Relish] ++ complieIngredients xs
        | x == Condiment Tabasco = [AddCondiment Tabasco] ++ complieIngredients xs


complieIngredients [] = []

data Program = Program [SandwichOp]
               deriving Show

complie :: Platter -> Program
complie (Platter x) = Program([StartPlatter] ++ compliePlatter x ++ [FinishPlatter])

compliePlatter :: [Sandwich] -> [SandwichOp]
compliePlatter (x:xs) = complieSandwich x ++ compliePlatter xs
compliePlatter _  = []


