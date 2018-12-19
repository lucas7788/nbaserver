package com.github.ontio;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.ontio.contract.NbaContract;
import com.github.ontio.dao.NbaMatchMapper;
import com.github.ontio.model.NbaMatch;
import com.github.ontio.smartcontract.neovm.abi.AbiInfo;
import com.github.ontio.utils.ConfigParam;
import com.github.ontio.utils.ConstantParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private ConfigParam configParam;

    @Autowired
    private Environment env;

    @Autowired
    private NbaMatchMapper nbaMatchMapper;

    @Scheduled(fixedRate = 3000)
    public void timerRate() throws Exception {
        String today = getToday();
        logger.info(ConstantParam.CURR_DATE);
        if (!today.equals(ConstantParam.CURR_DATE)){
            Map<String, String> dateMap = getDate();
            if(ConstantParam.CURR_DATE == ""){
                ConstantParam.CURR_DATE = dateMap.get("today");
                //第一次启动程序
                init(dateMap.get("tomorrow"));
            }
            String yesterday = dateMap.get("yesterday");
            //用于测试
            yesterday = dateMap.get("tomorrow");
            List<Map> yesterdayMatch = nbaMatchMapper.selectNbaMatchByDate(yesterday);
            if(yesterdayMatch != null && yesterdayMatch.size() != 0){
                //录入昨天的结果
                NbaContract.callOracle(yesterday);
                logger.info("invoke contract functions:" + "callOracle, " + "date: " + yesterday);
                NbaContract.setResult(yesterday);
                logger.info("invoke contract functions:" + "setResult, " + "date: " + yesterday);
            }
            //结束今天的比赛
            today = yesterday;
            List<Map> todayMatch = nbaMatchMapper.selectNbaMatchByDate(today);
            if(todayMatch != null && todayMatch.size()!=0){
                NbaContract.endBet(today);
                logger.info("invoke contract functions:" + "endBet, " + "date: " + today);
                nbaMatchMapper.updateMatchStateByDate("end", today);
            }
            //录入明天的比赛
            inputMatch(dateMap.get("tomorrow"));
        }
    }

    private void inputMatch(String date) throws Exception {
        List<Map> matchDate = nbaMatchMapper.selectNbaMatchByDate(date);
        if (matchDate!=null && matchDate.size() !=0){
            return;
        }
        //查询比赛信息
        String match = getMatch();
        JSONObject matchJson = JSONObject.parseObject(match);
        if (matchJson.get("games") == null || ((JSONArray)matchJson.get("games")).size()==0){
            logger.info("no match, " + date);
            return;
        }
        //读取合约abi文件
        NbaContract.readAbi();
        //输入比赛
        for(Object game : (JSONArray)matchJson.get("games")) {
            String gameId = ((JSONObject)game).getString("gameId");
            String hteamId = ((JSONObject)((JSONObject)game).get("hTeam")).getString("teamId");
            String vteamId = ((JSONObject)((JSONObject)game).get("vTeam")).getString("teamId");
            String txhash = NbaContract.inputMatch(date, gameId, hteamId, vteamId);
            NbaMatch nbaMatch = new NbaMatch();
            nbaMatch.setTxHash(txhash);
            nbaMatch.setDate(date);
            nbaMatch.setGameID(gameId);
            nbaMatch.sethTeamID(hteamId);
            nbaMatch.setvTeamID(vteamId);
            nbaMatch.setState("on");
            nbaMatchMapper.insertNbaMatch(nbaMatch);
        }
    }

    private String getMatch() throws IOException {
        URL u = new URL(ConstantParam.NBA_URL);
        HttpURLConnection http = (HttpURLConnection) u.openConnection();
        http.setConnectTimeout(50000);
        http.setReadTimeout(50000);
        http.setRequestMethod("GET");
        http.setRequestProperty("Content-Type","application/json");
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        StringBuilder sb = new StringBuilder();
        String DEFAULT_CHARSET = "UTF-8";
        try (InputStream is = http.getInputStream()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, DEFAULT_CHARSET))) {
                String str = null;
                while((str = reader.readLine()) != null) {
                    sb.append(str);
                    str = null;
                }
            }
        }
        if (http != null) {
            http.disconnect();
        }
        return sb.toString();
    }

    private void init(String tomorrow) throws Exception {
        ConstantParam.ONT_SDK = OntSdk.getInstance();
        ConstantParam.ONT_SDK.setRpc(configParam.MAIN_RPC_URL);
        for (int i = 0; i < configParam.NODE_AMOUNT; i++) {
            ConstantParam.MAINCHAIN_RPCLIST.add(env.getProperty("mainchain.rpc.url_" + i));
        }
        ConstantParam.NBA_CONTRACT_ADDRESS = configParam.nbaContractAddress;
        ConstantParam.ONT_SDK.openWalletFile(configParam.walletFile);
        ConstantParam.ABI_PATH = configParam.nbaAbi;
        ConstantParam.ADMIN_ACCOUNT = ConstantParam.ONT_SDK.getWalletMgr().getAccount(configParam.adminAddr,configParam.adminPwd);
        ConstantParam.NBA_URL = configParam.nbaUrl.replace("yyyyMMdd", tomorrow);
    }

    public static String getToday(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return sdfDate.format(date);
    }
    public static Map<String, String> getDate(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        Date tomorrow = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        Date afterTomorrow = calendar.getTime();
        Map<String, String> map =new HashMap();
        map.put("yesterday", sdfDate.format(yesterday));
        map.put("today", sdfDate.format(today));
        map.put("tomorrow", sdfDate.format(tomorrow));
        map.put("afterTomorrow", sdfDate.format(afterTomorrow));
        return map;
    }
}
