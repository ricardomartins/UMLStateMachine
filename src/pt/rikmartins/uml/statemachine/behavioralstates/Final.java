package pt.rikmartins.uml.statemachine.behavioralstates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.UMLStateMachineException;
import pt.rikmartins.uml.statemachine.Vertex;

/**
 * Created by ricardo on 06-08-2014.
 */
public final class Final extends SpecialBehavioralState implements Vertex.MultipleTransitionTarget {
    public Final(Region region) throws UMLStateMachineException {
        super(region, "Final");
    }

    @Override
    public boolean isFinalised() {
        return isActive();
    }
}
