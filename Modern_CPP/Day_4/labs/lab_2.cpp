#include <iostream>

class pair {
    int first;   // First value of the pair
    int second;  // Second value of the pair

public:
    // Default constructor initializing first and second to 0
    pair() : first(0), second(0) {}

    // Parameterized constructor initializing first and second to given values
    pair(int f, int s) : first(f), second(s) {}

    // Getter for first value
    int getFirst() const {
        return this->first;
    }

    // Getter for second value
    int getSecond() const {
        return this->second;
    }

    // Setter for first value
    void setFirst(int val) {
        this->first = val;
    }

    // Setter for second value
    void setSecond(int val) {
        this->second = val;
    }

    // Swap the values of first and second
    void swap() {
        int temp = this->first;
        this->first = this->second;
        this->second = temp;
    }

    // Print pairs from an array of pair objects
    void print_pairs(const pair l_pair[], const unsigned int size) const {
        for (unsigned int i = 0; i < size; ++i) {
            std::cout << l_pair[i].getFirst() << " : " << l_pair[i].getSecond() << std::endl;
        }
    }
};

int main() {
    int num1, num2;

    // Create a pair object with values 44 and 55
    pair p1(44, 55);
    
    // Get and print the first and second values
    num1 = p1.getFirst();
    num2 = p1.getSecond();
    std::cout << num1 << " : " << num2 << std::endl;

    // Swap the values of the pair object
    p1.swap();
    std::cout << "After swap: " << p1.getFirst() << " : " << p1.getSecond() << std::endl;

    // Create an array of pair objects
    pair pairs[] = {pair(1, 2), pair(3, 4), pair(5, 6)};
    
    // Print the pairs from the array
    p1.print_pairs(pairs, 3);

    return 0;
}
