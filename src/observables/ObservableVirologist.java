package observables;

import main.Virologist;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Observable for virologist.
 */
public class ObservableVirologist implements IObservable {

    /**
     * Virologist.
     */
    private final Virologist virologist;

    /**
     * Constructor
     * @param v virologist
     */
    public ObservableVirologist(Virologist v) {
        virologist = v;
    }

    /**
     * Creates the UI component for the virologist.
     * @return JComponent
     */
    @Override
    public JComponent onPaint() {
        JPanel virPanel = new JPanel(new BorderLayout());
        virPanel.add(new JLabel(virologist.getName()), BorderLayout.NORTH);
        try {
            virPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File(virologist.getImagePath())))), BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return virPanel;
    }
}
