package pt.rikmartins.uml.statemachine;

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

    private final Set<Behavior> externalBehaviors; // TODO: Improve name

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex) {
        this(fromVertex, toVertex, null);
    }

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex, Collection<Trigger> triggers) {
        this(fromVertex, toVertex, triggers, null);
    }

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex, Collection<Trigger> triggers, Constraint guard) {
        this(fromVertex, toVertex, triggers, guard, null);
    }

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex, Collection<Trigger> triggers, Constraint guard, Collection<Behavior> behaviors) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.triggers = new HashSet<Trigger>();
        if (triggers != null)
            this.triggers.addAll(triggers);
        this.guard = guard;
        this.behaviors = new HashSet<Behavior>();
        if (behaviors != null)
            this.behaviors.addAll(behaviors);
        fromVertex.registerOutTransition(this);
        toVertex.registerInTransition(this);

        externalBehaviors = new HashSet<Behavior>();
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

    public Set<Behavior> getExternalBehaviors() {
        return externalBehaviors;
    }

    public Set<Behavior> getAllBehaviors() {
        Set<Behavior> allBehaviors = new HashSet<Behavior>(behaviors);
        allBehaviors.addAll(externalBehaviors);
        return allBehaviors;
    }

    public final void addExternalBehavior(Behavior behavior) {
        this.externalBehaviors.add(behavior);
    }

    public final void addExternalBehaviors(Collection<Behavior> behaviors) {
        this.externalBehaviors.addAll(behaviors);
    }

    public Vertex transition() {
        return transition(null);
    }

    public Vertex transition(Event event) {
        Map<String, String> arguments = new HashMap<String, String>();
        if (event != null) {
            arguments.putAll(event.getArguments());
        }

        if (!(testTriggers(event) && testGuard(arguments)))
            return null;

        executeAllBehaviors(arguments);
        return toVertex;
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
        if (guard == null)
            return true;
        else
            return guard.testConstraint(arguments);
    }

    private boolean executeAllBehaviors(Map<String, String> arguments) {
        boolean result = true; // TODO: Clearly define what this function should return
        for (Behavior behavior : getAllBehaviors())
            if (!behavior.behavior(arguments))
                result = false;
        return result;
    }
}

//    def _execute_all_behaviors(self, **kwargs):
//        for behavior in self.all_behaviors:
//            behavior.execute_behavior(**kwargs)
//
//    def _test_triggers(self, event: Event) -> bool:
//        if len(self.triggers) < 1:
//            return True
//        for trigger in self.triggers:
//            if trigger.trigger(event):
//                return True
//        return False
//
//    def _test_guard(self, **kwargs) -> bool:
//        if self._guard is None:
//            return True
//        else:
//            return self._guard.test_constraint(**kwargs)
