package com.mech_serv_mng.services.specifications;

import java.text.MessageFormat;

public abstract class Specs {
    public static String preprocess(String expression){
        return MessageFormat.format("%{0}%",expression);
    }
}
