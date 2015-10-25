package fsm.turnstile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TurnstileFSM {
    static final Logger logger = LoggerFactory.getLogger(OneCoinTurnstileState.class);

    private TurnstileState state;

    void setState(TurnstileState state) {
        this.state = state;
    }

    public void pass() {
        state.pass(this);
        logger.debug("pass");
    }

    public void coin() {
        state.coin(this);
        logger.debug("coin");
    }

    public abstract void alarm();

    public abstract void lock();

    public abstract void unlock();

    public abstract void thankyou();
}
