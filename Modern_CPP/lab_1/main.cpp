#include <iostream>
#include <cstring>

using namespace std;

int names = 0;

char **SOA = NULL;


int main()
{
    char temp[20];

    cout << "Please enter Number of Names" << endl;
    cin >> names;

    SOA = (char**) malloc(names * sizeof(char*));

    for(int i =0; i < names ; i++)
    {
        SOA[i] = (char*)malloc(20 * sizeof(char));
    }

    for(int  i =0 ; i <names ; i++)
    {
        cout << "Please enter Names NO." << i +1 << endl;
        fflush(stdin);
        fgets(temp, sizeof(temp), stdin);
        strcpy(SOA[i] , temp);
    }

    cout << "********************This is Dynamic 2D Array********************" << endl;

    for(int  i =0 ; i <names ; i++)
    {
        printf("%s" , SOA[i]);
    }

    return 0;
}