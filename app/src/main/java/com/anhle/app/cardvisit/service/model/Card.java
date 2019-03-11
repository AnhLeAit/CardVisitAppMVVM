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
public class Card implements Parcelable, Searchable{

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("guid")
    @Expose
    private String guid;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("mobile")
    @Expose
    private String mobile;

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

    public final static Creator<Card> CREATOR = new Creator<Card>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        public Card[] newArray(int size) {
            return (new Card[size]);
        }

    };

    protected Card(Parcel in) {
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        this.guid = ((String) in.readValue((String.class.getClassLoader())));
        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
        this.picture = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.company = ((String) in.readValue((String.class.getClassLoader())));
        this.position = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.dob = ((String) in.readValue((String.class.getClassLoader())));
        this.about = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Card() {
    }

    public Long getId() {
        return id;
    }

    public Card setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public Card setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public String getAvatar() {
        if (avatar == null) {
            if (picture != null) {
                avatar = picture;
            } else {
                return avatar;
            }
        }
        return avatar.replace("http://", "https://");
    }

    public Card setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getName() {
        if (name == null) {
            if (firstName != null && lastName != null) {
                name = String.format("%s %s", firstName, lastName);
            } else {
                if (firstName != null) {
                    name = firstName;
                } else {
                    name = lastName;
                }
            }
        }
        return name;
    }

    public Card setName(String name) {
        this.name = name;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Card setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Card setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getMobile() {
        if (mobile == null && phone != null) {
            return phone;
        }
        return mobile;
    }

    public Card setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Card setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Card setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public Card setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Card setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getDob() {
        return dob;
    }

    public Card setDob(String dob) {
        this.dob = dob;
        return this;
    }

    public String getAbout() {
        return about;
    }

    public Card setAbout(String about) {
        this.about = about;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Card setEmail(String email) {
        this.email = email;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(guid);
        dest.writeValue(avatar);
        dest.writeValue(picture);
        dest.writeValue(name);
        dest.writeValue(firstName);
        dest.writeValue(lastName);
        dest.writeValue(mobile);
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

    @Override
    public boolean isMatchWith(String key) {
        if (!StringUtils.isEmpty(key)) {
            String displayName = getName();
            String phone = getMobile();
            String filterKey = key.toLowerCase();
            return (((displayName != null && displayName.toLowerCase().contains(filterKey)) || (phone != null && phone.toLowerCase().contains(filterKey))));
        }
        return false;
    }
}