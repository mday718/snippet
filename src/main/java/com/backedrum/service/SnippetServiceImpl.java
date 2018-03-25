package com.backedrum.service;

import com.backedrum.model.SourceCodeSnippet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SnippetServiceImpl implements SnippetService {

    private List<SourceCodeSnippet> snippets = new ArrayList<>();

    @Override
    public void addSnippet(SourceCodeSnippet snippet) {
        snippets.add(0, snippet);
    }

    @Override
    public List<SourceCodeSnippet> retrieveAllSnippets() {
        return Collections.unmodifiableList(snippets);
    }
}
