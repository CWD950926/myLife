package com.util.idmaker;

public class TraceIdUtil {


    /**
     * 生成15位的长整型；超过15位js的解析有精度问题
     * @return
     */
    public static String getId() {
        return GenLong19Util.genString19();
    }


}
