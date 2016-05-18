package com.codechallenges.service;

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
    ArrayList<String> guessPresents(ArrayList<Map<String,String>> wishlist);

    void setWishlist(ArrayList<Map<String,String>> wishlist);
    void setPresents(ArrayList<Map<String,String>> presents);

    ArrayList<Map<String,String>> getPresents();
    ArrayList<Map<String,String>> getWishlist();

    Map<String,String> getPresentForId(int id) throws ResourceNotFoundException;
    Map<String,String> getWishlistItemForId(int id) throws ResourceNotFoundException;

    void deletePresentForId(int id) throws ResourceNotFoundException;
    void deleteWishlistItemForId(int id) throws ResourceNotFoundException;

    void clearPresents();
    void clearWishlist();
}
