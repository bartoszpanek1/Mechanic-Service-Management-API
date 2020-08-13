package com.mech_serv_mng.util;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static String sqlLikePreprocess(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }
    public static boolean isEmailCorrect(String email){
        Pattern emailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailRegex.matcher(email);
        return matcher.find();
    }
}
