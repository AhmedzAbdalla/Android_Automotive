#include <iostream>

// Template class pair
template <typename T1 , typename T2>
class pair {
    T1 first;    // First value of the pair
    T2 second;   // Second value of the pair

public:
    // Default constructor initializing first and second to default values
    pair() : first(T1()), second(T2()) {}

    // Parameterized constructor initializing first and second to given values
    pair(const T1 &f, const T2 &s) : first(f), second(s) {}

    // Getter for first value
    T2 getvalue(const T1 &f) const {
        return second;
    }


    // Setter for first value
    void set(const T1 &key , const T2 &val) {
        first = key;
        second = val;
    }


};

int main() {
    // Create pair objects with different types
    pair<int , int> p1(10, 20);
    pair<double , double> p2(1.5, 2.5);

    // Get and print the first and second values
    std::cout <<p1.getvalue(10) << std::endl;
    std::cout <<p2.getvalue(1.5) << std::endl;

    return 0;
}
