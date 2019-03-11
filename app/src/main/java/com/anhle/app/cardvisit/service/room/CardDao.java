package com.anhle.app.cardvisit.service.room;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;

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
@Dao
public interface CardDao {
    @Query("SELECT * FROM card_table ORDER BY id DESC")
    Single<List<CardEntity>> getAll();

    @Query("SELECT * FROM card_table WHERE guid LIKE :guid LIMIT 1")
    Single<CardEntity> findByGuid(String guid);

    @Query("SELECT * FROM card_table ORDER BY id DESC LIMIT 1")
    Single<CardEntity> getLastCard();

    @Insert
    void insertAll(CardEntity... cardEntities);

    @Update
    void update(CardEntity cardEntity);

    @Delete
    void delete(CardEntity cardEntity);
}
