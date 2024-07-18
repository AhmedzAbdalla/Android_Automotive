#include "Time.hpp"

int main()
{
    const Time t1(10, 30, 45);
    const Time t2(3, 20, 35);
    Time t3;

    t3.add(t1, t2);

    std::cout << "Time 1: ";
    t1.display();

    std::cout << "Time 2: ";
    t2.display();

    std::cout << "Time 3 (sum of Time 1 and Time 2): ";
    t3.display();

    return 0;
}