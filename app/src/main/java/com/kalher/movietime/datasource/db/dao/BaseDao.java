package com.kalher.movietime.datasource.db.dao;

import com.kalher.movietime.datasource.db.entity.IEntity;

import java.util.List;

public interface BaseDao {

    void insert(IEntity entity);

    void insertAll(List<IEntity> entities);

    List<IEntity> getAll();

    IEntity getById(String id);

    void updateById(String id);

    void updateAll();

    void deleteAll();

    void deleteById(String id);

}
