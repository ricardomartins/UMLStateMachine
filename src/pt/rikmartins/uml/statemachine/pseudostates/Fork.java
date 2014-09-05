package pt.rikmartins.uml.statemachine.pseudostates;

import pt.rikmartins.uml.statemachine.*;
import pt.rikmartins.uml.statemachine.BehavioralStateSet;

/**
 * Created by ricardo on 05-08-2014.
 */
public class Fork extends PseudoState implements Vertex.SingleTransitionTarget, Vertex.MultipleTransitionSource {
    public Fork(Region region) throws UMLStateMachineException {
        super(region);
    }

    @Override
    protected boolean canRegisterOutTransition(BehavioralTransition transition) {
        if (!super.canRegisterOutTransition(transition)) return false;
        for(BehavioralTransition bt: getOutTransitions())
            // Prevent transitions to behavioral states in repeated regions
            if (bt.toVertex.getRegion() == transition.toVertex.getRegion()) return false;
        return true;
    }
}
