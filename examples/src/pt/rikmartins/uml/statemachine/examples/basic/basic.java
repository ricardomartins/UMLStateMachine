package pt.rikmartins.uml.statemachine.examples.basic;

import pt.rikmartins.uml.statemachine.BehavioralStateMachine;
import pt.rikmartins.uml.statemachine.Region;
import pt.rikmartins.uml.statemachine.events.Event;
import pt.rikmartins.uml.statemachine.exceptions.StateMachineException;
import pt.rikmartins.uml.statemachine.transitions.BehavioralTransition;
import pt.rikmartins.uml.statemachine.transitions.Trigger;
import pt.rikmartins.uml.statemachine.vertexes.Vertex;
import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.SimpleBehavioralState;

import java.awt.*;
import java.util.HashSet;

/**
 * Created by ricardo on 12-08-2014.
 */
public class basic {
    public static void main(String[] args) {
        BehavioralStateMachine container = new BehavioralStateMachine();
        Region region = new Region(container, "Main Region");


        Vertex red = null;
        Vertex yellow = null;
        Vertex green = null;
        try {
            red = new SimpleBehavioralState(region, "Red");
            yellow = new SimpleBehavioralState(region, "Yellow");
            green = new SimpleBehavioralState(region, "Green");
        } catch (StateMachineException e) {
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

        System.out.print(container.isActive());
        System.out.print(container.currentState());
    }
}
//    print(contentor.is_active())
//    print(contentor.current_state)
//
//    contentor.activate()
//    print(contentor.is_active())
//    print(contentor.current_state)
//
//    contentor.in_event(Event('Warn'))
//    print(contentor.is_active())
//    print(contentor.current_state)
//
//    contentor.in_event(Event('Close'))
//    print(contentor.is_active())
//    print(contentor.current_state)
//
//    contentor.in_event(Event('Open'))
//    print(contentor.is_active())
//    print(contentor.current_state)
//
//    contentor.in_event(Event('Close'))
//    print(contentor.is_active())
//    print(contentor.current_state)
