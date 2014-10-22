package pt.rikmartins.uml.statemachine.tests;

import org.junit.Test;
import pt.rikmartins.uml.statemachine.*;
import pt.rikmartins.uml.statemachine.behavioralstates.Final;
import pt.rikmartins.uml.statemachine.behavioralstates.SimpleBehavioralState;
import pt.rikmartins.uml.statemachine.pseudostates.Initial;

import java.util.Set;

import static org.junit.Assert.*;

public class RegionTest {

    @Test(expected = NullPointerException.class)
    public void constructorShouldNotAllowNullContainer() throws Exception {
        Region region = new Region(null);
    }

    @Test
    public void simplestConstructorShouldCreateNamelessAndVertexlessRegion() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine);

        assertEquals(region.getVertexes().size(), 0);
        assertNull(region.getInitialState());
        assertNull(region.getFinalState());
    }

    @Test
    public void constructorWithNameShouldCreateRegionWithNameAndIsNamelessShouldReturnFalse() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine, "region");

        assertFalse(region.isNameless());
        assertEquals(region.getName(), "region");
    }

    @Test
    public void constructorShouldCreateInitialAndFinalAsAsked() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region1 = new Region(behavioralStateMachine, false, false);
        Region region2 = new Region(behavioralStateMachine, false, true);
        Region region3 = new Region(behavioralStateMachine, true, false);
        Region region4 = new Region(behavioralStateMachine, true, true);

        assertNull(region1.getInitialState());
        assertNull(region1.getFinalState());

        assertNull(region2.getInitialState());
        assertNotNull(region2.getFinalState());

        assertNotNull(region3.getInitialState());
        assertNull(region3.getFinalState());

        assertNotNull(region4.getInitialState());
        assertNotNull(region4.getFinalState());
    }

    @Test
    public void isNamelessOfANamelessRegionShouldReturnTrue()  throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine);

        assertTrue(region.isNameless());
        assertEquals(region.getName(), "");
    }

    @Test
    public void getVertexesShouldNotReturnTheInnerCollection() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine);

        assertNotSame(region.getVertexes(), region.getVertexes());
    }

    @Test
    public void registeringAnInitialShouldSetInitalState() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine);

        assertNull(region.getInitialState());

        Initial initialState = new Initial(region);
        assertNotNull(region.getInitialState());
        assertSame(initialState, region.getInitialState());
    }

    @Test(expected = UMLStateMachineException.class)
    public void registeringAnInitialShouldNotBeAllowedIfInitialStateIsSet() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine, true, false);

        assertNotNull(region.getInitialState());

        new Initial(region);
    }

    @Test
    public void registeringAFinalShouldSetFinalState() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine);

        assertNull(region.getFinalState());

        Final finalState = new Final(region);
        assertNotNull(region.getFinalState());
        assertSame(finalState, region.getFinalState());
    }

    @Test(expected = UMLStateMachineException.class)
    public void registeringAFinalShouldNotBeAllowedIfFinalStateIsSet() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine, false, true);

        assertNotNull(region.getFinalState());

        new Final(region);
    }

    @Test
    public void testIsActive() throws Exception {

    }

    @Test
    public void activateShouldActivateRegionWithInitialStateNotOtherwise() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region1 = new Region(behavioralStateMachine, true, false);
        Region region2 = new Region(behavioralStateMachine, false, false);

        BehavioralState behavioralState = new SimpleBehavioralState(region1, "State");
        new BehavioralTransition(region1.getInitialState(), behavioralState);

        assertFalse(region1.isActive());
        assertFalse(region2.isActive());

        behavioralStateMachine.activate();

        assertTrue(region1.isActive());
        assertFalse(region2.isActive());
    }

    @Test(expected = UMLStateMachineException.class)
    public void activateShouldNotActivateAlreadyActiveRegion() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine, true, false);

        BehavioralState behavioralState = new SimpleBehavioralState(region, "State");
        new BehavioralTransition(region.getInitialState(), behavioralState);

        assertFalse(region.isActive());
        behavioralStateMachine.activate();
        assertTrue(region.isActive());

        behavioralStateMachine.activate();
    }

    @Test
    public void deactivateShouldClearTheCurrentState() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine, true, false);

        BehavioralState behavioralState = new SimpleBehavioralState(region, "State");
        new BehavioralTransition(region.getInitialState(), behavioralState);
        behavioralStateMachine.activate();

        assertEquals(region.getCurrentState().size(), 1);

        behavioralStateMachine.deactivate();

        assertEquals(region.getCurrentState().size(), 0);
    }

    @Test(expected = UMLStateMachineException.class)
    public void deactivateShouldNotAllowDeactivationOfDeactivatedRegion() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine);

        assertFalse(region.isActive());

        behavioralStateMachine.deactivate();
    }

    @Test
    public void getCurrentStateShouldReturnAnEmptySetNotNullWhenInactive() throws Exception {
        BehavioralStateMachine behavioralStateMachine = new BehavioralStateMachine();
        Region region = new Region(behavioralStateMachine);

        Set<BehavioralState> regionCurrentState = region.getCurrentState();
        assertNotNull(regionCurrentState);
        assertEquals(regionCurrentState.size(), 0);
    }

    @Test
    public void testSetCurrentState() throws Exception {

    }

    @Test
    public void testReceiveEvent() throws Exception {

    }

    @Test
    public void testIsFinalised() throws Exception {

    }
}