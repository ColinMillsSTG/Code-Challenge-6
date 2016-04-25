package com.codechallenges.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Implementation of data requests to be performed against lists of presents and wish lists.
 */

@Service
public class PresentServiceImpl implements PresentService{
    public ArrayList<String> guessPresents(String jsonWishList, String jsonPresents){

        ObjectMapper mapper = new ObjectMapper();

        ArrayList<String> confirmedPresents = new ArrayList<>();

        try {
            ArrayList<Map<String, String>> wishlist;
            ArrayList<Map<String, String>> presents;

            //parse out lists received from endpoint
            wishlist = mapper.readValue(jsonWishList, new TypeReference<ArrayList<Map<String,String>>>(){});
            presents = mapper.readValue(jsonPresents, new TypeReference<ArrayList<Map<String,String>>>(){});

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
