package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
  
    private static DateProvider instance = null;

    //Returns a instance to the DateProvider object
    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    //Obtains the current date object
    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
