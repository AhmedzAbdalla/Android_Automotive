#include <iostream>
#include <string>

using namespace std;

string my_replace(string l_haystack, string l_needle, string l_replacement);

int main()
{
    string haystack, needle, replacement;
    cout << "Please any string" << endl;
    // cin >> haystack;
    getline(std::cin, haystack);

    cout << "Please any needle" << endl;
    // cin >> needle;
    getline(std::cin, needle);

    cout << "Please any replacement" << endl;
    // cin >> replacement;
    getline(std::cin, replacement);

    string ret = my_replace(haystack, needle, replacement);

    cout << ret << endl;

    return 0;
}

string my_replace(string l_haystack, string l_needle, string l_replacement)
{
    auto index = l_haystack.find(l_needle);
    while (index != string::npos)
    {
        l_haystack.replace(index, l_needle.length(), l_replacement);
        index = l_haystack.find(l_needle);
    }
    return l_haystack;
}
