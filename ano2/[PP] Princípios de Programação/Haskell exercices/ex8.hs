--1 à 3

--1-
writePrimes :: [Int] -> IO ()
writePrimes [] = return ()
writePrimes (x:xs) = putStrLn (x++"th prime is "++(primes !! x))
                     writePrimes xs

--2-
{-a) main = do
     x <- getLine
     if(palindrome x) then "Sim"
     else "Não"

--b) main = forever do
   	 x <- getLine
     if(x /= "") then
    	         if(palindrome x) then "Sim"
    	         else "Não"
     else return ()

--c) main = forever do
	 x <- getLine
     if(x /= "") then
    	         if(palindrome x) then "Sim"
    	         else "Não"
     else interact 
-}

{-3-
a)
printEven :: Int -> IO()
printEven x | (x/2 == 0) = putStrLn "Par"
            | otherwise = putStrLn "Impar"
b)
showParity :: [Int] -> IO ()
showParity [] = return ()
showParity (x:xs) = printEven x 
                    showParity xs
c)

-}

