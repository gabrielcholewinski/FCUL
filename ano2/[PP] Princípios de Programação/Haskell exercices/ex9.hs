--1-
--toFile :: Show a => FilePath -> [a] -> IO ()


--5- a)
  filterFiles :: (String -> Bool) -> FilePath -> FilePath -> IO()
  filterFiles f arq1 arq2 = do
    leitura <- readFile arq1
    writeFile arq2 $ unlines $ filter f  $ lines leitura
{--  b)
    filterPrefix :: String -> FilePath -> FilePath -> IO()
    filterPrefix peneira inF outF = do
       filterFiles (isPrefix)

       -}