teclasParaPalavra :: [Char] -> [Char]
teclasParaPalavra [] = []
teclasParaPalavra xs |(wordRest == "") = firstLetter
                         |otherwise = firstLetter ++ teclasParaPalavra (tail wordRest)
                         where firstLetter = (alphaConverter (takeWhile (/=' ') xs) 0)
                               wordRest = (dropWhile (/=' ') xs)
{-
 - Funcao auxiliar que converte numero em alfabeto
 -}
alphaConverter :: [Char] -> Int -> [Char]
alphaConverter [] _ = []
alphaConverter [x] k = [(alphaNumeric x) !! k]
alphaConverter (x:xs) k | (head xs /= x) = [((alphaNumeric x)!!k)] ++ (alphaConverter (xs) 0)
                        | otherwise = alphaConverter (xs) (k+1)

{-
 - Funcao auxiliar que devolve o alfabeto correspondente de cada numero
 -}
alphaNumeric :: Char -> [Char]
alphaNumeric x = ["ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"] !! ((read [x]::Int)-2)
--                 2      3      4      5      6      7       8      9