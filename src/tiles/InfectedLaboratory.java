package tiles;

import agents.Agent;
import agents.Vaccine;

public class InfectedLaboratory extends Laboratory{
    Agent agent;
    public InfectedLaboratory(int id, String name, Agent agent) {
        super(id, name, agent);
    }
}
