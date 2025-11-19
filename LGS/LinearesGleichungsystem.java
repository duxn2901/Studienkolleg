import java.util.Scanner;

public class LinearesGleichungsystem {
    static Scanner scan = new Scanner(System.in);
    static double EPS = 1e-9;
    static double[][] matrix;
    
    static int zeile_anzahl;
    static int spalte_anzahl;
    public static void main(String[] args) {
        // erstellen();
        anzahlVariabelnGleichungenEingeben();
        eingeben();
        // loesen();
        loesen2();
        // ausgeben();
        scan.close();
    }

    //nicht mehr gebraucht
    public static void erstellen() {
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[0][2] = 0;
        matrix[0][3] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 2;
        matrix[1][2] = 3;
        matrix[1][3] = 2;
        matrix[2][0] = 2;
        matrix[2][1] = 3;
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
        System.out.println();
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
    public static void anzahlVariabelnGleichungenEingeben() {
        System.out.println("anzahl von variabeln");
        zeile_anzahl = scan.nextInt();
        spalte_anzahl = zeile_anzahl + 1;
        matrix = new double[zeile_anzahl][spalte_anzahl];
    }

    public static void eingeben() {
        for (int zeile = 0; zeile < zeile_anzahl; zeile++) {
            for (int spalte = 0; spalte < spalte_anzahl; spalte++) {
                System.out.println("matrix[" + zeile + "][" + spalte + "] = ");
                matrix[zeile][spalte] = scan.nextDouble();
            }
        }
    }

    public static void vertauschen(int zeil1, int zeil2) {
        for (int i = 0; i < spalte_anzahl; i++) {
            double temp = matrix[zeil1][i];
            matrix[zeil1][i] = matrix[zeil2][i];
            matrix[zeil2][i] = temp;
        }
    }

    public static void loesungablesen() {
        for (int zeil = 0; zeil < zeile_anzahl; zeil++) {
            char variable = (char) ('a' + zeil); //type casting um ein char a->z zu nehmen
            String result = String.format("%.3f", matrix[zeil][spalte_anzahl-1]); //string format um nur 3 nachkommastellen darzustellen
            System.out.println(variable + " = " + result);
        }
    }

    public static int loesungPruefen(int zeil) {
        for (int spalt = 0; spalt < spalte_anzahl; spalt++) {
            if (spalt != spalte_anzahl-1 && Math.abs(matrix[zeil][spalt]) > EPS) {
                break;
            }
            if (spalt == spalte_anzahl-1 && Math.abs(matrix[zeil][spalt]) < EPS) return 2;
            if (spalt == spalte_anzahl-1 && Math.abs(matrix[zeil][spalt]) > EPS) return 0;
        }
        return 1;
        //0: keine loesung; 1: eine loesung; 2: unendliche loesung
    }

    public static void loesen2() {
        //loop fuer alle pivoten [p][p] 
        for (int pivot = 0; pivot < zeile_anzahl; pivot++) {
            System.out.println("loop for pivot of line " + (pivot+1)); //TODO entfernen
            ausgeben();
            //um ein pivot != 0 zu finden
            boolean pivotGefunden = true;
            if (Math.abs(matrix[pivot][pivot]) < EPS) {
                pivotGefunden = false;
                int zeilNow = pivot + 1;
                while (zeilNow < zeile_anzahl) { //alle zeile unter pivot
                    if (Math.abs(matrix[zeilNow][pivot]) > EPS) {
                        vertauschen(zeilNow, pivot);
                        pivotGefunden = true;
                                                      //wenn ein pivot gefunden
                        break;
                    }
                    zeilNow++;
                }

                if (!pivotGefunden) continue;
            }
            System.out.println("finding pivot != 0 of line " + (pivot+1)); //TODO entfernen
            ausgeben();
            //pivot zu 1 multiplizieren wenn pivot != 0
            if (Math.abs(matrix[pivot][pivot]) > EPS)
                multiplizieren(pivot, 1/(matrix[pivot][pivot]));
            
            System.out.println("refactor pivot to 1 of line " + (pivot+1)); //TODO entfernen
            ausgeben();

            //spalten unter pivot nullen
            for (int zeilNow = pivot+1; zeilNow < zeile_anzahl; zeilNow++) { //alle zeile unter pivot
                if (Math.abs(matrix[pivot][pivot]) > EPS) { //wenn die zahl unter pivot != 0
                    addieren(zeilNow, pivot, -1*matrix[zeilNow][pivot]); //addieren von zeil pivot zu zeilNow
                }
            }
            System.out.println("remove all numbers under pivot of line " + (pivot+1)); //TODO entfernen
            ausgeben();

            //spalten oben pivot nullen
            for (int zeilNow = pivot-1; zeilNow >= 0; zeilNow--) { //alle zeile oben pivot
                if (Math.abs(matrix[pivot][pivot]) > EPS) { //wenn die zahl oben pivot != 0
                    addieren(zeilNow, pivot, -1*matrix[zeilNow][pivot]); //addieren von zeil pivot zu zeilNow
                }
            }
            System.out.println("remove all numbers on top of pivot of line " + (pivot+1)); //TODO entfernen
            ausgeben();
        }

        boolean hatLoesung = true;
        for (int zeil = 0; zeil < zeile_anzahl; zeil++) {
            if (loesungPruefen(zeil) == 0) {
                System.out.println("keine loesung");
                hatLoesung = false;
                break;
            }
            if (loesungPruefen(zeil) == 2) {
                System.out.println("unendliche loesung");
                hatLoesung = false;
                break;
            }
        }
        if (hatLoesung) loesungablesen();
        
    }
}
