import java.util.Scanner;

public final class Main {

    public static char[][] initialize() {
        char[][] M = new char[3][3];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                M[i][j] = '.';
            }
        }
        return M;
    }

    public static void print(char[][] M) {
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.print(M[i][j]);
                if (j < M[0].length - 1) {
                    System.out.print('|');
                }
            }
            System.out.println(" ");
        }
    }

    public static boolean step(char[][] M, int line, int col, char player) {
        if (M[line][col] == '.') {
            M[line][col] = player;
            return true;
        } else {
            return false;
        }
    }

    public static int check(char[] P) {
        char last = P[0];
        int sum = 0;
        for (int i = 0; i < P.length; i++) {
            if (P[i] == '.')
                return -1;
            if (P[i] == last)
                sum++;
        }
        if (sum == 3) {
            if (last == 'O')
                return 1;
            if (last == 'X')
                return 2;
        }
        return 0;
    }

    public static char[][] transpose(char[][] M) {
        char[][] T = new char[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                T[i][j] = M[j][i];
            }
        }
        return T;
    }

    public static int status(char[][] M) {
        int sum = 0;

        char[][] T = transpose(M);

        char[][] R = new char[M.length * 2 + 2][M.length];

        for (int i = 0; i < M.length; i++) {
            R[i] = M[i];
            R[M.length + i] = T[i];
            R[M.length * 2][i] = M[i][i];
            R[M.length * 2 + 1][i] = M[i][M.length - 1 - i];
        }
        for (int r = 0; r < R.length; r++) {
            int res = check(R[r]);
            if (res > 0)
                return res;
            sum += res;
        }
        if (sum < 0)
            return -1;
        return 0;

    }

    public static void game() {
        Scanner scanner = new Scanner(System.in);
        char[][] M = initialize();
        int turn = 0;
        while (status(M) < 0) {
            char player = turn > 0 ? 'X' : 'O';
            print(M);
            System.out.println("LINHA: ");
            int line = scanner.nextInt();
            System.out.println("COLUNA: ");
            int col = scanner.nextInt();
            if (step(M, line, col, player)) {
                turn = turn > 0 ? 0 : 1;
            }
        }
        System.out.println("RESULTADO: " + status(M));
        scanner.close();
    }

    public static void main(String args[]) {
        game();
    }
}