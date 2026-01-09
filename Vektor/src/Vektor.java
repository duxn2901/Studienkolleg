public class Vektor {
    public static void main(String[] args) {
        Bruch br1 = new Bruch(1,2);
        Bruch br2 = new Bruch(4,2);
        Bruch br3 = new Bruch(3,5);
        Bruch br4 = new Bruch(1,6);
        Bruch br5 = new Bruch(5,2);
        Bruch br6 = new Bruch(2,3);

        Vektor v1 = new Vektor(new Bruch[] {br1, br2, br3});
        Vektor v2 = new Vektor(new Bruch[] {br4, br5, br6});
        System.out.println(v1.vektorAddieren(v2));
        System.out.println(v1.skalarproduktMultiplizieren(v2));
        System.out.println(v1.faktorMultiplizieren(2));
        System.out.println(v1);
        System.out.println(v1.kreuzproduktMultiplizieren(v2));
    }

    Bruch[] koord = new Bruch[3];

    public Vektor(Bruch[] v) {
        this.koord = v;
    }

    public String toString() {
        return "(" + koord[0] + ", " + koord[1] + ", " + koord[2] + ")";
    }

    public Vektor vektorAddieren(Vektor v) {
        Vektor ergebnis = new Vektor(new Bruch[3]);
        for (int i = 0; i < 3; i++) {
            ergebnis.koord[i] = this.koord[i].addieren(v.koord[i]);
        }
        return ergebnis;
    }

    public Bruch skalarproduktMultiplizieren(Vektor v) {
        Bruch ergebnis = new Bruch(0,1);
        for (int i = 0; i < 3; i++) {
            ergebnis.addieren(this.koord[i].multiplizieren(v.koord[i]));
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

    public Vektor kreuzproduktMultiplizieren(Vektor v) {
        Vektor ergebnis = new Vektor(new double[3]);
        for (int i = 0; i < 3; i++) {
            ergebnis.koord[i] = this.koord[(i+1)%3] * v.koord[(i+2)%3] - this.koord[(i+2)%3] * v.koord[(i+1)%3];
        }
        return ergebnis;
    }
}
