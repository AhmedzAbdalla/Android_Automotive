#include <iostream>
#include <cstdlib>

class Battleship {
private:
    bool board[5][5];
    int guesses;
    int maxGuesses;
    int shipX, shipY;

public:
    Battleship(int maxGuesses) : maxGuesses(maxGuesses), guesses(0) {
        // Initialize the board to false (no ships)
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                board[i][j] = false;
            }
        }

        // Place the battleship at a random location
        srand(3);
        shipX = rand() % 5;
        shipY = rand() % 5;
        board[shipX][shipY] = true;
    }

    bool guess(int x, int y) {
        guesses++;
        if (x == shipX && y == shipY) {
            std::cout << "Hit! You've sunk the battleship!" << std::endl;
            return true;
        } else {
            if (x == shipX) {
                std::cout << "The x-coordinate is correct. ";
            } else if (x == shipX - 1 || x == shipX + 1) {
                std::cout << "You're almost right with the x-coordinate. ";
            } else {
                std::cout << "The x-coordinate is incorrect. ";
            }

            if (y == shipY) {
                std::cout << "The y-coordinate is correct." << std::endl;
            } else if (y == shipY - 1 || y == shipY + 1) {
                std::cout << "You're almost right with the y-coordinate." << std::endl;
            } else {
                std::cout << "The y-coordinate is incorrect." << std::endl;
            }
            return false;
        }
    }

    bool gameOver() const {
        return guesses >= maxGuesses;
    }

    int getGuesses() const {
        return guesses;
    }

    int getRemainingGuesses() const {
        return maxGuesses - guesses;
    }
};

int main() {
    Battleship game(5);
    int x, y;

    std::cout << "Welcome to Battleship!" << std::endl;
    std::cout << "You have 5 guesses to find the hidden battleship in a 5X5 grid." << std::endl;

    while (!game.gameOver()) {
        std::cout << "Enter your guess (row and column): ";
        std::cin >> x >> y;

        if (game.guess(x, y)) {
            break;
        }

        std::cout << "You missed, You have " << game.getRemainingGuesses()<<" guesses left." << std::endl;
    }

    if (game.gameOver() ) {
        std::cout << "Game over. You've used all your guesses." << std::endl;
    }

    return 0;
}
