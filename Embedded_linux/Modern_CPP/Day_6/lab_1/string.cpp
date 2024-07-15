#include <iostream>
#include <string>
using namespace std;

// Initialize the string with the given text
string text = "The cycle of life is a cycle of cycles";

int main()
{
    // Find the first occurrence of the word "cycle" in the string
    auto index = text.find("cycle");

    // Loop through the string to replace all occurrences of "cycle" with "circle"
    while (index != string::npos)
    {
        text.replace(index, 5, "circle"); // Replace "cycle" with "circle"
        index = text.find("cycle"); // Find the next occurrence of "cycle"
    }

    // Print the modified string after replacing all occurrences of "cycle"
    cout << text << endl;

    // Find the first occurrence of "circle" in the modified string
    index = text.find("circle");
    // Insert "great" before the first occurrence of "circle"
    text.insert(text.find("circle"), "great");
    // Add a space after "great"
    text.insert(index + 5, " ");
    // Print the modified string after inserting "great"
    cout << text << endl;

    // Find the first occurrence of "circle" in the modified string
    index = text.find("circle");
    // Find the second occurrence of "circle" by starting the search after the first occurrence
    auto index1 = text.find("circle", index + 6);
    // Replace the second occurrence of "circle" with "never-ending"
    text.replace(index1, 6, "never-ending");
    // Print the final modified string
    cout << text << endl;

    return 0;
}