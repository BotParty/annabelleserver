package org.botparty.annabelle.controller;

import org.botparty.annabelle.Data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by luke on 3/31/2017.
 */
public class AddTextController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String puppetText = Data.getInstance().getPuppetText();
        System.out.println("Puppet Text");

        Data.getInstance().getScriptContentModel().addElement(puppetText);
        String currentTitle = Data.getInstance().getCurrentScriptTitle();
        Data.getInstance().getScripts().get(currentTitle).lines.add(puppetText);
    }
}
