package observables;

import agents.Agent;
import javax.swing.*;

/**
 * Observable class for the agents.
 */
public class ObservableAgent implements IObservable {

    /**
     * The observed agent
     */
    private final Agent agent;

    /**
     * Constructor
     * @param agent The observed agent
     */
    public ObservableAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * Creates the UI component for the agent.
     * @return The UI component
     */
    @Override
    public JComponent onPaint() {
        return new JLabel(agent.toString());
    }

    /**
     * Getter fot the agent.
     * @return agent
     */
    public Agent getAgent() {
        return agent;
    }
}
