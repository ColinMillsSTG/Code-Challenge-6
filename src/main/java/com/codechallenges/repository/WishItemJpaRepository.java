package com.codechallenges.repository;

import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin.mills on 5/20/2016.
 */
@Repository
public interface WishItemJpaRepository extends JpaRepository<WishItem,Long>{
    List<WishItem> findAll();
}
