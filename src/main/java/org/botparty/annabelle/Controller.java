package org.botparty.annabelle;

import org.botparty.annabelle.controller.*;

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
    AddTextController addTextController;

    private Controller() {
        favoritesController = new FavoritesController();
        puppetController = new SendController();
        scriptListController = new ScriptListController();
        scriptContentController = new ScriptContentController();
        addTextController = new AddTextController();
    }

    public void send(String text) {
        System.out.println(text);
        ChatServer.getInstance().sendToAll(text);
        Data.getInstance().historyModel.addElement(text);
    }

    public SendController getPuppetController() {
        return puppetController;
    }

    public AddTextController getAddTextController() {
        return addTextController;
    }
}
