package tiles;

import agents.Agent;

public class InfectedLaboratory extends Laboratory{
    Agent agent;
    public InfectedLaboratory(int id, String name, Agent agent) {
        super(id, name, agent);
    }
}
