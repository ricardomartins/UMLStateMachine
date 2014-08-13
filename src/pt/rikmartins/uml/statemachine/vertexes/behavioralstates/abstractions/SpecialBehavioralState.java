package pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions;

import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.BehavioralState;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class SpecialBehavioralState extends BehavioralState {
    public SpecialBehavioralState(Region region) throws MalformedVertexException {
        super(region);
    }
}
