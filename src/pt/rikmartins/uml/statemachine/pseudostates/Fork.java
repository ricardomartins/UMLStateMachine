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
    public BehavioralStateSet activate() throws UMLStateMachineException {
        return null;
    }
}
