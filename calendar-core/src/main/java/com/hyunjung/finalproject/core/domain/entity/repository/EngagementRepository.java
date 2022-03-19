package com.hyunjung.finalproject.core.domain.entity.repository;

import com.hyunjung.finalproject.core.domain.entity.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {
}
