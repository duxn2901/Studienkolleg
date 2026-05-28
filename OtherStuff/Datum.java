
import java.util.Scanner;

public class Datum {
    int tag;
    int monat;
    int jahr;
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Datum dat = new Datum();
        System.out.println(dat);
        System.out.println(dat.tagInIntKonvertieren());
        System.out.println(dat.tagInIntKonvertieren() - 739761);
        System.out.println((dat.tagInIntKonvertieren() - 739761) % 7);
        System.out.println(dat.wochenTag());
    }

    public Datum() {
        this.jahr = -1;
        while (this.jahr < 0) {
            System.out.println("jahr");
            this.jahr = sc.nextInt();
        }
        this.monat = -1;
        while (this.monat < 1 || this.monat > 12) {
            System.out.println("monat");
            this.monat = sc.nextInt();
        }

        int maxTage = 31;
        if (this.monat == 4 || this.monat == 6 || this.monat == 9 || this.monat == 11) maxTage = 30;
        if (this.monat == 2) maxTage = (this.jahr % 400 == 0 || (this.jahr % 4 == 0 && this.jahr % 100 != 0))? 29:28;
        this.tag = -1;
        while (this.tag < 1 || this.tag > maxTage) {
            System.out.println("tag");
            this.tag = sc.nextInt();
        }
    }

    public String toString() {
        String s = "";
        if (this.tag < 10) s+= "0" +this.tag;
        else s += this.tag;
        if (this.monat < 10) s+= ".0"+this.monat;
        else s += "." + this.monat;
        if (this.jahr < 10) s+= ".000"+this.jahr;
        else if (this.jahr < 100) s+= ".00"+this.jahr;
        else if (this.jahr < 1000) s+= ".0"+this.jahr;
        else s+= "."+this.jahr;
        return s;
    }

    public int tagInIntKonvertieren() {
        int leapJahr = (this.jahr/4) - (this.jahr/100) + (this.jahr/400);
        int tagInt = (this.jahr-1) * 365 + leapJahr;
        for (int i = 1; i < this.monat; i++) {
            if (i == 2) tagInt += (this.jahr % 400 == 0 || (this.jahr % 4 == 0 && this.jahr % 100 != 0))? 29:28;
            else if (i == 4 || i == 6 || i == 9 || i == 11) tagInt += 30;
            else tagInt += 31;
        }

        tagInt += this.tag;
        return tagInt;
    }

    public String wochenTag() {
        int tagInt = this.tagInIntKonvertieren();
        switch ((tagInt-739761)%7) {
            case 0: return "Montag";
            case 1: return "Dienstag";
            case 2: return "Mittwoch";
            case 3: return "Donnerstag";
            case 4: return "Freitag";
            case 5: return "Samstag";
            case 6: return "Sonntag";
            case -1: return "Sonntag";
            case -2: return "Samstag";
            case -3: return "Freitag";
            case -4: return "Donnerstag";
            case -5: return "Mittwoch";
            case -6: return "Dienstag";
        }
        return "";
    }
}
