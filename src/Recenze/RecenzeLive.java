package Recenze;

public class RecenzeLive extends Recenze {

    public RecenzeLive(String jmenoDivaka, String komentar, String hodnoceni) {
        super(jmenoDivaka, komentar, hodnoceni);
    }

    @Override
    public void printRecenze() {
        System.out.printf("Jmeno divaka: %s, Komentar: %s, Hodnoceni: %s\n",
                getJmenoDivaka(), getKomentar(), getHodnoceni());
    }
}
