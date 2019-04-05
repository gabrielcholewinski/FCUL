{-
 - Diz se um elemento pertence ou não, à lista
 -}

pertence :: [Int] -> Int -> Bool
pertence [] _ = False
pertence (h:b) x | (h == x) = True 
                 |otherwise = pertence b x

maior :: [Int] -> Int
maior [x]= x
maior (x:xs) | (x > maior xs) = x 
             | otherwise = maior xs

todos_pares :: [Int] -> Bool
todos_pares [] = False
todos_pares [x] | (mod x 2 == 0) = True
todos_pares (h:b) | (mod h 2 == 0) = todos_pares b 
                  | otherwise = False