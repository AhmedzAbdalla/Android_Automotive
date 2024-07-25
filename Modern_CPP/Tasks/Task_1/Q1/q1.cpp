#include <iostream> // Include the input-output stream library
using namespace std; // Use the standard namespace to avoid prefixing std::

// Declare and initialize global variables
int result = 0; // This will hold the sum of entered numbers
int var = 0;    // This will hold the current number entered by the user

int main()
{
    cout << "Please, Enter number: "; // Prompt the user to enter a number
    cin >> var; // Read the number entered by the user and store it in 'var'
    
    // Loop until the user enters 0
    while (0 != var)
    {
        result += var; // Add the entered number to 'result'
        cout << "Please, Enter a new number: "; // Prompt the user to enter another number
        cin >> var; // Read the new number entered by the user
    }
    
    // After the loop ends (when the user enters 0)
    cout << "Result= " << result << endl; // Output the total sum of entered numbers
    
    return 0; // Return 0 to indicate the program ended successfully
}
