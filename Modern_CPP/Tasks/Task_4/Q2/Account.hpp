#ifndef _ACCOUNT_H_
#define _ACCOUNT_H_

#include <iostream>
#include <ctime>

class Account
{
private:
    static int m_noOfAccounts;
    static int m_totalAmount;
    static int m_totalNbDeposits;
    static int m_totalNbWithdrawals;
    static void m_displayTimestamp(void);

    int m_accountIndex;
    int m_amount;
    int m_noOfDeposits;
    int m_noOfWithdrawals;

public:
    Account(void);
    Account(int initial_deposit);
    ~Account(void);

    void makeDeposit(int deposit);
    bool makeWithdrawal(int withdrawal);
    void displayStatus(void) const;

    static void displayAccountsInfos(void);
};

#endif /* _ACCOUNT_H_ */