package pt.rikmartins.uml.statemachine.vertexes.behavioralstates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.SpecialBehavioralState;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.MultipleTransitionTarget;

/**
 * Created by ricardo on 06-08-2014.
 */
public class Final extends SpecialBehavioralState implements MultipleTransitionTarget {
    public Final(Region region) throws MalformedVertexException {
        super(region);
    }
}
