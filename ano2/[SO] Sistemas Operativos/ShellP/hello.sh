#!/bin/sh
if [ $1 -lt 0 ] 
then echo "$1 e' um numero negativo" 
elif [ $1 -eq 0 ]   
then echo "$1 e' o numero zero"   
else echo "$1 e' um numero positivo" 
fi
