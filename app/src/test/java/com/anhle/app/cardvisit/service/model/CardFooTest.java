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
public class CardFooTest {

    private CardFoo cardFoo;

    @Before
    public void setUp() throws Exception {
        cardFoo = new CardFoo();
    }

    @After
    public void tearDown() throws Exception {
        cardFoo = null;
    }

    @Test
    public void getId() {
        cardFoo.setId(null);
        assertEquals(cardFoo.getId(), null);

        cardFoo.setId(1l);
        assertTrue("Expect getId: 1", cardFoo.getId() != null && cardFoo.getId() == 1l);
    }

    @Test
    public void setId() {
        CardFoo returnCardFoo1 = cardFoo.setId(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getId(), null);

        CardFoo returnCardFoo2 = cardFoo.setId(1l);
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: 1",returnCardFoo2.getId() != null && returnCardFoo2.getId() == 1l);
    }

    @Test
    public void getGuid() {
        cardFoo.setGuid(null);
        assertEquals(cardFoo.getGuid(), null);

        cardFoo.setGuid("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(cardFoo.getGuid()));
    }

    @Test
    public void setGuid() {
        CardFoo returnCardFoo1 = cardFoo.setGuid(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getGuid(), null);

        CardFoo returnCardFoo2 = cardFoo.setGuid("BBBBB");
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCardFoo2.getGuid()));
    }

    @Test
    public void getPicture() {
        cardFoo.setPicture(null);
        assertEquals(cardFoo.getPicture(), null);

        cardFoo.setPicture("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(cardFoo.getPicture()));
    }

    @Test
    public void setPicture() {
        CardFoo returnCardFoo1 = cardFoo.setPicture(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getPicture(), null);

        CardFoo returnCardFoo2 = cardFoo.setPicture("BBBBB");
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCardFoo2.getPicture()));
    }

    @Test
    public void getName() {
        cardFoo.setName(null);
        assertEquals(cardFoo.getName(), null);

        cardFoo.setName("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(cardFoo.getName()));
    }

    @Test
    public void setName() {
        CardFoo returnCardFoo1 = cardFoo.setName(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getName(), null);

        CardFoo returnCardFoo2 = cardFoo.setName("BBBBB");
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCardFoo2.getName()));
    }

    @Test
    public void getPhone() {
        cardFoo.setPhone(null);
        assertEquals(cardFoo.getPhone(), null);

        cardFoo.setPhone("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(cardFoo.getPhone()));
    }

    @Test
    public void setPhone() {
        CardFoo returnCardFoo1 = cardFoo.setPhone(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getPhone(), null);

        CardFoo returnCardFoo2 = cardFoo.setPhone("BBBBB");
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCardFoo2.getPhone()));
    }

    @Test
    public void getAddress() {
        cardFoo.setAddress(null);
        assertEquals(cardFoo.getAddress(), null);

        cardFoo.setAddress("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(cardFoo.getAddress()));
    }

    @Test
    public void setAddress() {
        CardFoo returnCardFoo1 = cardFoo.setAddress(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getAddress(), null);

        CardFoo returnCardFoo2 = cardFoo.setAddress("BBBBB");
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCardFoo2.getAddress()));
    }

    @Test
    public void getCompany() {
        cardFoo.setCompany(null);
        assertEquals(cardFoo.getCompany(), null);

        cardFoo.setCompany("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(cardFoo.getCompany()));
    }

    @Test
    public void setCompany() {
        CardFoo returnCardFoo1 = cardFoo.setCompany(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getCompany(), null);

        CardFoo returnCardFoo2 = cardFoo.setCompany("BBBBB");
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCardFoo2.getCompany()));
    }

    @Test
    public void getPosition() {
        cardFoo.setPosition(null);
        assertEquals(cardFoo.getPosition(), null);

        cardFoo.setPosition("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(cardFoo.getPosition()));
    }

    @Test
    public void setPosition() {
        CardFoo returnCardFoo1 = cardFoo.setPosition(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getPosition(), null);

        CardFoo returnCardFoo2 = cardFoo.setPosition("BBBBB");
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCardFoo2.getPosition()));
    }

    @Test
    public void getGender() {
        cardFoo.setGender(null);
        assertEquals(cardFoo.getGender(), null);

        cardFoo.setGender("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(cardFoo.getGender()));
    }

    @Test
    public void setGender() {
        CardFoo returnCardFoo1 = cardFoo.setGender(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getGender(), null);

        CardFoo returnCardFoo2 = cardFoo.setGender("BBBBB");
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCardFoo2.getGender()));
    }

    @Test
    public void getDob() {
        cardFoo.setDob(null);
        assertEquals(cardFoo.getDob(), null);

        cardFoo.setDob("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(cardFoo.getDob()));
    }

    @Test
    public void setDob() {
        CardFoo returnCardFoo1 = cardFoo.setDob(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getDob(), null);

        CardFoo returnCardFoo2 = cardFoo.setDob("BBBBB");
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCardFoo2.getDob()));
    }

    @Test
    public void getAbout() {
        cardFoo.setAbout(null);
        assertEquals(cardFoo.getAbout(), null);

        cardFoo.setAbout("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(cardFoo.getAbout()));
    }

    @Test
    public void setAbout() {
        CardFoo returnCardFoo1 = cardFoo.setAbout(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getAbout(), null);

        CardFoo returnCardFoo2 = cardFoo.setAbout("BBBBB");
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCardFoo2.getAbout()));
    }

    @Test
    public void getEmail() {
        cardFoo.setEmail(null);
        assertEquals(cardFoo.getEmail(), null);

        cardFoo.setEmail("AAAAA");
        assertTrue("Expect: AAAAA", "AAAAA".equals(cardFoo.getEmail()));
    }

    @Test
    public void setEmail() {
        CardFoo returnCardFoo1 = cardFoo.setEmail(null);
        assertNotNull(returnCardFoo1);
        assertEquals(returnCardFoo1.getEmail(), null);

        CardFoo returnCardFoo2 = cardFoo.setEmail("BBBBB");
        assertNotNull(returnCardFoo2);
        assertTrue("Expect setId: BBBBB","BBBBB".equals(returnCardFoo2.getEmail()));
    }

    @Test
    public void convertToCard() {
        CardFoo cardFoo = new CardFoo();
        cardFoo.setId(1l);
        cardFoo.setGuid("guid");
        cardFoo.setPicture("picture");
        cardFoo.setName("name");
        cardFoo.setPhone("phone");
        cardFoo.setAddress("address");
        cardFoo.setCompany("company");
        cardFoo.setPosition("position");
        cardFoo.setGender("gender");
        cardFoo.setDob("dob");
        cardFoo.setAbout("about");
        cardFoo.setEmail("email");

        Card card = cardFoo.convertToCard();
        assertTrue(card.getId() != null && card.getId() == 1l);
        assertTrue("guid".equals(card.getGuid()));
        assertTrue("picture".equals(card.getAvatar()));
        assertTrue("name".equals(card.getName()));
        assertTrue("phone".equals(card.getMobile()));
        assertTrue("address".equals(card.getAddress()));
        assertTrue("company".equals(card.getCompany()));
        assertTrue("position".equals(card.getPosition()));
        assertTrue("gender".equals(card.getGender()));
        assertTrue("dob".equals(card.getDob()));
        assertTrue("about".equals(card.getAbout()));
        assertTrue("email".equals(card.getEmail()));

    }
}