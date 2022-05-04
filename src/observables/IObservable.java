package observables;

import javax.swing.*;

/**
 * Interface for all observables
 */
public interface IObservable {
    /**
     * Creates the UI component.
     * @return JComponent
     */
    JComponent onPaint();
}
