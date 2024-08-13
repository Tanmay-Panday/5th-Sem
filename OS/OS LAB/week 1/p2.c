#include <stdio.h>
#include <unistd.h>

int main()  {
    fork();
    printf("linux\n");
    fork();
    printf("unix\n");
    fork();
    printf("windows\n");
    return 0;
}