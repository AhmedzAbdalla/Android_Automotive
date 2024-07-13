#include "../include/calc.h"

int add(const int num1,const int num2)
{
    return num1+num2;
}
int subtract (const int num1,const int num2)
{
    int ret = 0;
    if(num1 >= num2)
    {
        ret = num1-num2;
    }
    else
    {
        ret = num2-num1;
    }
    return ret;
}
int multiply (const int num1,const int num2)
{
    return num1 * num2;
}
int divide   (const int num1,const int num2)
{
    int ret = 0;
    if(0==num2)
    {
        printf("ERROR\n");
    }
    else
    {
        ret= (int)(num1 / num2);
    }
    return ret;
}