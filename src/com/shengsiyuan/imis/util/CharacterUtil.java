package com.shengsiyuan.imis.util;

import org.apache.commons.lang.RandomStringUtils;

public class CharacterUtil {

    public static String randomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
