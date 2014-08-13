package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.BehavioralState;

import java.util.Set;

/**
 * Created by ricardo on 08-08-2014.
 */
public interface Activatable {
    public boolean isActive();
    public Set<BehavioralState> activate();
}
