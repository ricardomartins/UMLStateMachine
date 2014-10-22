package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.tools.Sets;

import java.util.*;

/**
 * Created by ricardo on 04-08-2014.
 */
public class BehavioralTransition {
    public final Vertex fromVertex;
    public final Vertex toVertex;

    private final Set<Trigger> triggers;
    private final Constraint guard;

    private final Set<Behavior> behaviors;

    private boolean transitioning;
    private final Map<String, String> transitioningArguments;

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex) throws UMLStateMachineException {
        this(fromVertex, toVertex, null, null, null);
    }

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex, Trigger trigger) throws UMLStateMachineException {
        this(fromVertex, toVertex, Sets.newHashSet(trigger));
    }

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex, Collection<Trigger> triggers) throws UMLStateMachineException {
        this(fromVertex, toVertex, triggers, null, null);
    }

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex, Constraint guard) throws UMLStateMachineException {
        this(fromVertex, toVertex, null, guard, null);
    }

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex, Collection<Trigger> triggers, Constraint guard, Collection<Behavior> behaviors) throws UMLStateMachineException {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.triggers = new HashSet<Trigger>();
        if (triggers != null)
            this.triggers.addAll(triggers);
        this.guard = guard;
        this.behaviors = new LinkedHashSet<Behavior>();
        if (behaviors != null)
            this.behaviors.addAll(behaviors);
        fromVertex.registerOutTransition(this);
        toVertex.registerInTransition(this);
        this.transitioning = false;

        transitioningArguments = new HashMap<String, String>();
    }

    public Set<Trigger> getTriggers() {
        return triggers;
    }

    public Constraint getGuard() {
        return guard;
    }

    public Set<Behavior> getBehaviors() {
        return behaviors;
    }

    public final void addBehavior(Behavior behavior) {
        this.behaviors.add(behavior);
    }

    public final void addBehaviors(Collection<Behavior> behaviors) {
        this.behaviors.addAll(behaviors);
    }

    public Vertex initiateTransition() throws UMLStateMachineException {
        return initiateTransition(null);
    }

    public Vertex initiateTransition(Event event) throws UMLStateMachineException {
        if (transitioning)
            throw new UMLStateMachineException("Transitioning already initiated.");

        transitioningArguments.clear();
        if (event != null)
            transitioningArguments.putAll(event.getArguments());

        if (!(testTriggers(event) && testGuard(new HashMap<String, String>(transitioningArguments))))
            return null;

        transitioning = true;

        return toVertex;
    }

    public void finaliseTransition() {
        executeBehaviors(transitioningArguments);
        transitioning = false;
    }

    private boolean testTriggers(Event event) {
        if (triggers.size() < 1) {
            return true;
        }
        for (Trigger trigger : triggers) {
            if (trigger.trigger(event))
                return true;
        }
        return false;
    }

    private boolean testGuard(Map<String, String> arguments) {
        return guard == null || guard.testConstraint(arguments);
    }

    private boolean executeBehaviors(Map<String, String> arguments) {
        boolean result = true; // TODO: Clearly define what this function should return
        for (Behavior behavior : behaviors)
            if (!behavior.behave(arguments))
                result = false;
        return result;
    }

    public boolean getTransitioning(){
        return transitioning;
    }
}
