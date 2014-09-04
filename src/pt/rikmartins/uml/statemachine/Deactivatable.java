package pt.rikmartins.uml.statemachine;

/**
 * Created by ricardo on 17-08-2014.
 */
public interface Deactivatable extends Activatable {
    /**
     * Deactivate and return a set with the deactivated
     * {@link pt.rikmartins.uml.statemachine.BehavioralState}
     *
     * @return a set with the deactivated
     * {@link pt.rikmartins.uml.statemachine.BehavioralState} or null if nothing
     * was deactivated
     */
    public BehavioralStateSet deactivate() throws UMLStateMachineException;
}
