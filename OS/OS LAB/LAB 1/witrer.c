#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main() {
    int fd;
    char *data = "Hello, world!\n";

    // Open a file for writing
    fd = open("output.txt", O_WRONLY | O_CREAT, 0644);
    if (fd == -1) {
        perror("open");
        return 1;
    }

    // Write data to the file
    int bytes_written = write(fd, data, sizeof(data) - 1);
    if (bytes_written == -1) {
        perror("write");
        close(fd);
        return 1;
    }

    // Close the file
    close(fd);

    return 0;
}
