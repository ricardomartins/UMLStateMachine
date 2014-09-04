package pt.rikmartins.uml.statemachine;

import java.util.Map;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class Behavior {  // TODO: Clarify function names
    private final String name;

    public Behavior() {
        this(null);
    }

    public Behavior(String name) {
        if (name == null) {
            this.name = "";
        } else {
            this.name = name;
        }
    }

    public abstract boolean behavior(Map<String, String> arguments);

    public final String getName() {
        return name;
    }
}
