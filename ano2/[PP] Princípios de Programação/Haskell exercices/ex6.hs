--2-

data Map k a = M [(k,a)]

empty :: Map k a -> Bool
empty [(_,_)] = True