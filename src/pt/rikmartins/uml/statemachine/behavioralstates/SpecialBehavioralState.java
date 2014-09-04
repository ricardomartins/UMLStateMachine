package pt.rikmartins.uml.statemachine.behavioralstates;

import pt.rikmartins.uml.statemachine.BehavioralState;
import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.UMLStateMachineException;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class SpecialBehavioralState extends BehavioralState {
    public SpecialBehavioralState(Region region, String name) throws UMLStateMachineException {
        super(region, name);
    }
}
