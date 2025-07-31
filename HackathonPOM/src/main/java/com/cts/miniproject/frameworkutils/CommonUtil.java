package com.cts.miniproject.frameworkutils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtil {
    public static String getCurrentDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
    }
    public static void sureWait(int seconds){

        try{
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }


}
