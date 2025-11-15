public class LGS {
    
    static double[][] matrix = new double[3][4];
    public static void main(String[] args) {
        erstellen();
        addieren(2, 0, -2);
        loesen();
        ausgeben();
    }

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
        //1.spalt: [1][0] und [2][0] nullen
        addieren(1, 0, -1*(matrix[1][0]/matrix[0][0]));
        addieren(2, 0, -1*(matrix[2][0]/matrix[0][0]));

        //2.spalt: [2][1] nullen
        addieren(2, 1, -1*(matrix[2][1]/matrix[1][1]));

        //[2][2] 1 machen
        multiplizieren(2, 1/matrix[2][2]);

        //3.spalt: [0][2] und [1][2] nullen
        addieren(0, 2, -1*(matrix[0][2]));
        addieren(1, 2, -1*(matrix[1][2]));

        //[1][1] 1 machen
        multiplizieren(1, 1/matrix[1][1]);

        //2.spalt [0][1] nullen
        addieren(0, 1, -1*(matrix[0][1]));

        //[0][0] 1 machen
        multiplizieren(0, 1/matrix[0][0]);
    }
}
