package pt.rikmartins.uml.statemachine.vertexes.pseudostates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.vertexes.pseudostates.abstractions.PseudoState;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.MultipleTransitionTarget;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.SingleTransitionSource;

/**
 * Created by ricardo on 05-08-2014.
 */
public class Join extends PseudoState implements MultipleTransitionTarget, SingleTransitionSource {
    public Join(Region region) throws MalformedVertexException {
        super(region);
    }
}
