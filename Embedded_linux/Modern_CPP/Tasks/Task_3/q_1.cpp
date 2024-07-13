#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;



struct Vertex
{
    int x;
    int y;
};


int main()
{
    clock_t var = static_cast<int>(clock());
    struct Vertex v[5]; 
    srandom(var);
    cout << var << endl;

    for(int i = 0; i < 5; i++)
    {
        
        v[i].x = rand();
        while(v[i].x > 100)
        {
            //cout << "gggggggg"<<endl;
            v[i].x = static_cast<int>((v[i].x) /1000);
        }
        v[i].y = rand();
        while(v[i].y > 100)
        {
            v[i].y = static_cast<int>(v[i].y  / 100);
        }
        //cout << rand() << endl;
    }

    for(int i = 0; i < 5; i++)
    {
        
        
        cout << v[i].x <<":"<< v[i].y << endl;
    }


    return 0;
}