public class BasicsTest {
    public static void main(String[] args) {
        int[] as = {1,2,3,4,5};
        int[] bs = Basics.shift(as);
        for(int x : bs) {
            System.out.println(x);
        }
    }
}