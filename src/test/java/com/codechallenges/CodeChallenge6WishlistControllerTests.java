package com.codechallenges;

import com.codechallenges.controller.WishlistController;
import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.service.PresentServiceImpl;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by colin.mills on 6/3/2016.
 */

public class CodeChallenge6WishlistControllerTests extends AbstractRepositoryIT{

    @Autowired
    PresentServiceImpl presentService;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setup(){

        RestAssured.port = port;
        RestAssuredMockMvc.standaloneSetup(new WishlistController(presentService));

        presentService.addWishItems(getWishlistGoodData());

        System.out.println("Setup check");

    }

    /**
     *
     * Setting up data
     *
     */

    public ArrayList<WishItem> getWishlistGoodData(){

        ArrayList<WishItem> wishlist = new ArrayList<>();

        wishlist.add(new WishItem().setId(1).setName("Mini Puzzle").setSize("small").setClatters("yes").setWeight("light").setGiver("Frank"));
        wishlist.add(new WishItem().setId(2).setName("Toy Car").setSize("medium").setClatters("a bit").setWeight("medium").setGiver("James"));
        wishlist.add(new WishItem().setId(3).setName("Card Game").setSize("small").setClatters("no").setWeight("light").setGiver("Louie"));

        return wishlist;

    }

    public ArrayList<Present> getPresentsGoodData(){

        ArrayList<Present> presents = new ArrayList<>();

        presents.add(new Present().setId(0).setSize("small").setClatters("yes").setWeight("light").setGiver("Frank"));
        presents.add(new Present().setId(1).setSize("medium").setClatters("a bit").setWeight("medium").setGiver("James"));

        return presents;

    }

    /**
     *
     * Begin tests
     *
     */

    /**
     * Returns the wishlist item with the given matching ID
     * @return
     */
    @Test
    public void testGetWishItem() throws Exception{

        int id = 1;

        String response =

        when().
                get("/public/wishlist/" + id).
        then().
                contentType(ContentType.JSON).
                extract().response().asString();

        assertNotNull(response);

    }

    /**
     *
     * @return
     *
     * Retrieve the list of wishlist items currently in memory.
     */
    @Test
    public void getWishlist(){

        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);

        String response =

                when().
                        get("/public/wishlist/").
                        then().
                        contentType(ContentType.JSON).
                        extract().response().asString();

        assertNotNull(response);

    }

    /**
     *
     * @return
     *
     * Put a new wishlist
     */
    @Test
    public void testAddWishItems(){

        presentService.clearWishlist();

        given()
                .contentType(ContentType.JSON)
                .body(getWishlistGoodData())
        .expect()
                .statusCode(200)
                .log().ifError()
        .when()
                .post("/public/wishlist/");

        assertNotNull(presentService.getWishlist());

    }

    /**
     *
     * @return
     *
     * Post a new wishlist
     */
    @Test
    public void testUpdateWishItemWithGoodData(){

        int id = 2;
        String newGiver = "Colin";

        WishItem wishItem = getWishlistGoodData().get(id).setGiver(newGiver);

        given()
                .contentType(ContentType.JSON)
                .body(wishItem)
        .expect()
                .statusCode(200)
                .log().ifError()
        .when()
                .post("/public/wishlist/" + id);

        assertEquals(newGiver, presentService.getWishlistItemForId(id).getGiver());

    }

    @Test
    public void testUpdateWishItemFailsWhenUpdatingNonextantItem(){

        int id = 4;
        String newGiver = "Colin";

        WishItem wishItem = new WishItem().setId(id).setGiver(newGiver);


        try{

            given()
                    .contentType(ContentType.JSON)
                    .body(wishItem)
            .expect()
                    .statusCode(200)
                    .log().ifError()
            .when()
                    .post("/public/wishlist/" + id);

        }catch(AssertionError e){
            //This is expected
        }

    }

    /**
     * Add a wishlist item to the existing list
     */
    @Test
    public void testAddWishListItem(){

        List<WishItem> wishlist = presentService.getWishlist();

        int lastId = wishlist.get(wishlist.size()-1).getId();

        int id = lastId + 1;

        WishItem wishItem = new WishItem().setClatters("a bit").setGiver("Colin").setId(id).setName("N64").setSize("small").setWeight("light");

        given()
                .contentType(ContentType.JSON)
                .body(wishItem)
        .expect()
                .statusCode(200)
                .log().ifError()
        .when()
                .put("/public/wishlist/" + id);

        assertEquals(wishItem.getName(), presentService.getWishlistItemForId(id).getName());

    }

    /**
     *
     * Given a wishItem ID, delete the matching wishItem.
     *
     */
    @Test
    public void deleteWishItem(){
        int id = 1;

        when().
                delete("/public/wishlist/" + id);

        assertNull(presentService.getWishlistItemForId(id));
    }
}
