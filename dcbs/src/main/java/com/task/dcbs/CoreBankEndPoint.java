package com.task.dcbs;

import com.dummy_core_bank.ws.*;
import com.task.dcbs.service.DummyCoreBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Developed by Developed by P G R Asanka - 901833109V
 */

@Endpoint
@RequiredArgsConstructor
public class CoreBankEndPoint {

    private static final String CORE_BANK_NAMESPACE_URI = "http://www.dummy-core-bank.com/ws";
    private final DummyCoreBankService coreBankService;

    /**
     * processAccountBalanceByAccNoRequest
     *
     * @param request
     * @return
     */
    @PayloadRoot(namespace = CORE_BANK_NAMESPACE_URI, localPart = "getAccountBalanceByAccNoRequest")
    @ResponsePayload
    public GetAccountBalanceByAccNoResponse processAccountBalanceByAccNoRequest(@RequestPayload GetAccountBalanceByAccNoRequest request) {
        return coreBankService.getAccountBalanceByAccountNo(request.getAccountNo());

    }

    /**
     * processTotalAccountBalanceRequest
     *
     * @param request
     * @return
     */
    @PayloadRoot(namespace = CORE_BANK_NAMESPACE_URI, localPart = "getTotalAccountBalanceRequest")
    @ResponsePayload
    public GetTotalAccountBalanceResponse processTotalAccountBalanceRequest(@RequestPayload GetTotalAccountBalanceRequest request) {
        return coreBankService.getTotalAccountBalance(request.getUserId());

    }

    /**
     * processFundTransferRequest
     *
     * @param request
     * @return
     */
    @PayloadRoot(namespace = CORE_BANK_NAMESPACE_URI, localPart = "fundTransferRequest")
    @ResponsePayload
    public FundTransferResponse processFundTransferRequest(@RequestPayload FundTransferRequest request) {
        return coreBankService.fundTransfer(request);

    }


}
