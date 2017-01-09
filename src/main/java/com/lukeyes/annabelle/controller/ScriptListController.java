package com.lukeyes.annabelle.controller;

import com.lukeyes.annabelle.Data;
import com.lukeyes.annabelle.domain.Script;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ScriptListController implements ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {

            ListSelectionModel listSelectionModel = (ListSelectionModel) e.getSource();

            int firstIndex = listSelectionModel.getMinSelectionIndex();
            System.out.println("Index: " + firstIndex);

            String currentTitle = Data.getInstance().getScriptModel().getElementAt(firstIndex);
            System.out.println("Current Title - " + currentTitle);

            Data.getInstance().getScriptContentModel().clear();
            Script currentScript = Data.getInstance().getScripts().get(currentTitle);
            currentScript.lines.forEach(Data.getInstance().getScriptContentModel()::addElement);
        }
    }
}
