--5-
data Tree a = EmptyTree | Node (Tree a) a (Tree a)

--a)
Empty :: Tree a
Empty = EmptyTree

--b)
size :: Tree a -> Int
size EmptyTree = 0
size Node (Tree b) a (Tree c) = 1 + size(Tree b) + size(Tree c)

--c)
depth :: Tree a -> Int
depth EmptyTree = 0
depth Node (Tree b) a (Tree c) = 1 + depth(Tree b) + depth(Tree c) 

--d)
flatten :: Tree a -> [a]
flatten EmptyTree = []
flatten Node a = [a]
flatten Node (Tree b) a (Tree c) = flatten a : flatten (Tree b) : flatten (Tree c)

--e)
isFull :: Tree a -> Bool
isFull EmptyTree = False
isFull Node a = True
isFull Node (Tree b) a (Tree c) = if(size (Tree b) == size (Tree c))

--f)
invert :: Tree a -> Tree a
invert Node a = Node a
invert Node (Tree b) a (Tree c) = Node (Tree c) a (Tree b)

--g)
makeTree :: [a] -> Tree a
makeTree [] = EmptyTree
makeTree [x] = Node x
makeTree (x:xs) = makeTree x : makeTree xs

--6-
 






