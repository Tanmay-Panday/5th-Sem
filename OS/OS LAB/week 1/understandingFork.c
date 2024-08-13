#include <stdio.h>
#include <unistd.h>

int main() {
    // Create a child process
    pid_t pid = fork();

    if (pid < 0) {
        // Error handling
        printf("Fork failed!\n");
        return 1;
    }
    if(pid == 0){   //fork() returning 0 means child process
        printf("child id = %d \n",getpid());
    }
    else{ // fork() returning positive means parent process
        printf("parent id = %d \n",getpid());
    }
    // printf("Tanmay \t process_id(pid) = %d\n",getpid() );

    return 0;
}
