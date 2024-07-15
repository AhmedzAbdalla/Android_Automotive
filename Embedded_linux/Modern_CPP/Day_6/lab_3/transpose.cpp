#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<std::vector<int>> transpose(vector<std::vector<int>> matrix);

std::vector<std::vector<int>> matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}};
int main()
{
    vector<std::vector<int>> ret1 = transpose(matrix);
    ;

    return 0;
}

vector<std::vector<int>> transpose(vector<std::vector<int>> matrix)
{
    std::vector<std::vector<int>> ret(matrix[0].size(), std::vector<int>(matrix.size())); // Assuming ret is a matrix of appropriate size
    int i = 0, j;
    for (std::vector<std::vector<int>>::iterator row = matrix.begin(); row != matrix.end(); ++row)
    {
        std::cout << "ffff" << std::endl;
        j = 0; // Reset j for each new row
        for (std::vector<int>::iterator it = row->begin(); it != row->end(); ++it)
        {
            // Assuming ret is intended to store the transposed version of matrix
            ret[j][i] = *it;
            std::cout << ret[j][i];
            j++;
        }
        std::cout << std::endl; // To separate rows in the output
        i++;
    }

    i = 0;
    j = 0;
    for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; j < 3; ++j)
        {
            std::cout << ret[i][j] << " ";
        }
        std::cout << std::endl;
    }

    return ret;
}