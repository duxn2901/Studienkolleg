public class Bruch{
    public static void main(String[] args) {
        Bruch br1 = new Bruch(2,3);
        System.out.println(br1);
        Bruch br2 = br1.multiplizieren(new Bruch(4,6));
        System.out.println(br2);
        System.out.println(br1);
        Bruch br3 = br1.dividieren(new Bruch(4,6));
        System.out.println(br3);
    }


    private int zaehler, nenner;
    public Bruch(int zaehler, int nenner) {
        this.zaehler = zaehler;
        this.nenner = nenner;
    }

    public String toString() {
        return zaehler + "/" + nenner;
    }

    public Bruch multiplizieren(Bruch br) {
        return new Bruch(this.zaehler*br.zaehler, this.nenner*br.nenner);
    }

    public Bruch kehrenBruch() {
        return new Bruch(this.nenner, this.zaehler);
    }

    public Bruch dividieren(Bruch br) {
        return multiplizieren(br.kehrenBruch());
    }


}