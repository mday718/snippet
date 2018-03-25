package com.backedrum.service;

import com.backedrum.model.HowTo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("howtoService")
public class HowToServiceImpl implements ItemsService<HowTo> {

    private List<HowTo> howTos = new ArrayList<>();

    @Override
    public void addItem(HowTo entity) {
        howTos.add(entity);
    }

    @Override
    public List<HowTo> retrieveAllItems() {
        return Collections.unmodifiableList(howTos);
    }
}
