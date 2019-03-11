package com.anhle.app.cardvisit.service.room;

import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.model.Searchable;
import com.anhle.app.cardvisit.utils.StringUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
@Entity(tableName = "card_table")
public class CardEntity implements Searchable {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "guid")
    public String guid;

    @ColumnInfo(name = "avatar")
    public String avatar;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "mobile")
    public String mobile;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "company")
    public String company;

    @ColumnInfo(name = "position")
    public String position;

    @ColumnInfo(name = "gender")
    public String gender;

    @ColumnInfo(name = "dob")
    public String dob;

    @ColumnInfo(name = "about")
    public String about;

    @ColumnInfo(name = "email")
    public String email;

    @Override
    public String toString() {
        return "CardEntity{" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", about='" + about + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static CardEntity mapToEntity(Card card) {
        if (card != null) {
            CardEntity cardEntity = new CardEntity();
            cardEntity.id = card.getId();
            cardEntity.guid = card.getGuid();
            cardEntity.avatar = card.getAvatar();
            cardEntity.name = card.getName();
            cardEntity.mobile = card.getMobile();
            cardEntity.address = card.getAddress();
            cardEntity.company = card.getCompany();
            cardEntity.position = card.getPosition();
            cardEntity.gender = card.getGender();
            cardEntity.dob = card.getDob();
            cardEntity.about = card.getAbout();
            cardEntity.email = card.getEmail();
            return cardEntity;
        }

        return null;
    }

    public static Card mapToDTO(CardEntity cardEntity) {
        if (cardEntity != null) {
            Card card = new Card();
            card.setId(cardEntity.id);
            card.setGuid(cardEntity.guid);
            card.setAvatar(cardEntity.avatar);
            card.setName(cardEntity.name);
            card.setMobile(cardEntity.mobile);
            card.setAddress(cardEntity.address);
            card.setCompany(cardEntity.company);
            card.setPosition(cardEntity.position);
            card.setGender(cardEntity.gender);
            card.setDob(cardEntity.dob);
            card.setAbout(cardEntity.about);
            card.setEmail(cardEntity.email);
            return card;
        }
        return null;
    }

    @Override
    public boolean isMatchWith(String key) {
        if (!StringUtils.isEmpty(key)) {
            String displayName = this.name;
            String phone = this.mobile;
            String filterKey = key.toLowerCase();
            return (((displayName != null && displayName.toLowerCase().contains(filterKey)) || (phone != null && phone.toLowerCase().contains(filterKey))));
        }
        return false;
    }
}