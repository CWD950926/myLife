package com.util.idmaker;

import java.util.Date;

public class IdUtil {


    /**
     * 生成15位的长整型；超过15位js的解析有精度问题
     * @return
     */
    public static long genId() {
        return GenLong15Util.genLong15();
    }

    public static Long timeStamp2Id(Long secondTimeStamp) {
        return GenLong15Util.timeStamp2Id(secondTimeStamp);
    }

    /**
     * 日期转id
     * @param date
     * @return
     */
    public static Long date2Id(Date date) {
        return GenLong15Util.timeStamp2Id(date.getTime()/1000);
    }

    /**
     * id转秒级时间戳
     * @param id
     * @return
     */
    public static Long id2TimeStamp(long id) {
        return GenLong15Util.id2TimeStamp(id);
    }



}
