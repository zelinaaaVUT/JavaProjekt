package Recenze;

public class RecenzeLive extends Recenze {
    private final String hodnoceni;

    public RecenzeLive(String jmenoDivaka, String komentar, String hodnoceni) {
        super(jmenoDivaka, komentar);
        this.hodnoceni = hodnoceni;
    }

    public String getHodnoceni() {
        return hodnoceni;
    }

    @Override
    public void printRecenze() {
        System.out.printf("Jmeno divaka: %s, Komentar: %s, Hodnoceni: %s\n%n",
                getJmenoDivaka(), getKomentar(), getHodnoceni());
    }
}
