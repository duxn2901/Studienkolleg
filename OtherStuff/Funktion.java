public class Funktion {
    
    double[] a;

    public static void main(String[] args) {
        double[] a1 = {1,2,1};
        double[] a2 = {1,3,3,1};
        Funktion f = new Funktion(a1);
        Funktion f1 = new Funktion(a1);
        Funktion f2 = new Funktion(a2);
        //System.out.println(f.addieren(f1));
        //System.out.println(f1.addieren2(f2));
        //System.out.println(f2.ableiten());
        //System.out.println(f.getWert(2));
        //System.out.println(f1.multiplizieren(f2));
        System.out.println(f1.integrieren(1,2));
    }

    public Funktion(double[] a) {
        this.a = a;
    }

    public double[] getter() {
        return this.a;
    }

    public void setter(int index, int value) {
        this.a[index] = value;
    }

    @Override
    public String toString() {
        String s = "f(x) = ";
        for (int i = 0; i < a.length; i++) {
            if (i == 0) s+= a[i] + " + ";
            else if (i != a.length-1) s+= a[i] + "x^" + i + " + ";
            else s+= a[i] + "x^" + i;    
        }   
        return s;
    }

    public Funktion addieren(Funktion summand) {
        Funktion summe = new Funktion(new double[this.a.length]);
        for (int i = 0; i < summe.a.length; i++) {
            summe.a[i] = this.a[i] + summand.a[i];
        }
        return summe;
    }

    public Funktion addieren2(Funktion summand) {
        Funktion summe = new Funktion(new double[Math.max(this.a.length, summand.a.length)]);
        for (int i = 0; i < summe.a.length; i++) {
            if (i >= this.a.length) summe.a[i] = summand.a[i];
            else if (i >= summand.a.length) summe.a[i] = this.a[i];
            else summe.a[i] = this.a[i] + summand.a[i];
        }
        return summe;
    }

    public Funktion ableiten() {
        Funktion ableitung = new Funktion(new double[this.a.length-1]);
        for (int i = 0; i < ableitung.a.length; i++) {
            ableitung.a[i] = this.a[i+1] * (i+1);
        }
        return ableitung;
    }

    public double getWert(double x) {
        double f = 0;
        for (int i = 0; i < this.a.length; i++) {
            f += Math.pow(x,i) * a[i];
        }
        return f;
    }

    public Funktion koeffizientMultiplizieren(double d, int exponent) {
        double[] neuA = new double[this.a.length+exponent];
        for (int i = 0; i < neuA.length; i++) {
            if (i < exponent) neuA[i] = 0;
            else neuA[i] = this.a[i-exponent] * d;
        }
        return new Funktion(neuA);
    } 

    public Funktion multiplizieren(Funktion f) {
        Funktion mult = new Funktion(new double[this.a.length + f.a.length-1]);
        for (int i = 0; i < f.a.length; i++) {
            mult = mult.addieren2(this.koeffizientMultiplizieren(f.a[i], i));
        }
        return mult;
    }

    public Funktion integrieren() {
        double[] neuA = new double[this.a.length+1];
        neuA[0] = 0;
        for (int i = 1; i < neuA.length; i++) {
            neuA[i] = this.a[i-1]/i;
        }
        return new Funktion(neuA);
    }

    public double integrieren(double a, double b) {
        Funktion integration = this.integrieren();
        return integration.getWert(b) - integration.getWert(a);
    }
}
