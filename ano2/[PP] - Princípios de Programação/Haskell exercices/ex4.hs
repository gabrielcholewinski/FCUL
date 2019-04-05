--1-
--a)
sum' :: Num a => [a] -> a
sum' (xs) = if(length (xs) == 1) then (head (xs))
           else head (xs) + sum' (tail (xs))

--b)
replicate' :: Int -> a -> [a]
replicate' n k = if(n<0) then []
else rep n k []


{-
 - Auxiliar method who return's list with n elements of k
 -}
rep :: Int -> a -> [a] -> [a]
rep n k xs = if(length xs == n) then xs
else rep n k (k:xs) 

--c)
maximo :: Ord a => [a] -> a
maximo xs = if(length xs == 1) then (head xs) 
else max' xs (head xs)

max' :: Ord a => [a] -> a -> a
max' xs y = if(length xs == 0) then y 
          else if ((head xs) > y) then max' (tail xs) (head xs)
          else max' (tail xs) y


--d)

--e) 
substitui :: Eq a => a -> a -> [a] -> [a]
substitui a b s = if(length s == 0) then s
else if(head s == a) then b : substitui a b (tail s)
else (head s) : substitui a b (tail s) 

--f)

--g)

--h)

--i)

--j) xs = [3,6,15,3,18]
posicoes :: [Int] -> Int -> [Int]
posicoes xs k = encontraPos xs [y | y<-xs , y `mod` k == 0] 0

encontraPos :: [Int] -> [Int] -> Int -> [Int]
encontraPos xs ys count = if(count > length xs || length ys == 0) then []
else if(xs !! count == head ys) then [count] ++ encontraPos xs (tail ys) count
else encontraPos xs ys (count+1) ++ []

--k)

--l)

--m)

--2-
repBinaria :: Int -> Int
repBinaria x = read(repBinaria' x)::Int

repBinaria' :: Int -> String
repBinaria' x = if(x<2) then show x
else   repBinaria' (x `div` 2) ++ show (x `mod` 2)
--FALTA TRANSFORMAR EM INTEIRO NOVAMENTE


--3-
odioso :: Int -> Bool
odioso x = (od (repBinaria x) 0) `mod`2 /= 0

od :: String -> Int -> Int
od s x = if(length s == 0) then x
else if(head s == '1') then od (tail s) (x+1)
else od (tail s) x
                     





















