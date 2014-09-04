package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.Event;

/**
 * Created by ricardo on 04-08-2014.
 */
public class Trigger {
    private final Event event;

    public Trigger(Event event) {
        this.event = event;

    }

    public boolean trigger(Event event) {
        return this.event.equalsEvent(event);
    }

    public final Event getEvent() {
        return event;
    }
}


//    def __init__(self, event: Event):
//        self._event = event
//
//    def trigger(self, event: Event) -> bool:
//        return event == self._event
//
//    def test_trigger(self, event: Event) -> bool:
//        return self.trigger(event)
