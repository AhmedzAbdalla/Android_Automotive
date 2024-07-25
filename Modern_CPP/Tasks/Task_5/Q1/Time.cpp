#include "Time.hpp"

Time::Time() : hours(0), minutes(0), seconds(0) {}

Time::Time(int h, int m, int s) : hours(h), minutes(m), seconds(s) {}

void Time::display() const
{
    std::cout << std::setw(2) << std::setfill('0') << hours << ":"
              << std::setw(2) << std::setfill('0') << minutes << ":"
              << std::setw(2) << std::setfill('0') << seconds << std::endl;
}

void Time::add(const Time &t1, const Time &t2)
{
    seconds = t1.seconds + t2.seconds;
    minutes = t1.minutes + t2.minutes + (seconds / 60);
    hours = t1.hours + t2.hours + (minutes / 60);
    seconds %= 60;
    minutes %= 60;
    hours %= 24; // Assuming a 24-hour format for the sake of this example
}