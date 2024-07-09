#ifndef DYNAMICARRAY_HPP
#define DYNAMICARRAY_HPP

#include <iostream>

class DynamicArray
{
private:
    int *array;
    int capacity;
    int currentSize;

public:
    // Constructors
    DynamicArray();
    DynamicArray(int size);
    DynamicArray(int size, int value);
    DynamicArray(int size, int *values);
    DynamicArray(const DynamicArray &other);

    // Destructor
    ~DynamicArray();

    // Member functions
    void resize();
    void pushback(int value);
    void popback();
    void removeAt(int index);
    void insertAt(int index, int value);
    void insertMiddle(int value);
    void removeMiddle();
    int size() const;
    void print() const;
};

#endif // DYNAMICARRAY_HPP
