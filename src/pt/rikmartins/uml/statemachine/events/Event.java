package pt.rikmartins.uml.statemachine.events;

import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.BehavioralState;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ricardo on 05-08-2014.
 */
public class Event {
    private final String action;
    private final Map<String, String> arguments;

    public Event(String action) {
        this(action, null);
    }

    public Event(String action, Map<String, String> arguments) {
        this.action = action;

        this.arguments = new HashMap<String, String>();
        if (arguments != null) {
            this.arguments.putAll(arguments);
        }
    }

    public String getAction() {
        return action;
    }

    public Map<String, String> getArguments() {
        Map<String, String> result = new HashMap<String, String>();
        result.putAll(arguments);
        return result;
    }

    public boolean equalsEvent(Event event) {
        return this.action.equals(event.getAction());
    }

    /**
     * Created by ricardo on 08-08-2014.
     */
    public static interface EventReceptor {
        public Set<BehavioralState> receiveEvent(Event event);
    }
}
