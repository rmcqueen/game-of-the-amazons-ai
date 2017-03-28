package ygraphs.ai.smart_fox.games;

import java.util.Timer;
import java.util.TimerTask;

/**
 * NOTE: This class is not used as our AI is able to make a turn within the 30 second time limit
 */
public class TimeKeeper extends Timer {
	    public static final int ENDTURN = 29000;
	    private Timer timer = new Timer();
	    private GamePlayer delegate;
	    private GameClient gameClient;

	    /**
	     *
	     * @param delegate: the player who is being timed
	     * @param gameClient: the GameClient object storing who the player is
	     */
	    public TimeKeeper(GamePlayer delegate, GameClient gameClient) {
	        this.delegate = delegate;
	        this.gameClient = gameClient;
	    }


	    /**
	     * startEndTurnTimer: A timer which executes our best move at 29 seconds
         * Unused as we make a move within 29 seconds
	     */
	    public void startEndTurnTimer() {
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                // todo: place move in here
	            }
	        }, ENDTURN);
	        timer.cancel(); // Ends the violation timer after we have made our move at 29 seconds
	    }
}
