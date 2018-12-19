package com.github.ontio.contract;

import com.alibaba.fastjson.JSON;
import com.github.ontio.common.Helper;
import com.github.ontio.core.transaction.Transaction;
import com.github.ontio.network.exception.ConnectorException;
import com.github.ontio.network.exception.RpcException;
import com.github.ontio.smartcontract.neovm.abi.AbiFunction;
import com.github.ontio.smartcontract.neovm.abi.AbiInfo;
import com.github.ontio.smartcontract.neovm.abi.BuildParams;
import com.github.ontio.utils.ConstantParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NbaContract {

    private static final Logger logger = LoggerFactory.getLogger(NbaContract.class);

    public static void readAbi() throws IOException {
        File file = new File(ConstantParam.ABI_PATH);
        if(!file.exists()){
            logger.info("nba abi not existed "+ConstantParam.ABI_PATH);
            return;
        }
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String text = new String(bytes);
        ConstantParam.NBA_ABI_INFO = JSON.parseObject(text, AbiInfo.class);
    }

    public static String inputMatch(String date, String gameID, String hTeamID,String vTeamID) throws Exception {
        if(ConstantParam.NBA_ABI_INFO == null){
            readAbi();
        }
        AbiFunction func = ConstantParam.NBA_ABI_INFO.getFunction("inputMatch");
        func.setParamsValue(date, gameID, hTeamID, vTeamID);
        byte[] params = BuildParams.serializeAbiFunction(func);
        Transaction tx = ConstantParam.ONT_SDK.vm().makeInvokeCodeTransaction(Helper.reverse(ConstantParam.NBA_CONTRACT_ADDRESS),null,params,
                ConstantParam.ADMIN_ACCOUNT.getAddressU160().toBase58(),ConstantParam.GAS_LIMIT,ConstantParam.GAS_PRICE);
        ConstantParam.ONT_SDK.addSign(tx, ConstantParam.ADMIN_ACCOUNT);
        int tryTime = 0;
        while (true){
            if(tryTime == 3){
                break;
            }
            tryTime++;
            try {
                ConstantParam.ONT_SDK.getConnect().sendRawTransactionSync(Helper.toHexString(tx.toArray()));
                break;
            } catch (ConnectorException e) {
                logger.info("Exception,invoke contract function: inputMatch," + e.getMessage());
                Thread.sleep(6000);
                continue;
            }
        }
        return tx.hash().toHexString();
    }

    public static String callOracle(String date) throws Exception {
        if(ConstantParam.NBA_ABI_INFO == null){
            readAbi();
        }
        AbiFunction func = ConstantParam.NBA_ABI_INFO.getFunction("callOracle");
        func.setParamsValue(date);
        byte[] params = BuildParams.serializeAbiFunction(func);
        Transaction tx = ConstantParam.ONT_SDK.vm().makeInvokeCodeTransaction(Helper.reverse(ConstantParam.NBA_CONTRACT_ADDRESS),null,params,
                ConstantParam.ADMIN_ACCOUNT.getAddressU160().toBase58(),ConstantParam.GAS_LIMIT,ConstantParam.GAS_PRICE);
        ConstantParam.ONT_SDK.addSign(tx, ConstantParam.ADMIN_ACCOUNT);
        int tryTime = 0;
        while (true){
            if(tryTime == 3){
                break;
            }
            tryTime++;
            try {
                ConstantParam.ONT_SDK.getConnect().sendRawTransactionSync(Helper.toHexString(tx.toArray()));
                break;
            }catch (ConnectorException e){
                logger.info("Exception,invoke contract function: callOracle," + e.getMessage());
                Thread.sleep(6000);
                continue;
            }
        }

        return tx.hash().toHexString();
    }

    public static String setResult(String date) throws Exception {
        if(ConstantParam.NBA_ABI_INFO == null){
            readAbi();
        }
        AbiFunction func = ConstantParam.NBA_ABI_INFO.getFunction("setResult");
        func.setParamsValue(date);
        byte[] params = BuildParams.serializeAbiFunction(func);
        Transaction tx = ConstantParam.ONT_SDK.vm().makeInvokeCodeTransaction(Helper.reverse(ConstantParam.NBA_CONTRACT_ADDRESS),null,params,
                ConstantParam.ADMIN_ACCOUNT.getAddressU160().toBase58(),ConstantParam.GAS_LIMIT,ConstantParam.GAS_PRICE);
        ConstantParam.ONT_SDK.addSign(tx, ConstantParam.ADMIN_ACCOUNT);
        int tryTime = 0;
        while (true){
            if(tryTime == 3){
                break;
            }
            tryTime++;
            try {
                ConstantParam.ONT_SDK.getConnect().sendRawTransactionSync(Helper.toHexString(tx.toArray()));
                break;
            }catch (ConnectorException e){
                logger.info("Exception,invoke contract function: setResult," + e.getMessage());
                Thread.sleep(6000);
                continue;
            }
        }
        return tx.hash().toHexString();
    }

    public static String endBet(String date) throws Exception {
        if(ConstantParam.NBA_ABI_INFO == null){
            readAbi();
        }
        AbiFunction func = ConstantParam.NBA_ABI_INFO.getFunction("endBet");
        func.setParamsValue(date);
        byte[] params = BuildParams.serializeAbiFunction(func);
        Transaction tx = ConstantParam.ONT_SDK.vm().makeInvokeCodeTransaction(Helper.reverse(ConstantParam.NBA_CONTRACT_ADDRESS),null,params,
                ConstantParam.ADMIN_ACCOUNT.getAddressU160().toBase58(),ConstantParam.GAS_LIMIT,ConstantParam.GAS_PRICE);
        ConstantParam.ONT_SDK.addSign(tx, ConstantParam.ADMIN_ACCOUNT);
        int tryTime = 0;
        while (true){
            if(tryTime == 3){
                break;
            }
            tryTime++;
            try {
                ConstantParam.ONT_SDK.getConnect().sendRawTransactionSync(Helper.toHexString(tx.toArray()));
                break;
            }catch (ConnectorException e){
                logger.info("Exception,invoke contract function: endBet" + e.getMessage());
                Thread.sleep(6000);
                continue;
            }
        }
        return tx.hash().toHexString();
    }
}
