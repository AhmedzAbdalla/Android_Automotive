#include "Account.hpp"

// Initialize static members
int Account::m_noOfAccounts = 0;
int Account::m_totalAmount = 0;
int Account::m_totalNbDeposits = 0;
int Account::m_totalNbWithdrawals = 0;

Account::Account(void) : m_accountIndex(m_noOfAccounts++), m_amount(0), m_noOfDeposits(0), m_noOfWithdrawals(0)
{
    m_totalAmount += m_amount;
}

Account::Account(int initial_deposit) : m_accountIndex(m_noOfAccounts++), m_amount(initial_deposit), m_noOfDeposits(0), m_noOfWithdrawals(0)
{
    m_totalAmount += m_amount;
}

Account::~Account(void)
{
    m_noOfAccounts--;
    m_totalAmount -= m_amount;
}

void Account::makeDeposit(int deposit)
{
    m_amount += deposit;
    m_noOfDeposits++;
    m_totalAmount += deposit;
    m_totalNbDeposits++;
}

bool Account::makeWithdrawal(int withdrawal)
{
    if (m_amount < withdrawal)
    {
        return false;
    }
    m_amount -= withdrawal;
    m_noOfWithdrawals++;
    m_totalAmount -= withdrawal;
    m_totalNbWithdrawals++;
    return true;
}

void Account::displayStatus(void) const
{
    m_displayTimestamp();
    std::cout << "index:" << m_accountIndex << ";amount:" << m_amount << ";deposits:" << m_noOfDeposits << ";withdrawals:" << m_noOfWithdrawals << std::endl;
}

void Account::displayAccountsInfos(void)
{
    m_displayTimestamp();
    std::cout << "accounts:" << m_noOfAccounts << ";total:" << m_totalAmount << ";deposits:" << m_totalNbDeposits << ";withdrawals:" << m_totalNbWithdrawals << std::endl;
}

void Account::m_displayTimestamp(void)
{
    time_t now = time(0);
    tm *ltm = localtime(&now);
    std::cout << "[" << 1900 + ltm->tm_year
              << (ltm->tm_mon < 9 ? "0" : "") << 1 + ltm->tm_mon
              << (ltm->tm_mday < 10 ? "0" : "") << ltm->tm_mday
              << "_"
              << (ltm->tm_hour < 10 ? "0" : "") << ltm->tm_hour
              << (ltm->tm_min < 10 ? "0" : "") << ltm->tm_min
              << (ltm->tm_sec < 10 ? "0" : "") << ltm->tm_sec
              << "] ";
}