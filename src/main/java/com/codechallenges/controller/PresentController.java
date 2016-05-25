package com.codechallenges.controller;

import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.service.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin.mills on 5/25/2016.
 */

@RestController
@RequestMapping("/present")
public class PresentController {

    @Autowired
    PresentService presentService;

    /**
     *
     * @return
     *
     * Retrieve the list of presents currently in memory.
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Present> getPresents(){
        return presentService.getPresents();
    }

    /**
     *
     * @param id
     * @return
     *
     * Given a present ID, find a matching present.
     *
     * Returns 404 if no such present exists.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Present getPresentForId(@RequestParam int id){
        return presentService.getPresentForId(id);
    }

    /**
     *
     * @param presents
     * @return
     *
     * Post a new list of presents
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = {"application/json"})
    public void postPresents(@RequestBody ArrayList<Present> presents){

        presentService.setPresents(presents);

    }

    /**
     * Add a present item to the existing list
     * @param present
     */
    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = {"application/json"})
    public void addPresent(Present present){
        presentService.addPresent(present);
    }

    //add present

    /**
     *
     * @param id
     *
     * Given a present ID, delete the matching present.
     *
     * Returns 404 if no such present exists.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deletePresent(@RequestParam int id){
        presentService.deletePresentForId(id);
    }

}
