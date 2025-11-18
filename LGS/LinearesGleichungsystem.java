import java.util.Scanner;

public class LGS {
    
    static double[][] matrix = new double[3][4];
    static int zeile_anzahl = 3;
    static int spalte_anzahl = 4;
    public static void main(String[] args) {
        erstellen();
        // eingeben();
        // loesen();
        loesen2();
        ausgeben();
    }

    //nicht mehr gebraucht
    public static void erstellen() {
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[0][2] = 0;
        matrix[0][3] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 0;
        matrix[1][2] = 0;
        matrix[1][3] = 2;
        matrix[2][0] = 0;
        matrix[2][1] = 0;
        matrix[2][2] = 1;
        matrix[2][3] = 4;

    }

    public static void addieren(int zu, int von, double x) {
        for (int i = 0; i < spalte_anzahl; i++) {
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
        for (int i = 0; i < spalte_anzahl; i++) {
            matrix[zeile][i] *= x;
        }
    }

    //nicht mehr gebraucht
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

    public static void vertauschen(int zeil1, int zeil2) {
        for (int i = 0; i < spalte_anzahl; i++) {
            double temp = matrix[zeil1][i];
            matrix[zeil1][i] = matrix[zeil1][i];
            matrix[zeil2][i] = temp;
        }
    }

    public static void loesen2() {
        //loop fuer alle spalten ausser ganz rechte
        for (int spalte_pivot = 0; spalte_pivot < spalte_anzahl-1; spalte_pivot++) {
            int zeile_pivot = spalte_pivot;
            int pivotFinden = -1;

            //vertauschen bis pivot !=0 ist
            //TODO edgecases where theres only 0s => 0 solution / infinite solutions
            for (int z = zeile_pivot; z < zeile_anzahl; z++) {
                if (matrix[z][spalte_pivot] != 0) {
                    pivotFinden = z;
                    break;
                }
            }
            if (pivotFinden != -1 && pivotFinden != zeile_pivot) vertauschen(pivotFinden, zeile_pivot);

            //pivot zu 1 multiplizieren
            double pivotVal = matrix[zeile_pivot][spalte_pivot];
            multiplizieren(zeile_pivot, 1/(pivotVal));

            //spalten unter pivot nullen
            for (int zeile = zeile_pivot+1; zeile < zeile_anzahl; zeile++) {
                if (matrix[zeile][spalte_pivot] != 0) {
                    addieren(zeile, zeile_pivot, -1*matrix[zeile][spalte_pivot]);
                }
            }
        }
    }
}
