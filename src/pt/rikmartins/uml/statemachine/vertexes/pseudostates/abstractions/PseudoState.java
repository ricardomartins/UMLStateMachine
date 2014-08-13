package pt.rikmartins.uml.statemachine.vertexes.pseudostates.abstractions;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.exceptions.StateMachineException;
import pt.rikmartins.uml.statemachine.vertexes.Vertex;
import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.BehavioralState;

import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class PseudoState extends Vertex {
    public PseudoState(Region region) throws StateMachineException {
        super(region);
    }

    @Override
    public final boolean isActive() {
        return false;
    }
}
