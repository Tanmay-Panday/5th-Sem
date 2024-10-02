#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(){
    pid_t pid= fork();
    if(pid<0){
        printf("fork failed\n");
        return 1;
    }
    else if(pid == 0){
        // child process
        // Child process
        printf("Child process (PID: %d) is exiting...\n", getpid());
        exit(0);  // Child process exits, becoming a zombie
    }
    else{
        // Parent process
        printf("Parent process (PID: %d) is sleeping...\n", getpid());
        sleep(10);  // Parent sleeps, giving the child enough time to exit and become a zombie

        // Child has exited, but parent has not called wait() yet, so it becomes a zombie
        printf("Parent process is still running. Run 'ps' command to see zombie process.\n");
        
        // Now we can clean up the zombie by calling wait()
        wait(NULL);  // This reaps the child process, removing its zombie status
        printf("Parent has now cleaned up the child process.\n");
    }
}