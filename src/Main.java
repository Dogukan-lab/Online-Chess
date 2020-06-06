import data.Data;
import ui.UIManager;

public class Main {

    private Data data = Data.getInstance();

    public static void main(String[] args) {

        UIManager.launch(UIManager.class);
    }
}
