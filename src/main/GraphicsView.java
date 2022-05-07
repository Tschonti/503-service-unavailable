package main;

import equipments.UsableEquipment;
import observables.*;
import tiles.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GraphicsView {

    private final Controller controller = new Controller(this);

    private final JFrame menu = new JFrame();
    private final JFrame game = new JFrame();

    JTextField nameInput;
    JTextArea textArea;

    JComboBox<Object> moveOptions;
    JComboBox<Object> usableOptions;
    JComboBox<Object> usableOnOptions;
    JComboBox<Object> robOptions;
    JComboBox<Object> dropOptions;
    JComboBox<Object> craftOptions;
    JComboBox<Object> stealEqOptions;

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

        JLabel neighboursLabel = new JLabel("Neighbours");
        neighboursLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel resourcesLabel = new JLabel("Resources");
        resourcesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel effectsLabel = new JLabel("Effects");
        effectsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel useablesLabel = new JLabel("Useables");
        useablesLabel.setHorizontalAlignment(SwingConstants.CENTER);

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


        JLabel geneticsLabel = new JLabel("Genetic codes", SwingConstants.CENTER);
        JLabel actionsLabel = new JLabel("Actions", SwingConstants.CENTER);

        JPanel actionsPanel = new JPanel();

        moveOptions     = new JComboBox<>();
        usableOnOptions = new JComboBox<>();
        usableOptions   = new JComboBox<>();
        robOptions      = new JComboBox<>();
        stealEqOptions  = new JComboBox<>();
        dropOptions     = new JComboBox<>();
        craftOptions    = new JComboBox<>();
        fillComboBoxes();

        JButton passButton = new JButton("Pass");
        JButton moveButton = new JButton("Move");
        JButton useButton = new JButton("Use");
        JButton robButton = new JButton("Rob");
        JButton dropButton = new JButton("Drop");
        JButton collectButton = new JButton("Collect");
        JButton craftButton = new JButton("Craft");

        actionsPanel.add(passButton);
        actionsPanel.add(moveButton);
        actionsPanel.add(moveOptions);
        actionsPanel.add(useButton);
        actionsPanel.add(usableOnOptions);
        actionsPanel.add(usableOptions);
        actionsPanel.add(usableOptions);
        actionsPanel.add(robButton);
        actionsPanel.add(robOptions);
        actionsPanel.add(stealEqOptions);
        actionsPanel.add(dropButton);
        actionsPanel.add(dropOptions);
        actionsPanel.add(collectButton);
        actionsPanel.add(craftButton);
        actionsPanel.add(craftOptions);


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
        rightPanel.add(actionsLabel);
        rightPanel.add(actionsPanel);
        gamePanel.add(rightPanel);

        gamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        game.add(gamePanel, BorderLayout.CENTER);
/*
        //TODO funny, delete later
        for (Virologist player : controller.getPlayers()) {
            game.add(player.getObsVirologist().onPaint(), BorderLayout.CENTER);
        }

 */
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
        at.getNeighbours().stream().map(t -> ((JLabel)t.getNameView().onPaint()).getText()).forEach(v -> moveOptions.addItem(v));

        usableOptions.removeAllItems();
        aInv.getUsableEquipments().stream().map(u -> ((JLabel)u.getView().onPaint()).getText()).forEach(v -> usableOptions.addItem(v));
        aInv.getCraftedAgents().stream().map(u -> ((JLabel)u.getView().onPaint()).getText()).forEach(v -> usableOptions.addItem(v));

        usableOnOptions.removeAllItems();
        at.getPlayers().stream().map(v -> ((JLabel)v.getObsVirologistName().onPaint()).getText()).forEach(v -> usableOnOptions.addItem(v));

        robOptions.removeAllItems();
        at.getPlayersToStealFrom().stream().map(v -> ((JLabel)v.getObsVirologistName().onPaint()).getText()).forEach(v -> robOptions.addItem(v));

        stealEqOptions.removeAllItems();
        if (robOptions.getSelectedItem() != null) {
            Virologist stolenFrom = ((ObservableVirologistName)robOptions.getSelectedItem()).getVirologist();
            if(stolenFrom != null)
                stolenFrom.getInventory().getEquipments().stream().map(e -> ((JLabel)e.getView().onPaint()).getText()).forEach(v -> stealEqOptions.addItem(v));
                stealEqOptions.setEnabled(true);
        } else {
            stealEqOptions.setEnabled(false);
        }

        dropOptions.removeAllItems();
        aInv.getEquipments().stream().map(e -> ((JLabel)e.getView().onPaint()).getText()).forEach(v -> dropOptions.addItem(v));

        craftOptions.removeAllItems();
        aInv.getLearntCodes().stream().map(gc -> ((JLabel)gc.getObsGeneticCode().onPaint()).getText()).forEach(v -> craftOptions.addItem(v));
    }

    /**
     * A Controllertől elkéri az soron lévő játékost, és annak összes birtokolt objektumától elkéri a megjelenítőjét.
     */
    public void Paint() {


    }

    public void onPassClick() {
        controller.pass();
    }

    public void onMoveClick() {
        if (moveOptions.getSelectedItem() != null) {
            controller.move(((ObservableTileName)moveOptions.getSelectedItem()).getTile());
        }
    }

    public void onUseClick() {
        if (usableOnOptions.getSelectedItem() != null && usableOptions.getSelectedItem() != null) {
            Object selected = usableOptions.getSelectedItem();
            Virologist v = ((ObservableVirologistName) usableOnOptions.getSelectedItem()).getVirologist();
            if(selected instanceof ObservableAgent)
                controller.use(((ObservableAgent) selected).getAgent(), v);
            else if(selected instanceof ObservableEquipment)
                controller.use((UsableEquipment) ((ObservableEquipment) selected).getEquipment(), v);
        }

    }

    public void onRobClick() {
        if (robOptions.getSelectedItem() != null && stealEqOptions.getSelectedItem() != null) { //TODO equipment ugye lehet null, ezt valahogy kezelni majd
            controller.steal(((ObservableVirologistName) robOptions.getSelectedItem()).getVirologist(), ((ObservableEquipment) stealEqOptions.getSelectedItem()).getEquipment());
        }
    }

    public void onDropClick() {
        if (dropOptions.getSelectedItem() != null) {
            controller.drop(((ObservableEquipment) dropOptions.getSelectedItem()).getEquipment());
        }
    }

    public void onCollectClick() {
        controller.collect();
    }

    public void onCraftClick() {
        if(craftOptions.getSelectedItem() != null)
            controller.craft(((ObservableGeneticCode) craftOptions.getSelectedItem()).getCode());
    }
}
