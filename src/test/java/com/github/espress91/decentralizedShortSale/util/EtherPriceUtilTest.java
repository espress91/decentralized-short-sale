package com.github.espress91.decentralizedShortSale.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EtherPriceUtilTest {

    @Autowired
    EtherPriceUtil etherPriceUtil;

    @Test
    public void getEtherPriceTest() {
        System.out.println(etherPriceUtil.getEtherPrice());
    }

}