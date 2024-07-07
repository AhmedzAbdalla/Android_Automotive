#include <iostream>
#include <string>
#include <cmath>
#include <algorithm>

namespace MathFunctions
{
    void print(const double num)
    {
        std::cout << "The square root of " << num << " is " << sqrt(num) << std::endl;
    }
}

namespace StringFunctions
{
    void print(std::string &str, bool op)
    {
        std::string result = str;
        if(true == op)
        {
            std::transform(result.begin(), result.end(), result.begin(), ::toupper);
            std::cout << result << std::endl;
        }
        else
        {
            std::transform(result.begin(), result.end(), result.begin(), ::tolower);
            std::cout << result << std::endl;;
        }
    }
}

namespace ArrayFunctions
{
    void print(int *arr ,unsigned int len)
    {
        for (int i = len - 1; i >= 0; --i) {
        std::cout << arr[i] << " ";
    }
        std::cout << std::endl;
    }
}

int main() {
    
    std::string str1 = "ahmed";

    int *arr = new int [5]{ 1 , 5,8,9};

    MathFunctions::print(36);
    StringFunctions::print(str1,true );
    StringFunctions::print(str1,false );
    ArrayFunctions::print(arr, 5);
    return 0;
}
