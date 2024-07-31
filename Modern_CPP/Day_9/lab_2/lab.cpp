#include <iostream>
#include <thread>
#include <vector>
#include <mutex>
#include <condition_variable>
#include <unordered_map>
#include <numeric>

// Shared memory and synchronization primitives
std::unordered_map<int, uint64_t> fib_results;
std::mutex fib_mutex;
std::condition_variable fib_cv;
bool results_ready = false;

uint64_t fibonacci(int n) {
    if (n <= 1) return n;
    uint64_t a = 0, b = 1, c;
    for (int i = 2; i <= n; ++i) {
        c = a + b;
        a = b;
        b = c;
    }
    return b;
}

void calculate_fibonacci(int n) {
    uint64_t result = fibonacci(n);

    // Lock mutex to protect shared memory
    std::lock_guard<std::mutex> lock(fib_mutex);
    fib_results[n] = result;
}

void print_results_and_sum(const std::vector<int>& indices) {
    // Wait for all calculations to complete
    std::unique_lock<std::mutex> lock(fib_mutex);
    fib_cv.wait(lock, [] { return results_ready; });

    // Print results
    uint64_t sum = 0;
    for (int index : indices) {
        std::cout << "Fibonacci(" << index << ") = " << fib_results[index] << std::endl;
        sum += fib_results[index];
    }
    std::cout << "Sum of results: " << sum << std::endl;
}

int main() {
    // Fibonacci indices to calculate
    std::vector<int> indices = {4, 9, 14, 17};
    std::vector<std::thread> threads;

    // Launch threads to calculate Fibonacci numbers
    for (int index : indices) {
        threads.emplace_back(calculate_fibonacci, index);
    }

    // Thread to print results and calculate sum
    std::thread printer_thread(print_results_and_sum, indices);

    // Wait for all calculation threads to finish
    for (std::thread& t : threads) {
        t.join();
    }

    // Signal that results are ready
    {
        std::lock_guard<std::mutex> lock(fib_mutex);
        results_ready = true;
    }
    fib_cv.notify_all();

    // Wait for the printer thread to finish
    printer_thread.join();

    return 0;
}
