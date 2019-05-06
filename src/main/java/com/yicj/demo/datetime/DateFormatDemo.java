package com.yicj.demo.datetime;

import java.text.DateFormat;
import java.util.Date;
import static java.lang.System.out;
import static java.text.DateFormat.*;

public class DateFormatDemo {

    public static void main(String[] args) {

        Date curDate = new Date() ;
        dateInstanceDemo(curDate);
        timeInstanceDemo(curDate) ;
        dateTimeInstanceDemo(curDate) ;

    }


    static void dateInstanceDemo(Date date){
        out.println("getDateInstance() demo");
        out.printf("\tLong: %s\n", getDateInstance(LONG).format(date)) ;
        out.printf("\tMedium: %s\n",getDateInstance(MEDIUM).format(date)) ;
        out.printf("\tShort: %s\n",getDateInstance(SHORT).format(date)) ;
    }

    static void timeInstanceDemo(Date date){
        out.println("getTimeInstance() demo");
        out.printf("\tLong: %s\n",getTimeInstance(LONG).format(date)) ;
        out.printf("\tMedium: %s\n",getTimeInstance(MEDIUM).format(date)) ;
        out.printf("\tShort: %s\n",getTimeInstance(SHORT).format(date)) ;
    }

    static void dateTimeInstanceDemo(Date date){
        out.println("getDateTimeInstance() demo");
        out.printf("\tLong: %s\n",getDateTimeInstance(LONG,LONG).format(date)) ;
        out.printf("\tMedium: %s\n",getDateTimeInstance(MEDIUM,MEDIUM).format(date));
        out.printf("\tShort: %s\n",getDateTimeInstance(SHORT,SHORT).format(date)) ;
    }
}
