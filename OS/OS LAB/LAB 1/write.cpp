#include <iostream>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
using namespace std;
int main() {
    string txt="hello world";
    int fd=open("output.txt", O_WRONLY|O_CREAT , 0644);
    if(fd == -1){
        cout << "error in opening file :)" << endl;
        return 1;
    }
    int numberOfBytesToBeWritten = txt.size();
    cout << "file opened successfully..   fd = " << fd << endl;
    int bytesWritten = write(fd,txt.c_str(),numberOfBytesToBeWritten);
    // low level write() takes parameters file directory,
    if(bytesWritten == -1){
        cout << "error in writing into file" << endl;
        return 1;
    }
    close(fd);
    cout << "write() system call implemented successfully" << endl;
    return 0;
}