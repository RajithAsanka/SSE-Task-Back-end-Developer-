package com.task.integratorservice.controller;

import com.task.integratorservice.rest.FundTransferRestRequest;
import com.task.integratorservice.rest.FundTransferRestResponse;
import com.task.integratorservice.rest.GetAccountBalanceByAccountRestResponse;
import com.task.integratorservice.rest.GetTotalAccountBalanceByUserIdRestResponse;
import com.task.integratorservice.service.IntegratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Developed by P G R Asanka - 901833109V
 */

@RestController
@RequestMapping(
        value = "/rest/api/v1")
public class IntegratorController {

    @Autowired
    IntegratorService integratorService;


    @GetMapping(value = "/get/account/balance/by/accountNo")
    public GetAccountBalanceByAccountRestResponse getAccountBalanceByAccount(@RequestParam("accountNo") String accountNo) {

        return integratorService.getAccountBalanceByAccountNo(accountNo);
    }


    @GetMapping(value = "/get/total/account/balance/by/userId")
    public GetTotalAccountBalanceByUserIdRestResponse getTotalAccountBalanceByUserId(@RequestParam("userId") String userId) {
        return integratorService.getTotalBalanceByUserId(userId);
    }

    @PostMapping(value = "/make/fundtransfer")
    public FundTransferRestResponse fundTransfer(@RequestBody FundTransferRestRequest request) {
        return integratorService.makeFundTransfer(request);
    }


}
