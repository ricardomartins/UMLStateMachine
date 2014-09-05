package pt.rikmartins.uml.statemachine.pseudostates;

import pt.rikmartins.uml.statemachine.*;

import java.util.Collection;
import java.util.Set;

/**
 * Created by ricardo on 05-08-2014.
 */
public class Initial extends PseudoState implements Vertex.SingleTransitionSource {
    public Initial(Region region) throws UMLStateMachineException {
        super(region);
    }
}
