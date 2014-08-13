package pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions;

import pt.rikmartins.uml.statemachine.Coiso;
import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.events.Event;
import pt.rikmartins.uml.statemachine.exceptions.StateMachineException;
import pt.rikmartins.uml.statemachine.vertexes.Vertex;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class BehavioralState extends Vertex implements Event.EventReceptor, Coiso {
    private final String name;

    public BehavioralState(Region region, String name) throws StateMachineException {
        super(region);
        this.name = name;
    }

    @Override
    public Set<BehavioralState> receiveEvent(Event event) {
        return null;
    }

    public final String getName() {
        return name;
    }

    @Override
    public boolean isActive() {
        return this.equals(getRegion().getCurrentState());
    }

    @Override
    public Set<BehavioralState> activate() {
        Set<BehavioralState> states = new HashSet<BehavioralState>();
        states.add(this);
        try {
            this.getRegion().setCurrentState(this);
        } catch (StateMachineException e) {
            e.printStackTrace();
        }
        return states;
    }

    @Override
    public void getIt() {

    }
}
