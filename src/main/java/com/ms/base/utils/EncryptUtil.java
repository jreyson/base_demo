package com.ms.base.utils;

import java.util.Random;

/**
 * @author JamelHuang
 * @date 2018/3/6
 */
public class EncryptUtil {
    public static String generateIdentifierBySize(int size) {
        Random random = new Random();
        String identifier;
        identifier = "";
        for (int i = 0; i < size; i++) {
            int num = random.nextInt(10);
            identifier += num;
        }
        return identifier;
    }
}
