package pt.rikmartins.uml.statemachine.pseudostates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.UMLStateMachineException;
import pt.rikmartins.uml.statemachine.Vertex;

/**
 * Created by ricardo on 06-08-2014.
 */
public class Merge extends Junction implements Vertex.MultipleTransitionTarget, Vertex.SingleTransitionSource {
    public Merge(Region region) throws UMLStateMachineException {
        super(region);
    }

    @Override
    public pt.rikmartins.uml.statemachine.tools.BigStructure activate() throws UMLStateMachineException {
        return null;
    }
}
