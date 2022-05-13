package observables;

import equipments.Equipment;
import javax.swing.*;

/**
 * Observable class for the equipments.
 */
public class ObservableEquipment implements IObservable {

    /**
     * The observed equipment
     */
    private final Equipment equipment;

    /**
     * Constructor
     * @param equipment The observed equipment
     */
    public ObservableEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * Creates the UI component for the agent.
     * @return The UI component
     */
    @Override
    public JComponent onPaint() {
        return new JLabel(equipment.toString());
    }

    /**
     * Getter for the equipment.
     * @return equipment
     */
    public Equipment getEquipment() {
        return equipment;
    }
}
