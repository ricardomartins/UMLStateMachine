package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.BehavioralState;

import java.util.Set;

/**
 * Created by ricardo on 09-08-2014.
 */
public interface Deactivatable extends Activatable {
    public Set<BehavioralState> deactivate();
}
