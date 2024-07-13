#include <iostream>
#include <cstdlib>  // For rand() and srand()
#include <ctime>    // For time() and clock()

using namespace std;

struct Vertex
{
    int x;
    int y;
};

int main()
{
    clock_t var = static_cast<int>(clock());  // Get current clock time and cast it to int
    struct Vertex v[5];  // Array of 5 Vertex structures
    
    srandom(var);  // Seed the random number generator with var
    cout << var << endl;  // Output the value of var
    
    // Generate random values for each Vertex
    for(int i = 0; i < 5; i++)
    {
        v[i].x = rand();  // Generate a random integer
        while(v[i].x > 100)
        {
            v[i].x = static_cast<int>((v[i].x) / 1000);  // Reduce the value of x if it's greater than 100
        }
        
        v[i].y = rand();  // Generate a random integer for y
        while(v[i].y > 100)
        {
            v[i].y = static_cast<int>(v[i].y / 100);  // Reduce the value of y if it's greater than 100
        }
    }

    // Output the generated Vertex values
    for(int i = 0; i < 5; i++)
    {
        cout << v[i].x << ":" << v[i].y << endl;  // Output each Vertex's x and y values
    }

    return 0;
}

