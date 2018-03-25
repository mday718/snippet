package com.backedrum.service;

import com.backedrum.model.SourceCodeSnippet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("snippetService")
public class SnippetServiceImpl implements ItemsService<SourceCodeSnippet> {

    private List<SourceCodeSnippet> snippets = new ArrayList<>();

    @Override
    public void addItem(SourceCodeSnippet snippet) {
        snippets.add(0, snippet);
    }

    @Override
    public List<SourceCodeSnippet> retrieveAllItems() {
        return Collections.unmodifiableList(snippets);
    }
}
