#include <stdio.h>
#include<stdio.h>
#include<fcntl.h>
#include<unistd.h>

int main() {

    int fd1, fd2;
    char buf=' ';


    fd1 = open("/sys/class/leds/input4::capslock/brightness" , O_RDONLY);
    fd2 = open("output.txt" , O_WRONLY);


    read(fd1,&buf,1);

    write(fd2, &buf, 1);

    

    return 0;
}
