#include <iostream>   // Standard input/output stream
#include <string>     // String handling functions
#include <cmath>      // Math functions
#include <algorithm>  // Algorithms

namespace MathFunctions
{
    // Function to print the square root of a number
    void print(const double num)
    {
        std::cout << "The square root of " << num << " is " << sqrt(num) << std::endl;
    }
}

namespace StringFunctions
{
    // Function to print a string in uppercase or lowercase based on 'op'
    void print(std::string &str, bool op)
    {
        std::string result = str;  // Create a copy of the input string
        
        if(op == true)  // Check if 'op' is true
        {
            std::transform(result.begin(), result.end(), result.begin(), ::toupper);  // Convert string to uppercase
            std::cout << result << std::endl;  // Print the uppercase string
        }
        else  // 'op' is false
        {
            std::transform(result.begin(), result.end(), result.begin(), ::tolower);  // Convert string to lowercase
            std::cout << result << std::endl;  // Print the lowercase string
        }
    }
}

namespace ArrayFunctions
{
    // Function to print an array in reverse order
    void print(int *arr, unsigned int len)
    {
        for (int i = len - 1; i >= 0; --i) {  // Loop through the array in reverse order
            std::cout << arr[i] << " ";      // Print each element of the array followed by a space
        }
        std::cout << std::endl;  // Move to the next line after printing all elements
    }
}

int main() {
    // Main function where program execution begins
    
    std::string str1 = "ahmed";  // Declare and initialize a string variable
    
    int *arr = new int [5]{ 1 , 5, 8, 9 };  // Declare and initialize an array of integers
    
    MathFunctions::print(36);  // Call the MathFunctions namespace to print the square root of 36
    StringFunctions::print(str1, true);   // Call the StringFunctions namespace to print 'str1' in uppercase
    StringFunctions::print(str1, false);  // Call the StringFunctions namespace to print 'str1' in lowercase
    ArrayFunctions::print(arr, 5);         // Call the ArrayFunctions namespace to print the 'arr' array in reverse order
    
    delete[] arr;  // Deallocate memory for the dynamically allocated array
    
    return 0;  // Return 0 to indicate successful completion
}
