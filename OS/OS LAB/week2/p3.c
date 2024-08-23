#include <stdio.h>
#include <unistd.h>

int main() {
    int n;
    printf("enter size of array : ");
    scanf("%d",&n);
    int esum=0,osum=0;

    int arr[n];
    printf("enter array: ");
    for(int i=0;i<n;i++){
        scanf("%d",&arr[i]);
    }

    pid_t pid= fork();  // child creation

    if(pid<0){ // fork() failed
        printf("fork failed :) \n");
        return 1;
    }
    else if(pid == 0){  //fork() returns 0 for child process
    // doing odd sum by child
        printf("child id = %d \n",getpid());
        for(int i=0;i<n;i++){
            if(arr[i]%2!=0){
                osum+=arr[i];
            }
        }
        printf("odd sum by child = %d\n",osum);
    }
    else{   //fork() returns +ve value for parent process
        // doing even sum by parent
        printf("parent id = %d \n",getpid());
        for(int i=0;i<n;i++){
            if(arr[i]%2==0){
                esum+=arr[i];
            }
        }
        printf("even sum by parent = %d\n",esum);
    }
    return 0;
}
