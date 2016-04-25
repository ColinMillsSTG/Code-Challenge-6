package com.codechallenges.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Implementation of data requests to be performed against lists of presents and wish lists.
 */
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
            for(Map<String,String> present : presents) { //for each present, check each key against...
                for (Map<String, String> wishlistItem : wishlist) { //...each item of the wishlist

                    Boolean containsKey = true; //hope springs eternal

                    for (String key : present.keySet()) { // Just using the hashmap keys allows the input file to contain whatever qualities we want

                        if (wishlistItem.containsKey(key) && !present.get(key).equals(wishlistItem.get(key))) {
                            containsKey = false;
                        }

                        if(!containsKey){ //this wishlist item doesn't match up to what this present seems like, move on
                            break;
                        }

                    }

                    if(containsKey){ //we have a confirmed wishlist present!
                        confirmedPresents.add(wishlistItem.get("name"));
                    }

                }
            }
        }catch(Exception e){
            System.out.println(e.getCause());
        }

        return confirmedPresents;
    }
}
