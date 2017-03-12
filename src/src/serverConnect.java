package src;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import ygraphs.ai.smart_fox.GameMessage;
import ygraphs.ai.smart_fox.games.AmazonsGameMessage;
import ygraphs.ai.smart_fox.games.GameClient;
import ygraphs.ai.smart_fox.games.GamePlayer;
/**
 * An example showing how to implement a GamePlayer
 * @author yongg
 */
public class serverConnect {
    private GameClient gameClient;

    /**
     * @param args
     */
    public static void main(String[] args) {
        GameClient gameClient;
        GamePlayer player1 = new GamePlayer() {
            @Override
            public void onLogin() {
                System.out.println("I am now logged in");
            }

            @Override
            public boolean handleGameMessage(String s, Map<String, Object> map) {
                return true;
            }

            @Override
            public String userName() {
                return "Ronald Bergman";
            }
        };
        if (args.length >= 2)//if input, use input
            gameClient = new GameClient(args[0], args[1], player1);
        else//default to best user
            gameClient = new GameClient("Yong", "Gao", player1);
        System.out.println(player1.userName());

    }




}
