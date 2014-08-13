package pt.rikmartins.uml.statemachine.vertexes.pseudostates;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.vertexes.pseudostates.abstractions.PseudoState;

/**
 * Created by ricardo on 06-08-2014.
 */
public class ExitPoint extends PseudoState { // TODO: In and out transitions

    public ExitPoint(Region region) throws MalformedVertexException {
        super(region);
    }
}
