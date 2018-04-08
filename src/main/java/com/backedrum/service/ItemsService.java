package com.backedrum.service;

import com.backedrum.model.BaseEntity;

import java.util.List;

public interface ItemsService<T extends BaseEntity> {

    void addItem(T entity);

    List<T> retrieveAllItems();
}
