import java.util.ArrayList;

public class Basics {

    public static void printNumbers() {
        for(int i = 0; i < 255; i++) {
            System.out.println(i);
        }
    }
    public static void printOdds() {
        for(int i = 1; i < 255; i = i + 2) {
            System.out.println(i);
        }
    }
    public static void sums() {
        int sum = 0;
        for(int i = 0; i < 255; i++) {
            sum = sum + i;
            System.out.println(sum);
        }
    }
    public static void printArray(int[] as) {
        for(int x : as) {
            System.out.println(x);
        }
    }
    public static void findMax(int[] as) {
        int max = as[0];
        for(int x : as) {
            if(x > max) {
                max = x;
            }
        }
        System.out.println(max);
    }
    public static void average(int[] as) {
        int sum = 0;
        for(int x : as) {
            sum = sum + x;
        }
        System.out.println(sum / as.length);
    }
    public static ArrayList<Integer> getOdds() {
        ArrayList<Integer> as = new ArrayList<Integer>();
        for(int i = 1; i < 255; i = i + 2) {
            as.add(i);
        }
        return(as);
    }
    public static ArrayList<Integer> greaterThanY(ArrayList<Integer> as, int y) {
        ArrayList<Integer> bs = new ArrayList<Integer>();
        for(Integer i : as) {
            if(i > y) {
                bs.add(i);
            }
        }
        return(bs);
    }
    public static int[] square(int[] as) {
        int[] bs = new int[as.length];
        for(int i = 0; i < as.length; i++) {
            bs[i] = as[i] * as[i];
        }
        return(bs);
    }
    public static ArrayList<Integer> removeNegatives(int[] as) {
        ArrayList<Integer> bs = new ArrayList<Integer>();
        for(int x : as) {
            if(x > 0) {
                bs.add(x);
            }
        }
        return(bs);
    }
    public static int[] minMaxAvg(int[] as) {
        int min = as[0];
        int max = as[0];
        int sum = 0;
        for(int i = 0; i < as.length; i++) {
            if(as[i] < min) {
                min = as[i];
            }
            if(as[i] > max) {
                max = as[i];
            }
            sum = sum + as[i];
        }
        int[] result = {min, max, sum / as.length};
        return(result);
    }
    public static int[] shift(int[] as) {
        for(int i = 0; i < as.length - 1; i++) {
            as[i] = as[i + 1];
        }
        as[as.length - 1] = 0;
        return(as);
    }
}