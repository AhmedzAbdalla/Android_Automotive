#include <iostream>
#include <string>
#include <cmath>
#include <algorithm>

namespace DynamicAlloc
{
    int** create2DArray(const unsigned int size)
    {
        int ** arr_2d = new int *[size];

        for(int i = 0; i <= size ;i++)
        {
            arr_2d[i] = new int[5];
        }
        return arr_2d;
    }

    void delete2DArray(int** arr_2d, const unsigned int size)
    {
        

        for(int i = 0; i <= size ;i++)
        {
            delete[] arr_2d[i];
        }
        delete[]arr_2d;
    }
    
}

int main() {
    
    
    return 0;
}
