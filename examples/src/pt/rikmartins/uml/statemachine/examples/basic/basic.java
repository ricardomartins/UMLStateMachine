package pt.rikmartins.uml.statemachine.examples.basic;

import pt.rikmartins.uml.statemachine.BehavioralStateMachine;
import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.Event;
import pt.rikmartins.uml.statemachine.UMLStateMachineException;
import pt.rikmartins.uml.statemachine.BehavioralTransition;
import pt.rikmartins.uml.statemachine.Trigger;
import pt.rikmartins.uml.statemachine.Vertex;
import pt.rikmartins.uml.statemachine.behavioralstates.SimpleBehavioralState;

import java.util.HashSet;

/**
 * Created by ricardo on 12-08-2014.
 */
public class basic {
    public static void main(String[] args) {
        BehavioralStateMachine container = new BehavioralStateMachine();
        Region region = null;
        try {
            region = new Region(container, "Main Region");
        } catch (UMLStateMachineException e) {
            e.printStackTrace();
        }

        Vertex red = null;
        Vertex yellow = null;
        Vertex green = null;
        try {
            red = new SimpleBehavioralState(region, "Red");
            yellow = new SimpleBehavioralState(region, "Yellow");
            green = new SimpleBehavioralState(region, "Green");
        } catch (UMLStateMachineException e) {
            e.printStackTrace();
        }

        new BehavioralTransition(region.getInitialState(), green);

        HashSet<Trigger> triggersHelper = new HashSet<Trigger>();

        triggersHelper.add(new Trigger(new Event("Warn")));
        new BehavioralTransition(green, yellow, triggersHelper);

        triggersHelper.clear();
        triggersHelper.add(new Trigger(new Event("Close")));
        new BehavioralTransition(yellow, red, triggersHelper);

        triggersHelper.clear();
        triggersHelper.add(new Trigger(new Event("Open")));
        new BehavioralTransition(red, green, triggersHelper);

        System.out.println(container.isActive());
        System.out.println(container.getCurrentState());

        try {
            container.activate();
        } catch (UMLStateMachineException e) {
            e.printStackTrace();
        }
        System.out.println(container.isActive());
        System.out.println(container.getCurrentState());

        container.receiveEvent(new Event("Warn"));
        System.out.println(container.getCurrentState());

        container.receiveEvent(new Event("Close"));
        System.out.println(container.getCurrentState());

        container.receiveEvent(new Event("Open"));
        System.out.println(container.getCurrentState());

        container.receiveEvent(new Event("Close"));
        System.out.println(container.getCurrentState());
    }
}
