#! /bin/bash
#author: viniciosfaustino
#organization: capivara-rex
 
#Aqui verificamos se o arquivo em C foi passado por parâmetro
if test "$1"
	#caso tenha sido passado, então podemos compilar o programa
	then gcc -Wall $1 -o program -lm
#caso contrário, abortamos a operação
else
echo -e
echo "Nenhum arquivo para compilar. Finalizando execução."
exit
fi
 
#verificando se o executável do programa foi gerado para podermos executá-lo
FILE=program
if [ -e "$FILE" ]
then
#verificando a existência dos parâmetros de arquivos de entrada e saída
if  test "$2" = "<"  && test "$3"
then
if test "$4" = ">" && test "$5"
then
#executando o programa passando arquivo de entrada e saída;
./program < $3 > $4
else
#executando o programa passando somente arquivo de entrada;
./program < $3
fi
else
#executando o programa sem arquivos de entrada/saída;
./program
fi
echo -e
echo  "Finalizando execução."
#aqui iremos fazer uma cópia do executável
cp program executavel
#e em seguida remover o executável para que na próxima compilação
#possamos garantir que se ele existe, então o programa compilou sem erros
rm program
else
 
#caso o executável não tenha sido gerado, significa que houveram erros no código
echo -e
echo  "Houveram erros ao compilar o arquivo, verifique seu código."
fi
