#include <stdio.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    printf("Antes dos fork's\n");
    fork();
    printf("Entre os fork's\n");
    fork();
    printf("Depois dos fork's\n");
    return 0;
}
