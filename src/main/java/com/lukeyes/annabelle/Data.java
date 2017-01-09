package com.lukeyes.annabelle;

import com.lukeyes.annabelle.domain.Favorites;
import com.lukeyes.annabelle.domain.Script;
import com.lukeyes.annabelle.test.StoneSoup;
import com.lukeyes.annabelle.test.TestFavorites;
import com.lukeyes.annabelle.test.Twinkle;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Data {

    static Data instance = null;

    public static Data getInstance() {
        if(instance == null) {
            instance = new Data();
        }

        return instance;
    }

    Map<String, Script> scripts;
    Favorites favorites;
    String puppetText;


    DefaultListModel<String> historyModel;
    DefaultListModel<String> scriptModel;
    DefaultListModel<String> scriptContentModel;

    public DefaultListModel<String> getScriptModel() {
        return scriptModel;
    }

    public DefaultListModel<String> getScriptContentModel() {
        return scriptContentModel;
    }

    public Map<String, Script> getScripts() {
        return scripts;
    }

    public Favorites getFavorites() {
        return favorites;
    }

    public String getPuppetText() {
        return puppetText;
    }

    public void setPuppetText(String puppetText) {
        this.puppetText = puppetText;
    }

    private Data() {
        scripts = new HashMap<>();

        Script twinkle = Twinkle.create();
        scripts.put(twinkle.title, twinkle);

        Script stoneSoup = StoneSoup.create();
        scripts.put(stoneSoup.title, stoneSoup);

        Set<String> titles = scripts.keySet();
        scriptModel = new DefaultListModel<>();
        titles.forEach(scriptModel::addElement);

        favorites = TestFavorites.create();
        historyModel = new DefaultListModel<>();

        scriptContentModel = new DefaultListModel<>();

        Script currentScript = scripts.get("Twinkle Twinkle Little Star");
        currentScript.lines.forEach(scriptContentModel::addElement);
    }
}
