package com.backedrum.service;

import com.backedrum.model.HowTo;
import com.backedrum.repository.HowToRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Service("howtoService")
public class HowToServiceImpl implements ItemsService<HowTo> {

    @Inject
    private HowToRepository repository;

    @Override
    @Transactional
    public void addItem(HowTo entity) {
        repository.save(entity);
    }

    @Override
    public List<HowTo> retrieveAllItems() {
        return repository.findAll();
    }
}
