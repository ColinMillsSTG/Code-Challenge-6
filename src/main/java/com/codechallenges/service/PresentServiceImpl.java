package com.codechallenges.service;

import com.codechallenges.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Implementation of data requests to be performed against lists of presents and wish lists.
 */

@Service
public class PresentServiceImpl implements PresentService{

    ArrayList<Map<String,String>> presents = new ArrayList<>();
    ArrayList<Map<String,String>> wishlist = new ArrayList<>();

    public ArrayList<String> guessPresents(){

        ArrayList<String> confirmedPresents = new ArrayList<>();

        try {
            //check list of presents against wishlist
            for(Map<String,String> wishListItem : wishlist) {
                if(isPresentPresent(wishListItem, presents)){
                    confirmedPresents.add(wishListItem.get("name")); //I'm assuming we know what the thing is called
                }
            }
        }catch(Exception e){
            System.out.println(e.getCause());
        }

        return confirmedPresents;
    }

    public ArrayList<String> guessPresents(ArrayList<Map<String,String>> wishlist){

        setWishlist(wishlist);

        return guessPresents();
    }

    public void setWishlist(ArrayList<Map<String,String>> wishlist){
        this.wishlist = wishlist;
    }

    public void setPresents(ArrayList<Map<String,String>> presents){
        this.presents = presents;
    }

    public ArrayList<Map<String,String>> getWishlist() { return wishlist; }

    public ArrayList<Map<String,String>> getPresents(){
        return presents;
    }

    public Map<String,String> getWishlistItemForId(int id) throws ResourceNotFoundException{
        return wishlist.get(id);
    }

    public Map<String,String> getPresentForId(int id) throws ResourceNotFoundException{
        return presents.get(id);
    }

    public void deleteWishlistItemForId(int id) throws ResourceNotFoundException{
        wishlist.remove(id);
    }

    public void deletePresentForId(int id) throws ResourceNotFoundException{
        presents.remove(id);
    }

    public void clearWishlist(){
        wishlist = new ArrayList<>();
    }

    public void clearPresents(){
        presents = new ArrayList<>();
    }

    //Logic methods
    private Boolean isPresentPresent(Map<String,String> wishListItem, ArrayList<Map<String, String>> presents){

        for(Map<String,String> present : presents){ // For each present we shook
            Boolean isPresent = true;

            for(String quality : present.keySet()){ // check its qualities against our wishlist
                if(!present.get(quality).equals(wishListItem.get(quality))){// Present isn't like the wishlist item
                    isPresent = false;
                }
            }

            if(isPresent){
                return isPresent;
            }

        }

        return false;
    }
}
