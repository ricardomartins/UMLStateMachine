package pt.rikmartins.uml.statemachine.vertexes.behavioralstates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.exceptions.StateMachineException;
import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.BehavioralState;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.MultipleTransitionSource;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.MultipleTransitionTarget;

/**
 * Created by ricardo on 06-08-2014.
 */
public class CompositeState extends BehavioralState implements MultipleTransitionSource, MultipleTransitionTarget {
    public CompositeState(Region region) throws StateMachineException {
        super(region);
    }
}
