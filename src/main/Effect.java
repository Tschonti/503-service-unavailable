package main;
import agents.Agent;

public interface Effect {
    void onTurnImpact(Virologist to);
    void endTurnImpact(Virologist to);
    void counterImpact(Agent agent, Virologist from, Virologist to);
    boolean allowStealing();
    void decrement(Virologist v);
}
