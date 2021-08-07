package simplelenetworking;

import simplelenetworking.SimpleChatClient;
import simplelenetworking.SimpleChatServer;

public class SimpleNetworkingTest {
    public static void main(String[] args) {

        new Thread(() -> new SimpleChatServer().go()).start();

        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
        }

        new Thread(() -> new SimpleChatClient().go("TalkerA")).start();
        try {
            Thread.sleep(10);
        } catch (Exception ex) {
        }
        new Thread( () -> new SimpleChatClient().go("TalkerB")).start();
        try {
            Thread.sleep(10);
        } catch (Exception ex) {
        }
        new Thread( () -> new SimpleChatClient().go("TalkerC")).start();

    }
}