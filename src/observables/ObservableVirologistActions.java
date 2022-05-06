package observables;

import main.Virologist;

import javax.swing.*;

import static main.Constants.numberOfActions;

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
