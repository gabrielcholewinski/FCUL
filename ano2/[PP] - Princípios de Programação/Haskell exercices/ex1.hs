--1-
--a) 
sum x y z = x+y+z
--b) 
soma (x,y,z) | (x>0 && y>0 && z>0) = x+y+z 
             | otherwise = 0 

--2-
func(x,y,z) | (abs(x-y) < z) = True
            | otherwise = False

--3-
addDigit (x, y) | (x>0) = (x*10)+y 
                | otherwise = (x*10)-y

--5-
--a)
list :: [t] -> Bool
list (h:t) = length(h:t) > 10 
--b)
emptyList :: [t] -> String
emptyList (h:t) | (length(h:t) == 0) = "Estah vazia"
                | otherwise = "Nao estah vazia" 

--c)
retiraCarac :: String -> String
retiraCarac x = init(tail(x))

--d)
scdList :: [t] -> t
scdList(h:t) = head(tail(h:t))
--e)
pnuList :: [t] -> t
pnuList (h:t) = last(init(h:t))

--6-
--a)
prefix :: String -> String -> Bool
prefix x y = (take(length x) y) == x
--b)
suffix :: String -> String -> Bool
suffix x y = if(length(x) == length(y)) then(x == y) 
             else
               suffix x (tail(y))  
--c)
prefixOrSuffix :: String -> String -> Bool
prefixOrSuffix x y | (x == "" || y == "") = False
                   | otherwise = (prefix x y || suffix x y)

--7-
particao :: Double -> Double -> Double -> [Double]
particao a b c = if((a/c)>0) then [a,a+(a/c)..b] 
                 else [a,a+(b/c)..b]

--8- 
--   [x | x <- [a,b,c]] = [a,b,c]
--   "uma lista formada por x elementos, tal que esse x pertence a sublista [a,b,c]"
--a) [2,4,6]

--	[x | x <- [a,b,c], mod x 2 == 0]
--	"uma lista formada por x elementos que pertencem a sublsita [a,b,c], só se mod x 2 for == 0" (mod(haskell) == %(java))
--b) [4,16,36,64] (contudo, pode ser que o 'mod' esteja errado, pois era suposto ser: "mod x 2 == 0")

--c) A priori daria erro pois o isDigit não está definido, mas se estivesse:
--   [A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S]

--d) [(1,1),(1,2),(1,3),(3,1),(3,2),(3,3)]

--e) [(1,1),(1,2),(1,3),(3,1),(3,2),(3,3)]

--9-
    --sum [x^2 | x <- [1..100]]

--10-
    --pitagoricos n = [(x,y,z) | x <-[1..n], y <-[1..n],z <-[1..n], x^2+y^2 == z^2 ]

--11-
--a) fatores y = [x | x <- [1..y], mod y x == 0]
--b) todosPerfeitos y =  y == sum(fatores y)-y
--   perfeitos y = [x | x <- [1..y], todosPerfeitos x]

--12- [2^x | x <- [1..]]

--13-{ [x1,x2,x3] [y1,y2,y3] = sum[x1*y1,x2*y2,x3*y3]
produtoEscalar :: [Integer] -> [Integer] -> Integer
produtoEscalar xs ys = if(length (xs) == 1) then (head xs * head ys) else (head xs * head ys) + (produtoEscalar (tail xs) (tail ys))

  --    produtoEscalar :: [Integer] -> [Integer] -> Integer
  --    produtoEscalar (xs) (ys) = sum[x | x<-sum]

--14- 
--reproduz :: [Integer] -> [Integer]
reproduz xs = if(length xs == 1) then replicate (head xs) (head xs) else  (replicate (head xs) (head xs)) ++ reproduz (tail xs)

--15-
--concat xs ys = if(length xs == 1 && length ys == 1) then [(head xs, head ys)] else [(head xs, head ys)]++(concat (tail xs) (tail ys))

--16- a) Haskell (Lista Char)
--	  b) Haskell (Tupla Char)
--	  c) Not Haskell
--	  d) Haskell (Lista Bool)
--	  e) Haskell (Lista String)
--    f) Haskell (Tupla mista)
--	  g) Not Haskell, pois as assinaturas das funções chamadas não existem
--	  h) Haskell (Tupla de listas)
--	  i) Not Haskell

--17-
pares :: Integer -> [(Integer,Integer)]
pares n = [(x,y) | x <- [1..n], y <- [1..n], mod x 2 == 0 && mod y 2 == 0, y /= x]

--18-
fromTo :: Int -> Int -> String -> String
fromTo x y s = take (y-1) (aux x s)

aux :: Int -> String -> String
aux x s = if(x == 0) then s else aux (x-1) (tail s)
