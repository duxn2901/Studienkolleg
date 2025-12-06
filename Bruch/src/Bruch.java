public class Bruch{
    public static void main(String[] args) {
        // Bruch br1 = new Bruch(2,3);
        // System.out.println(br1);
        // Bruch br2 = br1.multiplizieren(new Bruch(4,6));
        // System.out.println(br2);
        // System.out.println(br1);
        // Bruch br3 = br1.dividieren(new Bruch(4,6));
        // System.out.println(br3);
        // Bruch br4 = new Bruch(12,2);
        // br4 = br4.kuerzen();
        // System.out.println(br4);
        Bruch br5 = new Bruch(7,6);
        Bruch br6 = new Bruch(1,13);
        System.out.println(br5.nennenAngleichen(br6));
    }


    private int zaehler, nenner;
    public Bruch(int zaehler, int nenner) {
        this.zaehler = zaehler;
        this.nenner = nenner;
    }

    public String toString() {
        if (nenner == 1) return zaehler + "";
        return zaehler + "/" + nenner;
    }

    public Bruch multiplizieren(Bruch br) {
        return new Bruch(zaehler*br.zaehler, nenner*br.nenner);
    }

    public Bruch kehrenBruch() {
        return new Bruch(nenner, zaehler);
    }

    public Bruch dividieren(Bruch br) {
        return multiplizieren(br.kehrenBruch());
    }

    public Bruch kuerzen() {
        int gcd = GCDFinden(zaehler, nenner);
        return new Bruch(zaehler / gcd, nenner / gcd);
    }

    public static int GCDFinden(int a, int b) {    
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a%b;
            a = temp;
        }
        return a;
    }

    // public Bruch addieren(Bruch br) {
    //     return
    // }

    // public Bruch subtrahieren(Bruch br) {
    //     return
    // }

    public Bruch nennenAngleichen(Bruch br) {
        int lcm = LCMFinden(br.nenner, nenner);       
        return new Bruch(zaehler * (lcm / nenner), lcm);
    }

    public int LCMFinden(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        return (a*b) / GCDFinden(a, b);
    }
}