package com.backedrum.service;

import com.backedrum.model.BaseEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemsService<T extends BaseEntity> {

    @Transactional
    void addItem(T entity);

    @Transactional
    void removeItem(Long id);

    List<T> retrieveAllItems();
}
