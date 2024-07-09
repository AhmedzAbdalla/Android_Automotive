#include "DynamicArray.hpp"
#include <iostream>
#include <algorithm>

// Default constructor with capacity of 1
DynamicArray::DynamicArray() : capacity(1), currentSize(0)
{
    array = new int[capacity];
}

// Constructor with given capacity
DynamicArray::DynamicArray(int size) : capacity(size), currentSize(0)
{
    array = new int[capacity];
}

// Constructor with given capacity and initial value for all elements
DynamicArray::DynamicArray(int size, int value) : capacity(size), currentSize(size)
{
    array = new int[capacity];
    std::fill(array, array + size, value);
}

// Constructor with given capacity and initial values
DynamicArray::DynamicArray(int size, int *values) : capacity(size), currentSize(size)
{
    array = new int[capacity];
    std::copy(values, values + size, array);
}

// Copy constructor
DynamicArray::DynamicArray(const DynamicArray &other) : capacity(other.capacity), currentSize(other.currentSize)
{
    array = new int[capacity];
    std::copy(other.array, other.array + currentSize, array);
}

// Destructor
DynamicArray::~DynamicArray()
{
    delete[] array;
}

// Resize the array to double the size
void DynamicArray::resize()
{
    capacity *= 2;
    int *newArray = new int[capacity];
    std::copy(array, array + currentSize, newArray);
    delete[] array;
    array = newArray;
}

// Add the value to the end of the array
void DynamicArray::pushback(int value)
{
    if (currentSize == capacity)
    {
        resize();
    }
    array[currentSize++] = value;
}

// Remove the last element from the array
void DynamicArray::popback()
{
    if (currentSize > 0)
    {
        currentSize--;
    }
}

// Remove the element at the given index
void DynamicArray::removeAt(int index)
{
    if (index >= 0 && index < currentSize)
    {
        std::copy(array + index + 1, array + currentSize, array + index);
        currentSize--;
    }
}

// Insert the value at the given index and shift the elements to the right
void DynamicArray::insertAt(int index, int value)
{
    if (index >= 0 && index <= currentSize)
    {
        if (currentSize == capacity)
        {
            resize();
        }
        std::copy_backward(array + index, array + currentSize, array + currentSize + 1);
        array[index] = value;
        currentSize++;
    }
}

// Insert the value in the middle of the array
void DynamicArray::insertMiddle(int value)
{
    insertAt(currentSize / 2, value);
}

// Remove the middle element from the array
void DynamicArray::removeMiddle()
{
    if (currentSize > 0)
    {
        removeAt(currentSize / 2);
    }
}

// Return the size of the array
int DynamicArray::size() const
{
    return currentSize;
}

// Print the array
void DynamicArray::print() const
{
    for (int i = 0; i < currentSize; ++i)
    {
        std::cout << array[i] << " ";
    }
    std::cout << std::endl;
}
