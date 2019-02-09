import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Random;

public class FitnessHesapla {

    public static double fitnessGetir(Birey birey) {
        // sayiAdetleriDizi indis değeri bizim için 0-15 arasındaki sayıların adedini temsil ediyor.

        byte[] bireyinGeni = birey.genleriGetir();
        String virguldenSonra = "";
        double aDegeri = 3.5;
        for (int i = 0; i < Baslangic.hassasiyet; i++) {
            virguldenSonra += sayiyiGetir(
                    Arrays.toString(
                            Arrays.copyOfRange(bireyinGeni, i * 9, (i * 9) + 10)
                    )
            );
        }
        aDegeri = Double.parseDouble("3." + virguldenSonra);
        if (aDegeri < 3.5) {
            aDegeri += 0.35;
        }


        // Bitleri Oluşturuyoruz.
        String bitDizilimleri = "";
        int[] sayiAdetleri = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        double sonXDegeri = 0.5;
        int birlerinSayisi = 0;
        String binnaryDizilimi = "";


        // 0 İle 4*Pi Arasında Rastgele Bir b Sayısı Üretiyoruz.
        Random b = new Random();
        double b_ust_limit = 4 * Math.PI;
        double bGelen = (b_ust_limit) * b.nextDouble();

        for (int i = 0; i < 1000000; i++) {
            if (i % 4 == 0 && i != 0) {
                sayiAdetleri[Integer.parseInt(binnaryDizilimi, 2)]++;
                binnaryDizilimi = "";
            }

            // Fonksiyon 1 = 'xn+1=a*xn*(1-xn)'
            // sonXDegeri = aDegeri * sonXDegeri * (1 - sonXDegeri);

            // Fonksiyon 2 = 'xn+1=a*xn*(1-xn)'
            // sonXDegeri = aDegeri * Math.sin(Math.PI * sonXDegeri);

            // Fonksiyon 3 = 'xn+1 = (xn + a - ((b/(2*PI))*(sin(2*PI*xn)))%1'
            // Ben tercihen 3. Fonksiyonu kullanarak bitleri üretiyorum.
            sonXDegeri = (sonXDegeri + aDegeri - (bGelen/(2 * Math.PI) * (Math.sin(2 * Math.PI * sonXDegeri))))%1;


            // Eşik Değerini Tercihen 0.593 Olarak Seçiyorum.
            if (sonXDegeri > 0.5) {
                // Dizideki 1 lerin toplamı bize o anki sayıyı verecek .
                // Örneğin gen dizilimide 5 adet 1 , 4 adet 0 var ise o anki bit değerimiz
                // 5 olacağından x değerinin 0.5 büyük olma durumunda sayi değerini arttıracağız.
                birlerinSayisi++;
                binnaryDizilimi += "1";
            } else {
                binnaryDizilimi += "0";
            }

        }

        // Aralarındaki Farkı Minimize Edeceğiz O yüzden En büyük fark değeri bizim fitness değrimiz olacak
        // En küçük değeri aradığımız için 1/Değer olmak zorunda. Fitness değeri ters mantık çalışır.
        // En büyük fitness değeri bizim için en iyi birey olacaktır.
        // Bu sebepten dolayı eğer 1/değer şeklinde yapmaz isek bize en kötü sonucu verecektir.
        // Dizideki max değer sayısı - min değer sayısı bize aradaki farkı verecek.


        IntSummaryStatistics istatistik = Arrays.stream(sayiAdetleri).summaryStatistics();

        birey.sayilarinSayisiniDegistir(sayiAdetleri);
        birey.birlerinSayisiDegistir(birlerinSayisi);
        birey.aDegeriniDegistir(aDegeri);
        return 1.0 / (istatistik.getMax() - istatistik.getMin());
    }

    static int sayiyiGetir(String deger) {
        int adet = 0;
        for (int i = 0; i < deger.length(); i++) {
            if (deger.charAt(i) == '1') {
                adet++;
            }
        }
        return adet;
    }
}
