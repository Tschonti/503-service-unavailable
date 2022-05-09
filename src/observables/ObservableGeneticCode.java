package observables;

import main.GeneticCode;

import javax.swing.*;

/**
 * Observable for the genetic code.
 */
public class ObservableGeneticCode implements IObservable {

    /**
     * Genetic code.
     */
    private final GeneticCode code;

    /**
     * Constructor
     * @param gc genetic code
     */
    public ObservableGeneticCode(GeneticCode gc) {
        code = gc;
    }

    /**
     * Creates the UI component for the genetic code.
     * @return JComponent
     */
    @Override
    public JComponent onPaint() {
        return new JLabel(code.toString());
    }

    public GeneticCode getCode() {
        return code;
    }
}
