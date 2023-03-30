package Recenze;

public abstract class Recenze {
    private String jmenoDivaka;
    private Integer hodnoceni;    private String komentar;

    public Recenze(String jmenoDivaka, String komentar) {
        this.jmenoDivaka = jmenoDivaka;
        this.komentar = komentar;
    }

    public abstract void printRecenze();

    public String getJmenoDivaka() {
        return jmenoDivaka;
    }
    public String getKomentar() {
        return komentar;
    }
}
