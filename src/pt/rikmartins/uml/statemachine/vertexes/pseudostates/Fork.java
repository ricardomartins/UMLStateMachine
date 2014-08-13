package pt.rikmartins.uml.statemachine.vertexes.pseudostates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.vertexes.pseudostates.abstractions.PseudoState;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.MultipleTransitionSource;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.SingleTransitionTarget;

/**
 * Created by ricardo on 05-08-2014.
 */
public class Fork extends PseudoState implements SingleTransitionTarget, MultipleTransitionSource {
    public Fork(Region region) throws MalformedVertexException {
        super(region);
    }
}
