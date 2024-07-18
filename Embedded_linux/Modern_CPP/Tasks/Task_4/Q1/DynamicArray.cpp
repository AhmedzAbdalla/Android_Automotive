
template <typename T>
DynamicArray<T>::DynamicArray() : capacity(1), size(0)
{
    array = new T[capacity];
}

template <typename T>
DynamicArray<T>::~DynamicArray()
{
    delete[] array;
}

template <typename T>
void DynamicArray<T>::resize(size_t newCapacity)
{
    T *newArray = new T[newCapacity];
    for (size_t i = 0; i < size; i++)
    {
        newArray[i] = array[i];
    }
    delete[] array;
    array = newArray;
    capacity = newCapacity;
}

template <typename T>
void DynamicArray<T>::push_back(const T &value)
{
    if (size == capacity)
    {
        resize(capacity * 2);
    }
    array[size++] = value;
}

template <typename T>
void DynamicArray<T>::pop_back()
{
    if (size == 0)
    {
        throw std::out_of_range("Array is empty");
    }
    size--;
}

template <typename T>
T &DynamicArray<T>::operator[](size_t index)
{
    if (index >= size)
    {
        throw std::out_of_range("Index out of range");
    }
    return array[index];
}

template <typename T>
const T &DynamicArray<T>::operator[](size_t index) const
{
    if (index >= size)
    {
        throw std::out_of_range("Index out of range");
    }
    return array[index];
}

template <typename T>
size_t DynamicArray<T>::get_size() const
{
    return size;
}

template <typename T>
size_t DynamicArray<T>::get_capacity() const
{
    return capacity;
}
