package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String convertDate(String field) {
        Pattern pattern = Pattern.compile("(\\d{4})\\-(\\d{2})\\-(\\d{2})");
        Matcher matcher = pattern.matcher(field);
        if(matcher.matches()){
            String year = matcher.group(1);
            String month = matcher.group(2);
            String day = matcher.group(3);
            field = matcher.group(3)+"/"+matcher.group(2)+"/"+matcher.group(1);
            return field;
        }
        return field;
    }
}
