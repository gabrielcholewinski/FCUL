{-
 -Função que retorna o inverso da lista
 -}

{-
 		Alternativa 1
invert :: [t] -> [t]
inv_aux :: [t] -> [t] -> [t]

invert [] = []
invert l = inv_aux l []

inv_aux [] l_inv = l_inv
inv_aux (x:xs) l_inv = inv_aux xs l_inv++[x]
-}

{- Alternativa 2 -}
invert :: [t] -> [t]
invert [] = []
invert (h:t) = invert t ++ [h]