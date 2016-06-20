package com.codechallenges.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by colin.mills on 5/9/2016.
 */

@Entity
public class WishItem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    @JsonIgnore
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("weight")
    private String weight;

    @JsonProperty("clatters")
    private String clatters;

    @JsonProperty("size")
    private String size;

    @JsonProperty("giver")
    private String giver;

    public int getId() {
        return id;
    }

    public WishItem setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WishItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public WishItem setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getClatters() {
        return clatters;
    }

    public WishItem setClatters(String clatters) {
        this.clatters = clatters;
        return this;
    }

    public String getSize() {
        return size;
    }

    public WishItem setSize(String size) {
        this.size = size;
        return this;
    }

    public String getGiver() {
        return giver;
    }

    public WishItem setGiver(String giver) {
        this.giver = giver;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WishItem wishItem = (WishItem) o;

        if (getId() != wishItem.getId()) return false;
        if (!getName().equals(wishItem.getName())) return false;
        if (getWeight() != null ? !getWeight().equals(wishItem.getWeight()) : wishItem.getWeight() != null)
            return false;
        if (getClatters() != null ? !getClatters().equals(wishItem.getClatters()) : wishItem.getClatters() != null)
            return false;
        if (getSize() != null ? !getSize().equals(wishItem.getSize()) : wishItem.getSize() != null) return false;
        return getGiver().equals(wishItem.getGiver());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getWeight() != null ? getWeight().hashCode() : 0);
        result = 31 * result + (getClatters() != null ? getClatters().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + getGiver().hashCode();
        return result;
    }
}
