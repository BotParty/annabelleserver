package com.lukeyes.annabelle.controller;

import com.lukeyes.annabelle.Data;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ScriptContentController implements ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {

            ListSelectionModel listSelectionModel = (ListSelectionModel) e.getSource();

            int firstIndex = listSelectionModel.getMinSelectionIndex();
            System.out.println("Index: " + firstIndex);

            String currentLine = Data.getInstance().getScriptContentModel().getElementAt(firstIndex);
            System.out.println("Current Line - " + currentLine);

        }
    }
}
