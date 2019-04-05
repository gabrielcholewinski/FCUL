import System.Environment
import Data.List
import Test.QuickCheck
import System.Random
--Grupo 1
--a)

multiplosEntre :: Int -> Int -> Int -> Int
multiplosEntre a b c = length (multList a b c)

multList :: Int -> Int -> Int -> [Int]
multList 0 b c = multList 1 b c
multList a b c | (a==b && mod a c == 0) = [a]
               | (a==b && mod a c /= 0) = []
               | (b>a && mod a c == 0) =  a : multList (a+1) b c
               |otherwise = multList (a+1) b c

-----------------------------------------------------------------
--b)
intercalar :: [a] -> [[a]] -> [a]
intercalar xs [] = []
intercalar xs (ys:s) | (length (ys:s) == 1) = ys 
                     |otherwise =  ys++xs++(intercalar xs s)

-----------------------------------------------------------------
{--c)
transposta :: [[a]] -> [[a]]
transposta [[]] = []
transposta (x:xs) = head x : head xs : transposta ((tail x):xs)
-}----------------------------------------------------------------
--Grupo 2
--a)
data Html = Div [Html] | Texto String | Negrito String deriving (Eq)

profundidade :: Html -> Int
profundidade (Texto _) = 1
profundidade (Negrito _) = 1
profundidade (Div []) = 1
profundidade (Div (x:xs)) = 1+(profundidade (Div xs))

{--b)
realcar :: String -> Html -> Html
realcar _ (Negrito k) = (Negrito k)
realcar s (Texto k) = if(k == s) then (Negrito k) else (Texto k)
realcar s (Div (x:xs)) = if(Texto s == x) then (Negrito x):(realcar s (Div xs)) 
                         else (x:(realcar s (Div xs)))

--c)-}
instance Show (Html) where
 show (Negrito k) = "<b>"++k++"</b>"
 show (Texto k) = "<div>"++k++"</div>"
 show (Div (x:xs)) = (show x) ++ (show xs)

instance Arbitrary (Html) where
    arbitrary = do
      x <- choose(1,9)
        return $ (randomStr x)

randomStr :: Int -> Html
randomStr n = take n $ randomRs ('a','z')
 


 