package com.nitesh.spark.utils;

import android.util.Patterns;

/**
 * Created by Nitesh Singh(killer) on 9/1/2016.
 */
public class ValidationUtils {

    /**
     * This is used to check the given URL is valid or not.
     * @param url
     * @return boolean
     */
    public static boolean isValidWebUrl(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

    /**
     * This is used to check the given email is valid or not.
     * @param email
     * @return boolean
     */
    public static boolean isValidEmailAddress(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * This is used to check the given email is valid or not.
     * @param phone
     * @return boolean
     */
    public static boolean isValidPhoneNumber(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }
}
