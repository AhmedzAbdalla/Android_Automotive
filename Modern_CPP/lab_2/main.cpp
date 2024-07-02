
#include <iostream> // Include the iostream library for input and output

using namespace std; // Use the standard namespace

// Function declaration for ret, which inserts a new element into an array
int *ret (int *const arr, const int new_element, const int index, const int size);

int main()
{   
    int arr_size;
    cout << "Please, enter the array size" << endl;
    cin >> arr_size; // Get the array size from the user

    // Dynamically allocate memory for the array based on user input
    int *arr = (int*) malloc(arr_size * sizeof(int));
    for(int i = 0; i < 4; i++)
    {
        cout << "Please, enter element No. " << i + 1 << endl;
        cin >> *(arr + i); // Get array elements from the user
    }

    // Display the array before insertion
    cout << "Array before insertion" << endl;
    for(int i = 0; i < 4; i++)
    {
        cout << arr[i] << endl;
    }

    // Insert a new element into the array at the specified index
    arr = ret(arr, 66, 2, 4);
    
    cout << "===================================" << endl;
    // Display the array after insertion
    cout << "Array after insertion" << endl;
    for(int i = 0; i < 5; i++)
    {
        cout << arr[i] << endl;
    }

    return 0;
}

// Function definition for ret
int *ret (int *const arr, const int new_element, const int index, const int size)
{
    int *ret = NULL;
    // Allocate memory for a new array with one additional element
    ret = (int*)malloc(((size + 1) * sizeof(int)));
    if (NULL != ret)
    {
        int i = 0;
        // Copy elements from the original array up to the specified index
        for(i = 0; i < index; i++)
        {
           *(ret + i) = *(arr + i);
        }
        // Insert the new element at the specified index
        *(ret + index) = new_element;
        // Copy the remaining elements from the original array
        for(i = index + 1; i < size + 1; i++)
        {
           *(ret + i) = *(arr + i - 1);
        }
    }
    // Free the memory allocated for the original array
    free(arr);
    return ret; // Return the new array
}
