package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.tools.BigStructure;

import java.util.Set;

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
    public BigStructure activate() throws UMLStateMachineException;
}
