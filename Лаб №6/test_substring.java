import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class test_substring {
    public static void main(String[] args) {
        substring ab = new substring();
        ArrayList<Integer> res = new ArrayList();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++){
            str.append("abc");
        }
        String T = str.toString();
        String P = "abcab";

        System.out.println("Rabin-Karp algorithm");
        res = ab.alg_R_K(T, P);
        for (Integer i : res)
            System.out.println(i);

        System.out.println("The Boyer-Moore algorithm");
        res = ab.alg_B_M(T, P);
        for (Integer i : res)
            System.out.println(i);

        System.out.println("Knuth-Morris-Pratt algorithm");
        res = ab.alg_K_M_P(T, P);
        for (Integer i : res)
            System.out.println(i);

        System.out.println("Using a finite state machine");
        res = ab.alg_F_A(T, P);
        for (Integer i : res)
            System.out.println(i);
    }
}
