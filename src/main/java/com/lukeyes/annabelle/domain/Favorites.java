package com.lukeyes.annabelle.domain;

import java.util.Map;

public class Favorites {

    private Map<String, String> favorites;

    public Favorites() {
    }

    public Map<String, String> getFavorites() {
        return favorites;
    }

    public void setFavorites(Map<String, String> favorites) {
        this.favorites = favorites;
    }
}
