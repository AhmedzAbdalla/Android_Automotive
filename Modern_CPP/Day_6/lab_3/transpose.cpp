#include <iostream>
#include <string>
#include <vector>

using namespace std;

// Function prototype for transpose function
vector<std::vector<int>> transpose(vector<std::vector<int>> matrix);

// Initialize a 3x3 matrix with given values
std::vector<std::vector<int>> matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}};

int main()
{
    // Call the transpose function and store the result in ret1
    vector<std::vector<int>> ret1 = transpose(matrix);

    return 0;
}

// Function to transpose a given matrix
vector<std::vector<int>> transpose(vector<std::vector<int>> matrix)
{
    // Initialize ret with the dimensions of the transposed matrix
    std::vector<std::vector<int>> ret(matrix[0].size(), std::vector<int>(matrix.size()));

    int i = 0, j;
    // Iterate through each row of the original matrix
    for (std::vector<std::vector<int>>::iterator row = matrix.begin(); row != matrix.end(); ++row)
    {
        j = 0; // Reset j for each new row
        // Iterate through each element in the row
        for (std::vector<int>::iterator it = row->begin(); it != row->end(); ++it)
        {
            // Store the transposed value in ret
            ret[j][i] = *it;
            // Print the transposed value
            std::cout << ret[j][i];
            j++;
        }
        // Print a newline to separate rows in the output
        std::cout << std::endl;
        i++;
    }

    // Print the transposed matrix
    for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; j < 3; ++j)
        {
            std::cout << ret[i][j] << " ";
        }
        std::cout << std::endl;
    }

    // Return the transposed matrix
    return ret;
}