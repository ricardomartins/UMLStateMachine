package pt.rikmartins.uml.statemachine;

/**
 * Created by ricardo on 08-08-2014.
 */
public interface Activatable {
    /**
     * Returns true if active
     *
     * @return true if active, false otherwise.
     */
    public boolean isActive();

    /**
     * Activates
     *
     * @return
     * @throws UMLStateMachineException
     */
    public BehavioralStateSet activate() throws UMLStateMachineException;
}
