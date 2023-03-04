import java.util.*;

public class substring {
    char[] T;
    char[] P;
    int m;//length P
    int n;//length T
    ArrayList result;
    int[] stop;
    int d = 2048;//alphabet size

    // The Boyer-Moore algorithm
    public ArrayList alg_B_M(String str_T, String str_P) {
        T = str_T.toCharArray();
        P = str_P.toCharArray();
        n = T.length;
        m = P.length;
        int j = 0;
        ArrayList result = new ArrayList();
        int delta_stop;
        int delta_suff;

        int[] suff = getsuffixtable(P);
        int[] stop = getstoptable(str_P);

        for (int i = 0; i < n - m + 1; ) {
            j = m - 1;
            while ((j >= 0) && (P[j] == T[i + j]))
                j -= 1;

            if (j == -1) {
                result.add(i);
                delta_stop = 1;
            } else {
                delta_stop = j - stop[T[i + j]];
            }

            delta_suff = suff[j + 1];

            i += max(delta_stop, delta_suff);
        }
        return result;
    }

    public int max(int a, int b) {
        if (a < b) {
            return b;
        } else {
            return a;
        }
    }

    //suffix table
    public int[] getsuffixtable(char[] P) {
        int[] P1 = prefix(P);
        int[] P2 = prefix(reverse_P(P));
        int[] table = new int[m + 1];
        int ind;
        int shift;

        for (int i = 0; i < m + 1; i++)
            table[i] = m - P1[m - 1];

        for (int i = 0; i < m; i++) {
            ind = m - P2[i];

            shift = i - P2[i] + 1;

            if (table[ind] > shift)
                table[ind] = shift;
        }
        return table;
    }

    //inverted line
    public char[] reverse_P(char[] P) {
        char[] rev_P = new char[m];
        for (int i = 0; i < m; i++)
            rev_P[i] = P[m - i - 1];
        return rev_P;
    }

    //stop symbol
    public int[] getstoptable(String P) {
        stop = new int[d];
        for (int i = 0; i < d; i++)
            stop[i] = -1;
        for (int j = 0; j < m; j++)
            for (int i = 0; i < m; i++)
                if (P.charAt(j) == P.charAt(i))
                    stop[P.charAt(j)] = i;
        return stop;
    }

    //Prefix function
    public int[] prefix(char[] P) {
        m = P.length;
        int[] pi = new int[m];
        int k = 0;
        m = P.length;
        for (int i = 1; i < m; i++) {
            while ((k > 0) && (P[k] != P[i]))
                k = pi[k - 1];
            if (P[k] == P[i])
                k += 1;
            pi[i] = k;
        }
        return pi;
    }

    // Knuth-Morris-Pratt algorithm
    public ArrayList alg_K_M_P(String str_T, String str_P) {
        T = str_T.toCharArray();
        P = str_P.toCharArray();
        n = T.length;
        m = P.length;
        result = new ArrayList();
        int[] pi = prefix(P);
        int k = 0;
        for (int i = 0; i < n; i++) {
            while ((k > 0) && (P[k] != T[i]))
                k = pi[k - 1];

            if (P[k] == T[i])
                k += 1;

            if (k == m) {
                result.add(i - m + 1);
                k = pi[k - 1];
            }
        }
        return result;
    }

    // Rabin-Karp algorithm
    /*public ArrayList alg_R_K(String str_T, String str_P) {
        int q = 13;//prime number
        T = str_T.toCharArray();
        P = str_P.toCharArray();
        n = T.length;
        m = P.length;
        int h;
        int h0 = 0;
        int h1 = 0;
        result = new ArrayList();

        int d_m = ((int) Math.pow(d, m - 1)) % q;
        for (int i = 0; i < m; i++) { // hash of strings
            h0 = (d * h0 + P[i]) % q;
            h1 = (d * h1 + T[i]) % q;
        }
        for (int s = 0; s <= n - m; s++) {
            if (h0 == h1) {
                for (h = 0; h < m; h++) {
                    if (T[s + h] != P[h])
                        break;
                }
                if (h == m)
                    result.add(s);
            }
            if (s < n - m) {
                h1 = (d * (h1 - d_m * T[s]) + T[s + m]) % q;
                if (h1 < 0)
                    h1 = (h1 + q);
            }
        }
        return result;
    }*/
    public ArrayList alg_R_K(String str_T,String str_P) {
        int q = 13;
        int m = str_P.length();
        int n = str_T.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;
        int d = 10;
        result = new ArrayList();
        
        for (i = 0; i < m - 1; i++)
        h = (h * d) % q;
        
        // Calculate hash value for pattern and text
        for (i = 0; i < m; i++) {
        p = (d * p + str_P.charAt(i)) % q;
        t = (d * t + str_T.charAt(i)) % q;
        }
        
        // Find the match
        for (i = 0; i <= n - m; i++) {
        if (p == t) {
        for (j = 0; j < m; j++) {
        if (str_T.charAt(i + j) != str_P.charAt(j))
        break;
        }
        
        if (j == m)
        result.add(i);
        //System.out.println("Pattern is found at position: " + (i + 1));
        }
        
        if (i < n - m) {
        t = (d * (t - str_T.charAt(i) * h) + str_T.charAt(i + m)) % q;
        if (t < 0)
        t = (t + q);
        }
        }
        
        return result;
        }

    //next state
    public int next_state(char[] P, int m, int state, int x) {
        if (state < m && x == P[state])
            return state + 1;
        int n, i;
        for (n = state; n > 0; n--) {
            if (P[n - 1] == x) {
                for (i = 0; i < n - 1; i++)
                    if (P[i] != P[state - n + 1 + i])
                        break;
                if (i == n - 1)
                    return n;
            }
        }
        return 0;
    }

    public void table(char[] P, int m, int TF[][]) {
        int state, x;
        for (state = 0; state <= m; state++)
            for (x = 0; x < d; x++)
                TF[state][x] = next_state(P, m, state, x);
    }

    //A finite state machine search algorithm
    public ArrayList alg_F_A(String str_T, String str_P) {
        T = str_T.toCharArray();
        P = str_P.toCharArray();
        int n = T.length;
        int m = P.length;
        result = new ArrayList();
        int[][] TF = new int[m + 1][d];

        table(P, m, TF);

        int i, state = 0;
        for (i = 0; i < n; i++) {
            state = TF[state][T[i]];
            if (state == m)
                result.add(i - m + 1);
        }
        return result;
    }

}
