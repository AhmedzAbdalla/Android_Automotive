#include <iostream>
using namespace std;

int main() {
    double population = 162100; // Initial population
    double growth_rate = 0.065; // Annual growth rate (6.5%)
    int year = 0;               // Year counter

    // Loop to calculate the population each year until it surpasses one million
    while (population <= 1000000) {
        cout << "Year " << year << ": " << population << endl;
        population *= (1 + growth_rate); // Increase population by the growth rate
        year++; // Increment the year counter
    }

    // Output the number of years it took to surpass one million
    cout << "It will take " << year << " years for the population to surpass one million." << endl;

    return 0;
}
