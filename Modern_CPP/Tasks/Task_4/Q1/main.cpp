#include <iostream>
#include "DynamicArray.hpp"

int main()
{
    DynamicArray<int> intArray;
    intArray.push_back(1);
    intArray.push_back(2);
    intArray.push_back(3);

    std::cout << "Int Array: ";
    for (size_t i = 0; i < intArray.get_size(); i++)
    {
        std::cout << intArray[i] << " ";
    }
    std::cout << std::endl;

    DynamicArray<double> doubleArray;
    doubleArray.push_back(1.1);
    doubleArray.push_back(2.2);
    doubleArray.push_back(3.3);

    std::cout << "Double Array: ";
    for (size_t i = 0; i < doubleArray.get_size(); i++)
    {
        std::cout << doubleArray[i] << " ";
    }
    std::cout << std::endl;

    DynamicArray<char> charArray;
    charArray.push_back('a');
    charArray.push_back('b');
    charArray.push_back('c');

    std::cout << "Char Array: ";
    for (size_t i = 0; i < charArray.get_size(); i++)
    {
        std::cout << charArray[i] << " ";
    }
    std::cout << std::endl;

    return 0;
}