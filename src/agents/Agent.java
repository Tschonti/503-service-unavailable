package agents;

import main.Effect;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

public abstract class Agent implements Effect {
    protected int roundsLeft;

    public Agent(int rLeft) {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                OutputObject.generateParamsArray(rLeft)
        );
        Initializer.returnWrite(null);

        roundsLeft = rLeft;
    }

    public int getRoundsLeft() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getRoundLeft",
                null
        );
        Initializer.returnWrite(new OutputObject(roundsLeft));

        return roundsLeft;
    }
    public void setRoundsLeft(int rLeft) {
        Initializer.functionWrite(
                new OutputObject(this),
                "setRoundLeft",
                OutputObject.generateParamsArray(rLeft)
        );
        Initializer.returnWrite(null);

        roundsLeft = rLeft;
    }

    public void use(Virologist from, Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "use",
                OutputObject.generateParamsArray(from, to)
        );
        Initializer.returnWrite(null);
    }

    public abstract Agent create();

    /* Effect függvények */
    public void onTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "onTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        Initializer.returnWrite(null);
    }

    public void endTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "endTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        Initializer.returnWrite(null);
    }

    public void counterImpact(Agent agent, Virologist from, Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "counterImpact",
                OutputObject.generateParamsArray(agent, from, to)
        );
        Initializer.returnWrite(null);
    }

    public boolean allowStealing() {
        Initializer.functionWrite(
                new OutputObject(this),
                "allowStealing",
                null
        );
        Initializer.returnWrite(new OutputObject(false));
        return false;
    }

    public void decrement(Virologist v) {
        Initializer.functionWrite(
                new OutputObject(this),
                "decrement",
                OutputObject.generateParamsArray(v)
        );
        Initializer.returnWrite(null);
    }
}
