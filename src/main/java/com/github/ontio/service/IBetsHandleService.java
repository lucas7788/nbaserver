package com.github.ontio.service;

import com.github.ontio.model.BetInfo;
import com.github.ontio.model.WithdrawRecord;
import com.github.ontio.paramBean.Result;

public interface IBetsHandleService {
    Result getMyPlaceBetByPage(String address, Integer pageSize, Integer pageNumber);
    Result getMyWithdrawByPage(String address, Integer pageSize, Integer pageNumber);
    Result insertPlaceBet(BetInfo betInfo);
    Result insertWithdrawRecord(WithdrawRecord withdrawRecord);

}
