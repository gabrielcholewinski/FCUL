import Data.Char

--1-
--a) 
firstD :: (a,b) -> a
firstD (a,_) = a

--b)
swap :: (a,b) -> (b,a)
swap (a,b) = (b,a)

--c)
firstT :: (a,b,c) -> a
firstT (a,_,_) = a 

--d)
swap2 :: (a,b,c) -> (b,a,c)
swap2 (a,b,c) = (b,a, c) 

--e) 
secondList :: [t] -> t
secondList xs = head(tail(init xs))

--f)
secondEvenList :: [(a,b)] -> b
secondEvenList xs =  snd (head xs)

--2- 
somaVec :: (Double,Double) -> (Double,Double) -> (Double,Double)
somaVec (a,b) (c,d) = (a+c,b+d)

--3- Sim, a primeira, é geral em qualquer tipo(Char,Int,Bool,..) , a segunda é específica com inteiros, 
--   a terceira é igual a primeira em resultados, mas utiliza pattern matching;

--4-
--a) A primeira(a) e segunda(b) são semelhantes em resultados, mas diferem em sintaxe, podendo uma ser mais eficiente que outra,
--   a terceira(c) encontramos uma falha, pois o f3 0 nunca será alcançado pois sempre irá entrar no x-1, podendo retonar -1 ao invés de 0,
--   a quarta(d) é semelhante as duas primeiras podendo também ter mais ou menos eficiencia

--5- 
func :: (Double,Double) -> Double
func (a,b) = sqrt((a^2)+(b^2))

--6-

func2 :: Int -> String
func2 x | (x==1) = word++"st" 
        | (x==2) = word++"nd"
        | (x==3) = word++"rd"
        | (x>=4 && x<31) = word++"th"
        | otherwise = word++"st"
        where word = show x 

--7- NAO TERMINOU
--leetSpreakaux :: String -> String
--leetSpreakaux x = if(length x == 1) then [chr (ord (head x) + 32)]
--else [head x] ++  leetSpreak (tail x)

--8-

--9-
{--a)
halve :: [a] -> ([a],[a])
halve xs | (length xs `mod` 2 == 0) = ( take metade xs, reverse(take metade (reverse xs)) ) --PAR
         | (length xs `mod` 2 /= 0 ) = (take (metade+1) xs , reverse(take metade(reverse xs)))
         where metade = ((length xs) `div` 2) 
-}
--b) 
halve :: [a] -> ([a],[a])
halve xs = let metade = ((length xs) `div` 2) in if(length xs `mod` 2 == 0) then ( take metade xs, reverse(take metade (reverse xs)) )
else (take (metade+1) xs , reverse(take metade(reverse xs)))

--10-
raizesPolinomio :: (Double,Double,Double) -> (Double,Double)
raizesPolinomio (a,b,c) | (a > 0) = ( (-b - sqrt(delta)) / (2*a) ,  (-b + sqrt(delta)) / (2*a) ) 
                        where delta = b^2-4*a*c

-- -b +- sqrt(delta) / 2*a
-- delta = b^2-4*a*c







