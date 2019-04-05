import Expressao

x = ((|*|) (constante 1) (constante 2))  --(2 *1)
y = ((|*|) (constante 2) x)              --2 * (2 * 1)
z = ((|+|) (constante 2) (constante 0))  -- (2 + 0)
w = ((|+|) y z)                          --(2+0) * (2* (2 *1)
--façam avalia avalia w depois do load e tem que dar 6 
