
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }


    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti <= 0) {
            throw new IllegalArgumentException("Epäpositiivinen kapasiteetti");//heitin vaan jotain :D
        }
        if (kasvatuskoko <= 0) {
            throw new IllegalArgumentException("Epäpositiivinen kasvatuskoko");//heitin vaan jotain :D :D :D
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {

        boolean onJoukossa = kuuluu(luku);
        if (onJoukossa) {
            return false;
        }
        lukujono[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm == lukujono.length) {
            kasvata();
        }
        return true;
    }

    public void kasvata() {
        int[] kopio = this.toIntArray();
        lukujono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(kopio, lukujono);
    }

    public boolean kuuluu(int luku) {
        return etsi(luku) != -1;
    }

    public boolean poista(int luku) {
        int indeksi = etsi(luku);
        if (indeksi == -1) {
            return false;
        }
        // swapataan poistettava ja taulukon viimeinen luku
        lukujono[indeksi] = lukujono[alkioidenLkm - 1];
        lukujono[alkioidenLkm - 1] = 0;
        alkioidenLkm--;
        return true;
    }

    private int etsi(int luku) {
        int indeksi = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                indeksi = i; //siis luku löytyy tuosta kohdasta :D samaa mieltä D:
                break;
            }
        }
        return indeksi;
    }

    private void kopioiTaulukko(int[] mista, int[] minne) {
        for (int i = 0; i < mista.length; i++) {
            minne[i] = mista[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String toString = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            toString += lukujono[i];
            toString += ", ";
        }
        if (alkioidenLkm > 0) {
            toString += lukujono[alkioidenLkm - 1];
        }
        toString += "}";
        return toString;
    }


    public int[] toIntArray() {
        return Arrays.copyOf(lukujono, alkioidenLkm);
    }


    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdisteJoukko.lisaa(bTaulu[i]);
        }
        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkausJoukko.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkausJoukko;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotusJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotusJoukko.poista(i);
        }
        return erotusJoukko;
    }

}