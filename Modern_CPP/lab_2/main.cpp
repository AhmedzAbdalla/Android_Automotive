
#include <iostream> // Include the iostream library for input and output

using namespace std; // Use the standard namespace
int arr_size;
int arr_capcity;

int *init(const int arr_aize);

// Function declaration for ret, which inserts a new element into an array
int *insert(int *const arr, const int new_element, const int index, const int size);

int main()
{   
    
    cout << "Please, enter the array size" << endl;
    cin >> arr_capcity; // Get the array size from the user
    //arr_capcity = arr_size; //// capcity and size are equel when array is defined

    // Dynamically allocate memory for the array based on user input
    int *arr = init(arr_size);
    for(int i = 0; i < arr_capcity; i++)
    {
        cout << "Please, enter element No. " << i + 1 << endl;
        cin >> *(arr + i); // Get array elements from the user
        arr_size++;
    }

    // Display the array before insertion
    cout << "Array before insertion" << endl;
    for(int i = 0; i < arr_capcity; i++)
    {
        cout << arr[i] << endl;
    }

    // Insert a new element into the array at the specified index
    arr = insert(arr, 66, 2, arr_capcity);
    //arr = insert(arr, 66, 2, arr_size);
    
    cout << "===================================" << endl;
    // Display the array after insertion
    cout << "Array after insertion" << endl;
    for(int i = 0; i < arr_capcity; i++)
    {
        if(arr[i] != 0)
        {
            cout << arr[i] << endl;
        }
        
    }

    return 0;
}

// Function definition for ret
int *insert (int *const arr, const int new_element, const int index, const int size)
{
    int *ret = NULL;

    if(arr_capcity <= arr_size )
    {
        cout << "capcity increase" << endl;
        // Allocate memory for a new array with 2x elements
        ret = (int*)malloc(((arr_capcity *2) * sizeof(int)));
        //modify arr_size
        arr_capcity *=2;
        //arr_size++;
        
    }
    else
    {
        // create new array to store new data
        ret = (int*)malloc(((arr_capcity) * sizeof(int)));
        cout << "no increase" << endl;
    }

    
    //if (NULL != ret)
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
        for(i = index + 1; i < arr_size + 1; i++)
        {
           *(ret + i) = *(arr + i - 1);
           cout << *(ret + i) << endl;
        }
    }
    // Free the memory allocated for the original array
    free(arr);
    return ret; // Return the new array
}

int *init(const int arr_aize)
{
    return (int*) calloc(arr_aize , sizeof(int));
}
