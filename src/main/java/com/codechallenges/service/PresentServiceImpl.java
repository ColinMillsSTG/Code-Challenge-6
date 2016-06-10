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

    /*
    Get one
     */
    public WishItem getWishlistItemForId(Integer id) throws ResourceNotFoundException{

        return wishItemJpaRepository.findOne(id);

    }

    public Present getPresentForId(Integer id) throws ResourceNotFoundException{

        return presentJpaRepository.findOne(id);

    }

    /*
    Get all
     */
    public List<WishItem> getWishlist() { return wishItemJpaRepository.findAll(); }

    public List<Present> getPresents(){
        return presentJpaRepository.findAll();
    }

    public void addWishItems(List<WishItem> wishlist){
        wishItemJpaRepository.save(wishlist);
    }

    public void addPresents(List<Present> presents){
        presentJpaRepository.save(presents);
    }

    /*
    Post collection
     */
    public void replaceWishItem(WishItem wishItem){
        ArrayList<WishItem> wishItems = new ArrayList<>();
        wishItems.add(wishItem);
        wishItemJpaRepository.save(wishItem);
    }

    public void replacePresent(Present present){
        ArrayList<Present> presents = new ArrayList<>();
        presents.add(present);
        presentJpaRepository.save(presents);
    }

    /*
    Post one
     */
    public void updateWishlistForId(WishItem wishItem, int id){
        WishItem updatedItem = wishItemJpaRepository.findOne(id);

        if(updatedItem == null){
            throw new ResourceNotFoundException();
        }

        if(wishItem.getClatters() != null){
            updatedItem.setClatters(wishItem.getClatters());
        }

        if(wishItem.getWeight() != null){
            updatedItem.setWeight(wishItem.getWeight());
        }

        if(wishItem.getGiver() != null){
            updatedItem.setGiver(wishItem.getGiver());
        }

        if(wishItem.getName() != null){
            updatedItem.setName(wishItem.getName());
        }


        if(wishItem.getSize() != null){
            updatedItem.setSize(wishItem.getSize());
        }

        wishItemJpaRepository.save(updatedItem);
    }

    public void updatePresentForId(Present present, int id){
        Present updatedItem = presentJpaRepository.findOne(id);

        if(present.getClatters() != null){
            updatedItem.setClatters(present.getClatters());
        }

        if(present.getWeight() != null){
            updatedItem.setWeight(present.getWeight());
        }

        if(present.getGiver() != null){
            updatedItem.setGiver(present.getGiver());
        }

        if(present.getSize() != null){
            updatedItem.setSize(present.getSize());
        }

        presentJpaRepository.save(updatedItem);
    }

    /*
    Put one
     */

    /*
    Delete one
     */
    public void deleteWishlistItemForId(Integer id) throws ResourceNotFoundException{
        wishItemJpaRepository.delete(id);
    }

    public void deletePresentForId(Integer id) throws ResourceNotFoundException{
        presentJpaRepository.delete(id);
    }

    /*
    Delete all
     */
    public void clearWishlist(){
        wishItemJpaRepository.deleteAll();
    }

    public void clearPresents(){
        presentJpaRepository.deleteAll();
    }

    //Logic methods
    public List<String> guessPresents(){

        List<WishItem> wishlist = wishItemJpaRepository.findAll();
        List<Present> presents = presentJpaRepository.findAll();

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

        addWishItems(wishlist);

        return guessPresents();
    }

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
