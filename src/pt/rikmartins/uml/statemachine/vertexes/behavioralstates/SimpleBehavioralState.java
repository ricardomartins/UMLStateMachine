package pt.rikmartins.uml.statemachine.vertexes.behavioralstates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.exceptions.StateMachineException;
import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.BehavioralState;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.MultipleTransitionSource;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.MultipleTransitionTarget;

import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public final class SimpleBehavioralState extends BehavioralState implements MultipleTransitionSource, MultipleTransitionTarget {
    public SimpleBehavioralState(Region region, String name) throws StateMachineException {
        super(region, name);
    }

}
