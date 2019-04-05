import Control.Monad  
import Data.Char

daily :: IO()
daily = do
    putStr "date format (14/12/2050)"
    date <- getLine
    putStr "text: "
    contents <- getLine
    putStr "continue?y/n"
    answer <- getLine
    if(answer == "n") then putStrLn "see you space cowboy"
    else daily 
    appendFile  "ruhan.txt" (date++"\n"++"\n")
    appendFile "ruhan.txt" $ contents++"\n"
    return ()