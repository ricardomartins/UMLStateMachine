package pt.rikmartins.uml.statemachine.vertexes.pseudostates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.vertexes.pseudostates.abstractions.Junction;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.ElseTransitionSource;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.MultipleTransitionSource;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.SingleTransitionTarget;

/**
 * Created by ricardo on 06-08-2014.
 */
public class Split extends Junction implements SingleTransitionTarget, MultipleTransitionSource, ElseTransitionSource {
    public Split(Region region) throws MalformedVertexException {
        super(region);
    }
}
