package com.codechallenges.controller;

import com.codechallenges.entity.Present;
import com.codechallenges.service.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Contains end-points for wish list guessing service.
 */

@RestController
@RequestMapping("/api/presents")
public class PresentsController {

    @Autowired
    PresentService presentService;

    @Autowired
    public PresentsController(PresentService presentService){
        this.presentService = presentService;
    }
    /**
     *
     * @return
     *
     * Retrieve the list of presents currently in memory.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
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
    public Present getPresentForId(@PathVariable int id){
        return presentService.getPresentForId(id);
    }

    /**
     *
     * @param presents
     * @return
     *
     * Post a new list of presents
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseStatus(value=HttpStatus.OK)
    public void postPresents(@RequestBody ArrayList<Present> presents){

        presentService.addPresents(presents);

    }

    /**
     *
     * @param present
     * @param id
     * @return
     *
     * Post a new list of presents
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseStatus(value= HttpStatus.OK)
    public void updatePresent(@RequestBody Present present, @PathVariable int id){

        presentService.updatePresentForId(present, id);

    }

    /**
     * Add a present item to the existing list
     * @param present
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"})
    @ResponseStatus(value=HttpStatus.OK)
    public void replacePresent(@RequestBody Present present, @PathVariable int id){
        presentService.replacePresent(present, id);
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
    @ResponseStatus(value=HttpStatus.OK)
    public void deletePresent(@PathVariable int id){
        presentService.deletePresentForId(id);
    }

}
