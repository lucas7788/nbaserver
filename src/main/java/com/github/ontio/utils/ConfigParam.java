/*
 * Copyright (C) 2018 The ontology Authors
 * This file is part of The ontology library.
 *
 * The ontology is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ontology is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with The ontology.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.github.ontio.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @author zhouq
 * @date 2018/2/27
 */

@Service("ConfigParam")
public class ConfigParam {

    @Value("${mainchain.rpc.url}")
    public String MAIN_RPC_URL;

    @Value("${node.amount}")
    public int NODE_AMOUNT;

    @Value("${admin.wallet.file}")
    public String walletFile;

    @Value("${admin.address}")
    public String adminAddr;

    @Value("${admin.password}")
    public String adminPwd;

    @Value("${nba.url}")
    public String nbaUrl;

    @Value("${nba.abi}")
    public String nbaAbi;

    @Value("${nba.contractaddress}")
    public String nbaContractAddress;

    @Value("${gaslimit}")
    public long gaslimit;

    @Value("${gasprice}")
    public long gasprice;
}