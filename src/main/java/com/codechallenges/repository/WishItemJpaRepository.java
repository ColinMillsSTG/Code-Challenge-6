package com.codechallenges.repository;

import com.codechallenges.entity.WishItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by colin.mills on 5/20/2016.
 */
@Repository
public interface WishItemJpaRepository extends JpaRepository<WishItem, Integer>{
    List<WishItem> findAll();
}
