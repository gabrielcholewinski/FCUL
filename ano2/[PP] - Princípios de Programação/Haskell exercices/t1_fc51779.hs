import Data.Char

cifrarCesar :: Int -> [Char] -> [Char]
cifrarCesar k s = [chr(ord(y)+k) | y<-s]

descifrarCesar :: Int -> [Char] -> [Char]
descifrarCesar k s = [chr(ord(y)+k) | y<-s]




cifrarObelix :: Int -> [Char] -> [Char]
cifrarObelix k s = [chr(ord(fst x)+(snd x)) | x<-(zip s [k..(length s + k)])]

decifrarObelix :: Int -> [Char] -> [Char]
decifrarObelix k s = [chr(ord(fst x)-(snd x)) | x<-(zip s [k..(length s + k)])]




tresPalindromos :: Int -> (Int, Int, Int)
tresPalindromos n = head[(c,b,a) | a<-(palindromoList (n)), b<-(palindromoList (n-a)), c<-(palindromoList (n-b)), a+b+c==n]

{-
--Dado um inteiro n, retorna uma lista com todos os Palindromos existentes ate o n
-}
palindromoList :: Int -> [Int]
palindromoList x = reverse[a | a<-[0..x], ehPalindromo a]

{-
--Metodo auxiliar que diz se um dado inteiro ehPalindromo
-}
ehPalindromo :: Int -> Bool
ehPalindromo x = x == (read(reverse(show x))::Int) 