#include "Account.hpp"

int main() {
    Account acc1(100);
    Account acc2(200);

    acc1.makeDeposit(50);
    acc1.makeDeposit(30);
    acc1.makeWithdrawal(20);
    acc1.displayStatus();

    acc2.makeDeposit(100);
    acc2.makeWithdrawal(50);
    acc2.displayStatus();

    Account::displayAccountsInfos();

    return 0;
}