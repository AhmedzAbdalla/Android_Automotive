#include <iostream>
#include <string>

class Car {
private:
    std::string company;  // Private member variable to store the company name
    std::string model;    // Private member variable to store the model name
    int year;             // Private member variable to store the manufacturing year

public:
    // Default constructor
    Car() : company(""), model(""), year(0) {}

    // Parameterized constructor
    Car(const std::string& company, const std::string& model, int year) 
        : company(company), model(model), year(year) {}

    // Getter for company
    virtual std::string getCompany() const final {
        return company;
    }

    // Setter for company
    virtual void setCompany(const std::string& company) final{
        this->company = company;
    }

    // Getter for model
    virtual std::string getModel() const final{
        return model;
    }

    // Setter for model
    virtual void setModel(const std::string& model) final{
        this->model = model;
    }

    // Getter for year
    virtual int getYear() const final {
        return year;
    }

    // Setter for year
    virtual void setYear(int year) final{
        this->year = year;
    }

    // Function to display car details
    void display() const {
        std::cout << "Car Details:" << std::endl;
        std::cout << "Company: " << company << std::endl;
        std::cout << "Model: " << model << std::endl;
        std::cout << "Year: " << year << std::endl;
    }
};

int main() {
    // Create an object of Car using the default constructor
    Car car1;

    // Set values using setter functions
    car1.setCompany("Mitsubishi");
    car1.setModel("Lancer Ex");
    car1.setYear(2016);

    // Display car details
    car1.display();

    // Create another object of Car using the parameterized constructor
    Car car2("Audi", "Q7", 2023);

    // Display car details
    car2.display();

    return 0;
}
