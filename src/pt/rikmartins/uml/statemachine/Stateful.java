package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.exceptions.StateMachineException;
import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.BehavioralState;

/**
 * Created by ricardo on 12-08-2014.
 */
public interface Stateful {
    public Coiso getCurrentState();
    public void setCurrentState(BehavioralState state) throws StateMachineException;
}
