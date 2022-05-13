package observables;

import javax.swing.*;
import main.Virologist;

/**
 * Observable for the name of the virologist.
 */
public class ObservableVirologistName implements IObservable {

    /**
     * Virologist.
     */
    private final Virologist virologist;

    /**
     * Constructor
     * @param v virologist
     */
    public ObservableVirologistName(Virologist v) {
        virologist = v;
    }

    /**
     * Creates the UI component for the name of the virologist.
     * @return JComponent
     */
    @Override
    public JComponent onPaint() {
        return new JLabel(virologist.getName());
    }

    public Virologist getVirologist() {
        return virologist;
    }
}
