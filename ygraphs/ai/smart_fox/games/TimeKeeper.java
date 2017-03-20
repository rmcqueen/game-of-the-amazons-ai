package ygraphs.ai.smart_fox.games;

import java.util.Timer;
import java.util.TimerTask;

public class TimeKeeper extends Timer {
	 public static final int TIMEOUT = 30000;
	    public static final int ENDTURN = 29000;
	    static int violationCount = 0;
	    private Timer timer = new Timer();
	    private GamePlayer delegate;
	    private GameClient gameClient;

	    /**
	     *
	     * @param delegate: the player who is being timed
	     * @param gameClient
	     * @param numOfTimeOut: how many times they're gone passed the time limit
	     */
	    public TimeKeeper(GamePlayer delegate, GameClient gameClient, int numOfTimeOut) {
	        this.delegate = delegate;
	        this.gameClient = gameClient;
	        this.violationCount = numOfTimeOut;
	    }

	    /**
	     * Starts the timer when it's our turn
	     */
	    public void startClock() {
	        this.startEndTurnTimer();
	        this.startViolationTimer();
	    }


	    /**
	     * A timer which executes our best move at 29 seconds
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

	    /**
	     * Starts the timer to count the number of violations
	     */
	    public void startViolationTimer() {
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                TimeKeeper.violationCount++;
	                System.out.println("Time Violations: "+ TimeKeeper.violationCount + "for player: " + delegate.userName());
	            }
	        }, TIMEOUT);


	    }
}
