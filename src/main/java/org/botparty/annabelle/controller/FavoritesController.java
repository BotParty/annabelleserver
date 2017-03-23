package org.botparty.annabelle.controller;

import org.botparty.annabelle.Controller;
import org.botparty.annabelle.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FavoritesController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            String sayText = Data.getInstance().getFavorites().getFavorites().get(buttonText);
            Controller.getInstance().send(sayText);
    }
    }
}
