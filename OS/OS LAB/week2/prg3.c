#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
int main(){
    int n,i;
    int se=0,so=0;
    printf("enter size of array\nsize = ");
    scanf("%d",&n);
    int arr[n];
    printf("enter array\n");
    for(i=0;i<n;i++){
        scanf("%d",&arr[i]);
    }
    pid_t pid=fork();
    if(pid<0){
        printf("\nFork Failed");
        return 1;
    }
    else if(pid == 0){
        for(i=0;i<n;i++){
            if(arr[i]%2 == 0) se+=arr[i];
        }
        printf("even sum by child = %d\n",se);
    }
    else{
        for(i=0;i<n;i++){
            if(arr[i]%2 !=0) so+=arr[i];
        }
        printf("odd sum by parent = %d\n",so);
    }
    return 0;
}