package main;

import agents.Agent;
import observables.IObservable;

/**
 * This interface is implemented by everything that has any kind of impact to the Virologist, who has it.
 */
public interface Effect {
    /**
     * The Equipments impact at the start of the Virologists turn.
     * @param to The Virologist on turn.
     */
    void onTurnImpact(Virologist to);

    /**
     * The Equipments impact at the end of the Virologists turn.
     * @param to The Virologist on turn.
     */
    void endTurnImpact(Virologist to);
    /**
     * The Effect's impact in case of a Virologist throws an Agent on another Virologist.
     * @param agent The used Agent.
     * @param from The throwing Virologist.
     * @param to The Virologist, who gets thrown.
     */
    void counterImpact(Agent agent, Virologist from, Virologist to);

    /**
     * Tells if with this Equipment a Virologist is allowed to be robbed.
     * @return true, if a Virologist with this Equipment can be stolen from.
     */
    boolean allowStealing();

    /**
     * Decrements the Effect's time. It returns true if it expires.
     * @param v The owner Virologist.
     * @return True, if the effect is expired
     */
    boolean decrement(Virologist v);

    /**
     * Infects all the virologists that are on the same tile as v with the effect.
     * @param v The virologist whose neighbours will be infected
     */
    void infect(Virologist v);

    /**
     * Getter for the view of the effect
     * @return The view observing this effect
     */
    IObservable getView();
}
