#ifndef VECTOR // Include guard to prevent multiple inclusion
#define VECTOR

#include <iostream>
#include <stdlib.h>
#include <stdio.h>

using namespace std;

// Enumeration for return status
typedef enum {
    NOK = 0, // Failure status
    OK       // Success status
} ret_status;

// Structure representing a vector (dynamic array)
typedef struct {
    int* head;             // Pointer to the array storing elements
    unsigned int capacity; // Maximum capacity of the vector
    unsigned int size;     // Current number of elements in the vector
} vector;

// Function prototypes

// Initialize a vector with a given initial size
ret_status vect_init(vector* vect, const unsigned int size);

// Insert a value at a specific index in the vector
ret_status vect_insert_at(vector* vect, const int val, const unsigned int index);

// Delete a value at a specific index from the vector
ret_status vect_delete_at(vector* vect, const unsigned int index);

// Access and print the value at a specific index in the vector
ret_status vect_at(vector* vect, const unsigned int index);

// Print the elements and details of the vector
void print(vector* vect);

#endif // VECTOR
