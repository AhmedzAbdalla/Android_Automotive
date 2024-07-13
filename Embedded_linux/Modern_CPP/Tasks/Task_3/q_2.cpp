#include <iostream>
#include <cstdlib>  // For rand() and srand()
#include <ctime>    // For time()

using namespace std;

class Vertex {
private:
    int x;
    int y;

public:
    // Constructor
    Vertex() {
        // Initialize with random numbers
        setRandom();
    }

    // Setter to set (x, y) with random numbers
    void setRandom() {
        x = rand() % 101;  // Random number between 0 and 100
        y = rand() % 101;  // Random number between 0 and 100
    }

    // Getters for (x, y)
    int getX() const { return x; }
    int getY() const { return y; }

    // Method to return a string representation of the vertex
    string toString() const {
        return "(" + to_string(x) + "," + to_string(y) + ")";
    }
};

int main() {
    srand(static_cast<unsigned int>(time(nullptr)));  // Seed the random number generator with current time

    Vertex v[5];  // Array of 5 Vertex objects

    // Output the vertices using the toString() method
    for (int i = 0; i < 5; ++i) {
        cout << "Vertex " << i+1 << ": " << v[i].toString() << endl;
    }

    return 0;
}
