package org.botparty.annabelle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.botparty.annabelle.controller.*;
import org.botparty.annabelle.domain.CommunicationData;

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

        // set up the Communications Data to go from server->face
        CommunicationData data = new CommunicationData("server","face",text);

        // take communications data and wrap it as a json object
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.convertValue(data, JsonNode.class);
        String convertedText = node.asText();

        ChatServer.getInstance().sendToAll(convertedText);
        Data.getInstance().historyModel.addElement(text);
    }

    public SendController getPuppetController() {
        return puppetController;
    }

    public AddTextController getAddTextController() {
        return addTextController;
    }

    public ScriptContentController getScriptContentController() {
        return scriptContentController;
    }

}
