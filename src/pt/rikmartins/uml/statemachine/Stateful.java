package pt.rikmartins.uml.statemachine;

/**
 * Created by ricardo on 09-08-2014.
 */
public interface Stateful extends Deactivatable {
    /**
     * Returns the currently active behavioral states or null.
     *
     * @return a set with the current states or null if not active
     */
    public BehavioralStateSet getCurrentState();

    public BehavioralStateSet setCurrentState(BehavioralStateSet behavioralStates) throws UMLStateMachineException;
}
