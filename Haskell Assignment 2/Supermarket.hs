-- Justin Trotter 10144892 Tuesday, September 30, 2014
-- Assignment #2 Supermarket Billing

--Type declaration
type Name = String
type Price = Int
type BillType = [(String, Int)]
type BarCode = Int
type Item = (BarCode, Name, Price)
type Database = [Item]
type TillType = [BarCode]

-- 6.39
--
-- Converts number to dollar/cent format
-- Arguements: 	Integer
-- Returns: 	String
-- Terminates:	No direct recursion
formatCents :: Int -> String
formatCents x
	| x < 0			= error("Error!: Please enter positive value")
	| (x `mod` 100) < 10	= show (x `div` 100) ++ ".0" ++ show (x `mod` 100)
	| otherwise		= show (x `div` 100) ++ "." ++ show (x `mod` 100)

-- 6.40
--
-- Initialize line length
lineLength :: Int
lineLength 	= 30	-- Adjust this to change line size

-- Returns a formated String showing inputed name and price with "....." inbetween
-- Arguements:	(String, Int)
-- Returns:	String
-- Terminates:	No direct recursion
formatLine :: (Name,Price) -> String
formatLine (name, price) = name ++ dots  ++ showPrice
	where
	-- returns price in dollar/cent format
	showPrice :: String
	showPrice = formatCents price
	-- calculates and returns required length of dots
	remain :: Int
	remain 	= lineLength - length (name ++ showPrice)
	-- returns remaining number of dots
	dots :: String
	dots 	= replicate remain '.'

-- 6.41
--
-- Returns a String in a list of seperate lines in the formatLine format
-- Arguements: 	BillType == [(String, Int)]
-- Returns:	String
-- Terminates:	If finite list; length decreases by one for each recursion
formatLines :: BillType -> String
formatLines []			= ""
formatLines x	= unlines $ [formatLine lines | lines <- x]

-- 6.42
--
-- Returns sum of each price value in billtype list
-- Arguements:	BillType == [(String, Int)]
-- Returns:	Int
-- Terminates:	If finite list; length decreasse by one for each recursion
makeTotal :: BillType -> Price
makeTotal x 	= sum [p | (n, p) <- x]

-- 6.43
--
-- Returns input as total price in formatLine's format
-- Arguements:	Int
-- Returns:	String
-- Terminates:	No direct recursion
formatTotal :: Price -> String
formatTotal x	= formatLine ("Total", x)
	
-- 6.44
--
-- Returns entire formated bill
-- Arguements:	BillType == [(String, Int)]
-- Returns:	String
-- Terminates:	If finite list, length decreases by one for each recursion
formatBill :: BillType -> String
formatBill x	= unlines ["Haskell Stores", "", formatLines x, formatTotal (makeTotal x)]

-- 6.45
-- Returns Item name and price based on inputed barcode and external databse
-- Arguements:	Database == (BarCode, Name, Price), BarCode == Int
-- Returns:	(Name, Price)
-- Terminates: If finite list, length decreases by one for each recursion
look :: Database -> BarCode -> (Name, Price)
look d bc	| length w == 1 = head w
		| length w == 0 = (unwords ["Unknown item", 	show bc], 0)
		| length w > 1	= (unwords ["Duplicated item", 	show bc],0)
		where w = [(n, p) | (bc', n, p) <- d, bc' == bc]

-- 6.46
-- Initialize Database
codeIndex :: Database
codeIndex 	= [ 	(4719, "Fish Fingers", 121),
			(5643, "Nappies", 1010),
			(3814, "Orange Jelly", 56),
			(1111, "Hula Hoops", 21),
			(1112, "Hula Hoops (Giant)", 133),
			(1234, "Dry Sherry, 1lt", 540)]

-- Returns item name and price based on barcode and internal database
-- Arguements:	Barcode == Int
-- Returns:	(Name, Price)
-- Terminates:	If database is a finite list
lookup' :: BarCode -> (Name, Price)
lookup' bc	= look codeIndex bc 

-- 6.47
-- Returns list of items "scaned" by the barcode scanner
-- Arguements: 	A list of Barcodes == [BarCode]
-- Returns:	BillType == [(String, Int)]
-- Terminates: If finite list, length decreases by one for each recursion
makeBill :: TillType -> BillType
makeBill x	= [look codeIndex bc | bc <- x]
