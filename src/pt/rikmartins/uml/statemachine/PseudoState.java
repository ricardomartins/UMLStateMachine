package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.tools.BigStructure;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class PseudoState extends Vertex {
    public PseudoState(Region region) throws UMLStateMachineException {
        super(region);
    }

    @Override
    public final boolean isActive() {
        return false;
    }

    @Override
    public BigStructure activate() throws UMLStateMachineException {
        BigStructure result = new BigStructure();

        for(BehavioralTransition transition : getOutTransitions()){
            result.addAll(transition.initiateTransition().activate());
        }
        return result;
    }

    @Override
    protected boolean canRegisterOutTransition(BehavioralTransition transition) throws UMLStateMachineException {
        if (!super.canRegisterOutTransition(transition))
            return false;
        if (transition.getTriggers().size() > 0)
            return false;
        return true;
    }
}
