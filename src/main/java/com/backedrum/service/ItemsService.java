package com.backedrum.service;

import com.backedrum.model.BaseEntity;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ItemsService<T extends BaseEntity> {

    void addItem(T entity);

    List<T> retrieveAllItems();
}
