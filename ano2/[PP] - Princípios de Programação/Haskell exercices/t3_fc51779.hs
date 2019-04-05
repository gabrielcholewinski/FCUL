fromString :: String -> [(Char, Int)]
fromString [] = []
fromString (x1:x2:xs) = (x1,read[x2]::Int) : fromString xs


fromNotes :: [(Char, Int)] -> [Double]
fromNotes = map (frequency)

{-- Metodo auxiliar que calcula a frequencia de uma nota --}
frequency :: (Char,Int) -> Double
frequency (a,b) = 440*2**((difference (a,b))/12)

{-- Metodo auxiliar que calcula a diferenca entre entre o codigo MIDI da nota com o codigo MIDI da nota A4 --}
difference :: (Char,Int) -> Double
difference xs = (midi xs) - (midi ('A',4))

{-- Metodo auxiliar que calcula o codigo MIDI de uma nota --}
midi :: (Char,Int) -> Double
midi (x1,x2) = fromIntegral $ (x2*12+(index x1)+12)

{-- Metodo auxiliar que calcula o indice da nota na lista "CcDdEFfGgAaB" --}
index :: Char -> Int
index x = foldl (\acc _->acc+1) 1 (takeWhile (/=x) "CcDdEFfGgAaB")


notesBelow :: Double -> [(Char, Int)] -> [(Char, Int)]
notesBelow k = filter (\x -> (fromNotes [x]) < [k])


averageFrequency :: [(Char, Int)] -> Double
averageFrequency xs = (sum (fromNotes xs)) / (length' xs)

{-- Metodo auxiliar implementado para nao precisar aplicar fromIntegral --}
length' :: [a] -> Double
length' = foldr (\_ x -> x+1) 0
