#include <stdio.h>
#include <unistd.h>

int main()
{
  int var = 10;
  int pid = fork();
  if(pid == 0){
    printf("var = %d\n", var/2);
  }
  else {
      
    printf("var = %d\n", var*2);
  }
  return 0;
}
