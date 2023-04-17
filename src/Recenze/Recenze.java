package Recenze;

import java.util.List;

public abstract class Recenze {
    private final String jmenoDivaka;
    private final String komentar;
    private final String hodnoceni;

    public Recenze(String jmenoDivaka, String komentar, String hodnoceni) {
        this.jmenoDivaka = jmenoDivaka;
        this.komentar = komentar;
        this.hodnoceni = hodnoceni;
    }

    public abstract void printRecenze();

    public String getJmenoDivaka() {
        return jmenoDivaka;
    }

    public String getKomentar() {
        return komentar;
    }
    public String getHodnoceni(){
        return hodnoceni;
    }

}
