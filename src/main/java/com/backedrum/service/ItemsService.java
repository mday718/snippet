package com.backedrum.service;

import com.backedrum.model.Entity;

import java.util.List;

public interface ItemsService<T extends Entity> {

    void addItem(T entity);

    List<T> retrieveAllItems();
}
