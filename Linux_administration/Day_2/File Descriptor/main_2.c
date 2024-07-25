#include<stdio.h>
#include<fcntl.h>
#include<unistd.h>

int main()
{
    int fd1, fd2;
    char buf=' ';
    char buf1='1';
    char buf2='0';


    fd1 = open("/sys/class/leds/input4::capslock/brightness" , O_WRONLY);
    fd2 = open("output.txt" , O_RDONLY);

    read(fd2, &buf,1);

    printf(">>%c\n" , buf);
    
    if('0' == (buf))
    {
        printf("HELLLL\n");
        write(fd1,&buf1,1);
    }
    else
    {
        write(fd1,&buf2,1);
    }

    //write(fd2,var,1);


    return 0;
}