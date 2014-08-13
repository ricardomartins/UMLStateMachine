package pt.rikmartins.uml.statemachine.transitions;

import java.util.Map;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class Constraint {
    private final String name;

    public Constraint() {
        this("");
    }

    public Constraint(String name) {
        if (name == null)
            this.name = "";
        else
            this.name = name;
    }

    public abstract boolean testConstraint(Map<String, String> arguments); // TODO: Improve name

    public final String getName() {
        return name;
    }
}
