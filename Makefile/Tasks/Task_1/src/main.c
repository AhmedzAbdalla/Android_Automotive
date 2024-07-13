#include "../include/main.h"

int main()
{
    char op=' ';
    int num1 =0; 
    int num2 =0;

    
    printf("Enter \'+\' for addtion\n");
    printf("Enter \'-\' for Subtract\n");
    printf("Enter \'*\' for Multiply\n");
    printf("Enter \'/\' for division\n");
    printf("Enter operation: ");
    scanf("%c", &op);

    printf("Enter No.1: "); 
    scanf("%d", &num1);
    printf("Enter No.2: "); 
    scanf("%d", &num2);
    switch (op)
    {
    case '+':
        printf("Result = %d\n" , add(num1, num2));
        break;
    case '-':
        printf("Result = %d\n" , subtract(num1, num2));
        break;
    case '*':
        printf("Result = %d\n" , multiply(num1, num2));
        break;
    case '/':
        printf("Result = %d\n" , divide(num1, num2));
        break;
        printf("Ivalid option\n");
    
    default:
        break;
    }

    return 0; 
}