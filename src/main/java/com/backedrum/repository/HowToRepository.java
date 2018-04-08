package com.backedrum.repository;

import com.backedrum.model.HowTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HowToRepository extends JpaRepository<HowTo, Long> {
}
