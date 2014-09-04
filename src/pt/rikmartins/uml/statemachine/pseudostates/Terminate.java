package pt.rikmartins.uml.statemachine.pseudostates;

import pt.rikmartins.uml.statemachine.*;
import pt.rikmartins.uml.statemachine.BehavioralStateSet;

/**
 * Created by ricardo on 06-08-2014.
 */
public class Terminate extends PseudoState implements Vertex.MultipleTransitionTarget {

    public Terminate(Region region) throws UMLStateMachineException {
        super(region);
    }

    @Override
    public BehavioralStateSet activate() throws UMLStateMachineException {
        return null;
    }
}
