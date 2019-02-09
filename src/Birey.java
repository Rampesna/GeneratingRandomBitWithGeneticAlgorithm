import java.util.Random;

public class Birey {

    //Virgülden sonra ilk 10 karakterlik hassasiyette arayacağız sistemi.
    //Tüm onluk parçalardaki birlerin toplamı , o an ki sayı değerini verecek.
    private byte[] genler = new byte[Baslangic.genAdedi];
    private double fitness = 0;

    private double aDegeri = 3.5;
    private int birlerinSayisi = 0;
    private int[] sayilarinSayisi;

    public void sayilarinSayisiniDegistir(int[] sayilarinSayisi) {
        this.sayilarinSayisi = sayilarinSayisi;
    }

    public int[] sayilarinSayisiniGetir() {
        return this.sayilarinSayisi;
    }

    public void birlerinSayisiDegistir(int sayi) {
        this.birlerinSayisi = sayi;
    }

    public int birlerinSayisiGetir() {
        return this.birlerinSayisi;
    }


    public void aDegeriniDegistir(double aDegeri) {
        this.aDegeri = aDegeri;
    }

    public double aDegeriniGetir() {
        return this.aDegeri;
    }

    public void bireyOlustur() {
        for (int i = 0; i < Baslangic.genAdedi; i++) {
            genler[i] = (byte) Math.round(Math.random());
        }
    }

    public byte geniGetir(int indis) {
        return genler[indis];

    }

    public void geniDegistir(int indis, byte deger) {
        fitness = 0;
        genler[indis] = deger;
    }


    public double fitnessGetir() {
        if (fitness == 0) {
            fitness = FitnessHesapla.fitnessGetir(this);

        }
        return fitness;
    }

    public byte[] genleriGetir() {
        return this.genler;
    }

    @Override
    public String toString() {
        //geni okumak için ihtiyacımız var
        String genDizilimi = "";
        for (int i = 0; i < Baslangic.genAdedi; i++) {
            genDizilimi += geniGetir(i);
        }
        return genDizilimi;
    }

}
