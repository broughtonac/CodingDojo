import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Puzzling {
    public static ArrayList<Integer> puzzleOne() {
        int[] xs = {3,5,1,2,7,9,8,13,25,32};
        ArrayList<Integer> ys = new ArrayList<Integer>();
        int sum = 0;
        for(int x : xs) {
            sum = sum + x;
            if(x > 10) {
                ys.add(x);
            }
        }
        System.out.println(sum);
        return(ys);
    }
    public static ArrayList<String> puzzleTwo() {
        ArrayList<String> xs = new ArrayList<String>(Arrays.asList("Nancy","Jinichi","Fujibayashi","Momochi","Ishikawa"));
        ArrayList<String> ys = new ArrayList<String>();
        Collections.shuffle(xs);
        for(String x : xs) {
            System.out.println(x);
            if(x.length() > 5) {
                ys.add(x);
            }
        }
        return(ys);
    }
    public static void puzzleThree() {
        ArrayList<Character> xs = new ArrayList<Character>();
        for(char c = 'a'; c <= 'z'; c++) {
            xs.add(c);
        }
        Collections.shuffle(xs);
        System.out.println("last = " + xs.get(xs.size() - 1));
        System.out.println("first = " + xs.get(0));
        ArrayList<Character> vowels = new ArrayList<Character>(Arrays.asList('a','e','i','o','u'));
        if(vowels.contains(xs.get(0))) {
            System.out.println("vowel!");
        }
    }
    public static ArrayList<Integer> puzzleFour() {
        ArrayList<Integer> xs = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++) {
            xs.set(i, ThreadLocalRandom.current().nextInt(55,100));
        }
        return(xs);
    }
    public static ArrayList<Integer> puzzleFive() {
        ArrayList<Integer> xs = puzzleFour();
        Collections.sort(xs);
        System.out.println(xs.get(0));
        System.out.println(xs.get(xs.size() - 1));
        return(xs);
    }
    public static String puzzleSix() {
        String alphabet = "";
        for(char c = 'a'; c <='z'; c++) {
            alphabet = alphabet + c;
        }
        String s = "";
        for(int i = 0; i < 5; i++) {
            Random r = new Random();
            int j = r.nextInt(alphabet.length());
            s = s + alphabet.charAt(j);
        }
        return s;
    }
    public static ArrayList<String> puzzleSeven() {
        ArrayList<String> xs = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            String s = puzzleSix();
            xs.add(s);
        }
        return(xs);
    }
}