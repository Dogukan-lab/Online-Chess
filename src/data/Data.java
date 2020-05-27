package data;

public class Data {
     private static Data instance;

    synchronized public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    private Data() {

    }
}
