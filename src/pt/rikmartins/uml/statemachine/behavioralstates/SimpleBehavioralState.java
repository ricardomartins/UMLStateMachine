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
    protected boolean canRegisterOutTransition(BehavioralTransition transition) {
        if (!super.canRegisterOutTransition(transition)) return false;

        Iterator<BehavioralTransition> outTransitionsIterator = getOutTransitions().iterator();
        if (outTransitionsIterator.hasNext()) {
            if (outTransitionsIterator.next().getTriggers().size() < 1) return false;
            if (transition.getTriggers().size() < 1) return false;
        }

        return true;
    }

    public boolean isFinalised() {
        return false;
    }
}
