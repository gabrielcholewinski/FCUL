import System.Environment
import Data.List
import Test.QuickCheck
import System.Random
import Control.Monad
--Grupo 1
--a)
coordenadas :: String -> ([Int],[Int])
coordenadas [] = ([],[])
coordenadas x = ((map (\x -> linhaBifid x) x) ,(map (\x -> colunaBifid x) x) )

linhaBifid :: Char -> Int
linhaBifid x | (x == 'B' || x == 'G' || x == 'W'|| x == 'K'|| x == 'Z') = 1
             | (x == 'Q' || x == 'P' || x == 'N'|| x == 'D'|| x == 'S') = 2
             | (x == 'I' || x == 'J' || x == 'O'|| x == 'A'|| x == 'X' || x=='E') = 3
             | (x == 'F' || x == 'C' || x == 'L'|| x == 'U'|| x == 'M') = 4
             | otherwise = 5

colunaBifid :: Char -> Int
colunaBifid x | (x == 'B' || x == 'Q' || x == 'I'|| x == 'J'|| x == 'F'|| x=='T') = 1
             | (x == 'G' || x == 'P' || x == 'O'|| x == 'C'|| x == 'H') = 2
             | (x == 'W' || x == 'N' || x == 'A'|| x == 'L'|| x == 'Y' ) = 3
             | (x == 'K' || x == 'D' || x == 'X'|| x == 'U'|| x == 'V') = 4
             | otherwise = 5

--b)
bifidNumberToString :: (Int,Int) -> Char
bifidNumberToString x | (x == (1,1)) = 'B'
                      | (x == (1,2)) = 'G'
                      | (x == (1,3)) = 'W'
                      | (x == (1,4)) = 'K'
                      | (x == (1,5)) = 'Z'
                      | (x == (2,1)) = 'Q'
                      | (x == (2,2)) = 'P'
                      | (x == (2,3)) = 'N'
                      | (x == (2,4)) = 'D'
                      | (x == (2,5)) = 'S'
                      | (x == (3,1)) = 'I'
                      | (x == (3,1)) = 'J'
                      | (x == (3,2)) = 'O'
                      | (x == (3,3)) = 'A'
                      | (x == (3,4)) = 'X'
                      | (x == (3,5)) = 'E'
                      | (x == (4,1)) = 'F'
                      | (x == (4,2)) = 'C'
                      | (x == (4,3)) = 'L'
                      | (x == (4,4)) = 'U'
                      | (x == (4,5)) = 'M'
                      | (x == (5,1)) = 'T'
                      | (x == (5,2)) = 'H'
                      | (x == (5,3)) = 'Y'
                      | (x == (5,4)) = 'V'
                      |otherwise = 'R'

palavra :: [Int] -> String
palavra [] = ""
palavra (x:xs:xss) = (bifidNumberToString (x,xs)):palavra xss

--c)
convert :: ([Int],[Int]) -> [Int]
convert (xs,ys) = xs++ys

bifid :: String -> String
bifid x = palavra $ convert $ coordenadas x

--Grupo 2
data Exp = Variavel Char| Inteiro Int| Mais Exp Exp| Vezes Exp Exp deriving (Eq)
--a)
comprimento :: Exp -> Int
comprimento (Variavel _) =0
comprimento (Inteiro _) = 0
comprimento (Mais xs ys) = 1+(comprimento xs)+(comprimento ys)
comprimento (Vezes xs ys) = 1+(comprimento xs)+(comprimento ys)

--b)
avalia :: [(Char,Int)] -> Exp -> Int
avalia [(key,value)] (Variavel x) = if(key == x) then value else 0
avalia _ (Inteiro k) = k
avalia z (Mais x y) = (avalia z x) + (avalia z y)
avalia z (Vezes x y) = (avalia z x) * (avalia z y)

--c)
instance Show Exp where
 show (Variavel x) = show x
 show (Inteiro k) = show k
 show (Mais x y) = "("++(show x)++"+"++(show y)++")"
 show (Vezes x y) = (show x) ++ (show y)

--Grupo 3
--a)

instance Arbitrary Exp where
  arbitrary = oneof[liftM Variavel arbitrary,
                    liftM Inteiro arbitrary,
                    liftM2 Mais arbitrary arbitrary,
                    liftM2 Vezes arbitrary arbitrary]

--b)
prop_comprExp_NotNeg :: Exp -> Bool
prop_comprExp_NotNeg (Variavel _) = True
prop_comprExp_NotNeg (Inteiro _) = True
prop_comprExp_NotNeg (Mais x y) = comprimento x >= 0 && comprimento y >= 0
prop_comprExp_NotNeg (Vezes x y) = comprimento x >= 0 && comprimento y >= 0

--c)---FAILED
prop_prod_twoPos_isPos :: Exp -> Bool
prop_prod_twoPos_isPos (Vezes x y) | ((avalia [] x) >= 0 && (avalia [] y) >= 0) = True
                           |otherwise = True

--Grupo 4
calculadora :: [(Char,Int)] ->  -> IO()
calculadora x = do
   args <- getLine
   if((avalia x Exp) = 0)
