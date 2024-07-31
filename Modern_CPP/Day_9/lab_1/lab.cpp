#include <iostream>
#include <thread>
#include <atomic>

// Spinlock class
class Spinlock {
public:
    Spinlock() : flag(ATOMIC_FLAG_INIT) {}

    void lock() {
        while (flag.test_and_set(std::memory_order_acquire)) {
            // Busy-wait loop (spin)
        }
    }

    void unlock() {
        flag.clear(std::memory_order_release);
    }

private:
    std::atomic_flag flag;
};

Spinlock spinlock;
int shared_var = 1;

void multiply() {
    for (int i = 0; i < 20; ++i) {
        spinlock.lock();
        shared_var *= 2;
        std::cout << "Multiply: " << shared_var << std::endl;
        spinlock.unlock();
    }
}

void divide() {
    for (int i = 0; i < 20; ++i) {
        spinlock.lock();
        shared_var /= 2;
        std::cout << "Divide: " << shared_var << std::endl;
        spinlock.unlock();
    }
}

int main() {
    std::thread t1(multiply);
    std::thread t2(divide);

    t1.join();
    t2.join();

    return 0;
}
