package Recenze;

public class RecenzeAnimated extends Recenze {
    //private final Integer hodnoceni;

    public RecenzeAnimated(String jmenoDivaka, String komentar, String hodnoceni) {
        super(jmenoDivaka, komentar, hodnoceni);
        //this.hodnoceni = hodnoceni;
    }

    /*public Integer getHodnoceni() {
        return hodnoceni;
    }*/

    @Override
    public void printRecenze() {
        System.out.printf("Jmeno divaka: %s, Komentar: %s, Hodnoceni: %s\n%n",
                getJmenoDivaka(), getKomentar(), getHodnoceni());
    }
}
