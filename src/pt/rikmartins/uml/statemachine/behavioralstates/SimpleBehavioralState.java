package pt.rikmartins.uml.statemachine.behavioralstates;

import pt.rikmartins.uml.statemachine.*;

import java.util.Iterator;

/**
 * Created by ricardo on 04-08-2014.
 */
public final class SimpleBehavioralState extends BehavioralState implements Vertex.MultipleTransitionSource, Vertex.MultipleTransitionTarget {
    public SimpleBehavioralState(Region region, String name) throws UMLStateMachineException {
        super(region, name);
    }

    @Override
    protected boolean canRegisterOutTransition(BehavioralTransition transition) throws UMLStateMachineException {
        super.canRegisterOutTransition(transition);

        Iterator<BehavioralTransition> outTransitionsIterator = getOutTransitions().iterator();
        if (outTransitionsIterator.hasNext()) {
            if (outTransitionsIterator.next().getTriggers().size() < 1) throw new UMLStateMachineException("This behavioral state already has a default Transition from it.");
            if (transition.getTriggers().size() < 1) throw new UMLStateMachineException("Can not mix triggered transitions with default in simple behavioral states.");
        }

        return true;
    }

    public boolean isFinalised() {
        return isActive();
    }
}
