package com.anhle.app.cardvisit.utils;

import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.model.CardFoo;
import com.anhle.app.cardvisit.service.room.CardEntity;

import java.util.ArrayList;
import java.util.List;

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
public class ModelUtils {

    public static List<Card> convertToListCard(List<Object> data) {
        List<Card> cards = new ArrayList<>();
        if (data != null) {
            for (Object item : data) {
                if (item instanceof CardEntity) {
                    cards.add(CardEntity.mapToDTO((CardEntity) item));
                } else if (item instanceof CardFoo) {
                    cards.add(((CardFoo) item).convertToCard());
                } else if (item instanceof Card) {
                    cards.add((Card) item);
                }
            }
        }
        return cards;
    }

    /**
     * Only inject for CardEntity doesn't have guid before!
     *
     * @param cardEntities
     * @return
     */
    public static List<CardEntity> injectGuidForCardEntities(List<CardEntity> cardEntities) {
        if (cardEntities != null) {
            for (CardEntity card : cardEntities) {
                if (card.guid == null || "".equals(card.guid)) {
                    card.guid = StringUtils.generateGUID();
                }
            }
        }

        return cardEntities;
    }

    /**
     * Inject GUID for Card item inside cards if it doesn't have.
     *
     * @param cards
     * @return false if no need inject
     */
    public static void injectGuidForCards(List<Card> cards) {
        if (cards != null) {
            for (Card card : cards) {
                if (StringUtils.isEmpty(card.getGuid())) {
                    card.setGuid(StringUtils.generateGUID());
                }
            }
        }
    }
}
