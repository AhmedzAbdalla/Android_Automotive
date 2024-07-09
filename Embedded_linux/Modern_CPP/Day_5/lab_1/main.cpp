#include "DynamicArray.hpp"
#include <iostream>

int main()
{
    // Testing the constructors
    DynamicArray arr1;
    DynamicArray arr2(10);
    DynamicArray arr3(5, 42);
    int values[] = {1, 2, 3, 4, 5};
    DynamicArray arr4(5, values);
    DynamicArray arr5(arr4);

    // Testing the print function
    arr1.print();
    arr2.print();
    arr3.print();
    arr4.print();
    arr5.print();

    // Testing pushback
    arr1.pushback(10);
    arr1.pushback(20);
    arr1.print();

    // Testing popback
    arr1.popback();
    arr1.print();

    // Testing removeAt
    arr4.removeAt(2);
    arr4.print();

    // Testing insertAt
    arr4.insertAt(2, 99);
    arr4.print();

    // Testing insertMiddle
    arr4.insertMiddle(50);
    arr4.print();

    // Testing removeMiddle
    arr4.removeMiddle();
    arr4.print();

    // Testing size
    std::cout << "Size of arr4: " << arr4.size() << std::endl;

    return 0;
}
