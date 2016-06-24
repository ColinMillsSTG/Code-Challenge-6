package com.codechallenges;

import com.codechallenges.controller.WishlistController;
import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.exceptions.ResourceNotFoundException;
import com.codechallenges.repository.PresentJpaRepository;
import com.codechallenges.repository.WishItemJpaRepository;
import com.codechallenges.service.PresentService;
import com.codechallenges.service.PresentServiceImpl;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by colin.mills on 4/27/2016.
 *
 * Tests to check PresentServiceImpl functionality.
 */

public class CodeChallenge6PresentServiceTests extends AbstractRepositoryIT{

    @Autowired
    PresentServiceImpl presentService;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setup(){

        RestAssured.port = port;
        RestAssuredMockMvc.standaloneSetup(new WishlistController(presentService));

        presentService.addPresents(getPresentsGoodData());

        System.out.println("Setup check");

    }

    public ArrayList<WishItem> getWishlistGoodData(){

        ArrayList<WishItem> wishlist = new ArrayList<>();

        wishlist.add(new WishItem().setId(1).setName("Mini Puzzle").setSize("small").setClatters("yes").setWeight("light").setGiver("Frank"));
        wishlist.add(new WishItem().setId(2).setName("Toy Car").setSize("medium").setClatters("a bit").setWeight("medium").setGiver("James"));
        wishlist.add(new WishItem().setId(3).setName("Card Game").setSize("small").setClatters("no").setWeight("light").setGiver("Louie"));

        return wishlist;

    }

    public ArrayList<WishItem> getWishlistBadData(){

        ArrayList<WishItem> wishlist = new ArrayList<>();

        wishlist.add(new WishItem().setName("blah").setSize("blah").setClatters("blah").setWeight("blah"));
        wishlist.add(new WishItem().setName("Helium").setSize("2").setClatters("0").setWeight(".000002"));
        wishlist.add(new WishItem().setName("janky").setSize("tanky").setClatters("ganky").setWeight("healbot"));

        return wishlist;

    }

    public ArrayList<Present> getPresentsGoodData(){

        ArrayList<Present> presents = new ArrayList<>();

        presents.add(new Present().setId(1).setSize("small").setClatters("yes").setWeight("light").setGiver("Frank"));
        presents.add(new Present().setId(2).setSize("medium").setClatters("a bit").setWeight("medium").setGiver("James"));

        return presents;

    }

    public ArrayList<Present> getPresentsBadData(){

        ArrayList<Present> presents = new ArrayList<>();

        presents.add(new Present().setSize("foo").setClatters("bar").setWeight("baz"));
        presents.add(new Present().setSize("huge").setClatters("I think it's broken").setWeight("weightless"));

        return presents;

    }

    /**
     *
     * Begin tests
     *
     */

    /**
     * Returns the present item with the given matching ID
     * @return
     */
    @Test
    public void testGetPresentForId() throws Exception{

        int id = (int) presentService.getPresents().get(0).getId();

        String response =

                when().
                        get("/presents/" + id).
                        then().
                        contentType(ContentType.JSON).
                        extract().response().asString();

        assertNotNull(response);

    }

    /**
     *
     * @return
     *
     * Retrieve the list of present items currently in memory.
     */
    @Test
    public void getPresents(){

        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);

        String response =

                when().
                        get("/presents/").
                        then().
                        contentType(ContentType.JSON).
                        extract().response().asString();

        assertNotNull(response);

    }

    /**
     *
     * @return
     *
     * Put a new present list
     */
    @Test
    public void testAddPresents(){

        presentService.clearPresents();

        given()
                .contentType(ContentType.JSON)
                .body(getPresentsGoodData())
                .expect()
                .statusCode(200)
                .log().ifError()
                .when()
                .post("/presents/");

        assertNotNull(presentService.getPresents());

    }

    /**
     *
     * @return
     *
     * Post a new present list
     */
    @Test
    public void testUpdatePresentsWithGoodData(){

        int id = 1;
        String newGiver = "Colin";

        Present present = getPresentsGoodData().get(id).setGiver(newGiver);

        given()
                .contentType(ContentType.JSON)
                .body(present)
        .expect()
                .statusCode(200)
        .log()
                .ifError()
        .when()
                .post("/presents/" + id);

        assertEquals(newGiver, presentService.getPresentForId(id).getGiver());

    }

    @Test
    public void testUpdatePresentFailsWhenUpdatingNonextantItem(){

        int id = 4;
        String newGiver = "Colin";

        Present present = new Present().setId(id).setGiver(newGiver);


        try{

            given()
                    .contentType(ContentType.JSON)
                    .body(present)
                    .expect()
                    .statusCode(200)
                    .log().ifError()
                    .when()
                    .post("/presents/" + id);

        }catch(AssertionError e){
            //This is expected
        }

    }

    /**
     * Add a present item to the existing list
     */
    @Test
    public void testAddPresent(){
        int id = presentService.getPresents().size() + 1;

        Present present = new Present().setClatters("a bit").setGiver("Colin").setId(id).setSize("small").setWeight("light");

        given()
                .contentType(ContentType.JSON)
                .body(present)
        .expect()
                .statusCode(200)
        .log()
                .ifError()
        .when()
                .put("/presents/" + id);

        assertEquals(present, presentService.getPresentForId(id));
    }

    /**
     *
     * Given a present ID, delete the matching present.
     *
     */
    @Test
    public void deletePresent(){
        int id = 1;

        when().
                delete("/presents/" + id);

        assertNull(presentService.getPresentForId(id));
    }

}
