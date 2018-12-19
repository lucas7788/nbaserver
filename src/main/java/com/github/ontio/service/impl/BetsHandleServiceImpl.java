package com.github.ontio.service.impl;

import com.github.ontio.dao.BetsInfoMapper;
import com.github.ontio.model.BetInfo;
import com.github.ontio.model.WithdrawRecord;
import com.github.ontio.paramBean.Result;
import com.github.ontio.service.IBetsHandleService;
import com.github.ontio.utils.ErrorInfo;
import com.github.ontio.utils.Helper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("BetsHandleService")
@MapperScan("com.github.ontio.dao")
public class BetsHandleServiceImpl implements IBetsHandleService {

    private static final String VERSION = "1.0";

    @Autowired
    private BetsInfoMapper betsInfoMapper;


    @Override
    public Result getMyPlaceBetByPage(String address,Integer pageSize, Integer pageNumber) {
        int start = pageSize * (pageNumber - 1) < 0 ? 0 : pageSize * (pageNumber - 1);
        List<Map> hugeWinsTop= betsInfoMapper.selectMyPlaceBetByPage(address,start,pageSize);
        Map<String, Object> rs = new HashMap<>();
        rs.put("Result", hugeWinsTop);
        return Helper.result("getmyplacebetbypage", ErrorInfo.SUCCESS.code(),ErrorInfo.SUCCESS.desc(),
                VERSION,rs);
    }

    @Override
    public Result getMyWithdrawByPage(String address,Integer pageSize, Integer pageNumber) {
        int start = pageSize * (pageNumber - 1) < 0 ? 0 : pageSize * (pageNumber - 1);
        List<Map> hugeWinsTop= betsInfoMapper.selectMyWithdrawByPage(address, start, pageSize);
        Map<String, Object> rs = new HashMap<>();
        rs.put("Result", hugeWinsTop);
        return Helper.result("gethugewinstopbynum", ErrorInfo.SUCCESS.code(),ErrorInfo.SUCCESS.desc(),
                VERSION,rs);
    }

    @Override
    public Result insertWithdrawRecord(WithdrawRecord withdrawRecord) {
        int res = betsInfoMapper.insertWithdrawRecord(withdrawRecord);
        Map<String, Object> rs = new HashMap<>();
        rs.put("Result", res);
        return Helper.result("withdraw", ErrorInfo.SUCCESS.code(),ErrorInfo.SUCCESS.desc(),
                VERSION,rs);
    }

    @Override
    public Result insertPlaceBet(BetInfo betInfo) {
        int res = betsInfoMapper.insertPlaceBet(betInfo);
        Map<String, Object> rs = new HashMap<>();
        rs.put("Result", res);
        return Helper.result("placebet", ErrorInfo.SUCCESS.code(),ErrorInfo.SUCCESS.desc(),
                VERSION,rs);
    }

}
