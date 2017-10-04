package org.botparty.annabelle.panels;

import org.botparty.annabelle.Controller;
import org.botparty.annabelle.Data;
import org.botparty.annabelle.controller.ScriptContentController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ScriptContentPanel extends JPanel {

    private JButton button;

    public ScriptContentPanel() {
        super(new BorderLayout());
        button = new JButton("Send");

        ScriptContentController scriptContentController = Controller.getInstance().getScriptContentController();

        button.addActionListener(scriptContentController);
        this.add(button, BorderLayout.NORTH);

        JList<String> list = new JList<>();
        list.setModel(Data.getInstance().getScriptContentModel());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.getSelectionModel().addListSelectionListener(scriptContentController);

        JScrollPane scrollPane = new JScrollPane(list);

        this.add(scrollPane, BorderLayout.CENTER);

        list.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0), "send");
        list.getActionMap().put("send", scriptContentController.sendAction() );
    }

    public JButton getButton() {
        return button;
    }
}
