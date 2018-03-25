package com.backedrum.service;

import com.backedrum.model.Screenshot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("screenshotService")
public class ScreenshotServiceImpl implements ItemsService<Screenshot> {
    private List<Screenshot> screenshots = new ArrayList<>();

    @Override
    public void addItem(Screenshot entity) {
        screenshots.add(entity);
    }

    @Override
    public List<Screenshot> retrieveAllItems() {
        return Collections.unmodifiableList(screenshots);
    }
}
