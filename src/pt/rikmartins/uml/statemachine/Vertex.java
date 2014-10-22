package pt.rikmartins.uml.statemachine;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class Vertex implements Activatable {
    private final Region region;
    private final Set<BehavioralTransition> outTransitions;
    private final Set<BehavioralTransition> inTransitions;

    public Vertex(Region region) throws UMLStateMachineException {
        boolean isTransitionSource = this instanceof TransitionSource;
        boolean isTransitionTarget = this instanceof TransitionTarget;
        assert isTransitionSource || isTransitionTarget : "Vertex must either be TransitionSource or TransitionTarget or both.";

        boolean isSingleTransitionSource = this instanceof SingleTransitionSource;
        boolean isMultipleTransitionSource = this instanceof MultipleTransitionSource;
        boolean isSingleTransitionTarget = this instanceof SingleTransitionTarget;
        boolean isMultipleTransitionTarget = this instanceof MultipleTransitionTarget;
        assert !(isTransitionSource ^ (isSingleTransitionSource || isMultipleTransitionSource)) : "Don't implement TransitionSource directly, implement one of its descendants.";
        assert !(isTransitionTarget ^ (isSingleTransitionTarget || isMultipleTransitionTarget)) : "Don't implement TransitionTarget directly, implement one of its descendants.";

        assert !(isMultipleTransitionSource && isSingleTransitionSource) : "Can not be MultipleTransitionSource and SingleTransitionSource at the same time.";
        assert !(isMultipleTransitionTarget && isSingleTransitionTarget) : "Can not be MultipleTransitionTarget and SingleTransitionTarget at the same time.";

        this.region = region;
        this.region.registerVertex(this);
        this.outTransitions = new HashSet<BehavioralTransition>();
        this.inTransitions = new HashSet<BehavioralTransition>();
    }

    public final Region getRegion() {
        return region;
    }

    public final Set<BehavioralTransition> getInTransitions() {
        return inTransitions;
    }

    public final Set<BehavioralTransition> getOutTransitions() {
        return outTransitions;
    }

    public final void registerOutTransition(BehavioralTransition transition) throws UMLStateMachineException {
        assert canRegisterOutTransition(transition) : "Badly built canRegisterOutTransition, should either return true or throw UMLStateMachineException";

        outTransitions.add(transition);
    }

    public final void registerOutTransitions(Collection<BehavioralTransition> transitions) throws UMLStateMachineException {
        // TODO: If any Transition fails to register, none should be registered
        for (BehavioralTransition transition : transitions) {
            registerOutTransition(transition);
        }
    }

    public final void registerInTransitions(Collection<BehavioralTransition> transitions) throws UMLStateMachineException {
        // TODO: If any Transition fails to register, none should be registered
        for (BehavioralTransition transition : transitions) {
            registerInTransition(transition);
        }
    }

    public final void registerInTransition(BehavioralTransition transition) throws UMLStateMachineException {
        assert canRegisterInTransition(transition) : "Badly built canRegisterInTransition, should either return true or throw UMLStateMachineException";

        inTransitions.add(transition);
    }

    protected boolean canRegisterOutTransition(BehavioralTransition transition) throws UMLStateMachineException {
        if (!(this instanceof TransitionSource))
            throw new UMLStateMachineException("This vertex does accept transitions from it.");
        if (this instanceof SingleTransitionSource && outTransitions.size() > 0)
            throw new UMLStateMachineException("This vertex only accepts one Transition from it.");

        return true;
    }

    protected boolean canRegisterInTransition(BehavioralTransition transition) throws UMLStateMachineException {
        if (!(this instanceof TransitionTarget))
            throw new UMLStateMachineException("This vertex does accept transitions to it.");
        if ((this instanceof SingleTransitionTarget && inTransitions.size() > 0))
            throw new UMLStateMachineException("This vertex only accepts one Transition to it.");

        return true;
    }

    public static interface TransitionUser {
    }

    public static interface TransitionTarget extends TransitionUser {
    }

    public static interface TransitionSource extends TransitionUser {
    }

    public static interface SingleTransitionTarget extends TransitionTarget {
    }

    public static interface SingleTransitionSource extends TransitionSource {
    }

    public static interface MultipleTransitionTarget extends TransitionTarget {
    }

    public static interface MultipleTransitionSource extends TransitionSource {
    }

    public static interface ElseTransitionSource extends MultipleTransitionSource {
        public BehavioralTransition getElseTransition();

        public void setElseTransition(BehavioralTransition transition);
    }
}
