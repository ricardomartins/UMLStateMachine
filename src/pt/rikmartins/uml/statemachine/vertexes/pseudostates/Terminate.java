package pt.rikmartins.uml.statemachine.vertexes.pseudostates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.vertexes.pseudostates.abstractions.PseudoState;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.MultipleTransitionTarget;

/**
 * Created by ricardo on 06-08-2014.
 */
public class Terminate extends PseudoState implements MultipleTransitionTarget {
    // TODO: Define what Terminate will terminate, the container, the SubmachineState, or the state machine
    public Terminate(Region region) throws MalformedVertexException {
        super(region);
    }
}
