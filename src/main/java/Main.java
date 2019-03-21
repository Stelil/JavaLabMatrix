import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
    };

    static int[][] matrixB = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1},
    };


    /*static int[][] matrixA = {
            {1, 2, 3, 4},
            {2, 3, 4, 5},
            {3, 4, 5, 6},
            {4, 5, 6, 7},
    };

    static int[][] matrixB = {
            {1, 2, 3, 4},
            {2, 3, 4, 5},
            {3, 4, 5, 6},
            {4, 5, 6, 7},
    };*/

    static int lenX = 3, lenY = 3;

    static int[][] matrixC = new int[lenX][lenY];
    static List<ThreadFirst> listThread = new ArrayList<ThreadFirst>();

    public static void main(String[] args) {

        for (int x = 0; matrixA.length > x; x++) {
            for (int y = 0; matrixA[0].length > y; y++) {
                ThreadFirst thread1 = new ThreadFirst(x, y);
                thread1.start();

                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                listThread.add(thread1);
            }
        }

        for (int i = 0; lenX > i; i++) {
            for (int j = 0; lenY > j; j++) {
                System.out.print(matrixC[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static class ThreadFirst extends Thread {
        int x, y;

        public ThreadFirst(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void run() {
            matrixC[x][y] += matrixA[x][y] * matrixB[x][y] +
                    matrixA[x][y + 1] * matrixB[x + 1][y] +
                    matrixA[x][y + 2] * matrixB[x + 2][y];
            System.out.println(matrixA[x][y] + " " + matrixB[x][y] + " " +
                    matrixA[x][y + 1] + " " + matrixB[x + 1][y] + " " +
                    matrixA[x][y + 2] + " " + matrixB[x + 2][y]);
        }
    }
}