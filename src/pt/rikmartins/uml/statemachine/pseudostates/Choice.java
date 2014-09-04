package pt.rikmartins.uml.statemachine.pseudostates;

import pt.rikmartins.uml.statemachine.*;
import pt.rikmartins.uml.statemachine.BehavioralStateSet;

/**
 * Created by ricardo on 06-08-2014.
 */
public class Choice extends PseudoState implements Vertex.SingleTransitionTarget, Vertex.MultipleTransitionSource, Vertex.ElseTransitionSource {
    public Choice(Region region) throws UMLStateMachineException {
        super(region);
    }

    @Override
    public BehavioralStateSet activate() throws UMLStateMachineException {
        return null;
    }

    @Override
    public BehavioralTransition getElseTransition() {
        return null;
    }

    @Override
    public void setElseTransition(BehavioralTransition transition) {

    }
}
