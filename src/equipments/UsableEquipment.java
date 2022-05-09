package equipments;

import main.Virologist;
import observables.ObservableEquipment;

/**
 * An Equipment, which a Virologist can use on its own turn as an Action
 */
public interface UsableEquipment {
    /**
     * The action of the Equipment.
     * @param from The Virologist, who uses the Equipment.
     * @param to The Virologist, who concedes the action.
     */
    void use(Virologist from, Virologist to);

    ObservableEquipment getView();
}
