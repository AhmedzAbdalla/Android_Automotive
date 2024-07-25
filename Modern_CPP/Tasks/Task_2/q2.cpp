#include <iostream>
#include <cmath>

class Calculator {
private:
    // Private member functions for each operation
    double add(double a, double b) {
        return a + b;
    }

    double subtract(double a, double b) {
        return a - b;
    }

    double multiply(double a, double b) {
        return a * b;
    }

    double divide(double a, double b) {
        if (b == 0) {
            std::cerr << "Error: Division by zero." << std::endl;
            return NAN;
        }
        return a / b;
    }

    double power(double a, double b) {
        return pow(a, b);
    }

    double squareRoot(double a) {
        if (a < 0) {
            std::cerr << "Error: Square root of negative number." << std::endl;
            return NAN;
        }
        return sqrt(a);
    }

public:
    // Public function to interface with the user
    void calculate() {
        double num1, num2;
        char op;

        std::cout << "Enter first number: ";
        std::cin >> num1;

        std::cout << "Enter an operator (+, -, *, /, ^, S for sqrt): ";
        std::cin >> op;

        // Only ask for the second number if the operation is not square root
        if (op != 'S') {
            std::cout << "Enter second number: ";
            std::cin >> num2;
        }

        double result;
        switch (op) {
            case '+':
                result = add(num1, num2);
                break;
            case '-':
                result = subtract(num1, num2);
                break;
            case '*':
                result = multiply(num1, num2);
                break;
            case '/':
                result = divide(num1, num2);
                break;
            case '^':
                result = power(num1, num2);
                break;
            case 'S':
                result = squareRoot(num1);
                break;
            default:
                std::cerr << "Error: Invalid operator." << std::endl;
                return;
        }

        std::cout << "The result is: " << result << std::endl;
    }
};

int main() {
    Calculator calc;
    calc.calculate();

    return 0;
}
