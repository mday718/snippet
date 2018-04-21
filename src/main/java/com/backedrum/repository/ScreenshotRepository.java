package com.backedrum.repository;

import com.backedrum.model.Screenshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenshotRepository extends JpaRepository<Screenshot, Long> {
}
