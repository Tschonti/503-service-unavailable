package main;

import agents.Agent;
import equipments.Equipment;
import equipments.UsableEquipment;
import observables.*;
import tiles.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GraphicsView {

    private final Controller controller = new Controller(this);

    private final JFrame menu = new JFrame();
    private final JFrame game = new JFrame();

    JTextField nameInput;
    JTextArea textArea;

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

        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
        menu.setSize(550, 400);
        menu.setLocation(550, 50);

        menu.setTitle("Virologist game");
        menu.setResizable(true);
        menu.setLayout(new BorderLayout());
        generateMenu();

        menu.setVisible(true);

        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setSize(800, 800);
        game.setLocation(550, 50);
        game.setTitle("Virologist game");
        game.setResizable(true);
        game.setLayout(new BorderLayout());

    }

    private JComponent getGeneticCodesView() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(new JLabel("Genetic codes:"));
        ArrayList<GeneticCode> codes = controller.getActivePlayer().getInventory().getLearntCodes();
        for (int i = 0; i < codes.size(); i++) {
            p.add(codes.get(i).getObsGeneticCode().onPaint());
        }
        return p;
    }

    private JComponent getNeighboursVirologists() {
        ArrayList<Virologist> virologists = new ArrayList<>();
        Tile t = controller.getActivePlayer().getActiveTile();
        virologists.addAll(t.getPlayers());
        virologists.remove(controller.getActivePlayer());

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(new JLabel("Virologist on your tile:"));
        for (int i = 0; i < virologists.size(); i++) {
            p.add(virologists.get(i).getObsVirologistName().onPaint());
        }
        return p;
    }


    private JComponent getResources() {
        ArrayList<Resource> resources = new ArrayList<>();

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(new JLabel("Resources:"));

        resources.addAll(controller.getActivePlayer().getInventory().getResources());
        for (int i = 0; i < resources.size(); i++) {
            p.add(resources.get(i).getView().onPaint());
        }
        return p;
    }


    private JComponent getEffects() {
        ArrayList<Effect> effects = new ArrayList<>();
        effects.addAll(controller.getActivePlayer().getActiveEffects());
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(new JLabel("Active effects:"));

        for (int i = 0; i < effects.size(); i++) {
            p.add(effects.get(i).getView().onPaint());
        }
        return p;
    }

    private JComponent getUseables() {
        ArrayList<UsableEquipment> useables = new ArrayList<>();
        useables.addAll(controller.getActivePlayer().getInventory().getUsableEquipments());
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(new JLabel("Useables:"));

        for (int i = 0; i < useables.size(); i++) {
            p.add(useables.get(i).getView().onPaint());
        }
        return p;
    }
    public void generateGame() {
        JMenuBar menuBar = new JMenuBar();
        JMenu jMenu = new JMenu("File");

        JMenuItem gameOverMenuItem = new JMenuItem("Menu");
        gameOverMenuItem.addActionListener(e -> {
            game.setVisible(false);
            nameInput.setText("");
            textArea.setText("");
            SwingUtilities.updateComponentTreeUI(menu);
            menu.setVisible(true);
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
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));

        JComponent neighboursLabel = getNeighboursVirologists();
        JComponent resourcesLabel = getResources();
        JComponent effectsLabel = getEffects();
        JComponent useablesLabel = getUseables();

        Virologist activePlayer = controller.getActivePlayer();

        JLabel nameLabel = (JLabel) activePlayer.getObsVirologistName().onPaint();
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel actionsLeftLabel = (JLabel) activePlayer.getObsVirologistActions().onPaint();
        actionsLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel virologistLabel = (JLabel) activePlayer.getObsVirologistPicture().onPaint();
        virologistLabel.setHorizontalAlignment(SwingConstants.CENTER);
        virologistLabel.setPreferredSize(new Dimension(100,100));
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

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));

        moveOptions     = new JComboBox<>();
        usableOnOptions = new JComboBox<>();
        usableOptions   = new JComboBox<>();
        stealFrom = new JComboBox<>();
        stealEqOptions  = new JComboBox<>();
        dropOptions     = new JComboBox<>();
        craftOptions    = new JComboBox<>();

        passButton = new JButton("Pass");
        moveButton = new JButton("Move");
        useButton = new JButton("Use");
        robButton = new JButton("Rob");
        dropButton = new JButton("Drop");
        collectButton = new JButton("Collect");
        craftButton = new JButton("Craft");

        passButton.addActionListener(e -> onPassClick());
        moveButton.addActionListener(e -> onMoveClick());
        useButton.addActionListener(e -> onUseClick());
        robButton.addActionListener(e -> onRobClick());
        dropButton.addActionListener(e -> onDropClick());
        collectButton.addActionListener(e -> onCollectClick());
        craftButton.addActionListener(e -> onCraftClick());

        fillComboBoxes();

        JPanel firstLineActions = new JPanel();
        firstLineActions.setLayout(new FlowLayout());
        firstLineActions.add(passButton);
        actionsPanel.add(firstLineActions);

        JPanel secondLineActions = new JPanel();
        secondLineActions.setLayout(new FlowLayout());
        secondLineActions.add(moveButton);
        secondLineActions.add(moveOptions);
        actionsPanel.add(secondLineActions);

        JPanel thirdLineActions = new JPanel();
        thirdLineActions.setLayout(new FlowLayout());
        thirdLineActions.add(useButton, 2, 0);
        thirdLineActions.add(usableOnOptions, 2, 1);
        thirdLineActions.add(usableOptions, 2, 2);
        actionsPanel.add(thirdLineActions);
        //actionsPanel.add(usableOptions);

        JPanel fourthLineActions = new JPanel();
        fourthLineActions.setLayout(new FlowLayout());
        fourthLineActions.add(robButton);
        fourthLineActions.add(stealFrom);
        fourthLineActions.add(stealEqOptions);
        actionsPanel.add(fourthLineActions);

        JPanel fifthLineActions = new JPanel();
        fifthLineActions.setLayout(new FlowLayout());
        fifthLineActions.add(dropButton);
        fifthLineActions.add(dropOptions);
        actionsPanel.add(fifthLineActions);

        JPanel sixthLineActions = new JPanel();
        sixthLineActions.setLayout(new FlowLayout());
        sixthLineActions.add(collectButton);
        actionsPanel.add(sixthLineActions);

        JPanel seventhLineActions = new JPanel();
        seventhLineActions.setLayout(new FlowLayout());
        seventhLineActions.add(craftButton);
        seventhLineActions.add(craftOptions);
        actionsPanel.add(seventhLineActions);

        JPanel leftPanel = new JPanel();
        leftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        leftPanel.setLayout(new GridLayout(5, 1));
        leftPanel.add(neighboursLabel);
        leftPanel.add(resourcesLabel);
        leftPanel.add(effectsLabel);
        leftPanel.add(useablesLabel);
        gamePanel.add(leftPanel);

        JPanel middlePanel = new JPanel();
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
        middlePanel.add(nameLabel);
        middlePanel.add(actionsLeftLabel);
        middlePanel.add(virologistLabel);
        middlePanel.add(tileLabel);
        gamePanel.add(middlePanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        rightPanel.setLayout(new GridLayout(2, 1));
        rightPanel.add(geneticsLabel);
        rightPanel.add(actionsPanel);
        gamePanel.add(rightPanel);

        gamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        game.add(gamePanel, BorderLayout.CENTER);
    }

    public void generateMenu() {
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
                JFrame errorFrame = new JFrame();
                JOptionPane.showMessageDialog(errorFrame, "At least 2 players are needed!", "Error", JOptionPane.ERROR_MESSAGE);
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
                controller.addPlayer(new Virologist(nameInput.getText()), "Hungary");
                textArea.append(nameInput.getText()+'\n');
                nameInput.setText("");
                nameInput.requestFocus();
                SwingUtilities.updateComponentTreeUI(menu);
            } catch (Exception ex) {
                JFrame errorFrame = new JFrame();
                JOptionPane.showMessageDialog(errorFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    }

    //TODO
    public void gameOver(Virologist winner) {
        System.out.println("Lol vége van a játéknak xd");
    }

    private void fillComboBoxes() {
        Virologist ap = controller.getActivePlayer();
        Tile at = ap.getActiveTile();
        Inventory aInv = ap.getInventory();
        moveOptions.removeAllItems();
        at.getNeighbours().forEach(v -> moveOptions.addItem(v));

        usableOptions.removeAllItems();
        aInv.getUsableEquipments().forEach(v -> usableOptions.addItem(v));
        aInv.getCraftedAgents().forEach(v -> usableOptions.addItem(v));

        usableOnOptions.removeAllItems();
        at.getPlayers().forEach(v -> usableOnOptions.addItem(v));
        useButton.setEnabled(usableOptions.getItemCount() > 0 && usableOnOptions.getItemCount() > 0);

        stealFrom.removeAllItems();
        at.getPlayersToStealFrom().forEach(v -> stealFrom.addItem(v));
        robButton.setEnabled(stealFrom.getItemCount() > 0);

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
        dropButton.setEnabled(dropOptions.getItemCount() > 0);

        craftOptions.removeAllItems();
        aInv.getLearntCodes().forEach(v -> craftOptions.addItem(v));
        craftButton.setEnabled(craftOptions.getItemCount() > 0);

    }

    /**
     * A Controllertől elkéri az soron lévő játékost, és annak összes birtokolt objektumától elkéri a megjelenítőjét.
     */
    public void Paint() {
        fillComboBoxes();
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
        if (usableOnOptions.getSelectedItem() != null && usableOptions.getSelectedItem() != null) {
            Object selected = usableOptions.getSelectedItem();
            Virologist v = (Virologist) usableOnOptions.getSelectedItem();
            if(selected instanceof Agent)
                controller.use(((Agent) selected), v);
            else if(selected instanceof UsableEquipment)
                controller.use((UsableEquipment) selected, v);
            else
                throw new IllegalArgumentException("Invalid item selected selected!");
        } else {
            throw new IllegalArgumentException("No virologist or item selected!");
        }
    }

    public void onRobClick() {
        if (stealFrom.getSelectedItem() != null && stealEqOptions.getSelectedItem() != null) { //TODO equipment ugye lehet null, ezt valahogy kezelni majd
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
        controller.collect();
    }

    public void onCraftClick() {
        if(craftOptions.getSelectedItem() != null) {
            controller.craft((GeneticCode) craftOptions.getSelectedItem());
        } else {
            throw new IllegalArgumentException("No genetic code selected!");
        }
    }
}
