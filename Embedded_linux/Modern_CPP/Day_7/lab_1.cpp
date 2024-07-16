#include <iostream>
#include <fstream>
#include <vector>
#include <iomanip>  
#include <limits>   

using namespace std;

int main() {
    
    const string inputFile = "input.txt";
    const string outputFile = "output.txt";

    
    vector<int> numbers;
    int number;

    ifstream inFile(inputFile);
    if (!inFile) {
        cerr << "Error opening input file\n";
        return -1;
    }

    while (inFile >> number) {
        numbers.push_back(number);
    }
    inFile.close();

   
    int sum = 0;
    int min = numeric_limits<int>::max();
    int max = numeric_limits<int>::min();

    for (int num : numbers) {
        sum += num;
        if (num < min) min = num;
        if (num > max) max = num;
    }

    double avg = static_cast<double>(sum) / numbers.size();

   
    ofstream outFile(outputFile);
    if (!outFile) {
        cerr << "Error opening output file\n";
        return -1;
    }

    outFile << fixed << setprecision(2); 
    outFile << "----------------------------------------------------------------------------------" << std::endl;
    outFile << "|"<< setw(10) << "Sum" << setw(10) <<"|" << setw(10) << "Avg" << setw(10) <<"|" << setw(10)<< "Min" << setw(10)<<"|" << setw(10) << "Max" << setw(10) << "|"<<endl;
    outFile << "----------------------------------------------------------------------------------" << std::endl;
    outFile << "|"<< setw(10) << sum << setw(10) << "|" << setw(10) << avg << setw(10) << "|" << setw(10) << min << setw(10) << "|" << setw(10)<< max << setw(10)<< "|"<< endl;
    outFile << "----------------------------------------------------------------------------------" << std::endl;
    outFile.close();

    return 0;
}