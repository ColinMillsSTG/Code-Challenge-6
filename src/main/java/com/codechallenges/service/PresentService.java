package com.codechallenges.service;

import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Service for handling different data requests against a given set of presents and a wishlist.
 */

public interface PresentService {

    ArrayList<String> guessPresents();
    ArrayList<String> guessPresents(ArrayList<WishItem> wishlist);

    ArrayList<Present> getPresents();
    ArrayList<WishItem> getWishlist();

    void setWishlist(ArrayList<WishItem> wishlist);
    void setPresents(ArrayList<Present> presents);

    void addWishlistItem(WishItem wishItem);
    void addPresent(Present present);

    Present getPresentForId(int id) throws ResourceNotFoundException;
    WishItem getWishlistItemForId(int id) throws ResourceNotFoundException;

    void deletePresentForId(int id) throws ResourceNotFoundException;
    void deleteWishlistItemForId(int id) throws ResourceNotFoundException;

    void clearPresents();
    void clearWishlist();
}
