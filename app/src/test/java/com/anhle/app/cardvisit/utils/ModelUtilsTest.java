package com.anhle.app.cardvisit.utils;

import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.model.CardFoo;
import com.anhle.app.cardvisit.service.room.CardEntity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
public class ModelUtilsTest {

    @Test
    public void convertToListCard() {
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(new Card());
        arr.add(new CardFoo());
        arr.add(new CardEntity());
        List<Card> arrOutput = ModelUtils.convertToListCard(arr);
        for (Object item : arrOutput) {
            assertTrue("Must be convert this item to an object type of Card", item instanceof Card);
        }
    }

    @Test
    public void injectGuidForCardEntities() {
        ArrayList<CardEntity> arr = new ArrayList<>();
        CardEntity ce1 = new CardEntity(); ce1.guid = null; arr.add(ce1);
        CardEntity ce2 = new CardEntity(); ce2.guid = ""; arr.add(ce2);
        CardEntity ce3 = new CardEntity(); ce3.guid = "3b93da4e58b741ffb8d146bb7be380e2"; arr.add(ce3);
        ModelUtils.injectGuidForCardEntities(arr);
        assertTrue("You must inject guid when it is null, buddy!", arr.get(0).guid.length() > 0);
        assertTrue("You must inject guid when it is \"\", buddy!", arr.get(1).guid.length() > 0);
        assertTrue("Never inject for CardEntity has guid before!", "3b93da4e58b741ffb8d146bb7be380e2".equals(arr.get(2).guid));
    }

    @Test
    public void injectGuidForCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card().setGuid(null));
        cards.add(new Card().setGuid(""));
        cards.add(new Card().setGuid("3b93da4e58b741ffb8d146bb7be380e2"));
        ModelUtils.injectGuidForCards(cards);
        // Test Inject guid for all card success
        for (Card card : cards) {
            assertTrue("GUID injected must be have length great than 0", card.getGuid().length() > 0);
        }
    }
}