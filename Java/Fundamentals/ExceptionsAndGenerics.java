import java.util.ArrayList;
import java.util.Arrays;

public class ExceptionsAndGenerics {
    public static void main(String[] args) throws ClassCastException {
        ArrayList<Object> myList = new ArrayList<Object>();
        myList.add("13");
        myList.add("hello world");
        myList.add(48);
        myList.add("Goodbye World");

        for(Object x : myList) {
            try {
                x = (Integer) x;
            }
            catch (ClassCastException e) {
                System.out.println("ERROR AT INDEX " + myList.indexOf(x));
            }
        }
        System.out.println(Arrays.toString(myList.toArray()));

        ArrayList<Integer> myList1 = new ArrayList<Object>();
        myList1.add(48);
    }
}
