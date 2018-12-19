package com.github.ontio.controller;


import com.alibaba.fastjson.JSON;
import com.github.ontio.model.BetInfo;
import com.github.ontio.model.WithdrawRecord;
import com.github.ontio.paramBean.Result;
import com.github.ontio.service.impl.BetsHandleServiceImpl;
import com.github.ontio.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/api/v1/bets")
public class BetsController {

    private static final Logger logger = LoggerFactory.getLogger(BetsController.class);

    private final String CLASS_NAME = this.getClass().getSimpleName();

    private static final String VERSION = "1.0";

    @Autowired
    private BetsHandleServiceImpl betsHandleService;


    /**
     * query the last few blocks
     *
     * @return
     */
    @RequestMapping(value = "/getmyplacebetbypage/{address}/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    @ResponseBody
    public Result getMyBetsByPage(@PathVariable("address") String address, @PathVariable("pageSize") int pageSize,
                                  @PathVariable("pageNumber") int pageNumber) {

        logger.info("########{}.{} begin...", CLASS_NAME, Helper.currentMethod());

        Result rs = betsHandleService.getMyPlaceBetByPage(address, pageSize,pageNumber);
        return rs;
    }

    /**
     * 查询提现记录
     *
     * @return
     */
    @RequestMapping(value = "/getmywithdrawbypage/{address}/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    @ResponseBody
    public Result getHugeWins(@PathVariable("address") String address,@PathVariable("pageSize") int pageSize,
                              @PathVariable("pageNumber") int pageNumber) {

        logger.info("########{}.{} begin...", CLASS_NAME, Helper.currentMethod());

        Result rs = betsHandleService.getMyWithdrawByPage(address, pageSize,pageNumber);
        return rs;
    }

    /**
     * 保存withdraw 记录
     *
     * @return
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ResponseBody
    public Result saveWithdraw(@RequestBody String reqParam) {

        logger.info("########{}.{} begin...", CLASS_NAME, Helper.currentMethod());

        WithdrawRecord withdrawRecord = JSON.parseObject(reqParam, WithdrawRecord.class);
        Result rs = betsHandleService.insertWithdrawRecord(withdrawRecord);
        return rs;
    }

    /**
     * 保存palcebet 记录
     *
     * @return
     */
    @RequestMapping(value = "/placebet", method = RequestMethod.POST)
    @ResponseBody
    public Result savePlaceBet(@RequestBody String reqParam) {

        logger.info("########{}.{} begin...", CLASS_NAME, Helper.currentMethod());

        BetInfo betInfo = JSON.parseObject(reqParam, BetInfo.class);
        Result rs = betsHandleService.insertPlaceBet(betInfo);
        return rs;
    }

}
