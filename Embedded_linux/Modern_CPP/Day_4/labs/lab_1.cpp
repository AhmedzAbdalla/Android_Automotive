#include<iostream>

enum class Errorcode
{
    Bad_Request = 400,
    Not_found = 404,
    Server_ERROR = 500,
    Gateway_Timeout = 504,

};

void printError(const enum Errorcode & EC)
{
    switch (EC)
    {
    case Errorcode::Bad_Request :
        std::cout << "Bad_Request "<< static_cast<int>(Errorcode::Bad_Request)<< std::endl;
        break;

    case Errorcode::Not_found :
        std::cout << "Not_found " << static_cast<int>(Errorcode::Not_found)<< std::endl;
        break;
    
    case Errorcode::Server_ERROR :
        std::cout << "Server_ERROR " <<static_cast<int>( Errorcode::Server_ERROR)<< std::endl;
        break;
    
    case Errorcode::Gateway_Timeout :
        std::cout << "Gateway_Timeout "<< static_cast<int>(Errorcode::Gateway_Timeout)<< std::endl;
        break;
    
    default:
        break;
    }
}

int main()
{

    enum Errorcode EC1=Errorcode::Bad_Request;
    printError(EC1);

    EC1=Errorcode::Not_found;
    printError(EC1);

    EC1=Errorcode::Server_ERROR;
    printError(EC1);

    EC1=Errorcode::Gateway_Timeout;
    printError(EC1);


    return 0;
}