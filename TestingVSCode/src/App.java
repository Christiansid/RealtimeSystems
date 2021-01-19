import java.util.HashMap;
import java.util.Random;

public class App {
    public static void main(final String[] args) throws Exception {
        System.out.println("Hello, World!");
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("Tjena", "Hej");
        System.out.println(p.toString());
        Random r = new Random();
        int t = r.nextInt(5);
        System.out.println(t);
    }
}
