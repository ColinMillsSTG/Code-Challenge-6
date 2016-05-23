package com.codechallenges.repository;

import com.codechallenges.entity.Present;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by colin.mills on 5/20/2016.
 */

@Repository
public interface PresentJpaRepository extends JpaRepository<Present, Long> {
}
