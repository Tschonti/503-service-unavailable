package observables;

import static main.Constants.numberOfActions;

import javax.swing.*;
import main.Virologist;

/**
 * Observable for the actions of the virologist.
 */
public class ObservableVirologistActions implements IObservable {

    /**
     * Virologist.
     */
    private final Virologist virologist;

    /**
     * Constructor
     * @param v virologist
     */
    public ObservableVirologistActions(Virologist v) {
        virologist = v;
    }

    /**
     * Creates the UI component for the actions of the virologist.
     * @return JComponent
     */
    @Override
    public JComponent onPaint() {
        return new JLabel("Actions left: " + virologist.getActionsLeft() + "/" + numberOfActions);
    }
}
