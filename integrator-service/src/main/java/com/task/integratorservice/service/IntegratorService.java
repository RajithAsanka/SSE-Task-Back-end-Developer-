package com.task.integratorservice.service;

import com.task.integratorservice.CoreBankSOAPConnector;
import com.task.integratorservice.common.RequestType;
import com.task.integratorservice.model.IntegratorServiceAudit;
import com.task.integratorservice.repository.IntegratorAuditRepository;
import com.task.integratorservice.rest.FundTransferRestRequest;
import com.task.integratorservice.rest.FundTransferRestResponse;
import com.task.integratorservice.rest.GetAccountBalanceByAccountRestResponse;
import com.task.integratorservice.rest.GetTotalAccountBalanceByUserIdRestResponse;
import com.task.schemas.corebank.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Developed by P G R Asanka - 901833109V
 */

@Service
public class IntegratorService {

    @Autowired
    CoreBankSOAPConnector coreBankSOAPConnector;

    @Autowired
    IntegratorAuditRepository auditRepository;

    private static final String CORE_BANK_NAMESPACE_URI = "http://localhost:8082/dummy-core-bank/ws";

    /**
     * get account balance by account number
     *
     * @param accountNo
     * @return
     */
    public GetAccountBalanceByAccountRestResponse getAccountBalanceByAccountNo(String accountNo) {

        //save audit events
        saveAuditeventForGetRequests(RequestType.GET.name(), "getAccountBalanceByAccountNo", accountNo);

        GetAccountBalanceByAccountRestResponse balanceByAccountRestResponse = new GetAccountBalanceByAccountRestResponse();

        GetAccountBalanceByAccNoRequest request = new GetAccountBalanceByAccNoRequest();
        request.setAccountNo(accountNo);

        GetAccountBalanceByAccNoResponse response = (GetAccountBalanceByAccNoResponse) coreBankSOAPConnector.callWebService(CORE_BANK_NAMESPACE_URI, request);


        if (null != response.getAccountInfo()) {
            balanceByAccountRestResponse.setUserId(checkValues(response.getAccountInfo().getUserId()));
            balanceByAccountRestResponse.setAccountNo(checkValues(response.getAccountInfo().getAccountNo()));
            balanceByAccountRestResponse.setBalance(response.getAccountInfo().getBalance());
        }
        if (null != response.getServiceStatus()) {
            balanceByAccountRestResponse.setStatus(checkValues(response.getServiceStatus().getStatus()));
            balanceByAccountRestResponse.setMessage(checkValues(response.getServiceStatus().getMessage()));
        }


        return balanceByAccountRestResponse;


    }


    /**
     * get total balance by userId
     *
     * @param userId
     * @return
     */
    public GetTotalAccountBalanceByUserIdRestResponse getTotalBalanceByUserId(String userId) {

        saveAuditeventForGetRequests(RequestType.GET.name(), "getTotalBalanceByUserId", userId);

        GetTotalAccountBalanceByUserIdRestResponse totalAccountBalanceByUserId = new GetTotalAccountBalanceByUserIdRestResponse();

        GetTotalAccountBalanceRequest request = new GetTotalAccountBalanceRequest();
        request.setUserId(userId);

        GetTotalAccountBalanceResponse response = (GetTotalAccountBalanceResponse) coreBankSOAPConnector.callWebService(CORE_BANK_NAMESPACE_URI, request);

        //exception handling
        if (null != response.getTotalUserAccountBalanceInfo()) {
            totalAccountBalanceByUserId.setUserId(checkValues(response.getTotalUserAccountBalanceInfo().getUserId()));
            totalAccountBalanceByUserId.setAccountDetails(response.getTotalUserAccountBalanceInfo().getAccountDetails());
            totalAccountBalanceByUserId.setTotalBalanceForUser(response.getTotalUserAccountBalanceInfo().getTotalBalanceForUser());
        }
        if (null != response.getServiceStatus()) {
            totalAccountBalanceByUserId.setStatus(checkValues(response.getServiceStatus().getStatus()));
            totalAccountBalanceByUserId.setMessage(checkValues(response.getServiceStatus().getMessage()));
        }


        return totalAccountBalanceByUserId;


    }

    /**
     * make fund transfer according to fund transfer type
     *
     * @param request
     * @return
     */
    public FundTransferRestResponse makeFundTransfer(FundTransferRestRequest request) {

        saveAuditeventForPostRequestes(RequestType.POST.name(), "FUND-TRANSFER", request);

        FundTransferRestResponse fundTransferRestResponse = new FundTransferRestResponse();
        FundTransferRequest fundTransferRequest = new FundTransferRequest();
        fundTransferRequest.setFundTransferType(request.getFundTransferType());
        fundTransferRequest.setUserId(request.getUserId());
        fundTransferRequest.setFromAccountNO(request.getFromAccountNO());
        fundTransferRequest.setToAccountNO(request.getToAccountNO());
        fundTransferRequest.setAmount(request.getAmount());

        FundTransferResponse response = (FundTransferResponse) coreBankSOAPConnector.callWebService(CORE_BANK_NAMESPACE_URI, fundTransferRequest);
        fundTransferRestResponse.setDebitUserId(response.getFundTransferInfo().getDebitUserId());
        fundTransferRestResponse.setDebitAccountNo(response.getFundTransferInfo().getDebitAccountNo());
        fundTransferRestResponse.setDebitAccountBalance(response.getFundTransferInfo().getDebitAccountBalance());
        fundTransferRestResponse.setCreditUserId(response.getFundTransferInfo().getCreditUserId());
        fundTransferRestResponse.setCreditAccountNo(response.getFundTransferInfo().getCreditAccountNo());
        fundTransferRestResponse.setCreditAccountBalance(response.getFundTransferInfo().getCreditAccountBalance());
        fundTransferRestResponse.setStatus(response.getServiceStatus().getStatus());
        fundTransferRestResponse.setMessage(response.getServiceStatus().getMessage());

        return fundTransferRestResponse;

    }

    /**
     * save integrator service audit events for get requests
     *
     * @param requestType
     * @param requestedService
     * @param requestParameter
     */
    private void saveAuditeventForGetRequests(String requestType, String requestedService, String requestParameter) {
        IntegratorServiceAudit serviceAudit = new IntegratorServiceAudit();
        serviceAudit.setRequestType(requestType);
        serviceAudit.setRequestMethod(requestedService);
        serviceAudit.setFundTransferType(requestParameter);
        serviceAudit.setRequestedDate(Instant.now());
        auditRepository.save(serviceAudit);
    }

    /**
     * save integrator service audit events for get requests
     *
     * @param requestType
     * @param requestedService
     * @param requestParameter
     */
    private void saveAuditeventForPostRequestes(String requestType, String requestedService, FundTransferRestRequest requestParameter) {
        IntegratorServiceAudit serviceAudit = new IntegratorServiceAudit();
        serviceAudit.setRequestType(requestType);
        serviceAudit.setRequestMethod(requestedService);
        serviceAudit.setFundTransferType(requestParameter.getFundTransferType());
        serviceAudit.setFromAccountNO(requestParameter.getFromAccountNO());
        serviceAudit.setToAccountNO(requestParameter.getToAccountNO());
        serviceAudit.setAmount(requestParameter.getAmount());
        serviceAudit.setRequestedDate(Instant.now());

        auditRepository.save(serviceAudit);
    }

    /**
     * check null or empty strings
     *
     * @param value
     * @return
     */
    private String checkValues(String value) {
        String validatedValue = null != value ? value : "";
        return validatedValue;

    }

}
