package observables;

import tiles.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * Observable for the name of the tiles.
 */
public class ObservableTileName implements IObservable {

    /**
     * The observed tile.
     */
    private final Tile tile;

    /**
     * The color of the observed tile
     */
    private final Color color;

    /**
     * Constructor
     * @param t tile
     * @param c color
     */
    public ObservableTileName(Tile t, Color c) {
        tile = t;
        color = c;
    }

    /**
     * Creates the UI component for the name of the tile.
     * @return JComponent
     */
    @Override
    public JComponent onPaint() {
        JLabel res = new JLabel(tile.getName());
        res.setForeground(color);
        return res;
    }
}
