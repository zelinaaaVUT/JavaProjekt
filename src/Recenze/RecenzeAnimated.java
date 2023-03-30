package Recenze;

public class RecenzeAnimated extends Recenze {
    private Integer doporucenyVek;
    private Integer hodnoceni;
    public RecenzeAnimated(String jmenoDivaka, String komentar, Integer hodnoceni) {
        super(jmenoDivaka, komentar);
        this.hodnoceni = hodnoceni;
    }

    /*public Integer getDoporucenyVek() {
        return doporucenyVek;
    }*/
    public Integer getHodnoceni(){ return hodnoceni;}

    @Override
    public void printRecenze() {
        System.out.println(String.format("Jmeno divaka: %s, Komentar: %s, Hodnoceni: %s\n",
                getJmenoDivaka(), getKomentar(), getHodnoceni()));
    }
}
