#ifndef DYNAMICARRAY_HPP
#define DYNAMICARRAY_HPP

#include <iostream>

// Enumeration for return status
typedef enum
{
    NOK = 0, // Failure status
    OK       // Success status
} ret_status;

class DynamicArray
{
private:
    int *array;      // Pointer to the dynamic array
    int capacity;    // Capacity of the array
    int currentSize; // Current size (number of elements) of the array

public:
    // Constructors
    DynamicArray();                          // Default constructor with capacity of 1
    DynamicArray(int size);                  // Constructor with given capacity
    DynamicArray(int size, int value);       // Constructor with given capacity and initial value for all elements
    DynamicArray(int size, int *values);     // Constructor with given capacity and initial values
    DynamicArray(const DynamicArray &other); // Copy constructor

    // Destructor
    ~DynamicArray();

    // Member functions
    ret_status resize();                       // Resize the array to double the size
    ret_status pushback(int value);            // Add the value to the end of the array
    ret_status popback();                      // Remove the last element from the array
    ret_status removeAt(int index);            // Remove the element at the given index
    ret_status insertAt(int index, int value); // Insert the value at the given index and shift the elements to the right
    ret_status insertMiddle(int value);        // Insert the value in the middle of the array
    ret_status removeMiddle();                 // Remove the middle element from the array
    int size() const;                          // Return the size of the array
    void print() const;                        // Print the array
};

#endif // DYNAMICARRAY_HPP
