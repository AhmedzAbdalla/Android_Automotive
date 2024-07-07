#include <iostream>
using namespace std;

// Function to perform binary search on a sorted array
int binary_search(const int *arr, const unsigned int size, const unsigned num);

int main() {
    // Array of integers
    int arr[8] = {11, 33, 44, 55, 66, 77, 88};

    // Search for the number 44 in the array
    int result = binary_search(arr, 8, 44);
    if(result != -1) {
        cout << "Index: " << result << endl;
    } else {
        cout << "Element not found" << endl;
    }

    // Search for the number 2 in the array
    result = binary_search(arr, 8, 2);
    if(result != -1) {
        cout << "Index: " << result << endl;
    } else {
        cout << "Element not found" << endl;
    }

    return 0;
}

/**
 * Function to perform binary search on a sorted array
 * @param arr: Pointer to the first element of the array
 * @param size: Size of the array
 * @param num: The number to search for
 * @return: Index of the number if found, otherwise -1
 */
int binary_search(const int *arr, const unsigned int size, const unsigned num) {
    int left = 0;
    int right = size - 1;
    unsigned int mid;
    int ret = -1;

    // Check if the array is null
    if (arr == NULL) {
        // Nothing to do
    } else {
        // Loop until the search space is exhausted
        while (left <= right) {
            mid = left + static_cast<unsigned int>((right - left) / 2);
            if (*(arr + mid) == num) {
                ret = mid; // Number found, return its index
                break;
            } else if (*(arr + mid) < num) {
                left = mid + 1; // Ignore left half
            } else {
                right = mid - 1; // Ignore right half
            }
         }
    }
    return ret; // Return the result, -1 if not found
}
