package com.backedrum.service;

import com.backedrum.model.SourceCodeSnippet;

import java.util.List;

public interface SnippetService {

    void addSnippet(SourceCodeSnippet snippet);

    List<SourceCodeSnippet> retrieveAllSnippets();
}
