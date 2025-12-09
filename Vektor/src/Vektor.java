public class Vektor {
    public static void main(String[] args) {
        Vektor v1 = new Vektor(new double[] {1,2,3});
        Vektor v2 = new Vektor(new double[] {3,4,5});
        System.out.println(v1.addieren(v2));
        System.out.println(v1.skalarproduktMultiplizieren(v2));
        System.out.println(v1.faktorMultiplizieren(2));
        System.out.println(v1);
    }

    double[] koord = new double[3];

    public Vektor(double[] v) {
        this.koord = v;
    }

    public String toString() {
        return "(" + koord[0] + ", " + koord[1] + ", " + koord[2] + ")";
    }

    public Vektor addieren(Vektor v) {
        Vektor ergebnis = new Vektor(new double[3]);
        for (int i = 0; i < 3; i++) {
            ergebnis.koord[i] = this.koord[i] + v.koord[i];
        }
        return ergebnis;
    }

    public double skalarproduktMultiplizieren(Vektor v) {
        double ergebnis = 0;
        for (int i = 0; i < 3; i++) {
            ergebnis += this.koord[i] * v.koord[i];
        }
        return ergebnis;
    }

    public Vektor faktorMultiplizieren(double d) {
        Vektor ergebnis = new Vektor(new double[3]);
        for (int i = 0; i < 3; i++) {
            ergebnis.koord[i] = this.koord[i] * d;
        }
        return ergebnis;
    }
}
