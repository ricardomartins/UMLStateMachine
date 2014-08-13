package pt.rikmartins.uml.statemachine.vertexes.pseudostates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.vertexes.pseudostates.abstractions.Junction;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.MultipleTransitionTarget;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.SingleTransitionSource;

/**
 * Created by ricardo on 06-08-2014.
 */
public class Merge extends Junction implements MultipleTransitionTarget, SingleTransitionSource {
    public Merge(Region region) throws MalformedVertexException {
        super(region);
    }
}
