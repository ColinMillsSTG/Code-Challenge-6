package com.codechallenges.service;

import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.exceptions.ResourceNotFoundException;
import com.codechallenges.repository.PresentJpaRepository;
import com.codechallenges.repository.WishItemJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Implementation of data requests to be performed against lists of presents and wish lists.
 */

@Service
public class PresentServiceImpl implements PresentService {

    /**
     * @Todo: change out arraylists for JPA repos
     */
    @Autowired
    WishItemJpaRepository wishItemJpaRepository;

    @Autowired
    PresentJpaRepository presentJpaRepository;

    List<WishItem> wishlist;
    List<Present> presents;

    @PostConstruct
    private void init(){
        wishlist = wishItemJpaRepository.findAll();
        presents = presentJpaRepository.findAll();
    }

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

    public List<WishItem> getWishlist() { return wishItemJpaRepository.findAll(); }

    public List<Present> getPresents(){
        return presentJpaRepository.findAll();
    }

    public void setWishlist(List<WishItem> wishlist){
        wishItemJpaRepository.save(wishlist);
    }

    public void setPresents(List<Present> presents){
        presentJpaRepository.save(presents);
    }

    public void addWishlistItem(WishItem wishItem){
        ArrayList<WishItem> wishItems = new ArrayList<>();
        wishItems.add(wishItem);
        wishItemJpaRepository.save(wishItem);
    }

    public void addPresent(Present present){
        ArrayList<Present> presents = new ArrayList<>();
        presents.add(present);
        presentJpaRepository.save(presents);
    }

    public WishItem getWishlistItemForId(Integer id) throws ResourceNotFoundException{

        return wishItemJpaRepository.findOne(id);

    }

    public Present getPresentForId(Integer id) throws ResourceNotFoundException{

        return presentJpaRepository.findOne(id);

    }

    public void deleteWishlistItemForId(Integer id) throws ResourceNotFoundException{
        wishItemJpaRepository.delete(id);
    }

    public void deletePresentForId(Integer id) throws ResourceNotFoundException{
        presentJpaRepository.delete(id);
    }

    public void clearWishlist(){
        wishItemJpaRepository.deleteAll();
    }

    public void clearPresents(){
        presentJpaRepository.deleteAll();
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
