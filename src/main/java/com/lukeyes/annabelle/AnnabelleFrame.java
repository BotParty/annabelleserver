package com.lukeyes.annabelle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukeyes.annabelle.domain.Script;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class AnnabelleFrame extends JFrame implements  ActionListener {

    JList<String> scriptList = null;
    JList<String> lineList = null;
    JMenuItem menuItemSave;
    JMenuBar menuBar;
    JButton sendButton;
    JTextField puppetTextField;

    public static AnnabelleFrame createAndShowGUI() {
        AnnabelleFrame frame = new AnnabelleFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

     //   frame.pack();
        frame.setState(Frame.NORMAL);
        frame.setVisible(true);
        return frame;
    }

    public AnnabelleFrame() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,3));

        JPanel scriptPanel = listPanel();

        mainPanel.add(scriptPanel);

        JPanel scriptContentPanel = scriptContentPanel();
        mainPanel.add(scriptContentPanel);

        JPanel miscPanel = miscPanel();
        mainPanel.add(miscPanel);

        this.add(mainPanel);

        createMenu();

        GraphicsDevice defaultDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
       // Rectangle screenBounds = defaultDevice.getDefaultConfiguration().getBounds();
        this.setSize(1024, 768);
      //  this.setSize(screenBounds.width, screenBounds.height);
   //     mainPanel.setSize(screenBounds.width, screenBounds.height);
   }

    public JPanel listPanel() {
        JPanel scriptPanel = new JPanel(new BorderLayout());

        JList<String> list = new JList<>();
        list.setModel(Data.getInstance().scriptModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        list.setSelectedIndex(0);
        list.getSelectionModel().addListSelectionListener(Controller.getInstance().scriptListController);

        scriptPanel.add(list, BorderLayout.CENTER);

        scriptList = list;

        return scriptPanel;
    }

    public JPanel scriptContentPanel() {
        JPanel scriptContentPanel = new JPanel(new BorderLayout());

        JList<String> list = new JList<>();
        list.setModel(Data.getInstance().scriptContentModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.addListSelectionListener(Controller.getInstance().scriptContentController);

        scriptContentPanel.add(list, BorderLayout.CENTER);

        lineList = list;

        return scriptContentPanel;
    }

    public JPanel miscPanel() {
        JPanel miscPanel = new JPanel();
     //   miscPanel.setLayout(new BoxLayout(miscPanel,BoxLayout.PAGE_AXIS));
        miscPanel.setLayout(new GridLayout(3,1));

        JPanel puppetPanel = puppetPanel();
        miscPanel.add(puppetPanel);

        JPanel favoritesPanel = favoritesPanel();
        miscPanel.add(favoritesPanel);

        JPanel historyPanel = historyPanel();
        miscPanel.add(historyPanel);

        return miscPanel;
    }

    public JPanel puppetPanel() {
        JPanel puppetPanel = new JPanel();
        puppetPanel.setLayout(new GridBagLayout());

        puppetTextField = new JTextField(50);
        puppetTextField.getDocument().addDocumentListener(Controller.getInstance().puppetController);
        sendButton = new JButton("Send");
        sendButton.addActionListener(Controller.getInstance().puppetController);

        GridBagConstraints textFieldContrainsts = new GridBagConstraints();
        textFieldContrainsts.fill = GridBagConstraints.HORIZONTAL;
        textFieldContrainsts.gridx = 0;
        textFieldContrainsts.gridy = 0;
        textFieldContrainsts.gridwidth = 3;
        textFieldContrainsts.gridheight = 1;
        textFieldContrainsts.weightx = 0.5;
        textFieldContrainsts.weighty = 0.5;
        puppetPanel.add(puppetTextField, textFieldContrainsts);

        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 3;
        buttonConstraints.gridwidth = 1;
        puppetPanel.add(sendButton, buttonConstraints);

        return puppetPanel;
    }

    public JPanel favoritesPanel() {
        JPanel favoritesPanel = new JPanel();
        favoritesPanel.setLayout(new GridLayout(3,3));

        Set<String> keySet = Data.getInstance().favorites.getFavorites().keySet();
        for(String key : keySet) {
            JButton button = new JButton(key);
            button.addActionListener(Controller.getInstance().favoritesController);
            favoritesPanel.add(button);

        }

        return favoritesPanel;
    }

    public JPanel historyPanel() {
        JPanel historyPanel = new JPanel(new BorderLayout());

        JList<String> list = new JList<>();
        list.setModel(Data.getInstance().historyModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        JLabel label = new JLabel("History");
        historyPanel.add(label, BorderLayout.NORTH);

        historyPanel.add(list, BorderLayout.CENTER);

        return historyPanel;
    }

    public void createMenu() {
        menuItemSave = new JMenuItem("Save");
        menuItemSave.addActionListener(this);

        JMenu menu = new JMenu("File");
        menu.add(menuItemSave);

        menuBar = new JMenuBar();
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuItemSave) {

            // save current script
            System.out.println("Save current script");

            String title = scriptList.getSelectedValue();

            Script currentScript = Data.getInstance().scripts.get(title);

            ObjectMapper mapper = new ObjectMapper();
            String scriptAsJSONString = null;
            try {
                scriptAsJSONString = mapper.writeValueAsString(currentScript);
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
            System.out.println(scriptAsJSONString);
        }
    }


}
