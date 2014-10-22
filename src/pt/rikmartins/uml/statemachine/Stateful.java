package pt.rikmartins.uml.statemachine;

import java.util.Set;

/**
 * Created by ricardo on 09-08-2014.
 */
public interface Stateful extends Deactivatable {
    /**
     * Returns the currently active behavioral states or null.
     *
     * @return a set with the current states or empty set if no state
     */
    public Set<BehavioralState> getCurrentState();

    public Set<BehavioralState> setCurrentState(Set<BehavioralState> behavioralStates) throws UMLStateMachineException;
}
