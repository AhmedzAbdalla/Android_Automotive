#include <iostream> // Include the iostream library for input and output
#include "vector.hpp" // Include the custom vector library

using namespace std; // Use the standard namespace

// Declare a vector object
vector vec1;

// Declare a status variable to track the status of vector operations
ret_status stat = NOK;

int main()
{   
    // Initialize the vector with an initial capacity of 2
    stat = vect_init(&vec1, 2); 

    // Insert the value 11 at index 0
    stat = vect_insert_at(&vec1, 11, 0);

    // Insert the value 22 at index 1
    stat = vect_insert_at(&vec1, 22, 1);

    // Print the current state of the vector
    print(&vec1);

    // Insert the value 33 at index 2, testing reallocation if necessary
    stat = vect_insert_at(&vec1, 33, 2);

    // Print the current state of the vector
    print(&vec1);

    // Insert the value 66 at index 1, testing insertion and shifting
    stat = vect_insert_at(&vec1, 66, 1);

    // Print the current state of the vector
    print(&vec1);

    // Delete the value at index 1, testing deletion and shifting
    stat = vect_delete_at(&vec1, 1);

    // Print the current state of the vector
    print(&vec1);
    
    // Access the value at index 2, testing access
    stat = vect_at(&vec1, 2);

    return 0; // Return 0 indicating successful execution
}
