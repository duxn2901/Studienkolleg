import java.util.Scanner;

public class LGS {
    
    static double[][] matrix = new double[3][4];
    public static void main(String[] args) {
        // erstellen();
        eingeben();
        loesen();
        ausgeben();
    }

    //nicht mehr gebraucht
    public static void erstellen() {
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[0][3] = 6;
        matrix[1][0] = 1;
        matrix[1][1] = -2;
        matrix[1][2] = 2;
        matrix[1][3] = 3;
        matrix[2][0] = 2;
        matrix[2][1] = 1;
        matrix[2][2] = -1;
        matrix[2][3] = 1;

    }

    public static void addieren(int zu, int von, double x) {
        for (int i = 0; i < 4; i++) {
            matrix[zu][i] += matrix[von][i] * x;
        }
    }

    public static void ausgeben() {
        for (double[] row : matrix) {
            for (double num : row) {
                System.out.print(num);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void multiplizieren(int zeile, double x) {
        for (int i = 0; i < 4; i++) {
            matrix[zeile][i] *= x;
        }
    }

    public static void loesen() {
        //1.spalte: [1][0] und [2][0] nullen
        addieren(1, 0, -1*(matrix[1][0]/matrix[0][0]));
        addieren(2, 0, -1*(matrix[2][0]/matrix[0][0]));

        //2.spalte: [2][1] nullen
        addieren(2, 1, -1*(matrix[2][1]/matrix[1][1]));

        //[2][2] 1 machen
        multiplizieren(2, 1/matrix[2][2]);

        //3.spalte: [0][2] und [1][2] nullen
        addieren(0, 2, -1*(matrix[0][2]));
        addieren(1, 2, -1*(matrix[1][2]));

        //[1][1] 1 machen
        multiplizieren(1, 1/matrix[1][1]);

        //2.spalte [0][1] nullen
        addieren(0, 1, -1*(matrix[0][1]));

        //[0][0] 1 machen
        multiplizieren(0, 1/matrix[0][0]);
    }

    public static void eingeben() {
        Scanner scan = new Scanner(System.in);
        for (int zeile = 0; zeile < 3; zeile++) {
            for (int spalte = 0; spalte < 4; spalte++) {
                System.out.println("matrix[" + zeile + "][" + spalte + "] = ");
                matrix[zeile][spalte] = scan.nextDouble();
            }
        }
        scan.close();
    }
}
