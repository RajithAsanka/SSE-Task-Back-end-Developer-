package com.task.integratorservice;

import com.task.integratorservice.rest.GetAccountBalanceByAccountRestResponse;
import com.task.integratorservice.service.IntegratorService;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class IntegratorServiceApplicationTests {

    @Autowired
    IntegratorService integratorService;


    @org.junit.Test()
    public void getAccountBalanceByAccountNo() {

        GetAccountBalanceByAccountRestResponse balance = integratorService.getAccountBalanceByAccountNo("");

        // check response parameters
        Assert.assertNotNull(balance);
        Assert.assertNotNull(balance.getAccountNo());
        Assert.assertNotNull(balance.getBalance());
        Assert.assertNotNull(balance.getUserId());
        Assert.assertTrue(balance.getAccountNo() != null);
        Assert.assertTrue(balance.getMessage() != null);


    }

}
