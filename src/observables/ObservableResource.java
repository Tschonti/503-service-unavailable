package observables;

import javax.swing.*;
import main.Resource;

/**
 * Observable class for the resources.
 */
public class ObservableResource implements IObservable {

    /**
     * The observed resource
     */
    private final Resource resource;

    /**
     * Constructor
     * @param resource The observed resource
     */
    public ObservableResource(Resource resource) {
        this.resource = resource;
    }

    /**
     * Creates the UI component for the resource.
     * @return The UI component
     */
    @Override
    public JComponent onPaint() {
        return new JLabel(resource.toString());
    }
}
