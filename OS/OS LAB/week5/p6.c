#include<stdio.h> //HEADER FILES
#include<stdlib.h>
#include<unistd.h>
#include<string.h>
int main() //MAIN FUNCTION
{
pid_t pid;
char arr[100],str[100];
int fd[2],nbr,nbw;
pipe(fd); //CREATING A PIPE
pid=fork(); //CALLING FORK TO CREATE A CHILD PROCESS
if(pid==0)
{
printf("\nEnter a string: "); 
gets(str); 
nbw=write(fd[1],str,strlen(str));
printf("Child wrote %d bytes\n",nbw); 
exit(0);
}
else
{
nbr=read(fd[0],arr,sizeof(arr));
arr[nbr]='\0';
printf("Parent read %d bytes : %s\n",nbr,arr);
}
return 0;
} //END OF MAIN