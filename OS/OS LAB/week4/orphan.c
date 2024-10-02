#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>

// orphan process is a condition when a parent process finishes execution but child process has not been not completely executed, meaning child process continues to exist.

// how does child process continue to run if parent has been finished execution?
// after child process becomes orphan process, it is adopted by init process (pid = 1 in UNIX-LINUX SYSTEMS)


int main() {
    pid_t pid = fork();  // Fork a new process

    if (pid == 0) {
        // Child process
        printf("Child process is created with PID: %d\n", getpid());
        printf("Parent PID of the child process is: %d\n", getppid());
        printf("Making child process sleep for 12 seconds so the parent can exit...\n");
        sleep(12);  // Child sleeps, allowing parent to exit
        printf("After parent exit, child process is orphaned, new parent PID: %d\n", getppid());
        sleep(5);
        printf("Child process is now exiting.\n");
        sleep(3);  // Give time to observe the orphan status
    } else if (pid > 0) {
        // Parent process
        printf("Parent process (PID: %d) is running.\n", getpid());
        sleep(5);  // Parent sleeps for 5 seconds, then exits
        printf("Parent process is now exiting, making the child an orphan.\n");
        exit(0);  // Parent exits
    } else {
        // Fork failed
        printf("Fork failed!\n");
        return 1;
    }

    printf("\n\nEnd of program\n");
    return 0;
}