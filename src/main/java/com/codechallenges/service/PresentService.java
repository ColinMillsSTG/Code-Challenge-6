package com.codechallenges.service;

import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Service for handling different data requests against a given set of presents and a wishlist.
 */

public interface PresentService {

    List<String> guessPresents();
    List<String> guessPresents(List<WishItem> wishlist);

    List<Present> getPresents();
    List<WishItem> getWishlist();

    void setWishlist(List<WishItem> wishlist);
    void setPresents(List<Present> presents);

    void addWishlistItem(WishItem wishItem);
    void addPresent(Present present);

    Present getPresentForId(Integer id) throws ResourceNotFoundException;
    WishItem getWishlistItemForId(Integer id) throws ResourceNotFoundException;

    void deletePresentForId(Integer id) throws ResourceNotFoundException;
    void deleteWishlistItemForId(Integer id) throws ResourceNotFoundException;

    void clearPresents();
    void clearWishlist();
}
