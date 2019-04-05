#!/bin/bash

# GRUPO SO-068
# Carlos Marques, nº 51964
# Gabriel Freitas, nº 51035
# Ruhan Azevedo, nº 51779

sort $1 > soperpro_ordenado.txt
sort $2 > esperado_ordenado.txt
DIFF=$(diff soperpro_ordenado.txt esperado_ordenado.txt)
if [ "$DIFF" == "" ]
then echo "IGUAIS"
else echo "DIFERENTES"
fi

rm -f soperpro_ordenado.txt
rm -f esperado_ordenado.txt
