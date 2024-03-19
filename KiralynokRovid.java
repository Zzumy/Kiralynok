package kiralynoklegacy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class KiralynokRovid {

    private final char[][] T;
    private final char UresCella;

    public KiralynokRovid(char c) {
        UresCella = c;
        T = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                T[i][j] = UresCella;
            }
        }
    }

    public void Megjelenit() {
        for (char[] row : T) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void Elhelyez(int db) {
        while (db > 0) {
            int x = (int) (Math.random() * 8);
            int y = (int) (Math.random() * 8);
            if (T[x][y] == UresCella) {
                T[x][y] = 'K';
                db--;
            }
        }
    }

    public boolean UresOszlop(int m) {
        for (int i = 0; i < 8; i++) {
            if (T[i][m] != UresCella) {
                return false;
            }
        }
        return true;
    }

    public int UresOszlopokSzama() {
        int db = 0;
        for (int i = 0; i < 8; i++) {
            if (UresOszlop(i)) {
                db++;
            }
        }
        return db;
    }

    public boolean UresSor(int m) {
        for (int i = 0; i < 8; i++) {
            if (T[m][i] != UresCella) {
                return false;
            }
        }
        return true;
    }

    public int UresSorokSzama() {
        int db = 0;
        for (int i = 0; i < 8; i++) {
            if (UresSor(i)) {
                db++;
            }
        }
        return db;
    }

    public void FajlbaIr() {
        try (FileWriter fw = new FileWriter("tablak64.txt", true)) {
            for (char[] row : T) {
                fw.write(row);
                fw.write("\r\n");
            }
            fw.write("\r\n");
        } catch (IOException e) {
        }
    }

    public void FajlTorles() {
        File file = new File("tablak64.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    public static void main(String[] args) {
        KiralynokRovid t = new KiralynokRovid('.');
        t.FajlTorles();

        System.out.println("4. feladat: az üres tábla:");
        t.Megjelenit();
        t.Elhelyez(8);

        System.out.println("6. feladat: a feltöltött tábla:");
        t.Megjelenit();

        System.out.println("9. feladat: Üres oszlopok és sorok száma:");
        System.out.println("Oszlopok: " + t.UresOszlopokSzama());
        System.out.println("Sorok: " + t.UresSorokSzama());

        for (int x = 1; x <= 64; x++) {
            KiralynokRovid tt = new KiralynokRovid('*');
            tt.Elhelyez(x);
            tt.FajlbaIr();
        }
    }
}
