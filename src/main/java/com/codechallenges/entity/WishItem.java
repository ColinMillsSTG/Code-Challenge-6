package com.codechallenges.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by colin.mills on 5/9/2016.
 */

@Entity
public class WishItem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    private String weight;
    private String clatters;
    private String size;
    private String giver;

    
}
