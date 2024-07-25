#include <iostream>
#include <cmath>

using namespace std;

// Declare global variables
int op;    // This will hold the user's chosen operation
int num1;  // This will hold the number entered by the user

// Function prototypes
void decimalToBinary(int num);
void BinaryTodecimal(int num);

int main()
{
    // Display menu options to the user
    cout << "1) Decimal to Binary" << endl;
    cout << "2) Binary to Decimal" << endl;
    cout << "Please, Enter the operation: ";
    cin >> op;  // Read the user's choice
    cout << "Please, Enter a number: ";
    cin >> num1;  // Read the number entered by the user

    // Perform the chosen operation
    switch (op)
    {
    case 1:
        decimalToBinary(num1);  // Convert decimal to binary
        break;
    case 2:
        BinaryTodecimal(num1);  // Convert binary to decimal
        break;
    default:
        cout << "Invalid operation" << endl;  // Handle invalid operation
        break;
    }

    return 0;
}

// Function to convert a decimal number to binary
void decimalToBinary(int num)
{
    int arr[32];  // Array to store binary digits, assuming the number won't require more than 32 bits
    int i = 0;

    // Convert decimal to binary
    while (num > 0)
    {
        arr[i] = num % 2;  // Store the remainder (0 or 1) in the array
        num = num / 2;     // Update the number by dividing it by 2
        i++;
    }

    // Print binary result in reverse order
    cout << "Binary is = ";
    for (int j = i - 1; j >= 0; j--)
    {
        cout << arr[j];  // Print each binary digit
    }
    cout << endl;
}

// Function to convert a binary number to decimal
void BinaryTodecimal(int num)
{
    int dec = 0, i = 0, rem;

    // Convert binary to decimal
    while (num != 0)
    {
        rem = num % 10;          // Get the last digit (remainder) of the binary number
        num /= 10;               // Remove the last digit from the binary number
        dec += rem * pow(2, i);  // Add the value of the current digit to the decimal result
        ++i;                     // Increment the position counter
    }

    cout << "Decimal value: " << dec << endl;  // Print the decimal result
}
