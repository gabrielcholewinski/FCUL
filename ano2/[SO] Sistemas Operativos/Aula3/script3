#! /bin/bash

#o argumento $2 é a segunda linha do arg1 logo o $3 é a terceira
#func(){
#    echo "S1 = $1"
#    echo "S4 = $4"
#    x=`diff $1 $2`
#    if [ -z x ]
#        then echo "é null e existe"
#    else echo "nao eh null ou nao existe"
#    fi
#}



if [ -a $1 ] && [ -a $2 ]
    then sort $1>arg1.txt
         sort $2>arg2.txt
         res=`diff arg1.txt arg2.txt`
         if [ -z $res ]
            then echo "São iguais"
         else echo "São diferentes"
         fi
         rm arg1.txt
         rm arg2.txt
fi
