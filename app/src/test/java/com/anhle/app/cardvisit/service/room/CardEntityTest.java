package com.anhle.app.cardvisit.service.room;

import com.anhle.app.cardvisit.service.model.Card;

import org.junit.Test;

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
public class CardEntityTest {

    @Test
    public void mapToEntity() {
        Card card = new Card();
        card.setId(1l);
        card.setGuid("guid");
        card.setAvatar("avatar");
        card.setName("name");
        card.setMobile("mobile");
        card.setAddress("address");
        card.setCompany("company");
        card.setPosition("position");
        card.setGender("gender");
        card.setDob("dob");
        card.setAbout("about");
        card.setEmail("email");

        CardEntity cardEntity = CardEntity.mapToEntity(card);

        assertTrue(cardEntity.id != null && cardEntity.id == 1l);
        assertTrue("guid".equals(cardEntity.guid));
        assertTrue("avatar".equals(cardEntity.avatar));
        assertTrue("name".equals(cardEntity.name));
        assertTrue("mobile".equals(cardEntity.mobile));
        assertTrue("address".equals(cardEntity.address));
        assertTrue("company".equals(cardEntity.company));
        assertTrue("position".equals(cardEntity.position));
        assertTrue("gender".equals(cardEntity.gender));
        assertTrue("dob".equals(cardEntity.dob));
        assertTrue("about".equals(cardEntity.about));
        assertTrue("email".equals(cardEntity.email));
    }

    @Test
    public void mapToDTO() {
        CardEntity cardEntity = new CardEntity();
        cardEntity.id = 1l;
        cardEntity.guid = "guid";
        cardEntity.avatar = "avatar";
        cardEntity.name = "name";
        cardEntity.mobile = "mobile";
        cardEntity.address = "address";
        cardEntity.company = "company";
        cardEntity.position = "position";
        cardEntity.gender = "gender";
        cardEntity.dob = "dob";
        cardEntity.about = "about";
        cardEntity.email = "email";

        Card card = CardEntity.mapToDTO(cardEntity);

        assertTrue(card.getId() != null && card.getId() == 1l);
        assertTrue("guid".equals(card.getGuid()));
        assertTrue("avatar".equals(card.getAvatar()));
        assertTrue("name".equals(card.getName()));
        assertTrue("mobile".equals(card.getMobile()));
        assertTrue("address".equals(card.getAddress()));
        assertTrue("company".equals(card.getCompany()));
        assertTrue("position".equals(card.getPosition()));
        assertTrue("gender".equals(card.getGender()));
        assertTrue("dob".equals(card.getDob()));
        assertTrue("about".equals(card.getAbout()));
        assertTrue("email".equals(card.getEmail()));
    }
}