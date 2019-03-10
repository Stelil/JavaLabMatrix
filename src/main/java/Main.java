import java.util.Random;

public class Main {
    static int[][] matrixA = {
            {123, 211, 399},
            {223, 311, 499},
            {323, 411, 599},
    };

    static int[][] matrixB = {
            {155, 266, 377},
            {255, 366, 477},
            {355, 466, 577},
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

    public static void main(String[] args) {

        ThreadFirst thread1 = new ThreadFirst();
        thread1.start();
        ThreadSecond thread2 = new ThreadSecond();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; lenX > i; i++) {
            for (int j = 0; lenY > j; j++) {
                System.out.print(matrixC[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static class ThreadFirst extends Thread {
        public void run() {
            for (int i = 0; lenX / 2 > i; i++) {
                for (int j = 0; lenY > j; j++) {
                    for (int x = 0; lenY > x; x++) {
                        matrixC[i][j] += matrixA[i][x] * matrixB[x][j];
                    }
                }
            }
        }
    }

    public static class ThreadSecond extends Thread {
        public void run() {
            for (int i = lenX / 2; lenX > i; i++) {
                for (int j = 0; lenY > j; j++) {
                    for (int x = 0; lenY > x; x++) {
                        matrixC[i][j] += matrixA[i][x] * matrixB[x][j];
                    }
                }
            }
        }
    }
}