package observables;

import tiles.Tile;

import javax.swing.*;

/**
 * Observable class for the tiles.
 */
public class ObservableTile implements IObservable {

    /**
     * The observed tile
     */
    private final Tile tile;

    /**
     * Constructor
     * @param tile The observed tile
     */
    public ObservableTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * Creates the UI component for the tile.
     * @return The UI component
     */
    @Override
    public JComponent onPaint() {
        return null;
    }
}
