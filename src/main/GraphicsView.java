package main;

import agents.Agent;
import equipments.Equipment;
import equipments.UsableEquipment;
import tiles.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static main.Constants.numOfVirPics;

public class GraphicsView {

    private final Controller controller = new Controller(this);

    private final JFrame menu = new JFrame();
    private final JFrame help = new JFrame();
    private JFrame game;

    JTextField nameInput;
    JTextArea textArea;

    private JPanel leftPanel;
    JPanel middlePanel;
    private JPanel rightPanel;

    JPanel actionsPanel;
    private JComboBox<Object> moveOptions;
    private JComboBox<Object> usableOptions;
    private JComboBox<Object> usableOnOptions;
    private JComboBox<Object> stealFrom;
    private JComboBox<Object> dropOptions;
    private JComboBox<Object> craftOptions;
    private JComboBox<Object> stealEqOptions;

    private JButton passButton;
    private JButton moveButton;
    private JButton useButton;
    private JButton robButton;
    private JButton dropButton;
    private JButton collectButton;
    private JButton craftButton;

    private final HashSet<Integer> imageNumbers = new HashSet<>();

    public static void setUIFont(FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                Font font = new Font(f.getFontName(), Font.PLAIN, f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }

    public GraphicsView() {
        setUIFont(new FontUIResource(new Font("Comic Sans MS", Font.PLAIN, 18)));

        help.setDefaultCloseOperation(EXIT_ON_CLOSE);
        help.setLocation(550, 50);
        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
        menu.setSize(550, 400);
        menu.setLocation(550, 50);

        menu.setTitle("Virologist game");
        menu.setResizable(true);
        menu.setLayout(new BorderLayout());
        generateMenu();

        menu.setVisible(true);
    }

    private void comboBoxInit(JComboBox<Object> box, int howMany) {
        box.setForeground(new Color(39, 55,115));
        box.setBackground(new Color(141, 208, 186));
        box.setBorder(new LineBorder(new Color(39, 55,115), 2));
        if(howMany <= 1)
            box.setPreferredSize(new Dimension(145, 35));
        if(howMany == 2)
            box.setPreferredSize(new Dimension(70, 35));
    }

    private void buttonInit(JButton b) {
        b.setForeground(new Color(39, 55,115));
        b.setBackground(new Color(141, 208, 186));
        b.setBorder(new LineBorder(new Color(39, 55,115), 2));
        b.setPreferredSize(new Dimension(70, 35));
        b.setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor() {
                return new Color(200, 10, 10);
            }
        });
    }

    private void enableButton(boolean enabled, JButton b) {
        if(enabled) {
            b.setBackground(new Color(141, 208, 186));
            b.setBorder(new LineBorder(new Color(39, 55,115), 2));
        }
        else {
            b.setBackground(new Color(39, 55, 115));
            b.setBorder(new LineBorder(new Color(120, 0,0), 2));
        }
        b.setEnabled(enabled);
    }

    private JComponent getGeneticCodesView() {
        JPanel p = new JPanel();
        p.setBackground(new Color(251, 248, 190));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel gcLabel = new JLabel("Genetic codes:");
        gcLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        p.add(gcLabel);

        controller.getActivePlayer().getInventory().getLearntCodes().forEach(c->p.add(c.getView().onPaint()));
        /*ArrayList<GeneticCode> codes = controller.getActivePlayer().getInventory().getLearntCodes();
        codes.forEach(c->p.add(c.getView().onPaint()));
        for (int i = 0; i < codes.size(); i++) {
            p.add(codes.get(i).getView().onPaint());
        }*/
        return p;
    }

    private JComponent getNeighboursVirologists() {
        Tile t = controller.getActivePlayer().getActiveTile();
        ArrayList<Virologist> virologists = new ArrayList<>(t.getPlayers());
        virologists.remove(controller.getActivePlayer());

        JPanel p = new JPanel();
        p.setBackground(new Color(251, 248, 190));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel neighLabel = new JLabel("Virologist on your tile:");
        neighLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        p.add(neighLabel);
        virologists.forEach(v->p.add(v.getObsVirologistName().onPaint()));
        /*for (int i = 0; i < virologists.size(); i++) {
            p.add(virologists.get(i).getObsVirologistName().onPaint());
        }*/
        return p;
    }

    private JComponent getResources() {
        JPanel p = new JPanel();
        p.setBackground(new Color(251, 248, 190));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel resLabel = new JLabel("Resources:");
        resLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        p.add(resLabel);

        ArrayList<Resource> resources = new ArrayList<>(controller.getActivePlayer().getInventory().getResources());
        for (Resource resource : resources) {
            JLabel jl = (JLabel) resource.getView().onPaint();
            jl.setText(jl.getText() + "/" + controller.getActivePlayer().getInventory().getMaxResourceAmount());
            p.add(jl);
        }
        return p;
    }

    private JComponent getEffects() {
        JPanel p = new JPanel();
        p.setBackground(new Color(251, 248, 190));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel aeLabel = new JLabel("Active effects:");
        aeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        p.add(aeLabel);
        controller.getActivePlayer().getActiveEffects().forEach(e->p.add(e.getView().onPaint()));
         /*ArrayList<Effect> effects = new ArrayList<>(controller.getActivePlayer().getActiveEffects());
        effects.forEach(e->p.add(e.getView().onPaint()));
        for (int i = 0; i < effects.size(); i++) {
            p.add(effects.get(i).getView().onPaint());
        }*/
        return p;
    }

    private JComponent getUsables() {
        JPanel p = new JPanel();
        p.setBackground(new Color(251, 248, 190));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel usablesLabel = new JLabel("Usables:");
        usablesLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        p.add(usablesLabel);

        controller.getActivePlayer().getInventory().getUsableEquipments().forEach(u->p.add(u.getView().onPaint()));
        /*ArrayList<UsableEquipment> usables = new ArrayList<>(controller.getActivePlayer().getInventory().getUsableEquipments());
        usables.forEach(u->p.add(u.getView().onPaint()));
        for (int i = 0; i < usables.size(); i++) {
            p.add(usables.get(i).getView().onPaint());
        }*/
        controller.getActivePlayer().getCraftedAgents().forEach(a -> p.add(a.getView().onPaint()));
        return p;
    }

    private JComponent getCollectable() {
        JPanel p = new JPanel();
        p.setBackground(new Color(251, 248, 190));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel colLabel = new JLabel("Collectable:");
        colLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        p.add(colLabel);

        Collectable c = controller.getActivePlayer().getActiveTile().getCollectableItem();
        if (c != null) {
            JLabel label = (JLabel) c.getView().onPaint();
            String str = label.getText();
            if (str.contains("#")) {
                str = str.substring(0, str.indexOf("#") - 1);
            }
            if (str.contains(":")) {
                str = str.substring(0, str.indexOf(":"));
            }
            p.add(new JLabel(str));
        }
        else {
            p.add(new JLabel("-"));
        }
        return p;

    }

    public void generateGame() {
        game = new JFrame();
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setSize(1200, 800);
        game.setLocation(550, 50);
        game.setTitle("Virologist game");
        game.setResizable(true);
        game.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu jMenu = new JMenu("File");

        JMenuItem gameOverMenuItem = new JMenuItem("Menu");
        gameOverMenuItem.addActionListener(e -> {
            game.setVisible(false);
            nameInput.setText("");
            textArea.setText("");
            SwingUtilities.updateComponentTreeUI(menu);
            menu.setVisible(true);
            help.setVisible(false);
            controller.endGame();
        });
        jMenu.add(gameOverMenuItem);
        menuBar.add(jMenu);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        jMenu.add(exitMenuItem);
        menuBar.add(jMenu);

        game.add(menuBar);
        game.setJMenuBar(menuBar);

        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(new Color(251, 248, 190));
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));

        JComponent neighboursLabel = getNeighboursVirologists();
        JComponent resourcesLabel = getResources();
        JComponent effectsLabel = getEffects();
        JComponent usablesLabel = getUsables();

        Virologist activePlayer = controller.getActivePlayer();

        JLabel nameLabel = (JLabel) activePlayer.getObsVirologistName().onPaint();
        nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel actionsLeftLabel = (JLabel) activePlayer.getObsVirologistActions().onPaint();
        actionsLeftLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        actionsLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel virologistLabel = (JLabel) activePlayer.getObsVirologistPicture().onPaint();
        virologistLabel.setHorizontalAlignment(SwingConstants.CENTER);
        virologistLabel.setPreferredSize(new Dimension(100,100));

        JComponent collectableLabel = getCollectable();
        /*try {
            virologistLabel = new JLabel(new ImageIcon(ImageIO.read(new File(controller.getPlayers().get(0).getImagePath()))), SwingConstants.CENTER);
            virologistLabel.setSize(200, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        JLabel tileLabel = (JLabel) activePlayer.getActiveTile().getNameView().onPaint();
        JLabel tileTypeLabel = (JLabel) activePlayer.getActiveTile().getTypeView().onPaint();
        tileLabel.setText("Active tile: " + tileLabel.getText() + ", " + tileTypeLabel.getText());
        tileLabel.setHorizontalAlignment(SwingConstants.CENTER);


        JComponent geneticsLabel = getGeneticCodesView();

        actionsPanel = new JPanel();
        actionsPanel.setBackground(new Color(251, 248, 190));
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));

        moveOptions     = new JComboBox<>();
        usableOnOptions = new JComboBox<>();
        usableOptions   = new JComboBox<>();
        stealFrom       = new JComboBox<>();
        stealEqOptions  = new JComboBox<>();
        dropOptions     = new JComboBox<>();
        craftOptions    = new JComboBox<>();

        comboBoxInit(moveOptions    ,1);
        comboBoxInit(usableOnOptions,2);
        comboBoxInit(usableOptions  ,2);
        comboBoxInit(stealFrom      ,2);
        comboBoxInit(stealEqOptions ,2);
        comboBoxInit(dropOptions    ,1);
        comboBoxInit(craftOptions   ,1);

        passButton = new JButton("Pass");
        moveButton = new JButton("Move");
        useButton = new JButton("Use");
        robButton = new JButton("Rob");
        dropButton = new JButton("Drop");
        collectButton = new JButton("Collect");
        craftButton = new JButton("Craft");

        buttonInit(passButton);
        buttonInit(moveButton);
        buttonInit(useButton);
        buttonInit(robButton);
        buttonInit(dropButton);
        buttonInit(collectButton);
        buttonInit(craftButton);

        passButton.addActionListener(e -> onPassClick());
        moveButton.addActionListener(e -> onMoveClick());
        useButton.addActionListener(e -> onUseClick());
        robButton.addActionListener(e -> onRobClick());
        dropButton.addActionListener(e -> onDropClick());
        collectButton.addActionListener(e -> onCollectClick());
        craftButton.addActionListener(e -> onCraftClick());

        fillComboBoxes();

        JPanel firstLineActions = new JPanel();
        firstLineActions.setBackground(new Color(251, 248, 190));
        firstLineActions.setLayout(new FlowLayout(FlowLayout.LEFT));
        firstLineActions.add(passButton);
        actionsPanel.add(firstLineActions);

        JPanel secondLineActions = new JPanel();
        secondLineActions.setBackground(new Color(251, 248, 190));
        secondLineActions.setLayout(new FlowLayout(FlowLayout.LEFT));
        secondLineActions.add(moveButton);
        secondLineActions.add(moveOptions);
        actionsPanel.add(secondLineActions);

        JPanel thirdLineActions = new JPanel();
        thirdLineActions.setBackground(new Color(251, 248, 190));
        thirdLineActions.setLayout(new FlowLayout(FlowLayout.LEFT));
        thirdLineActions.add(useButton, 2, 0);
        thirdLineActions.add(usableOnOptions, 2, 1);
        thirdLineActions.add(usableOptions, 2, 2);
        actionsPanel.add(thirdLineActions);
        //actionsPanel.add(usableOptions);

        JPanel fourthLineActions = new JPanel();
        fourthLineActions.setBackground(new Color(251, 248, 190));
        fourthLineActions.setLayout(new FlowLayout(FlowLayout.LEFT));
        fourthLineActions.add(robButton);
        fourthLineActions.add(stealFrom);
        fourthLineActions.add(stealEqOptions);
        actionsPanel.add(fourthLineActions);

        JPanel fifthLineActions = new JPanel();
        fifthLineActions.setBackground(new Color(251, 248, 190));
        fifthLineActions.setLayout(new FlowLayout(FlowLayout.LEFT));
        fifthLineActions.add(dropButton);
        fifthLineActions.add(dropOptions);
        actionsPanel.add(fifthLineActions);

        JPanel sixthLineActions = new JPanel();
        sixthLineActions.setBackground(new Color(251, 248, 190));
        sixthLineActions.setLayout(new FlowLayout(FlowLayout.LEFT));
        sixthLineActions.add(collectButton);
        actionsPanel.add(sixthLineActions);

        JPanel seventhLineActions = new JPanel();
        seventhLineActions.setBackground(new Color(251, 248, 190));
        seventhLineActions.setLayout(new FlowLayout(FlowLayout.LEFT));
        seventhLineActions.add(craftButton);
        seventhLineActions.add(craftOptions);
        actionsPanel.add(seventhLineActions);

        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(251, 248, 190));
        leftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        leftPanel.setLayout(new GridLayout(5, 1));
        leftPanel.add(neighboursLabel);
        leftPanel.add(resourcesLabel);
        leftPanel.add(effectsLabel);
        leftPanel.add(usablesLabel);
        gamePanel.add(leftPanel);

        middlePanel = new JPanel();
        middlePanel.setBackground(new Color(251, 248, 190));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        middlePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        actionsLeftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionsLeftLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        virologistLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        virologistLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        tileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tileLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        collectableLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        collectableLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        middlePanel.add(nameLabel);
        middlePanel.add(actionsLeftLabel);
        middlePanel.add(virologistLabel);
        middlePanel.add(tileLabel);
        middlePanel.add(collectableLabel);
        gamePanel.add(middlePanel);

        JPanel space = new JPanel();
        space.setBackground(new Color(251, 248, 190));
        space.setPreferredSize(new Dimension(30,10));

        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(251, 248, 190));
        rightPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(BorderLayout.NORTH, geneticsLabel);
        rightPanel.add(BorderLayout.CENTER, space);
        rightPanel.add(BorderLayout.SOUTH , actionsPanel);
        gamePanel.add(rightPanel);

        gamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        game.add(gamePanel, BorderLayout.CENTER);
        game.pack();
    }

    public void generateMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu helpMenu = new JMenu("File");
        JMenuItem helpMenuItem = new JMenuItem("Help");
        helpMenuItem.addActionListener(e -> {
            //game.setVisible(false);
            menu.setVisible(false);
            generateHelp();
            SwingUtilities.updateComponentTreeUI(help);
            help.setVisible(true);
        });
        helpMenu.add(helpMenuItem);
        menuBar.add(helpMenu);
        menu.add(menuBar);
        menu.setJMenuBar(menuBar);


        JLabel title = new JLabel("Virologist game");
        try {
            title = new JLabel(new ImageIcon(ImageIO.read(new File("resources\\title.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }


        JLabel credits = new JLabel("Developed by service_unavailable");
        try {
            credits = new JLabel(new ImageIcon(ImageIO.read(new File("resources\\credits.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            if (controller.getPlayers().size() < 2) { //TODO controller
                //JFrame errorFrame = new JFrame();
                //JOptionPane.showMessageDialog(errorFrame, "At least 2 players are needed!", "Error", JOptionPane.ERROR_MESSAGE);
                errorFrame("At least 2 players are needed!");
            } else {
                menu.setVisible(false);
                controller.gameStart();
                generateGame();
                game.setVisible(true);
            }

            System.out.println("TODO START"); //TODO
        });
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(startButton, BorderLayout.NORTH);
        buttonPanel.add(quitButton, BorderLayout.SOUTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setAutoscrolls(true);
        JScrollPane textAreaScroll = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        textAreaScroll.setPreferredSize(new Dimension(300,300));


        Virologist.setController(controller);
        JLabel nameLabel = new JLabel("Name:");
        nameInput = new JTextField("", 10);
        nameInput.setToolTipText("Write a name");
        nameInput.setEditable(true);
        nameInput.setSize(500,200);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            try {
                if (nameInput.getText().trim().equals("")) { //TODO controller
                    throw new IllegalArgumentException("Give the player a name!");
                }
                if (controller.getPlayers().size()>=8){
                    throw new IllegalArgumentException("Maximum 8 players can play the game!");
                }
                int imgNum = -1;
                while (imageNumbers.size() < numOfVirPics) {
                    imgNum = (SRandom.nextRandom(numOfVirPics)+1);
                    if (!imageNumbers.contains(imgNum)) {
                        imageNumbers.add(imgNum);
                        break;
                    }
                }
                if (imageNumbers.size() == numOfVirPics) {
                    imageNumbers.clear();
                }
                controller.addPlayer(new Virologist(nameInput.getText(), "resources\\virologist" +  imgNum  + ".png" ), "Hungary");
                controller.getPlayers().forEach(x->System.out.println(x.getName()));
                textArea.append(nameInput.getText()+'\n');
                nameInput.setText("");
                nameInput.requestFocus();
                SwingUtilities.updateComponentTreeUI(menu);
            } catch (Exception ex) {
                errorFrame(ex.getMessage());
            }
        }); //TODO
        addButton.setSize(100, 50);

        JPanel getNewPlayerPanel = new JPanel(new FlowLayout());
        getNewPlayerPanel.setSize(600, 800);
        getNewPlayerPanel.add(nameLabel);
        getNewPlayerPanel.add(nameInput);
        getNewPlayerPanel.add(nameInput);
        getNewPlayerPanel.add(addButton);

        JPanel addPlayerPanel = new JPanel(new BorderLayout());
        addPlayerPanel.add(getNewPlayerPanel, BorderLayout.NORTH);
        addPlayerPanel.add(textAreaScroll, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(buttonPanel, BorderLayout.EAST);
        centerPanel.add(addPlayerPanel, BorderLayout.WEST);

        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.add(title,  BorderLayout.NORTH);
        menuPanel.add(centerPanel, BorderLayout.CENTER);
        menuPanel.add(credits, BorderLayout.SOUTH);

        menu.add(menuPanel);
        menu.pack();
    }

    public void generateHelp() {
        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("How to play");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        helpPanel.add(titleLabel);

        JLabel menuLabel = new JLabel("How to start the game");
        menuLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

        JLabel menuTextLabel = new JLabel("<html>" +
                " - You can add the name of the virologist after the Name: box." +
                "<br>" +
                " - You can add the player one by one with the Add button" +
                "<br>" +
                " - You can start the game with the Start button" +
                "<br>" +
                " - To start the game at least 2 player is required" +
                "<br>" +
                " - You can close the game with the Quit button" +
                "</html>");

        helpPanel.add(menuLabel);
        helpPanel.add(menuTextLabel);

        JLabel gameLabel = new JLabel("How to play");
        gameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

        JLabel gameTextLabel = new JLabel("<html>" +
                " - Players have 2 actions in every round" +
                "<br>" +
                " - All button represents an action" +
                "<br>" +
                " - Only the available action's buttons are active" +
                "<br>" +
                " - If there are options to how to perform the action, you can select it from dropdown lists" +
                "</html>");
        helpPanel.add(gameLabel);
        helpPanel.add(gameTextLabel);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            help.setVisible(false);
            //game.setVisible(false);
            menu.setVisible(true);
        });
        helpPanel.add(backButton);

        help.add(helpPanel);
        help.pack();
    }

    private void errorFrame(String text){
        JFrame errorFrame = new JFrame();
        JOptionPane.showMessageDialog(errorFrame, text, "Error", JOptionPane.ERROR_MESSAGE);
    }

    //TODO
    public void gameOver(Virologist winner) {
        System.out.println("Lol vége van a játéknak xd");
        errorFrame("Lol vége van xd, nyert:" + (winner == null ?  "Medvék " : winner.getName()));

        game.setVisible(false);
        nameInput.setText("");
        textArea.setText("");
        SwingUtilities.updateComponentTreeUI(menu);
        game.pack();
        menu.setVisible(true);
        controller.endGame();

    }

    private void updateMiddlePanel(){
        middlePanel.removeAll();
        Virologist activePlayer = controller.getActivePlayer();

        JLabel nameLabel = (JLabel) activePlayer.getObsVirologistName().onPaint();
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel actionsLeftLabel = (JLabel) activePlayer.getObsVirologistActions().onPaint();
        actionsLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel virologistLabel = (JLabel) activePlayer.getObsVirologistPicture().onPaint();
        virologistLabel.setHorizontalAlignment(SwingConstants.CENTER);
        virologistLabel.setPreferredSize(new Dimension(100,100));

        JLabel tileLabel = (JLabel) activePlayer.getActiveTile().getNameView().onPaint();
        JLabel tileTypeLabel = (JLabel) activePlayer.getActiveTile().getTypeView().onPaint();
        tileLabel.setText("Active tile: " + tileLabel.getText() + ", " + tileTypeLabel.getText());
        tileLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JComponent collectableLabel = getCollectable();

        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        actionsLeftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionsLeftLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        virologistLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        virologistLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        tileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tileLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        collectableLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        collectableLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        middlePanel.add(activePlayer.getObsVirologistName().onPaint());
        middlePanel.add(actionsLeftLabel);
        middlePanel.add(virologistLabel);
        middlePanel.add(tileLabel);
        middlePanel.add(collectableLabel);
    }

    private void fillComboBoxes() {
        Virologist ap = controller.getActivePlayer();
        Tile at = ap.getActiveTile();
        Inventory aInv = ap.getInventory();
        moveOptions.removeAllItems();
        at.getNeighbours().forEach(v -> moveOptions.addItem(v));

        usableOptions.removeAllItems();
        usableOnOptions.removeAllItems();

        aInv.getUsableEquipments().forEach(v -> usableOptions.addItem(v));
        aInv.getCraftedAgents().forEach(v -> usableOptions.addItem(v));
        at.getPlayers().forEach(v -> usableOnOptions.addItem(v));

        boolean useBool = (usableOptions.getItemCount() > 0);

        if (!useBool) {
            usableOptions.removeAllItems();
            usableOnOptions.removeAllItems();
        }
        usableOnOptions.setEnabled(useBool);
        usableOptions.setEnabled(useBool);
        enableButton(useBool,useButton);

        stealFrom.removeAllItems();
        at.getPlayersToStealFrom().forEach(v -> stealFrom.addItem(v));
        stealFrom.setEnabled(stealFrom.getItemCount() > 0);
        enableButton(stealFrom.getItemCount() > 0, robButton);

        stealEqOptions.removeAllItems();
        if (stealFrom.getSelectedItem() != null) {
            Virologist stolenFrom = (Virologist) stealFrom.getSelectedItem();
            if(stolenFrom != null)
                stolenFrom.getInventory().getEquipments().forEach(v -> stealEqOptions.addItem(v));
                stealEqOptions.setEnabled(true);
        } else {
            stealEqOptions.setEnabled(false);
        }

        dropOptions.removeAllItems();
        aInv.getEquipments().forEach(v -> dropOptions.addItem(v));
        dropOptions.setEnabled(dropOptions.getItemCount() > 0);
        enableButton(dropOptions.getItemCount() > 0, dropButton);

        collectButton.setEnabled(at.getCollectableItem() != null);
        enableButton(at.getCollectableItem() != null, collectButton);

        craftOptions.removeAllItems();
        aInv.getLearntCodes().forEach(v -> craftOptions.addItem(v));
        craftOptions.setEnabled(craftOptions.getItemCount() > 0);
        enableButton(craftOptions.getItemCount() > 0, craftButton);

    }

    /**
     * A Controllertől elkéri az soron lévő játékost, és annak összes birtokolt objektumától elkéri a megjelenítőjét.
     */
    public void Paint() {
        leftPanel.removeAll();
        leftPanel.add(getNeighboursVirologists());
        leftPanel.add(getResources());
        leftPanel.add(getEffects());
        leftPanel.add(getUsables());

        updateMiddlePanel();

        rightPanel.removeAll();

        int numOfCodes = controller.getActivePlayer().getInventory().getLearntCodes().size();
        getGeneticCodesView().setPreferredSize(new Dimension(getGeneticCodesView().getWidth(),70 + numOfCodes * 40));
        rightPanel.add(BorderLayout.NORTH, getGeneticCodesView());
        rightPanel.add(BorderLayout.SOUTH, actionsPanel);

        fillComboBoxes();
        SwingUtilities.updateComponentTreeUI(game);
        game.pack();
    }

    public void onPassClick() {
        controller.pass();
    }

    public void onMoveClick() {
        if (moveOptions.getSelectedItem() != null) {
            Tile selected = (Tile)moveOptions.getSelectedItem();
            controller.move(selected);
        } else {
            throw new IllegalArgumentException("No tile selected!");
        }
    }

    public void onUseClick() {
        try {
            if (usableOnOptions.getSelectedItem() != null && usableOptions.getSelectedItem() != null) {
                Object selected = usableOptions.getSelectedItem();
                Virologist v = (Virologist) usableOnOptions.getSelectedItem();
                if (selected instanceof Agent)
                    controller.use(((Agent) selected), v);
                else if (selected instanceof UsableEquipment)
                    controller.use((UsableEquipment) selected, v);
                else
                    throw new IllegalArgumentException("Invalid item selected selected!");
            } else {
                throw new IllegalArgumentException("No virologist or item selected!");
            }
        }catch (Exception e){
            errorFrame(e.getMessage());
        }
    }

    public void onRobClick() {
        if (stealFrom.getSelectedItem() != null) {
            controller.steal((Virologist) stealFrom.getSelectedItem(), (Equipment) stealEqOptions.getSelectedItem());
        } else {
            throw new IllegalArgumentException("No virologist selected!");
        }
    }

    public void onDropClick() {
        if (dropOptions.getSelectedItem() != null) {
            controller.drop((Equipment) dropOptions.getSelectedItem());
        } else {
            throw new IllegalArgumentException("No equipment selected!");
        }
    }

    public void onCollectClick() {
        try {
            controller.collect();
        }catch (Exception e){
            errorFrame(e.getMessage());
        }
    }

    public void onCraftClick() {
        try {
            if (craftOptions.getSelectedItem() != null) {
                controller.craft((GeneticCode) craftOptions.getSelectedItem());
            } else {
                throw new IllegalArgumentException("No genetic code selected!");
            }
        }catch(Exception e){
            errorFrame(e.getMessage());
        }
    }
}
