package com.anhle.app.cardvisit.service.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
public class CardTest {

    private Card card;

    @Before
    public void setUp() throws Exception {
        card = new Card();
    }

    @After
    public void tearDown() throws Exception {
        card = null;
    }

    @Test
    public void getId() {
        card.setId(null);
        assertEquals(card.getId(), null);

        card.setId(1l);
        assertTrue("Expect getId: 1",card.getId() != null && card.getId() == 1l);
    }

    @Test
    public void setId() {
        Card returnCard1 = card.setId(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getId(), null);

        Card returnCard2 = card.setId(1l);
        assertNotNull(returnCard2);
        assertTrue("Expect setId: 1",returnCard2.getId() != null && returnCard2.getId() == 1l);
    }

    @Test
    public void getGuid() {
        card.setGuid(null);
        assertEquals(card.getGuid(), null);

        card.setGuid("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getGuid()));
    }

    @Test
    public void setGuid() {
        Card returnCard1 = card.setGuid(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getGuid(), null);

        Card returnCard2 = card.setGuid("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getGuid()));
    }

    @Test
    public void getAvatar() {
        card.setAvatar(null);
        assertEquals(card.getAvatar(), null);

        card.setAvatar("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getAvatar()));
    }

    @Test
    public void setAvatar() {
        Card returnCard1 = card.setAvatar(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getAvatar(), null);

        Card returnCard2 = card.setAvatar("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getAvatar()));
    }

    @Test
    public void getName() {
        card.setName(null);
        card.setFirstName(null);
        card.setLastName(null);
        assertEquals(card.getName(), null);

        card.setName("NNNNN");
        assertEquals(card.getName(), "NNNNN");

        card.setName(null);
        card.setFirstName("FFFFF");
        card.setLastName(null);
        assertEquals(card.getName(), "FFFFF");

        card.setName(null);
        card.setFirstName(null);
        card.setLastName("LLLLL");
        assertEquals(card.getName(), "LLLLL");

        card.setName(null);
        card.setFirstName("FFFFF");
        card.setLastName("LLLLL");
        assertEquals(card.getName(), "FFFFF LLLLL");

    }

    @Test
    public void setName() {
        card.setName(null);
        card.setFirstName("FFFFF");
        card.setLastName("LLLLL");
        assertEquals(card.getName(), "FFFFF LLLLL");

        Card returnCard2 = card.setName("NNNNN");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: NNNNN","NNNNN".equals(returnCard2.getName()));
    }

    @Test
    public void getFirstName() {
        card.setFirstName(null);
        assertEquals(card.getFirstName(), null);

        card.setFirstName("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getFirstName()));
    }

    @Test
    public void setFirstName() {
        Card returnCard1 = card.setFirstName(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getFirstName(), null);

        Card returnCard2 = card.setFirstName("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getFirstName()));
    }

    @Test
    public void getLastName() {
        card.setLastName(null);
        assertEquals(card.getLastName(), null);

        card.setLastName("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getLastName()));
    }

    @Test
    public void setLastName() {
        Card returnCard1 = card.setLastName(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getLastName(), null);

        Card returnCard2 = card.setLastName("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getLastName()));
    }

    @Test
    public void getMobile() {
        card.setMobile(null);
        assertEquals(card.getMobile(), null);

        card.setMobile("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getMobile()));
    }

    @Test
    public void setMobile() {
        Card returnCard1 = card.setMobile(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getMobile(), null);

        Card returnCard2 = card.setMobile("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getMobile()));
    }

    @Test
    public void getAddress() {
        card.setAddress(null);
        assertEquals(card.getAddress(), null);

        card.setAddress("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getAddress()));
    }

    @Test
    public void setAddress() {
        Card returnCard1 = card.setAddress(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getAddress(), null);

        Card returnCard2 = card.setAddress("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getAddress()));
    }

    @Test
    public void getCompany() {
        card.setCompany(null);
        assertEquals(card.getCompany(), null);

        card.setCompany("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getCompany()));
    }

    @Test
    public void setCompany() {
        Card returnCard1 = card.setCompany(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getCompany(), null);

        Card returnCard2 = card.setCompany("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getCompany()));
    }

    @Test
    public void getPosition() {
        card.setPosition(null);
        assertEquals(card.getPosition(), null);

        card.setPosition("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getPosition()));
    }

    @Test
    public void setPosition() {
        Card returnCard1 = card.setPosition(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getPosition(), null);

        Card returnCard2 = card.setPosition("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getPosition()));
    }

    @Test
    public void getGender() {
        card.setGender(null);
        assertEquals(card.getGender(), null);

        card.setGender("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getGender()));
    }

    @Test
    public void setGender() {
        Card returnCard1 = card.setGender(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getGender(), null);

        Card returnCard2 = card.setGender("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getGender()));
    }

    @Test
    public void getDob() {
        card.setDob(null);
        assertEquals(card.getDob(), null);

        card.setDob("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getDob()));
    }

    @Test
    public void setDob() {
        Card returnCard1 = card.setDob(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getDob(), null);

        Card returnCard2 = card.setDob("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getDob()));
    }

    @Test
    public void getAbout() {
        card.setAbout(null);
        assertEquals(card.getAbout(), null);

        card.setAbout("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getAbout()));
    }

    @Test
    public void setAbout() {
        Card returnCard1 = card.setAbout(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getAbout(), null);

        Card returnCard2 = card.setAbout("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getAbout()));
    }

    @Test
    public void getEmail() {
        card.setEmail(null);
        assertEquals(card.getEmail(), null);

        card.setEmail("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(card.getEmail()));
    }

    @Test
    public void setEmail() {
        Card returnCard1 = card.setEmail(null);
        assertNotNull(returnCard1);
        assertEquals(returnCard1.getEmail(), null);

        Card returnCard2 = card.setEmail("BBBBB");
        assertNotNull(returnCard2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCard2.getEmail()));
    }
}