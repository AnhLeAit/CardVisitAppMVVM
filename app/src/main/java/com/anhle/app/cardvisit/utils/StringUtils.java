package com.anhle.app.cardvisit.utils;

import java.util.UUID;

/**
 * Copyright 2019 (C) Lê Ngọc Anh
 * github: https://github.com/AnhLeAit
 * email: anhle.ait@gmail.com
 *
 * <p>
 * Created on : 12 Mar, 2019
 * Author     : anhle
 * <p>
 * -----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 * -----------------------------------------------------------------------------
 * VERSION     AUTHOR/           DESCRIPTION OF CHANGE
 * OLD/NEW     DATE              RFC NO
 * -----------------------------------------------------------------------------
 * --/1.0    | anhle           | Initial Create.
 *           | 12 Mar, 2019    |
 * ----------|-----------------|------------------------------------------------
 *           | Author          | Defect ID 1/Description
 *           | Date            |
 * ----------|-----------------|------------------------------------------------
 **/
public class StringUtils {

    public static boolean isEmpty(String s) {
        return  (s == null || "".equals(s));
    }

    /**
     * Generate GUID (or UUID) is an acronym for 'Globally Unique Identifier'
     * (or 'Universally Unique Identifier'). It is a 128-bit integer number
     * used to identify resources. The term GUID is generally used by developers
     * working with Microsoft technologies, while UUID is used everywhere else.
     *
     * @return guid
     */
    public static String generateGUID() {
        String guid = UUID.randomUUID().toString().replace("-", "");
        return  guid;
    }

    /**
     * Create Date of birth String
     *
     * @param year
     * @param monthOfYear <0..11>
     * @param dayOfMonth
     * @return
     */
    public static String createDobString(int year, int monthOfYear, int dayOfMonth) {
        return String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year);
    }

}
