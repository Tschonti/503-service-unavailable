package equipments;

import main.Collectable;
import main.Inventory;
import main.Virologist;

public class Axe extends Equipment implements UsableEquipment {
    public Axe(){
        usesLeft=1;
    }
    @Override
    public void collect(Inventory inv) {
        super.collect(inv);
        inv.addUsableEquipment(this);
    }

    public Collectable cloneCollectable() {
        return new Axe();
    }

    public void use(Virologist from, Virologist to){
        if(usesLeft>0) {
            to.getActiveTile().removeVirologist(to);
            Virologist.getController().removeVirologist(to);
            this.durabilityDecreases();
            if(usesLeft==0){
                from.getInventory().removeUsableEquipment(this);
            }
        }
    }

    @Override
    public String toString() {
        return "Axe: "+ usesLeft +" uses left";
    }
}
