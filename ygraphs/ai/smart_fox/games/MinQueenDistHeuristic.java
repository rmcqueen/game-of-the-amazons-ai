package ygraphs.ai.smart_fox.games;
/**
 * Created by r on 2/15/17.
 */
public class MinQueenDistHeuristic {

    public int minHeuristic(int targetX, int targetY, int[] blackQueenPositions, int[] whiteQueenPositions) {
        int evalScore = 0;
        double distB = 0.0;
        for(int i = 1; i < blackQueenPositions.length; i++) {
            Math.min(euclidianDistance(targetX, targetY, blackQueenPositions[i], whiteQueenPositions[i]),
                    euclidianDistance(targetX, targetY, blackQueenPositions[i-1], whiteQueenPositions[i-1]));
            /*
            @author yongg
            this function does absolutely fucking nothing
             */
        }
        return 0;
    }

    public double euclidianDistance(int x, int y, int xBlack, int yBlack) {
        return Math.sqrt((x-xBlack)^2 + (y-yBlack)^2);
    }
}
