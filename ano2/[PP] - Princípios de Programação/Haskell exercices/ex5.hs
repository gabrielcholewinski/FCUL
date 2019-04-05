--3-
--a) [2,3,4]
--b) [True,False,False,False]
--c) ["As","artes","dos","alunos"]
--d) ["so","saluno","sbem-comportado"]
--e) [[1,4],[9,16,25]]
--f) [6]
--g) [2,4,6,8,10]
--h) [9,4,1,1,4,9]
--i) [1,4,9]

--4- 
a)      zipWith' :: (a->b->c)-> [a] -> [b] -> [c]
        zipWith' _ [] _ = []
        zipWith' _ _ [] = []
        zipWith' f (x:xs) (y:ys) =  (f x y) : zipWith' f (xs) (ys) 


--b) 
zipWith' :: (a->b->c)-> [a] -> [b] -> [c]
zipWith' f xs ys = [f x y | x<-xs, y<- ys]


--b) 
zip' :: [a] -> [b] -> [(a, b)]
zip' [] _ = []
zip' _ [] = []
zip' (x:xs) (y:ys) = (zip' xs ys) : (zipWith (\xs ys -> (head x,head y)) x y)


--5-
takeWhile' :: (a -> Bool) -> [a] -> [a]
takeWhile' f [] = []
takeWhile' f (x:xs) | (f x) = x : rec
                    |otherwise = rec
                    where rec = takeWhile f xs

--8
  a)
	
	total:: (Int -> Int) -> Int -> Int
	total f 0 = 1
	total f x = f x+total f (x-1)

  b) 
	total:: (Int -> Int) -> Int -> Int
   	total f x= sum[f y | y<-[0..x]]


--c)
total:: (Int -> Int) -> Int -> Int
total f x = sum(map f [0..x])


