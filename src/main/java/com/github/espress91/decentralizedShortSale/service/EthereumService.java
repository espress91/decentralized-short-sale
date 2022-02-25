package com.github.espress91.decentralizedShortSale.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class EthereumService {

    private String contract;
    private String from;
    private String pwd;

    private Admin web3j;

    public EthereumService(@Value("${ethereum.contract.address}") String contract,
                           @Value("${ethereum.owner.address}") String from,
                           @Value("${ethereum.owner.passwd}") String pwd) {
        this.contract = contract;
        this.from = from;
        this.pwd = pwd;
        web3j = Admin.build(new HttpService()); // default server : http://localhost:8545
    }

    public Object ethCall(Function function) throws IOException {
        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from, pwd).send();

        if (personalUnlockAccount.accountUnlocked()) {
            Transaction transaction = Transaction.createEthCallTransaction(from, contract,
                    FunctionEncoder.encode(function));

            EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();

            List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
                                                             function.getOutputParameters());

            System.out.println("ethCall.getResult() = " + ethCall.getResult());
            System.out.println("getValue = " + decode.get(0).getValue());
            System.out.println("getType = " + decode.get(0).getTypeAsString());

            return decode.get(0).getValue();
        } else {
            throw new PersonalLockException("check ethereum personal Lock");
        }
    }

    public String ethSendTransaction(Function function) throws IOException, InterruptedException, ExecutionException {
        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from, pwd).send();

        if (personalUnlockAccount.accountUnlocked()) {
            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.LATEST).sendAsync().get();

            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            Transaction transaction = Transaction.createFunctionCallTransaction(from, nonce, Transaction.DEFAULT_GAS, null, contract, FunctionEncoder.encode(function));
            EthSendTransaction ethSendTransaction = web3j.ethSendTransaction(transaction).send();
            String transactionHash = ethSendTransaction.getTransactionHash();

            Thread.sleep(5000);

            return transactionHash;
        } else {
            throw new PersonalLockException("check ethereum personal Lock");
        }
    }

    public TransactionReceipt getReceipt(String transactionHash) throws IOException {
        EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).send();
        if(transactionReceipt.getTransactionReceipt().isPresent()) {
            System.out.println("transactionReceipt.getResult().getContractAddress() = " + transactionReceipt.getResult());
        } else {
            System.out.println("transaction complete not yet");
        }
        return transactionReceipt.getResult();
    }

    private static class PersonalLockException extends RuntimeException {
        public PersonalLockException(String msg)
        {
            super(msg);
        }
    }

}
