package observables;

import main.Resource;

import javax.swing.*;

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
