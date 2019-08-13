package com.example.springboot1.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {

    public static List<String > printDate(){

        Date date = new Date(System.currentTimeMillis());
        GregorianCalendar gc =new GregorianCalendar();
        SimpleDateFormat sf=new SimpleDateFormat("MM-dd");

        List<String> dates = new ArrayList<>();
        gc.setTime(date);
        for (int i=6; i>=0; i--){

            gc.add(5,-1);//减一天
            dates.add(sf.format(gc.getTime()));
        }
        return dates;
    }
}
