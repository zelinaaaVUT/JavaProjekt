package Recenze;

public class RecenzeAnimated extends Recenze {

    public RecenzeAnimated(String jmenoDivaka, String komentar, String hodnoceni) {
        super(jmenoDivaka, komentar, hodnoceni);
    }

    @Override
    public void printRecenze() {
        System.out.printf("Jmeno divaka: %s, Komentar: %s, Hodnoceni: %s\n%n",
                getJmenoDivaka(), getKomentar(), getHodnoceni());
    }
}
