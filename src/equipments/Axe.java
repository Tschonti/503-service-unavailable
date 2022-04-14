package equipments;

import main.Collectable;

public class Axe extends Equipment{
    @Override
    public Collectable cloneCollectable() {
        return new Axe();
    }

    public void use(){

    }
}
