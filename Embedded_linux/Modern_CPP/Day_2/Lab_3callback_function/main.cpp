#include <iostream>
#include <stdlib.h> // Include for malloc and calloc

using namespace std;

int **SOA = NULL; // Global pointer to a pointer for the 2D array of integers

// Function to calculate the sum of elements in an array
int func(int* arr, int len)
{
    int sum = 0;
    for (int i = 0; i < len; ++i) {
        sum += arr[i];
    }
    return sum;
}

// Function to create and manipulate a 2D array of integers
int* func1(int ** arr_2d, int arr_size, int* row_sizes, int (*func) (int*, int))
{
    arr_2d = (int**) malloc(arr_size * sizeof(int*)); // Allocate memory for the array of pointers to rows

    // Create and fill the 2D array
    int test = 0;
    for (int i = 0; i < arr_size; ++i) {
        arr_2d[i] = (int*) malloc(row_sizes[i] * sizeof(int)); // Allocate memory for each row based on row_sizes

        // Fill each row with sequential values
        for (int j = 0; j < row_sizes[i]; ++j) {
            arr_2d[i][j] = ++test; // Assign values to the 2D array
            cout << arr_2d[i][j] << endl; // Print each element of the 2D array
        }
        cout << "================" << endl; // Separator after each row
    }

    // Allocate memory for array to store sums of each row
    int *ret = (int*) calloc(arr_size, sizeof(int));

    // Calculate the sum of each row using the provided callback function
    for (int i = 0; i < arr_size; ++i) {
        ret[i] = func(arr_2d[i], row_sizes[i]); // Calculate sum of each row
    }

    cout << "$$$$$$$$$$$$$$$$$$$$" << endl; // Separator before printing sums

    // Print the sums of each row
    for (int i = 0; i < arr_size; ++i) {
        cout << ret[i] << endl; // Print sum of each row
    }

    return ret; // Return array of sums
}

int main()
{
    int arr_size[3] = {5, 5, 3}; // Array indicating sizes of each row in the 2D array

    func1(SOA, 3, arr_size, &func); // Call func1 to create and process the 2D array

    return 0; // Return 0 to indicate successful execution
}

