import Data.Char


--A)
cifrarCesar :: Int -> [Char] -> [Char]
cifrarCesar k s = [chr(ord(x)+k) | x<-s]

descifrarCesar :: Int -> [Char] -> [Char]
descifrarCesar k s = [chr(ord(x)-k) | x<-s]



cifrarObelix :: Int -> [Char] -> [Char]
cifrarObelix k s = [(aux x y) | x<-s, y<-[0..10]]

aux :: Char-> Int -> Char
aux s k = chr((ord s)+k)



decifrarObelix :: Int -> [Char] -> [Char]
decifrarObelix k s = if(length s == 1) then [(chr (ord (head s) - k))]
else [(chr (ord (head s) - k))] ++ decifrarObelix (k+1) (tail s)



tresPalindromos :: Int -> (Int,Int,Int)
tresPalindromos n = head[(a,b,c) | a<-[0..n], b<-[0..n], c<-[0..n], a+b+c == n, ehPalindromo a && ehPalindromo b && ehPalindromo c]

{- 
--Metodo auxiliar que verifica se um dado inteiro é palindromo
-}
ehPalindromo :: Int -> Bool
ehPalindromo x = x == (read(reverse(show x))::Int) 