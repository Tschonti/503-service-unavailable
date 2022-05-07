package main;

import agents.Agent;
import equipments.Equipment;
import equipments.UsableEquipment;
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
    JComboBox<Object> robOptions;
    JComboBox<Object> dropOptions;
    JComboBox<Object> craftOptions;
    JComboBox<Object> castOnOptions;
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

    /**
     * A Controllertől elkéri az soron lévő játékost, és annak összes birtokolt objektumától elkéri a megjelenítőjét.
     */
    public void Paint() {
        moveOptions   = new JComboBox<>(controller.getActivePlayer().getActiveTile().getNeighbours().toArray());
        usableOptions = new JComboBox<>(controller.getActivePlayer().getInventory().getUsableEquipments().toArray());
        dropOptions   = new JComboBox<>(controller.getActivePlayer().getInventory().getEquipments().toArray());
        craftOptions  = new JComboBox<>(controller.getActivePlayer().getInventory().getLearntCodes().toArray());
        castOnOptions = new JComboBox<>(controller.getActivePlayer().getActiveTile().getPlayers().toArray());
        robOptions    = new JComboBox<>(controller.getActivePlayer().getActiveTile().getPlayersToStealFrom().toArray());
        Virologist stolenFrom = (Virologist)robOptions.getSelectedItem();
        if(stolenFrom != null)
            stealEqOptions = new JComboBox<>(stolenFrom.getInventory().getEquipments().toArray());
    }

    public void onPassClick() {
        controller.pass();
    }

    public void onMoveClick() {
        controller.move((Tile)moveOptions.getSelectedItem());
    }

    public void onUseClick() {
        usableOptions = new JComboBox<>(controller.getActivePlayer().getInventory().getUsableEquipments().toArray());
        Object selected = usableOptions.getSelectedItem();
        if(selected instanceof Agent)
            controller.use((Agent)selected, (Virologist) castOnOptions.getSelectedItem());
        else if(selected instanceof UsableEquipment)
            controller.use((UsableEquipment) selected, (Virologist) castOnOptions.getSelectedItem());
    }

    public void onRobClick() {
        robOptions = new JComboBox<>(controller.getActivePlayer().getActiveTile().getPlayersToStealFrom().toArray());
        controller.steal((Virologist) robOptions.getSelectedItem(), (Equipment) stealEqOptions.getSelectedItem());
    }

    public void onDropClick() {
        controller.drop((Equipment) dropOptions.getSelectedItem());
    }

    public void onCollectClick() {
        controller.collect();
    }

    public void onCraftClick() {
        if(craftOptions.getSelectedItem() != null)
            controller.craft((GeneticCode)(craftOptions.getSelectedItem()));
    }
}
