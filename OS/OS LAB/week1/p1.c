#include<stdio.h>
#include<unistd.h>
int main() {

    fork(); // creation of child process
    printf("tanmay\n"); // called by both parent and child process
    
    return 0;
}