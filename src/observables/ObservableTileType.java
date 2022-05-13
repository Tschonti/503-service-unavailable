package observables;

import java.awt.*;
import javax.swing.*;
import tiles.Tile;

/**
 * Observable for the name of the tiles.
 */
public class ObservableTileType implements IObservable {

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
    public ObservableTileType(Tile t, Color c) {
        tile = t;
        color = c;
    }

    /**
     * Creates the UI component for the name of the tile.
     * @return JComponent
     */
    @Override
    public JComponent onPaint() {
        JLabel res = new JLabel(tile.getClass().getName().split("\\.")[1]);
        res.setForeground(color);
        return res;
    }
}
