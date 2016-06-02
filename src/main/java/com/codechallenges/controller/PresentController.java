package com.codechallenges.controller;

import com.codechallenges.entity.Present;
import com.codechallenges.service.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin.mills on 5/25/2016.
 */

@RestController
@RequestMapping("/presents")
public class PresentController {

    @Autowired
    PresentService presentService;

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
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"})
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
    public void updatePresent(@RequestBody Present present, @RequestParam int id){

        presentService.updatePresentForId(present, id);

    }

    /**
     * Add a present item to the existing list
     * @param present
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"})
    public void replacePresent(Present present){
        presentService.replacePresent(present);
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
