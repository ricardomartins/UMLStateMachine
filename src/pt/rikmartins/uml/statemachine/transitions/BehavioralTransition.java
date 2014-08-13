package pt.rikmartins.uml.statemachine.transitions;

import pt.rikmartins.uml.statemachine.events.Event;
import pt.rikmartins.uml.statemachine.vertexes.Vertex;

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
    private final Set<Behavior> allBehaviors;
    private boolean allBehaviorsUpdated;

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex) {
        this(fromVertex, toVertex, null, null, null);
    }

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex, Collection<Trigger> triggers) {
        this(fromVertex, toVertex, triggers, null, null);
    }

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex, Collection<Trigger> triggers, Constraint guard) {
        this(fromVertex, toVertex, triggers, guard, null);
    }

    public BehavioralTransition(Vertex fromVertex, Vertex toVertex, Collection<Trigger> triggers, Constraint guard, Collection<Behavior> behaviors) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        if (triggers == null) {
            this.triggers = new HashSet<Trigger>();
        } else {
            this.triggers = new HashSet<Trigger>(triggers);
        }
        this.guard = guard;
        if (behaviors == null) {
            this.behaviors = new HashSet<Behavior>();
        } else {
            this.behaviors = new HashSet<Behavior>(behaviors);
        }
        fromVertex.registerOutTransitions(this);
        toVertex.registerInTransitions(this);

        externalBehaviors = new HashSet<Behavior>();
        allBehaviors = new HashSet<Behavior>();
        allBehaviorsUpdated = false;
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
        if (!allBehaviorsUpdated) {
            allBehaviors.clear();
            allBehaviors.addAll(behaviors);
            allBehaviors.addAll(externalBehaviors);
            allBehaviorsUpdated = true;
        }
        return allBehaviors;
    }

    public final void addExternalBehaviors(Behavior behavior) {
        this.externalBehaviors.add(behavior);
        allBehaviorsUpdated = false;
    }

    public final void addExternalBehaviors(Collection<Behavior> behaviors) {
        this.externalBehaviors.addAll(behaviors);
        allBehaviorsUpdated = false;
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
