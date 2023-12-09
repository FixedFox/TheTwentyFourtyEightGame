import ru.fixedfox.thetwentyfourtyeightgame.*;

import java.util.HashMap;

public class KeyTest {
    public static void main(String[] args) {
        var test = new HashMap<Key,Integer>();
        test.put(new Key(1,1),123);
        if(test.containsKey(new Key(1,1))) {
            System.out.println("test passed");
        }
        else {
            System.out.println("test not passed");
        }
    }
}
