package com.task.dcbs.service;

import com.dummy_core_bank.ws.*;
import com.task.dcbs.common.FundTransferType;
import com.task.dcbs.common.Status;
import com.task.dcbs.model.AccountEntity;
import com.task.dcbs.repository.AccountDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

/**
 * Developed by P G R Asanka - 901833109V
 */

@Service
@RequiredArgsConstructor
public class DummyCoreBankService {
    private static final int OWN_FUNDTRANSFER_ACCOUNT_COUNT = 2;
    private final AccountDetailsRepository accountDetailsRepository;
    private final MessageSource messageSource;

    /**
     * get account balance of particular account
     *
     * @param accountNo
     * @return
     */
    public GetAccountBalanceByAccNoResponse getAccountBalanceByAccountNo(String accountNo) {

        AccountEntity account = accountDetailsRepository.findByAccountNo(accountNo);
        GetAccountBalanceByAccNoResponse response = new GetAccountBalanceByAccNoResponse();

        if (checkValue(accountNo)) {
            if (null != account) {
                response.setAccountInfo(setAccountDetails(account));
                response.setServiceStatus(setServiceDetails(Status.SUCCESS, ""));
                return response;
            } else {
                response.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.err.no.acc.found", null, Locale.ENGLISH)));
                return response;
            }
        } else {
            response.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.err.no.acc.num.found", null, Locale.ENGLISH)));
            return response;
        }

    }

    /**
     * get total account balance for particular user
     *
     * @param userId
     * @return
     */
    public GetTotalAccountBalanceResponse getTotalAccountBalance(String userId) {
        List<AccountEntity> accountList = accountDetailsRepository.findByUserId(userId);
        GetTotalAccountBalanceResponse totalAccountBalanceResponse = new GetTotalAccountBalanceResponse();
        if (null != accountList && !(accountList.isEmpty())) {
            totalAccountBalanceResponse.setTotalUserAccountBalanceInfo(setUserAccountBalanceInfo(accountList));
            totalAccountBalanceResponse.setServiceStatus(setServiceDetails(Status.SUCCESS, ""));
        } else {
            totalAccountBalanceResponse.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.err.no.acc.for.user", null, Locale.ENGLISH)));
        }
        return totalAccountBalanceResponse;
    }

    /**
     * common fund transfer method for core banking system
     *
     * @param transferRequest
     * @return
     */
    public FundTransferResponse fundTransfer(FundTransferRequest transferRequest) {

        FundTransferResponse transferResponse = new FundTransferResponse();
        if (null != transferRequest.getFundTransferType() && !(transferRequest.getFundTransferType().isEmpty())) {

            if (transferRequest.getFundTransferType().equals(FundTransferType.OWN.name())) {
                //do own account fundtransfer
                doOwnAccountFundTransfer(transferRequest, transferResponse);
            } else if (transferRequest.getFundTransferType().equals(FundTransferType.THIRDPARTY.name())) {
                doThirdPartFundTransfer(transferRequest, transferResponse);
            } else {
                transferResponse.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.invalid.transaction", null, Locale.ENGLISH)));
            }
        } else {
            transferResponse.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.invalid.transaction", null, Locale.ENGLISH)));
        }
        return transferResponse;

    }

    /**
     * execute own account fund transfer
     *
     * @param transferRequest
     * @param transferResponse
     */
    private void doOwnAccountFundTransfer(FundTransferRequest transferRequest, FundTransferResponse transferResponse) {
        List<AccountEntity> accountList = accountDetailsRepository.findByUserId(transferRequest.getUserId());
        if (null != accountList && !(accountList.isEmpty() && (accountList.size() >= OWN_FUNDTRANSFER_ACCOUNT_COUNT))) {
            boolean fromAccResult = validateOwnAccount(accountList, transferRequest.getFromAccountNO());
            boolean toAccResult = validateOwnAccount(accountList, transferRequest.getToAccountNO());

            if (fromAccResult && toAccResult) {
                AccountEntity fromAccount = accountDetailsRepository.findByAccountNo(transferRequest.getFromAccountNO());
                AccountEntity toAccount = accountDetailsRepository.findByAccountNo(transferRequest.getToAccountNO());
                if (checkAccountBalance(fromAccount.getBalance(), transferRequest.getAmount())) {
                    FundTransferResponse transferResponse1 = doFundTransfer(fromAccount, toAccount, transferRequest.getAmount(), transferResponse);
                } else {
                    transferResponse.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.insufficient.balance", null, Locale.ENGLISH)));
                }

            } else {
                transferResponse.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.own.error", null, Locale.ENGLISH)));
            }


        } else {
            transferResponse.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.own.error", null, Locale.ENGLISH)));
        }
    }

    /**
     * execute other account fund transfer
     *
     * @param transferRequest
     * @param transferResponse
     */
    private void doThirdPartFundTransfer(FundTransferRequest transferRequest, FundTransferResponse transferResponse) {


        AccountEntity fromAccount = accountDetailsRepository.findByAccountNo(transferRequest.getFromAccountNO());
        AccountEntity toAccount = accountDetailsRepository.findByAccountNo(transferRequest.getToAccountNO());

        if ((null != fromAccount) && (null != toAccount)) {
            if (checkAccountBalance(fromAccount.getBalance(), transferRequest.getAmount())) {
                doFundTransfer(fromAccount, toAccount, transferRequest.getAmount(), transferResponse);
            } else {
                transferResponse.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.insufficient.balance", null, Locale.ENGLISH)));
            }
        } else {
            transferResponse.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.fund.no.acc.found", null, Locale.ENGLISH)));
        }
    }

    /**
     * execute transaction against dummy-core-banks
     *
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @param transferResponse
     * @return
     */
    private FundTransferResponse doFundTransfer(AccountEntity fromAccount, AccountEntity toAccount, BigDecimal amount, FundTransferResponse transferResponse) {

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountDetailsRepository.save(fromAccount);
        accountDetailsRepository.save(toAccount);

        transferResponse.setServiceStatus(setServiceDetails(Status.SUCCESS, messageSource.getMessage("dcbs.success", null, Locale.ENGLISH)));
        //set fund transfer transaction details
        setFundtransferSuccessDetails(fromAccount, toAccount, transferResponse);
        return transferResponse;
    }

    /**
     * Set transaction success details
     *
     * @param fromAccount
     * @param toAccount
     * @param transferResponse
     */
    private void setFundtransferSuccessDetails(AccountEntity fromAccount, AccountEntity toAccount, FundTransferResponse transferResponse) {
        AccountEntity updatedFromAccount = accountDetailsRepository.findByAccountNo(fromAccount.getAccountNo());
        AccountEntity updatedToAccount = accountDetailsRepository.findByAccountNo(toAccount.getAccountNo());

        FundTransferInfo fundTransferInfo = new FundTransferInfo();
        fundTransferInfo.setCreditUserId(updatedToAccount.getUserId());
        fundTransferInfo.setCreditAccountNo(updatedToAccount.getAccountNo());
        fundTransferInfo.setCreditAccountBalance(updatedToAccount.getBalance());
        fundTransferInfo.setDebitUserId(updatedFromAccount.getUserId());
        fundTransferInfo.setDebitAccountNo(updatedFromAccount.getAccountNo());
        fundTransferInfo.setDebitAccountBalance(updatedFromAccount.getBalance());
        transferResponse.setFundTransferInfo(fundTransferInfo);
    }

    /**
     * Check account balance
     *
     * @param balance
     * @param transferAmount
     * @return
     */
    private boolean checkAccountBalance(BigDecimal balance, BigDecimal transferAmount) {
        boolean validity = false;
        int result = balance.compareTo(transferAmount);
        if (result != -1) {
            validity = true;
        }
        return validity;
    }

    /**
     * check own accounts or not
     *
     * @param accountList
     * @param accountNo
     * @return
     */
    private boolean validateOwnAccount(List<AccountEntity> accountList, String accountNo) {

        boolean validity = false;
        for (AccountEntity account : accountList) {
            if (account.getAccountNo().equals(accountNo)) {
                validity = true;
                break;
            }
        }
        return validity;
    }


    /**
     * set core bank service details for particular request
     *
     * @param success
     * @param message
     * @return
     */
    private ServiceStatus setServiceDetails(Status success, String message) {
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus(success.name());
        serviceStatus.setMessage(message);
        return serviceStatus;
    }

    /**
     * set account details fot particular user
     *
     * @param balanceByAccountNo
     * @return
     */
    private AccountInfo setAccountDetails(AccountEntity balanceByAccountNo) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUserId(balanceByAccountNo.getUserId());
        accountInfo.setAccountNo(balanceByAccountNo.getAccountNo());
        accountInfo.setBalance(balanceByAccountNo.getBalance());
        return accountInfo;
    }

    /**
     * set user account details for total account balance option
     *
     * @param accountList
     * @return
     */
    private TotalUserAccountBalanceInfo setUserAccountBalanceInfo(List<AccountEntity> accountList) {

        TotalUserAccountBalanceInfo userAccountBalanceInfo = new TotalUserAccountBalanceInfo();
        final StringBuilder accountDetailsBuilder = new StringBuilder();

        BigDecimal totalBalance = BigDecimal.ZERO;

        for (AccountEntity account : accountList) {
            accountDetailsBuilder.append(account.getAccountNo() + "-" + account.getBalance() + ",");
            totalBalance = totalBalance.add(account.getBalance());
        }
        userAccountBalanceInfo.setUserId(accountList.get(0).getUserId());
        userAccountBalanceInfo.setTotalBalanceForUser(totalBalance);
        userAccountBalanceInfo.setAccountDetails(accountDetailsBuilder.toString());
        return userAccountBalanceInfo;

    }

    /**
     * check string values null or empty
     *
     * @param value
     * @return
     */

    private boolean checkValue(String value) {

        boolean validity = false;
        if (null != value && !(value.isEmpty())) {
            validity = true;
        }
        return validity;
    }
}
