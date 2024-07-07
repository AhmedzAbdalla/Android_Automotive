#include <iostream> // Include the iostream library for input and output
#include <cstring>  // Include the cstring library for string manipulation functions

using namespace std; // Use the standard namespace

int names = 0;       // Declare a global variable to store the number of names

char **SOA = NULL;   // Declare a global pointer to a pointer for the 2D array of characters (Strings of Arrays)

int main()
{
    char temp[20];   // Temporary array to store the input name

    cout << "Please enter Number of Names" << endl; // Prompt user to enter the number of names
    cin >> names;    // Read the number of names from user input

    SOA = (char**) malloc(names * sizeof(char*)); // Allocate memory for the array of pointers to strings

    for(int i = 0; i < names; i++)
    {
        SOA[i] = (char*) malloc(20 * sizeof(char)); // Allocate memory for each string (assuming max length 20)
    }

    for(int i = 0; i < names; i++)
    {
        cout << "Please enter Names NO." << i + 1 << endl; // Prompt user to enter each name
        fflush(stdin); // Clear the input buffer
        fgets(temp, sizeof(temp), stdin); // Read the name from user input
        strcpy(SOA[i], temp); // Copy the name to the allocated memory
    }

    cout << "********************This is Dynamic 2D Array********************" << endl;

    for(int i = 0; i < names; i++)
    {
        printf("%s", SOA[i]); // Print each name stored in the dynamic 2D array
    }

    return 0; // Return 0 to indicate successful execution
}
