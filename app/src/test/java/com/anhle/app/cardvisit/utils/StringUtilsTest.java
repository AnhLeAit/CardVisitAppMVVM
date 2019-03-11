package com.anhle.app.cardvisit.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
public class StringUtilsTest {

    @Test
    public void isEmpty() {
        assertEquals(true, StringUtils.isEmpty(null));
        assertEquals(true, StringUtils.isEmpty(""));
        assertEquals(false, StringUtils.isEmpty("AAAAAA"));
    }

    @Test
    public void generateGUID() {
        assertEquals(false, StringUtils.generateGUID() == null);
        assertEquals(false, "".equals(StringUtils.generateGUID()));
        assertEquals(32, StringUtils.generateGUID().length());

        // Test Generate GUID between 2 generate times must be different.
        String guid1 = StringUtils.generateGUID();
        String guid2 = StringUtils.generateGUID();
        assertFalse("Test generate GUID between 2 generate times must be different.", guid1.equals(guid2));
    }

    @Test
    public void createDobString() {
        assertEquals("02/12/1992", StringUtils.createDobString(1992, 11, 2));
        assertEquals("24/01/1992", StringUtils.createDobString(1992, 0, 24));
        assertEquals("01/01/1992", StringUtils.createDobString(1992, 0, 1));
        assertEquals("12/12/1992", StringUtils.createDobString(1992, 11, 12));

    }
}