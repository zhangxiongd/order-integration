import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zhangxiong on 16/5/3.
 */
public class CallThread {

    public static void main(String[] args) throws Exception {
        CallThread callThread = new CallThread();

        //create a thread pool
        ExecutorService service = Executors.newFixedThreadPool(2);

        Callable c1 = callThread.new MyCallable("chenqin");
        Callable c2 = callThread.new MyCallable("zhangbohan");

        Future future1 = service.submit(c1);
        Future future2 = service.submit(c2);

        System.out.println(future1.get().toString());
        System.out.println(future2.get().toString());

        service.shutdown();
    }


    class MyCallable implements Callable {

        private String name;

        public MyCallable(String name) {
            this.name = name;
        }

        @Override
        public Object call() throws Exception {
            return name + "zhangxiong return name";
        }
    }
}
