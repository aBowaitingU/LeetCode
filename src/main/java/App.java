import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {


    public static void main(String[] args) {
        test();

    }

    private static void test() {

        CloneExample e1 = new CloneExample();
        try {
            Object e2 = e1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }


    static class CloneExample{
        private int a;

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

}
