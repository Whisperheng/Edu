/*
package com.hank_01.edu.common.util;

import com.juqitech.zb.common.framework.locale.enums.CurrencyEnum;
import com.juqitech.zb.common.framework.locale.money.Money;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Currency;


*/
/**
 * Money Process Utility
 * <p>
 * Created by Michael.Zhong on 17/10/31.
 *//*

public class MoneyUtil {

    public static final Money ZERO = new Money(0);

    */
/**
     * 是否金额格式
     *
     * @param str
     * @return
     *//*

    public static boolean isMoneyFormat(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            char chr = str.charAt(i);
            if (i == 0 && chr == '-') {
                continue;
            }
            if (!Character.isDigit(chr) && chr != '.') {
                return false;
            }
        }

        return true;
    }

    */
/**
     * moneyCent convert to money
     *
     * @param moneyCent
     * @return
     *//*

    public static Money getMoneyByCent(Long moneyCent) {
        if (moneyCent != null) {
            Money money = new Money();
            money.setCent(moneyCent);
            return money;
        }
        return null;
    }

    */
/**
     * moneyCent convert to money with given currency.
     *
     * @param moneyCent
     * @param currency
     * @return
     *//*

    public static Money getMoneyByCent(Long moneyCent, Currency currency) {
        if (currency == null) {
            currency = Currency.getInstance(Money.DEFAULT_CURRENCY_CODE);
        }
        if (moneyCent != null) {
            Money money = new Money(0, 0, currency);
            money.setCent(moneyCent);
            return money;
        }
        return null;
    }

    */
/**
     * convert money string to Money Type
     *
     * @param strMoney
     * @return
     *//*

    public static Money getMoneyByCent(String strMoney) {
        if (StringUtils.isNotBlank(strMoney)) {
            Money money = new Money();
            long lMoney = round(new Double(strMoney));
            money.setCent(lMoney);
            return money;
        } else if (StringUtils.isBlank(strMoney)) {
            return null;
        } else {
            throw new RuntimeException("Invalid Money Format。value：" + StringUtils.defaultIfEmpty("", strMoney));
        }
    }


    */
/**
     * 按照美元的币种精度进行四舍五入
     *
     * @param cent 金额，单位：分
     *//*

    public static long round(String cent) {
        return round(Double.valueOf(cent));
    }

    */
/**
     * 按照人民币的币种精度进行四舍五入
     *
     * @param cent 金额，单位：分
     *//*

    public static long round(double cent) {
        return round(cent, CurrencyEnum.TWD);
    }

    */
/**
     * 按照币种精度进行四舍五入
     *
     * @param cent     金额，单位：分
     * @param currency 币种
     *//*

    public static long round(double cent, CurrencyEnum currency) {
        return round(cent, currency.getFractionDigits());
    }

    */
/**
     * 按照币种精度进行四舍五入
     *
     * @param cent     金额，单位：分
     * @param currency 币种
     *//*

    public static long round(long cent, CurrencyEnum currency) {
        return round(cent, currency.getFractionDigits());
    }

    */
/**
     * 按照币种精度进行四舍五入
     *
     * @param cent                     金额，单位：分
     * @param fractionDigitsOfCurrency 币种精度
     *//*

    public static long round(long cent, int fractionDigitsOfCurrency) {
        long multiple = exponent(10, 2 - fractionDigitsOfCurrency);
        BigDecimal tempNum = new BigDecimal((double) cent / multiple);
        tempNum = tempNum.setScale(0, BigDecimal.ROUND_HALF_UP);
        long result = tempNum.longValue() * multiple;

        return result;
    }

    */
/**
     * 按照币种精度进行四舍五入
     *
     * @param cent                     金额，单位：分
     * @param fractionDigitsOfCurrency 币种精度
     *//*

    public static long round(double cent, int fractionDigitsOfCurrency) {
        long multiple = exponent(10, 2 - fractionDigitsOfCurrency);
        BigDecimal tempNum = new BigDecimal(cent / multiple);
        tempNum = tempNum.setScale(0, BigDecimal.ROUND_HALF_UP);
        long result = tempNum.longValue() * multiple;

        return result;
    }

    */
/**
     * 计算指数幂（x的n次方）
     *
     * @param bottom   底数x
     * @param exponent 指数n
     *//*

    public static long exponent(int bottom, int exponent) {
        if (bottom == 0) {
            return 0;
        }
        if (exponent <= 0) {
            return 1;
        }

        long result = bottom;
        for (int i = 0; i < exponent - 1; i++) {
            result *= bottom;
        }

        return result;
    }


    public static CurrencyEnum currencyToEnum(Currency currency) {
        return currency == null ? null : CurrencyEnum.getEnumByCurrencyEnglishAbbr(currency.getSymbol());
    }

    public static Money calcaluteMoneyByRatio(Long cent, Double ratio){
        if (cent == null){return null;}
        if (ratio==null||ratio<0||ratio>1){
            ratio = 1D;
        }
        return MoneyUtil.getMoneyByCent(MoneyUtil.round(MoneyUtil.getMoneyByCent(cent).multiply(ratio).getCent(), 0));
    }
}

*/
