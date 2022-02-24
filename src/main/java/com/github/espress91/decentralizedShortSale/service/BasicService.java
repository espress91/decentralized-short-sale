package com.github.espress91.decentralizedShortSale.service;

import org.springframework.stereotype.Service;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Service
public class BasicService{

    private EthereumService ethereumService;

    public BasicService(EthereumService ethereumService)
    {
        this.ethereumService = ethereumService;
    }

    public int getTotalSupply() throws IOException, ExecutionException, InterruptedException {
        Function function = new Function("totalSupply",
                                         Collections.emptyList(),
                                         Arrays.asList(new TypeReference<Uint256>() {}));

        return ((BigInteger)ethereumService.ethCall(function)).intValue();
    }

    public int getBalanceOf(String tokenOwner) throws IOException, ExecutionException, InterruptedException {
        Function function = new Function("balanceOf",
                Arrays.asList(new Address(tokenOwner)),
                Arrays.asList(new TypeReference<Uint>() {}));

        return ((BigInteger)ethereumService.ethCall(function)).intValue();
    }

    public void setTransfer(String receiver, int numTokens) throws IOException, ExecutionException, InterruptedException {
        Function function = new Function("transfer",
                Arrays.asList(new Address(receiver),
                        new Uint256(numTokens)),
                Arrays.asList(new TypeReference<Bool>() {}));

        String txHash = ethereumService.ethSendTransaction(function);

        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }

    public void setApprove(String delegate, int numTokens) throws IOException, ExecutionException, InterruptedException {
        Function function = new Function("approve",
                Arrays.asList(new Address(delegate), new Uint256(numTokens)),
                Arrays.asList(new TypeReference<Bool>() {}));

        String txHash = ethereumService.ethSendTransaction(function);

        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }

    public void setAllowance(String owner, String delegate) throws IOException, ExecutionException, InterruptedException {
        Function function = new Function("allowance",
                Arrays.asList(new Address(owner), new Address(delegate)),
                Arrays.asList(new TypeReference<Uint>() {}));

        String txHash = ethereumService.ethSendTransaction(function);

        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }

    public void setTransferFrom(String owner, String buyer, int numTokens) throws IOException, ExecutionException, InterruptedException {
        Function function = new Function("transferFrom",
                Arrays.asList(new Address(owner), new Address(buyer), new Uint256(numTokens)),
                Arrays.asList(new TypeReference<Bool>() {}));

        String txHash = ethereumService.ethSendTransaction(function);

        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }

}
