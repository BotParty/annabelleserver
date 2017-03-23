package org.botparty.annabelle;

import org.botparty.annabelle.controller.FavoritesController;
import org.botparty.annabelle.controller.ScriptContentController;
import org.botparty.annabelle.controller.ScriptListController;
import org.botparty.annabelle.controller.SendController;

public class Controller {

    private static Controller instance;

    public static Controller getInstance() {
        if( instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    FavoritesController favoritesController;
    SendController puppetController;
    ScriptListController scriptListController;
    ScriptContentController scriptContentController;

    private Controller() {
        favoritesController = new FavoritesController();
        puppetController = new SendController();
        scriptListController = new ScriptListController();
        scriptContentController = new ScriptContentController();
    }

    public void send(String text) {
        System.out.println(text);
        ChatServer.getInstance().sendToAll(text);
        Data.getInstance().historyModel.addElement(text);
    }
}
