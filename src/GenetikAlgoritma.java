public class GenetikAlgoritma {

    /* Genetik Algoritma Parametreleri */
    private static final double caprazlamaDegeri = 0.5;
    private static final double mustasyonDegeri = 0.075;
    private static final int secilecekKisiSayisi = 50;
    private static final boolean enIyiKoru = true;

    public static Populasyon evrimGecirt(Populasyon populasyon) {

        Populasyon yeniPopulasyon = new Populasyon(populasyon.bireySayisi(), false);

        // En iyi bireyi koru , yani yeni populasyona en iyi bireyi ekleyelim ki ,
        // En iyi değerimizin üzerinden sonuca ulaşabilelim.
        if (enIyiKoru) {
            yeniPopulasyon.bireyEkle(0, populasyon.enIyiBirey());
        }

        int baslangic = 0;
        if (enIyiKoru) {
            baslangic = 1;
        }
        for (int i = baslangic; i < yeniPopulasyon.bireySayisi(); i++) {
            Birey birinciBirey = randomBireySec(populasyon);
            Birey ikinciBirey = randomBireySec(populasyon);
            Birey yeniBirey = caprazlamaYap(birinciBirey, ikinciBirey);
            yeniPopulasyon.bireyEkle(i, yeniBirey);
        }

        // Mutate population
        for (int i = baslangic; i < yeniPopulasyon.bireySayisi(); i++) {
            bireyiMutasyonaUgrat(yeniPopulasyon.bireyiGetir(i));
        }

        return yeniPopulasyon;


    }


    // Çaprazlama işleminin gerçekleştirilmesi
    private static Birey caprazlamaYap(Birey birinciBirey, Birey ikinciBirey) {
        Birey caprazlananBirey = new Birey();
        // Tüm genleri dolaşarak caprazlama değerinin altında veya üstünde ise 1. veya 2.
        // Genlerdeki değeri yeni genimize atayarak yeni bir birey yaratıyoruz...
        for (int i = 0; i < Baslangic.genAdedi; i++) {
            // Çaprazlama işlemini gerçekleştir.
            if (Math.random() <= caprazlamaDegeri) {
                caprazlananBirey.geniDegistir(i, birinciBirey.geniGetir(i));
            } else {
                caprazlananBirey.geniDegistir(i, ikinciBirey.geniGetir(i));
            }
        }
        return caprazlananBirey;
    }

    // Bireyler tekrar etmemesi için mutasyon geçirtmek gerekiyor.
    // Sonsuz döngüden veya aynı beylerin tekrarlamasının önüne geçmek için mutasyon geçirmeliyiz.
    private static void bireyiMutasyonaUgrat(Birey birey) {
        // Tüm genleri tek tek dolaşıyoruz
        for (int i = 0; i < Baslangic.genAdedi; i++) {
            if (Math.random() <= mustasyonDegeri) {
                // Yeni bir gen oluşturuyoruz (Gen 1 de gelebilir 0 da gelebilir.)
                //Istenilirse o anki gen değeri 0 ise 1 e veya 1 ise 0 a çevirilebiliriz.
                //Fakat gen değerini random atamayı tercih ettim ben.
                birey.geniDegistir(i, (byte) Math.round(Math.random()));
            }
        }
    }

    // Bir birey topluluğu oluşturacağız random,
    // Bu oluşan bireylerin arasındaki en iyi bireyleri secip yeni populasyonumuza ekleyeceğiz.
    // Populasyonun kisi sayisini genelde toplam populasyon/50 şeklinde se�mek gerekiyor.
    private static Birey randomBireySec(Populasyon populasyon) {
        // secilen populasyonumuzu oluşturuyoruz
        Populasyon secilenPopulasyon = new Populasyon(secilecekKisiSayisi, false);
        // kaç tane birey ekleyecek isek. Belirtilen sayı kadar populasyona birey ekliyoruz.
        // Seçilen bireyler rastgele seçilen bireylerdir.
        // Zamanla değişebileceği için Algoritma her seferinde aynı sonucu vermeyecektiz.
        // Buradaki rastsallığı kaldırıp kendi kurgunuzu yapabilirsiniz..
        for (int i = 0; i < secilecekKisiSayisi; i++) {
            int secilenBireyinSirasi = (int) (Math.random() * populasyon.bireySayisi());
            secilenPopulasyon.bireyEkle(i, populasyon.bireyiGetir(secilenBireyinSirasi));
        }
        return secilenPopulasyon.enIyiBirey();
    }

}
