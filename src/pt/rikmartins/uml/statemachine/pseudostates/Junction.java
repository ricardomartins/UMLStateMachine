package pt.rikmartins.uml.statemachine.pseudostates;

import pt.rikmartins.uml.statemachine.PseudoState;
import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.UMLStateMachineException;

/**
 * Created by ricardo on 06-08-2014.
 */
public abstract class Junction extends PseudoState {
    public Junction(Region region) throws UMLStateMachineException {
        super(region);
    }
}
