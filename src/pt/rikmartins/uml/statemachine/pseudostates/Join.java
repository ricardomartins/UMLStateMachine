package pt.rikmartins.uml.statemachine.pseudostates;

import pt.rikmartins.uml.statemachine.*;

/**
 * Created by ricardo on 05-08-2014.
 */
public class Join extends PseudoState implements Vertex.MultipleTransitionTarget, Vertex.SingleTransitionSource {
    public Join(Region region) throws UMLStateMachineException {
        super(region);
    }

    @Override
    protected boolean canRegisterInTransition(BehavioralTransition transition) throws UMLStateMachineException {
        if (!super.canRegisterInTransition(transition)) return false;
        for(BehavioralTransition bt: getInTransitions())
            // Prevent transitions to behavioral states in repeated regions
            if (bt.toVertex.getRegion() == transition.toVertex.getRegion()) return false;
        return true;
    }
}
