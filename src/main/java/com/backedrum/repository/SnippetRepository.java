package com.backedrum.repository;

import com.backedrum.model.SourceCodeSnippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnippetRepository extends JpaRepository<SourceCodeSnippet, Long> {
}
