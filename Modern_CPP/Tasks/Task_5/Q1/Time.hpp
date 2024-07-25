#ifndef TIME_HPP
#define TIME_HPP

#include <iostream>
#include <iomanip>

class Time
{
private:
    int hours;
    int minutes;
    int seconds;

public:
    Time();                    // Default constructor
    Time(int h, int m, int s); // Parameterized constructor

    void display() const;                     // Display time in 11:59:59 format
    void add(const Time &t1, const Time &t2); // Add two Time objects

    int getHours() const { return hours; }
    int getMinutes() const { return minutes; }
    int getSeconds() const { return seconds; }
};

#endif // TIME_HPP