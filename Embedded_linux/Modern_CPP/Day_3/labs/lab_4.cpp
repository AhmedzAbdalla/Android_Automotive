#include <iostream>

// Enum to define the data types that can be stored in the array
enum DataType { INT, DOUBLE, CHAR, UNKNOWN };

class DynamicArray {
private:
    void** array;        // Pointer to an array of void pointers
    int size;            // Size of the array
    DataType* types;     // Array to store the type of each element

public:
    // Constructor to initialize the array and types array with given size
    DynamicArray(int size) {
        this->size = size;
        array = new void*[size];
        types = new DataType[size];
        for (int i = 0; i < size; ++i) {
            array[i] = nullptr;    // Initialize each element to nullptr
            types[i] = UNKNOWN;    // Initialize each type to UNKNOWN
        }
    }

    // Destructor to delete the array and its elements
    ~DynamicArray() {
        for (int i = 0; i < size; ++i) {
            deleteElement(i);      // Delete each element if it's not nullptr
        }
        delete[] array;            // Delete the array of void pointers
        delete[] types;            // Delete the array of types
    }

    // Function to set a value at a specific index in the array
    void setValue(int index, void* value, DataType type) {
        if (index >= 0 && index < size) {
            deleteElement(index);  // Delete any existing element at index
            array[index] = value;  // Set the new value
            types[index] = type;   // Set the type of the new value
        } else {
            std::cerr << "Index out of bounds" << std::endl;
        }
    }

    // Function to get a value at a specific index in the array
    void* getValue(int index) const {
        if (index >= 0 && index < size) {
            return array[index];   // Return the value at the index
        } else {
            std::cerr << "Index out of bounds" << std::endl;
            return nullptr;
        }
    }

    // Function to get the size of the array
    int getSize() const {
        return size;
    }

    // Function to get the data type at a specific index in the array
    DataType getDataType(int index) const {
        if (index >= 0 && index < size) {
            return types[index];   // Return the type at the index
        } else {
            std::cerr << "Index out of bounds" << std::endl;
            return UNKNOWN;
        }
    }

    // Function to print the array with the data type of each element
    void printArray() const {
        for (int i = 0; i < size; ++i) {
            std::cout << "Index " << i << ": ";
            if (array[i] == nullptr) {
                std::cout << "null";
            } else {
                switch (types[i]) {
                    case INT:
                        std::cout << "INT " << *(static_cast<int*>(array[i]));
                        break;
                    case DOUBLE:
                        std::cout << "DOUBLE " << *(static_cast<double*>(array[i]));
                        break;
                    case CHAR:
                        std::cout << "CHAR " << *(static_cast<char*>(array[i]));
                        break;
                    default:
                        std::cout << "UNKNOWN";
                        break;
                }
            }
            std::cout << std::endl;
        }
    }

private:
    // Helper function to delete an element at a specific index
    void deleteElement(int index) {
        if (array[index] != nullptr) {
            switch (types[index]) {
                case INT:
                    delete static_cast<int*>(array[index]);
                    break;
                case DOUBLE:
                    delete static_cast<double*>(array[index]);
                    break;
                case CHAR:
                    delete static_cast<char*>(array[index]);
                    break;
                default:
                    break;
            }
            array[index] = nullptr;    // Set the element to nullptr
            types[index] = UNKNOWN;    // Set the type to UNKNOWN
        }
    }
};

int main() {
    DynamicArray arr(5);  // Create a DynamicArray of size 5

    // Create pointers to different data types
    int* intPtr = new int(10);
    double* doublePtr = new double(20.5);
    char* charPtr = new char('a');

    // Set values in the array
    arr.setValue(0, intPtr, INT);
    arr.setValue(1, doublePtr, DOUBLE);
    arr.setValue(2, charPtr, CHAR);

    // Print the array
    arr.printArray();

    return 0;
}
