package com.codechallenges.entity;

import javax.persistence.*;

/**
 * Created by colin.mills on 5/9/2016.
 */
@Entity
public class Present{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String size;
    private String clatters;
    private String weight;
    private String giver;

    public long getId() {
        return id;
    }

    public Present setId(int id) {
        this.id = id;
        return this;
    }

    public String getSize() {
        return size;
    }

    public Present setSize(String size) {
        this.size = size;
        return this;
    }

    public String getClatters() {
        return clatters;
    }

    public Present setClatters(String clatters) {
        this.clatters = clatters;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public Present setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getGiver() {
        return giver;
    }

    public Present setGiver(String giver) {
        this.giver = giver;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Present present = (Present) o;

        if (getId() != present.getId()) return false;
        if (getSize() != null ? !getSize().equals(present.getSize()) : present.getSize() != null) return false;
        if (getClatters() != null ? !getClatters().equals(present.getClatters()) : present.getClatters() != null)
            return false;
        if (getWeight() != null ? !getWeight().equals(present.getWeight()) : present.getWeight() != null) return false;
        return getGiver().equals(present.getGiver());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getClatters() != null ? getClatters().hashCode() : 0);
        result = 31 * result + (getWeight() != null ? getWeight().hashCode() : 0);
        result = 31 * result + (getGiver() != null ? getGiver().hashCode() : 0);
        return result;
    }
}
