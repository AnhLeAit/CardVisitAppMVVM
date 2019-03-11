package com.anhle.app.cardvisit.service.room;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

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
public class CardDaoTest {

    private CardDao cardDao;
    private AppDatabase db;

    @Before
    public void initDb() {
        Context context = InstrumentationRegistry.getContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();
        cardDao = db.cardDao();
    }

    @After
    public void closeDb() throws IOException {
        db.clearAllTables();
        db.close();
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void findByGuid() {
        CardEntity CARD = new CardEntity();
        CARD.guid = "guid";
        CARD.name = "name";

        cardDao.insertAll(CARD);

        cardDao.findByGuid(CARD.guid)
                .test()
                .assertValue(cardEntity -> cardEntity.guid != null && cardEntity.guid.equals(CARD.guid))
                .assertValue(cardEntity -> cardEntity.name != null && cardEntity.name.equals(CARD.name))
                .assertValue(cardEntity -> {
                    // Insert new CardEntity the 'id' must be auto increase and > 0
                    return cardEntity.id != null && cardEntity.id > 0;
                })
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void getLastCard() {
        CardEntity c1 = new CardEntity(); c1.guid = "guid1";
        CardEntity c2 = new CardEntity(); c2.guid = "guid2";
        CardEntity c3 = new CardEntity(); c3.guid = "guid3";

        cardDao.insertAll(c1);
        cardDao.insertAll(c2);
        cardDao.insertAll(c3);

        cardDao.getLastCard()
                .test()
                .assertValueCount(1)
                .assertValue(cardEntity -> (cardEntity.guid != null && cardEntity.guid.equals(c3.guid)))
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void insertAllAndGetAll() {
        // Test add 3 cards and get 3 cards back
        CardEntity c1 = new CardEntity(); c1.guid = "guid1";
        CardEntity c2 = new CardEntity(); c2.guid = "guid2";
        CardEntity c3 = new CardEntity(); c3.guid = "guid3";

        cardDao.insertAll(c1);
        cardDao.insertAll(c2);
        cardDao.insertAll(c3);

        cardDao.getAll()
                .test()
                .assertValueCount(1)
                .assertValue(cardEntities -> {
                    // Sure for size is 3
                    boolean sizeOK = cardEntities != null && cardEntities.size() == 3;
                    // Sure that insert c1 -> c2 -> c3 but get c3 -> c2 -> c1
                    boolean c1OK = cardEntities.get(0).guid != null && cardEntities.get(0).guid.equals(c3.guid);
                    boolean c2OK = cardEntities.get(1).guid != null && cardEntities.get(1).guid.equals(c2.guid);
                    boolean c3OK = cardEntities.get(2).guid != null && cardEntities.get(2).guid.equals(c1.guid);
                    return sizeOK && c1OK && c2OK && c3OK;
                })
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}