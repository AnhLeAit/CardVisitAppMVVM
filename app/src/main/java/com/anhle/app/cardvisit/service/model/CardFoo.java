package com.anhle.app.cardvisit.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.anhle.app.cardvisit.utils.StringUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
public class CardFoo implements Parcelable, Searchable {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("guid")
    @Expose
    private String guid;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("position")
    @Expose
    private String position;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("dob")
    @Expose
    private String dob;

    @SerializedName("about")
    @Expose
    private String about;

    @SerializedName("email")
    @Expose
    private String email;

    public final static Creator<CardFoo> CREATOR = new Creator<CardFoo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CardFoo createFromParcel(Parcel in) {
            return new CardFoo(in);
        }

        public CardFoo[] newArray(int size) {
            return (new CardFoo[size]);
        }

    };

    protected CardFoo(Parcel in) {
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        this.guid = ((String) in.readValue((String.class.getClassLoader())));
        this.picture = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.company = ((String) in.readValue((String.class.getClassLoader())));
        this.position = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.dob = ((String) in.readValue((String.class.getClassLoader())));
        this.about = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CardFoo() {
    }

    public Long getId() {
        return id;
    }

    public CardFoo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public CardFoo setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public CardFoo setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getName() {
        return name;
    }

    public CardFoo setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CardFoo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CardFoo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public CardFoo setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public CardFoo setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public CardFoo setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getDob() {
        return dob;
    }

    public CardFoo setDob(String dob) {
        this.dob = dob;
        return this;
    }

    public String getAbout() {
        return about;
    }

    public CardFoo setAbout(String about) {
        this.about = about;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CardFoo setEmail(String email) {
        this.email = email;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(guid);
        dest.writeValue(picture);
        dest.writeValue(name);
        dest.writeValue(phone);
        dest.writeValue(address);
        dest.writeValue(company);
        dest.writeValue(position);
        dest.writeValue(gender);
        dest.writeValue(dob);
        dest.writeValue(about);
        dest.writeValue(email);
    }

    public int describeContents() {
        return 0;
    }

    public Card convertToCard() {
        Card card = new Card();
        card.setId(id);
        card.setGuid(guid);
        card.setAvatar(picture);
        card.setName(name);
        card.setMobile(phone);
        card.setAddress(address);
        card.setCompany(company);
        card.setPosition(position);
        card.setGender(gender);
        card.setDob(dob);
        card.setAbout(about);
        card.setEmail(email);
        return card;
    }

    @Override
    public boolean isMatchWith(String key) {
        if (!StringUtils.isEmpty(key)) {
            String displayName = getName();
            String phone = getPhone();
            String filterKey = key.toLowerCase();
            return (((displayName != null && displayName.toLowerCase().contains(filterKey)) || (phone != null && phone.toLowerCase().contains(filterKey))));
        }
        return false;
    }
}