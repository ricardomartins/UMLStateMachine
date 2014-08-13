package pt.rikmartins.uml.statemachine.vertexes.pseudostates.abstractions;

import pt.rikmartins.uml.statemachine.Region;

/**
 * Created by ricardo on 06-08-2014.
 */
public abstract class Junction extends PseudoState {
    public Junction(Region region) throws MalformedVertexException {
        super(region);
    }
}
