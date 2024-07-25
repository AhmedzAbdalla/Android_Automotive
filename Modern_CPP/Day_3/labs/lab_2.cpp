#include <iostream>   // Standard input/output stream
#include <string>     // String handling functions
#include <cmath>      // Math functions
#include <algorithm>  // Algorithms

namespace DynamicAlloc
{
    // Function to create a 2D integer array dynamically
    int** create2DArray(const unsigned int size)
    {
        int ** arr_2d = new int *[size];  // Allocate memory for an array of 'size' pointers to int

        for(int i = 0; i < size ;i++)     // Loop through each pointer in the array
        {
            arr_2d[i] = new int[5];      // Allocate memory for a 1D array of 5 integers
        }
        return arr_2d;                   // Return the pointer to the 2D array
    }

    // Function to delete a dynamically allocated 2D integer array
    void delete2DArray(int** arr_2d, const unsigned int size)
    {
        for(int i = 0; i < size ;i++)     // Loop through each pointer in the array
        {
            delete[] arr_2d[i];          // Delete the 1D array pointed to by arr_2d[i]
        }
        delete[] arr_2d;                 // Delete the array of pointers itself
    }
    
}

int main() {
    // Main function where program execution begins
    
    int ** test = DynamicAlloc::create2DArray(5);   // Create a 2D array with 5 rows
    DynamicAlloc::delete2DArray(test , 5);          // Delete the created 2D array

    return 0;  // Return 0 to indicate successful completion
}
