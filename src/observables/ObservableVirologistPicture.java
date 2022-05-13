package observables;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import main.Virologist;

/**
 * Observable for the picture of the virologist.
 */
public class ObservableVirologistPicture implements IObservable {

    /**
     * Virologist.
     */
    private final Virologist virologist;

    /**
     * Constructor
     * @param v virologist
     */
    public ObservableVirologistPicture(Virologist v) {
        virologist = v;
    }

    /**
     * Creates the UI component for the picture of the virologist.
     * @return JComponent
     */
    @Override
    public JComponent onPaint() {
        try {
            Image picture = ImageIO.read(new File(virologist.getImagePath()));
            Image newPicture = picture.getScaledInstance(250, 250, Image.SCALE_DEFAULT);
            return new JLabel(new ImageIcon(newPicture));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JLabel("Picture not found.");
    }
}
