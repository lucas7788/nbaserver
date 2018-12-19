package com.github.ontio.dao;


import com.github.ontio.model.BetInfo;
import com.github.ontio.model.WithdrawRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component(value = "BetsInfoMapper")
public interface BetsInfoMapper {
    List<Map> selectMyPlaceBetByPage(String address,Integer start, Integer pageSize);
    List<Map> selectMyWithdrawByPage(String address,Integer start, Integer pageSize);
    int insertWithdrawRecord(WithdrawRecord withdrawRecord);
    int insertPlaceBet(BetInfo betInfo);
}
