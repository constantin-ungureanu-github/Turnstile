package fsm.turnstile;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TurnstileTest extends TurnstileFSM {
    static final Logger logger = LoggerFactory.getLogger(TurnstileTest.class);

    TurnstileFSM fsm;
    String actions;

    @Before
    public void setup() {
        fsm = this;
        fsm.setState(OneCoinTurnstileState.LOCKED);
        actions = "";
    }

    private void assertActions(String expected) {
        assertEquals(expected, actions);
    }

    @Test
    public void normalOperation() throws Exception {
        coin();
        assertActions("U");
        pass();
        assertActions("UL");
    }

    @Test
    public void forcedEntry() throws Exception {
        pass();
        assertActions("A");
    }

    @Test
    public void doublePayment() throws Exception {
        coin();
        coin();
        assertActions("UT");
    }

    @Test
    public void manyCoins() throws Exception {
        for (int i = 0; i < 5; i++)
            coin();
        assertActions("UTTTT");
    }

    @Test
    public void manyCoinsThenPass() throws Exception {
        for (int i = 0; i < 5; i++)
            coin();
        pass();
        assertActions("UTTTTL");
    }

    @Override
    public void unlock() {
        actions += "U";
    }

    @Override
    public void alarm() {
        actions += "A";
    }

    @Override
    public void thankyou() {
        actions += "T";
    }

    @Override
    public void lock() {
        actions += "L";
    }
}
