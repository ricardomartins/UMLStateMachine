package pt.rikmartins.uml.statemachine.pseudostates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.PseudoState;
import pt.rikmartins.uml.statemachine.UMLStateMachineException;

/**
 * Created by ricardo on 06-08-2014.
 */
public class EntryPoint extends PseudoState {
    public EntryPoint(Region region) throws UMLStateMachineException {
        super(region);
    }

    @Override
    public pt.rikmartins.uml.statemachine.tools.BigStructure activate() throws UMLStateMachineException {
        return null;
    } // TODO: In and out transitions

}
