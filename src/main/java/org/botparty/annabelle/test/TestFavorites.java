package org.botparty.annabelle.test;

import org.botparty.annabelle.domain.Favorites;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luke on 1/6/2017.
 */
public class TestFavorites {

    public static Favorites create() {
        Favorites favorites = new Favorites();

        Map<String,String> favoriteMap = new HashMap<>(9);

        favoriteMap.put("Hey","Hey");
        favoriteMap.put("Cheese","Cheese");
        favoriteMap.put("Freeze","Freeze");
        favoriteMap.put("Name","hello mah name is Anna bell ah am a robot with Bot Party, an interactive human and robot experience.");
        favoriteMap.put("Selfie","it is selfie time yall should take a selfie with me");
        favoriteMap.put("Chill Bro","Chill out brother");
        favoriteMap.put("Chill Sis","Chill out sister");
        favoriteMap.put("Thank","Thank you so much");
        favoriteMap.put("Sorry","Sorry.");

        favorites.setFavorites(favoriteMap);

        return favorites;
    }
}
