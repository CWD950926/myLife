package com.util.poster;

/**
 * @author Admin
 */
public class PhoneUtil {

    public static boolean isPhone(String phone) {
        //  String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        //存在一些校验规则校验不了的手机号,如 19138276846

        return null != phone && phone.length() == 11;
    }


}
