#include <iostream>
#include <string>

class pair {
    std::string first;   // First value of the pair
    std::string second;  // Second value of the pair

public:
    // Default constructor initializing first and second to empty strings
    pair() : first(""), second("") {}

    // Parameterized constructor initializing first and second to given values
    pair(const std::string &f, const std::string &s) : first(f), second(s) {}

    // Getter for first value
    std::string getFirst() const {
        return this->first;
    }

    // Getter for second value
    std::string getSecond() const {
        return this->second;
    }

    // Setter for first value
    void setFirst(const std::string &val) {
        this->first = val;
    }

    // Setter for second value
    void setSecond(const std::string &val) {
        this->second = val;
    }

    // Swap the values of first and second
    void swap() {
        std::string temp = this->first;
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
    std::string str1, str2;

    // Create a pair object with values "Hello" and "World"
    pair p1("Hello", "World");
    
    // Get and print the first and second values
    str1 = p1.getFirst();
    str2 = p1.getSecond();
    std::cout << str1 << " : " << str2 << std::endl;

    // Swap the values of the pair object
    p1.swap();
    std::cout << "After swap: " << p1.getFirst() << " : " << p1.getSecond() << std::endl;

    // Create an array of pair objects
    pair pairs[] = {pair("One", "Two"), pair("Three", "Four"), pair("Five", "Six")};
    
    // Print the pairs from the array
    p1.print_pairs(pairs, 3);

    return 0;
}
