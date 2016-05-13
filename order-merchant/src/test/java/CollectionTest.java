import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhangxiong on 16/4/26.
 */
public class CollectionTest {
    public static void main(String[] args) {
        List list = new ArrayList<String>();
        list.add("123213");
        list.add("wrwrew");
        Iterator it = list.iterator();

        
        while (it.hasNext()) {
            System.out.println(it.next());
            it.remove();
        }

    }
}
