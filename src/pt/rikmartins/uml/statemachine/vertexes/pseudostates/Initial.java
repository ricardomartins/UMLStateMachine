package pt.rikmartins.uml.statemachine.vertexes.pseudostates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.exceptions.StateMachineException;
import pt.rikmartins.uml.statemachine.transitions.BehavioralTransition;
import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.BehavioralState;
import pt.rikmartins.uml.statemachine.vertexes.pseudostates.abstractions.PseudoState;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.SingleTransitionSource;

import java.util.Set;

/**
 * Created by ricardo on 05-08-2014.
 */
public class Initial extends PseudoState implements SingleTransitionSource {
    public Initial(Region region) throws StateMachineException {
        super(region);
    }

    @Override
    public Set<BehavioralState> activate() {
        for(BehavioralTransition transition: getOutTransitions()){

        }
        return null;
    }
}
