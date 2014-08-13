package pt.rikmartins.uml.statemachine.vertexes;

import pt.rikmartins.uml.statemachine.Activatable;
import pt.rikmartins.uml.statemachine.transitions.BehavioralTransition;
import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.exceptions.StateMachineException;
import pt.rikmartins.uml.statemachine.vertexes.transitionwise.*;

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

    public Vertex(Region region) throws StateMachineException {
        if ((this instanceof MultipleTransitionSource && this instanceof SingleTransitionSource) ||
                (this instanceof MultipleTransitionTarget && this instanceof SingleTransitionTarget)) {
            throw new MalformedVertexException(); // TODO: Give explanation to the error
        }
        this.region = region;
        this.region.registerVertex(this);
        this.outTransitions = new HashSet<BehavioralTransition>();
        this.inTransitions = new HashSet<BehavioralTransition>();
    }

    public Region getRegion() {
        return region;
    }

    public Set<BehavioralTransition> getInTransitions() {
        return inTransitions;
    }

    public Set<BehavioralTransition> getOutTransitions() {
        return outTransitions;
    }

    public void registerOutTransitions(BehavioralTransition transition) {
        if(canRegisterOutTransition(transition))
            outTransitions.add(transition);
    }

    public final void registerOutTransitions(Collection<BehavioralTransition> transitions) {
        for (BehavioralTransition transition: transitions){
            registerOutTransitions(transition);
        }
    }

    public void registerInTransitions(BehavioralTransition transition) {
        if(canRegisterInTransition(transition))
            inTransitions.add(transition);
    }

    public final void registerInTransitions(Collection<BehavioralTransition> transitions) {
        for (BehavioralTransition transition: transitions){
            registerInTransitions(transition);
        }
    }

    private boolean canRegisterOutTransition(BehavioralTransition transition) {
        if (!(this instanceof TransitionSource)){
            return false;
        }
        if (this instanceof SingleTransitionSource && outTransitions.size() > 0) {
            return false;
        }
        return true;
    }

    private boolean canRegisterInTransition(BehavioralTransition transition) {
        if (!(this instanceof TransitionTarget)){
            return false;
        }
        return !(this instanceof SingleTransitionTarget && inTransitions.size() > 0);
    }

    public static class MalformedVertexException extends StateMachineException {
        public MalformedVertexException() {
            super();
        }

        public MalformedVertexException(String message) {
            super(message);
        }
    }
}
