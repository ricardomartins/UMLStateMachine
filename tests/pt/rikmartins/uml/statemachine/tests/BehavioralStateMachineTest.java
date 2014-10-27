package pt.rikmartins.uml.statemachine.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pt.rikmartins.uml.statemachine.*;
import pt.rikmartins.uml.statemachine.behavioralstates.SimpleBehavioralState;
import pt.rikmartins.uml.statemachine.pseudostates.Initial;
import pt.rikmartins.uml.statemachine.tools.BigStructure;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class BehavioralStateMachineTest {

    @Test
    public void getRegionsShouldNeverReturnNull() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        assertNotNull(behavioralStateMachine.getRegions());
    }

    @Test
    public void getRegionsShouldNotReturnTheInnerCollection() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();

        assertNotSame(behavioralStateMachine.getRegions(), behavioralStateMachine.getRegions());
    }

    @Test
    public void getRegionsShouldReturnTheRegisteredRegions() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();

        Set<Region> regionsInMachine = behavioralStateMachine.getRegions();
        assertEquals(regionsInMachine.size(), 0);

        Region region1 = new Region(behavioralStateMachine, "1");
        regionsInMachine = behavioralStateMachine.getRegions();
        assertTrue(regionsInMachine.contains(region1));
        assertEquals(regionsInMachine.size(), 1);

        Region region2 = new Region(behavioralStateMachine, "2");
        regionsInMachine = behavioralStateMachine.getRegions();
        assertTrue(regionsInMachine.contains(region2));
        assertTrue(regionsInMachine.contains(region1));
        assertEquals(regionsInMachine.size(), 2);
    }

    @Test(expected = UMLStateMachineException.class)
    public void registerRegionsShouldNotAllowAlreadyRegisteredRegions() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();

        Region region = new Region(behavioralStateMachine, "1");
        behavioralStateMachine.registerRegion(region);
    }

    @Test(expected = UMLStateMachineException.class)
    public void registerRegionsShouldNotAllowRegionsWithSameName() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();

        new Region(behavioralStateMachine, "1");
        new Region(behavioralStateMachine, "1");
    }

    @Test
    public void receiveEventShouldForwardEventToRegions() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine, true, true);

        BehavioralState testState = new SimpleBehavioralState(region, "Test State");
        new BehavioralTransition(region.getInitialState(), testState);
        new BehavioralTransition(testState, region.getFinalState(), new Trigger(new Event("Test")));
        assertFalse(behavioralStateMachine.isActive());

        behavioralStateMachine.activate();
        assertTrue(behavioralStateMachine.isActive());
        assertTrue(behavioralStateMachine.getCurrentState().contains(testState));

        behavioralStateMachine.receiveEvent(new Event("Test"));
        assertTrue(behavioralStateMachine.isFinalised());
        assertFalse(behavioralStateMachine.getCurrentState().contains(testState));
    }

    @Test
    public void activateShouldActivateActivatableRegions() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();

        Region activatableRegion1 = new Region(behavioralStateMachine, true, true);
        new BehavioralTransition(activatableRegion1.getInitialState(), activatableRegion1.getFinalState());

        Region activatableRegion2 = new Region(behavioralStateMachine, true, true);
        new BehavioralTransition(activatableRegion2.getInitialState(), activatableRegion2.getFinalState());

        Region notActivatableRegion = new Region(behavioralStateMachine);

        assertFalse(activatableRegion1.isActive());
        assertFalse(activatableRegion2.isActive());
        assertFalse(notActivatableRegion.isActive());

        behavioralStateMachine.activate();
        assertTrue(activatableRegion1.isActive());
        assertTrue(activatableRegion2.isActive());
        assertFalse(notActivatableRegion.isActive());
    }

    @Test
    public void deactivateShouldDeactivateAllRegions() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();

        Region activatableRegion1 = new Region(behavioralStateMachine, true, true);
        new BehavioralTransition(activatableRegion1.getInitialState(), activatableRegion1.getFinalState());

        Region activatableRegion2 = new Region(behavioralStateMachine, true, true);
        new BehavioralTransition(activatableRegion2.getInitialState(), activatableRegion2.getFinalState());

        Region notActivatableRegion = new Region(behavioralStateMachine);

        assertFalse(activatableRegion1.isActive());
        assertFalse(activatableRegion2.isActive());
        assertFalse(notActivatableRegion.isActive());

        behavioralStateMachine.activate();
        assertTrue(activatableRegion1.isActive());
        assertTrue(activatableRegion2.isActive());
        assertFalse(notActivatableRegion.isActive());

        behavioralStateMachine.deactivate();
        assertFalse(activatableRegion1.isActive());
        assertFalse(activatableRegion2.isActive());
        assertFalse(notActivatableRegion.isActive());
    }

    @Test(expected = UMLStateMachineException.class)
    public void deactivateShouldNotAllowDeactivationOfDeactivatedRegionContainer() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();

        Region activatableRegion1 = new Region(behavioralStateMachine, true, true);
        new BehavioralTransition(activatableRegion1.getInitialState(), activatableRegion1.getFinalState());

        Region activatableRegion2 = new Region(behavioralStateMachine, true, true);
        new BehavioralTransition(activatableRegion2.getInitialState(), activatableRegion2.getFinalState());

        Region notActivatableRegion = new Region(behavioralStateMachine);

        assertFalse(behavioralStateMachine.isActive());

        behavioralStateMachine.deactivate();
    }

    @Test
    public void isActiveShouldReturnTrueIfAtLeastOneRegionIsActive() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();

        Region activatableRegion1 = new Region(behavioralStateMachine, true, true);
        new BehavioralTransition(activatableRegion1.getInitialState(), activatableRegion1.getFinalState());

        Region activatableRegion2 = new Region(behavioralStateMachine, true, true);
        new BehavioralTransition(activatableRegion2.getInitialState(), activatableRegion2.getFinalState());

        Region notActivatableRegion = new Region(behavioralStateMachine);

        assertFalse(activatableRegion1.isActive());
        assertFalse(activatableRegion2.isActive());
        assertFalse(notActivatableRegion.isActive());
        assertFalse(behavioralStateMachine.isActive());

        behavioralStateMachine.activate();
        assertTrue(activatableRegion1.isActive());
        assertTrue(activatableRegion2.isActive());
        assertFalse(notActivatableRegion.isActive());
        assertTrue(behavioralStateMachine.isActive());
    }

    @Test
    public void isFinalisedShouldReturnTrueOnlyIfAtLeastOneRegionIsFinalisedAndTheOthersAreEitherFinalisedOrDeactivated() throws Exception {
        // TODO: May be redefined in the future
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region activatableRegion = new Region(behavioralStateMachine, true, true);
        Region notActivatableRegion = new Region(behavioralStateMachine);

        BehavioralState testState = new SimpleBehavioralState(activatableRegion, "Test State");
        new BehavioralTransition(activatableRegion.getInitialState(), testState);
        new BehavioralTransition(testState, activatableRegion.getFinalState(), new Trigger(new Event("Test")));
        assertFalse(activatableRegion.isFinalised());
        assertFalse(notActivatableRegion.isFinalised());
        assertFalse(behavioralStateMachine.isFinalised());

        behavioralStateMachine.activate();
        assertFalse(activatableRegion.isFinalised());
        assertFalse(notActivatableRegion.isFinalised());
        assertFalse(behavioralStateMachine.isFinalised());

        behavioralStateMachine.receiveEvent(new Event("Test"));
        assertTrue(activatableRegion.isFinalised());
        assertFalse(notActivatableRegion.isFinalised());
        assertTrue(behavioralStateMachine.isFinalised());
    }

    @Test
    public void setCurrentState() throws Exception {
        // TODO: Define it's behave
    }

    @Test
    public void getCurrentStateShouldReturnAnEmptySetNotNullWhenInactive() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine);

        Set<BehavioralState> machineCurrentState = behavioralStateMachine.getCurrentState();
        assertNotNull(machineCurrentState);
        assertEquals(machineCurrentState.size(), 0);
    }

    @Test
    public void getCurrentStateShouldReturnAllActiveStates() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region1 = new Region(behavioralStateMachine, true, true);
        Region region2 = new Region(behavioralStateMachine, false, true);
        Region region3 = new Region(behavioralStateMachine, true, false);
        Region region4 = new Region(behavioralStateMachine, false, false);

        SimpleBehavioralState state1 = new SimpleBehavioralState(region1, "State1");
        SimpleBehavioralState state2 = new SimpleBehavioralState(region2, "State2");
        SimpleBehavioralState state3 = new SimpleBehavioralState(region3, "State3");
        SimpleBehavioralState state4 = new SimpleBehavioralState(region4, "State4");

        Event testEvent = new Event("TestEvent");

        new BehavioralTransition(region1.getInitialState(), state1);
        new BehavioralTransition(region3.getInitialState(), state3);
        new BehavioralTransition(state1, region1.getFinalState(), new Trigger(testEvent));
        new BehavioralTransition(state3, state2, new Trigger(testEvent));
        new BehavioralTransition(state2, region2.getFinalState(), new Trigger(testEvent));

        assertFalse(behavioralStateMachine.isFinalised());
        assertFalse(behavioralStateMachine.isActive());

        BigStructure res = behavioralStateMachine.activate();
        assertTrue(res.getTheSetRENAME().contains(state1));
        assertFalse(res.getTheSetRENAME().contains(state2));
        assertTrue(res.getTheSetRENAME().contains(state3));
        assertFalse(res.getTheSetRENAME().contains(state4));
        assertFalse(behavioralStateMachine.isFinalised());
        assertTrue(behavioralStateMachine.isActive());

        res = behavioralStateMachine.receiveEvent(testEvent);
        assertFalse(res.getTheSetRENAME().contains(state1));
        assertTrue(res.getTheSetRENAME().contains(state2));
        assertFalse(res.getTheSetRENAME().contains(state3));
        assertFalse(res.getTheSetRENAME().contains(state4));
        assertFalse(behavioralStateMachine.isFinalised());
        assertTrue(behavioralStateMachine.isActive());

        res = behavioralStateMachine.receiveEvent(testEvent);
        assertFalse(res.getTheSetRENAME().contains(state1));
        assertFalse(res.getTheSetRENAME().contains(state2));
        assertFalse(res.getTheSetRENAME().contains(state3));
        assertFalse(res.getTheSetRENAME().contains(state4));
        assertTrue(behavioralStateMachine.isFinalised());
        assertTrue(behavioralStateMachine.isActive());

    }
}