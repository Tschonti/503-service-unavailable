package agents;

import java.util.ArrayList;
import main.Effect;
import main.Virologist;

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

    /**
     * Getter for the roundsLeft Attribute.
     * @return returns the  roundLeft attributes value.
     */
    public int getRoundsLeft() {
        return roundsLeft;
    }

    /**
     * Setter for the roundsLeft Attribute.
     * @param rLeft sets roundLeft attribute to this param.
     */
    public void setRoundsLeft(int rLeft) {
        roundsLeft = rLeft;
    }

    /**
     * Using an Agent to infect another Virologist.
     * @param from the Virologist that is trying to infect.
     * @param to the Virologist that is being infected.
     */
    public void use(Virologist from, Virologist to) {
        ArrayList<Effect> activeEffects = new ArrayList<>(to.getActiveEffects());
        if (from != null) {
            from.getInventory().removeCraftedAgent(this);
        }
        to.addEffect(this);
        if (from != to) {
            for (Effect e : activeEffects) {
                e.counterImpact(this, from, to);
            }
        } else {
            this.onTurnImpact(from);
        }
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
    public void onTurnImpact(Virologist to) {}

    /**
     * This is the function that the descendants override depending on their end turn impact.
     * @param to The Virologist on turn.
     */
    public void endTurnImpact(Virologist to) {}

    /**
     * This is the function that the descendants override depending on their counter impact.
     * @param agent The used Agent.
     * @param from The throwing Virologist.
     * @param to The Virologist, who gets thrown.
     */
    public void counterImpact(Agent agent, Virologist from, Virologist to) {}

    /**
     * This function returns if the Agent stops/allows another Virologist to steal from the Affected one.
     * @return Can be robbed?
     */
    public boolean allowStealing() {
        return false;
    }

    /**
     * Decreases the agent's roundLeft Attribute. Returns true if it reaches 0.
     * @param v The Virologist that has this Effect/Agent.
     * @return True, if the Agent is expired
     */
    public boolean decrement(Virologist v) {
        roundsLeft--;
        return roundsLeft == 0;
    }

    /**
     * This function gets called whenever a virus would infect a player. In most viruses it does nothing.
     * @param v The Virologist that is infecting.
     */
    public void infect(Virologist v) {}
}
