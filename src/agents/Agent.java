package agents;

import main.Effect;
import main.Virologist;

public abstract class Agent implements Effect {
    protected int roundsLeft;

    public Agent(int rLeft) { roundsLeft = rLeft; }
    public int getRoundsLeft() { return roundsLeft; }
    public void setRoundsLeft(int rLeft) { roundsLeft = rLeft; }

    public void use(Virologist from, Virologist to) {

    }

    public abstract Agent create();

    /* Effect függvények */
    public void onTurnImpact(Virologist to) {

    }
    public void endTurnImpact(Virologist to) {

    }
    public void counterImpact(Agent agent, Virologist from, Virologist to) {

    }
    public boolean allowStealing() {
        return false;
    }
    public void decrement(Virologist v) {

    }
}
