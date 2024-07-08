#include <iostream>   // Standard input/output stream
#include <string>     // String handling functions
#include <cmath>      // Math functions
#include <algorithm>  // Algorithms

// Declare a namespace to encapsulate functions related to array pairs
namespace ArrayPair
{
   // Function to create an array of pairs of integers
   std::pair<int, int>* createArray(const int size)
   {
        // Allocate memory for an array of pairs
        auto temp = new std::pair<int ,int>[size];
        return temp;  // Return the allocated memory
   }

   // Function to delete the allocated array of pairs
   void deletepair(std::pair<int,int> *l_pair)
   {
        // Delete the allocated memory
        delete [] l_pair;    
   }

   // Function to set a pair's values at a specific index in the array
   void setPair(std::pair<int,int> *l_pair , const int index)
   {     
        // Set the first value of the pair
        l_pair[index].first = 5; 
        // Set the second value of the pair
        (l_pair+index)->second = 5; 
   }

   // Function to get a pair's values from a specific index in the array
   std::pair<int , int> getPair(std::pair<int,int> *l_pair , const int index)
   {    
        std::pair<int , int> ret;
        // Retrieve the first value of the pair
        ret.first = (l_pair+index)->first; 
        // Retrieve the second value of the pair
        ret.second = (l_pair+index)->second;
        
        return ret;  // Return the pair
   }

   // Function to print all pairs in the array
   void printPair(std::pair<int,int> *l_pair , const int size)
   {    
        for(int i =0; i < size; i++ )
        {
            // Print the pair values at index i
            std::cout << "pair " << i+1 <<": "<< l_pair[i].first << " : " << l_pair[i].second << std::endl;
        }
   }
}

int main() {

     // Create an array of 5 pairs
     std::pair<int, int>* p1 = ArrayPair::createArray(5);

     // Set the pair values at index 2
     ArrayPair::setPair(p1 , 2);
     
     // Get the pair values from index 1
     std::pair<int, int> mypair1 = ArrayPair::getPair(p1 , 1);
     // Get the pair values from index 2
     std::pair<int, int> mypair2 = ArrayPair::getPair(p1 , 2);
     
     std::cout <<"this uninitialized pair" << std::endl;
     // Print the first value of the pair from index 1
     std::cout << mypair1.first << std::endl;
     // Print the second value of the pair from index 1
     std::cout << mypair1.second << std::endl;
     
     std::cout <<"this initialized pair" << std::endl;
     // Print the first value of the pair from index 2
     std::cout << mypair2.first << std::endl; 
     // Print the second value of the pair from index 2
     std::cout << mypair2.second << std::endl;

     // Print all pairs in the array
     ArrayPair::printPair(p1, 5);

     // Delete the allocated array of pairs
     ArrayPair::deletepair(p1);
     std::cout << "deleted successfully" << std::endl;

    return 0;  // Return success status
}
