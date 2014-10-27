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
            Vertex vertex = transition.initiateTransition();
            if (vertex != null){
                result.addToTransitionRENAME(transition); // TODO: This may be a bad solution, should be done in
            }
            result.addAll(vertex.activate());
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
