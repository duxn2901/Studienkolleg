public class LGS {
    
    static double[][] matrix = new double[3][4];
    public static void main(String[] args) {
        erstellen();
        System.out.println(matrix);
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

    public static void addieren(int a, int b, double x) {
        for (int i = 0; i < 4; i++) {
            matrix[a][i] += matrix[b][i] * x;
        }
    }
}
