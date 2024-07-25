#ifndef DYNAMICARRAY_HPP
#define DYNAMICARRAY_HPP

#include <iostream>
#include <stdexcept>

template <typename T>
class DynamicArray
{
private:
    T *array;
    size_t capacity;
    size_t size;

    void resize(size_t newCapacity);

public:
    DynamicArray();
    ~DynamicArray();

    void push_back(const T &value);
    void pop_back();
    T &operator[](size_t index);
    const T &operator[](size_t index) const;
    size_t get_size() const;
    size_t get_capacity() const;
};

#include "DynamicArray.cpp"

#endif // DYNAMICARRAY_HPP