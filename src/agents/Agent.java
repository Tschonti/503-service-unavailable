package agents;

import java.util.ArrayList;
import main.Effect;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * This abstract class represents a basic Agent. The more specific agents extend this class.
 */
public abstract class Agent implements Effect {

    /**
     * Stores info, how many rounds the effect will last.
     */
    protected int roundsLeft;

    /**
     * Agent constructor.
     * @param rLeft sets roundLeft attribute to this param.
     */
    public Agent(int rLeft) {
        roundsLeft = rLeft;
    }

    public int getRoundsLeft() {
        Initializer.functionWrite(new OutputObject(this), "getRoundLeft", null);
        Initializer.returnWrite(new OutputObject(roundsLeft));

        return roundsLeft;
    }

    /**
     * Setter for the roundsLeft Attribute.
     * @param rLeft sets roundLeft attribute to this param.
     */
    public void setRoundsLeft(int rLeft) {
        Initializer.functionWrite(
            new OutputObject(this),
            "setRoundLeft",
            OutputObject.generateParamsArray(rLeft)
        );
        Initializer.returnWrite(null);

        roundsLeft = rLeft;
    }

    /**
     * Using an Agent to infect another Virologist.
     * @param from the Virologist that is trying to infect.
     * @param to the Virologist that is being infected.
     */
    public void use(Virologist from, Virologist to) {
        Initializer.functionWrite(
            new OutputObject(this),
            "use",
            OutputObject.generateParamsArray(from, to)
        );
        ArrayList<Effect> activeEffects = new ArrayList<>(to.getActiveEffects());
        to.addEffect(this);
        if (from != to) for (Effect e : activeEffects) {
            e.counterImpact(this, from, to);
        } else {
            this.onTurnImpact(from);
        }
        Initializer.returnWrite(null);
    }

    /**
     * Creates an instance of the Agent.
     * @return Agent
     */
    public abstract Agent create();

    /* Effect functions */
    /**
     * This is the function that the descendants override depending on their impact.
     * @param to The Virologist that is being affected.
     */
    public void onTurnImpact(Virologist to) {
        Initializer.functionWrite(
            new OutputObject(this),
            "onTurnImpact",
            OutputObject.generateParamsArray(to)
        );
        Initializer.returnWrite(null);
    }

    /**
     * This is the function that the descendants override depending on their end turn impact.
     * @param to The Virologist on turn.
     */
    public void endTurnImpact(Virologist to) {
        Initializer.functionWrite(
            new OutputObject(this),
            "endTurnImpact",
            OutputObject.generateParamsArray(to)
        );
        Initializer.returnWrite(null);
    }

    /**
     * This is the function that the descendants override depending on their counter impact.
     * @param agent The used Agent.
     * @param from The throwing Virologist.
     * @param to The Virologist, who gets thrown.
     */
    public void counterImpact(Agent agent, Virologist from, Virologist to) {
        Initializer.functionWrite(
            new OutputObject(this),
            "counterImpact",
            OutputObject.generateParamsArray(agent, from, to)
        );
        Initializer.returnWrite(null);
    }

    /**
     * This function returns if the Agent stops/allows another Virologist to steal from the Affected one.
     * @return Can be robbed?
     */
    public boolean allowStealing() {
        Initializer.functionWrite(new OutputObject(this), "allowStealing", null);
        Initializer.returnWrite(new OutputObject(false));
        return false;
    }

    /**
     * Decreases the agent's roundLeft Attribute. Removes it if it reaches 0.
     * @param v The Virologist that has this Effect/Agent.
     */
    public void decrement(Virologist v) {
        Initializer.functionWrite(
            new OutputObject(this),
            "decrement",
            OutputObject.generateParamsArray(v)
        );
        roundsLeft--;
        if (roundsLeft == 0) {
            v.getInventory().removeCraftedAgent(this);
            v.removeEffect(this);
        }
        Initializer.returnWrite(null);
    }
}
