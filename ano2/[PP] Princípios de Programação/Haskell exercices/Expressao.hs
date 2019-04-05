module Expressao 
(
 constante, Expr, (|+|), (|*|), avalia
) where

data Expr = Const (Int) | Exp (Expr) (Char) (Expr)

instance Show Expr where
    show (Const x) = show x 
    show (Exp expr1 mSign expr2) = case mSign of
                 '+' -> "(" ++ show expr1 ++ " + " ++ show expr2 ++ ")"
                 '*' -> "(" ++ show expr1 ++ " * " ++ show expr2 ++ ")"

instance Eq Expr where
    (==) expr1 expr2 = (==) (avalia expr1) (avalia expr2) 

constante :: Int -> Expr 
constante k = Const k

(|+|):: Expr -> Expr -> Expr
(|+|) expr1 expr2 = Exp expr1 '+' expr2

(|*|):: Expr -> Expr -> Expr
(|*|) expr1 expr2 = Exp expr1 '*' expr2

avalia :: Expr -> Int 
avalia (Const k) = k 
avalia (Exp expr1 mSign expr2) | (mSign=='*') = (*) (avalia expr1) (avalia expr2)
                               | (mSign=='+') = (+) (avalia expr1) (avalia expr2)