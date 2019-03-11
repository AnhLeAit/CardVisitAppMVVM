package com.anhle.app.cardvisit;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
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
@RunWith(AndroidJUnit4.class)
public class CardVisitApplicationTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.anhle.app.cardvisit", appContext.getPackageName());
    }
}