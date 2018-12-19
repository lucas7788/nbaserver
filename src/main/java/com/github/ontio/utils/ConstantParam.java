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

import com.github.ontio.OntSdk;
import com.github.ontio.account.Account;
import com.github.ontio.smartcontract.neovm.abi.AbiInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouq
 * @version 1.0
 * @date 2018/2/27
 */
public class ConstantParam {

    public static String CURR_DATE = "";
    public static OntSdk ONT_SDK;

    public static Account ADMIN_ACCOUNT;

    public static List MAINCHAIN_RPCLIST = new ArrayList();
    public static int MAINNODE_INDEX = 0;

    public static String NBA_URL = "";
    public static AbiInfo NBA_ABI_INFO = null;
    public static String ABI_PATH = "";
    public static String NBA_CONTRACT_ADDRESS = "";
    public static long GAS_LIMIT = 2000000;
    public static long GAS_PRICE = 500;

    public static final String YESTERDAY = "yesterday";
    public static final String TODAY = "today";
    public static final String TOMORROW = "yesterday";
    public static final String AFTER_TOMORROW = "yesterday";

}
