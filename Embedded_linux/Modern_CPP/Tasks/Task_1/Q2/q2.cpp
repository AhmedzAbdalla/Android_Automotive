#include <iostream> // Include the input-output stream library
using namespace std; // Use the standard namespace to avoid prefixing std::

#define SEC_HOUR      (3600UL) // Define the number of seconds in an hour
#define SEC_MINUTE    (60UL)   // Define the number of seconds in a minute

// Declare global variables
unsigned int number;  // This will hold the input number of seconds
unsigned int hours;   // This will hold the calculated hours
unsigned int minutes; // This will hold the calculated minutes

int main()
{
    cout << "Please, Enter number in seconds: "; // Prompt the user to enter a number in seconds
    cin >> number; // Read the number entered by the user and store it in 'number'

    hours = (unsigned int)(number / SEC_HOUR); // Calculate the number of hours

    number = number - ((hours) * SEC_HOUR); // Subtract the hours part from the total number of seconds

    minutes = (unsigned int)(number / SEC_MINUTE); // Calculate the number of minutes from the remaining seconds

    number = number - ((minutes) * SEC_MINUTE); // Subtract the minutes part from the remaining seconds

    // Output the result in the format H:M:S
    cout << "H:M:S - " << hours << ":" << minutes << ":" << number << endl;

    return 0; // Return 0 to indicate the program ended successfully
}
