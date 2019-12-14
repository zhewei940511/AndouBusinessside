package com.zskjprojectj.andoubusinessside.utils;

import java.text.DecimalFormat;

public class FormatUtil {

    public static String getMoneyString(float money) {
        DecimalFormat df = new DecimalFormat("#0.00 ");
        return df.format(money);
    }
}
