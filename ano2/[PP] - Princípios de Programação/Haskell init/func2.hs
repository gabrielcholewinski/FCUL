--Tuplas(Conjunto de dados heterogenio) - Projeto 07
nomes :: (String, String, String)
nomes = ("Marcos", "Geeksbr", "Haskell")

selec_prim (x, _, _) = x
selec_sec (_, y, _) = y
selec_ter (_, _, z) = z