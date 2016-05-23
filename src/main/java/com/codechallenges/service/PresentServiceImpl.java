package com.codechallenges.service;

import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Implementation of data requests to be performed against lists of presents and wish lists.
 */

@Service
public class PresentServiceImpl implements PresentService{

    /**
     * @Todo: change out arraylists for JPA repos
     */

    List<Present> presents = new ArrayList<>();
    List<WishItem> wishlist = new ArrayList<>();

    public List<String> guessPresents(){

        List<String> confirmedPresents = new ArrayList<>();

        try {
            //check list of presents against wishlist
            for(WishItem wishListItem : wishlist) {
                if(isPresentPresent(wishListItem, presents)){
                    confirmedPresents.add(wishListItem.getName()); //I'm assuming we know what the thing is called
                }
            }
        }catch(Exception e){
            System.out.println(e.getCause());
        }

        return confirmedPresents;
    }

    public List<String> guessPresents(List<WishItem> wishlist){

        setWishlist(wishlist);

        return guessPresents();
    }

    public List<WishItem> getWishlist() { return wishlist; }

    public List<Present> getPresents(){
        return presents;
    }

    public void setWishlist(List<WishItem> wishlist){
        this.wishlist = wishlist;
    }

    public void setPresents(List<Present> presents){
        this.presents = presents;
    }

    public void addWishlistItem(WishItem wishItem){
        this.wishlist.add(wishItem);
    }

    public void addPresent(Present present){
        this.presents.add(present);
    }

    public WishItem getWishlistItemForId(int id) throws ResourceNotFoundException{
        for(WishItem wishItem : wishlist) {
            if(wishItem.getId() == id) {
                return wishItem;
            }
        }

        throw new ResourceNotFoundException();
    }

    public Present getPresentForId(int id) throws ResourceNotFoundException{
        for(Present present : presents) {
            if(present.getId() == id) {
                return present;
            }
        }

        throw new ResourceNotFoundException();
    }

    public void deleteWishlistItemForId(int id) throws ResourceNotFoundException{
        for(WishItem wishItem : wishlist) {
            if(wishItem.getId() == id) {
                wishlist.remove(wishItem);
                return;
            }
        }

        throw new ResourceNotFoundException();
    }

    public void deletePresentForId(int id) throws ResourceNotFoundException{
        for(Present present : presents) {
            if(present.getId() == id) {
                presents.remove(present);
                return;
            }
        }

        throw new ResourceNotFoundException();
    }

    public void clearWishlist(){
        wishlist = new ArrayList<>();
    }

    public void clearPresents(){
        presents = new ArrayList<>();
    }

    //Logic methods
    private Boolean isPresentPresent(WishItem wishListItem, List<Present> presents){

        for(Present present : presents){ // For each present we shook
            Boolean isPresent = true;

            if(present.getClatters()!= null && wishListItem.getClatters() != null && !present.getClatters().equals(wishListItem.getClatters())){
                isPresent = false;
            }

            if(present.getSize()!= null && wishListItem.getSize() != null && !present.getSize().equals(wishListItem.getSize())){
                isPresent = false;
            }

            if(present.getWeight()!= null && wishListItem.getWeight() != null && !present.getWeight().equals(wishListItem.getWeight())){
                isPresent = false;
            }

            if(present.getGiver()!= null && wishListItem.getGiver() != null && !present.getGiver().equals((wishListItem.getGiver()))){
                isPresent = false;
            }

            if(isPresent){
                return true;
            }

        }

        return false;
    }
}
