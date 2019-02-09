import java.util.Arrays;
import java.awt.List;
import java.util.IntSummaryStatistics;
import java.math.*;
import java.util.Random;

public class Baslangic {

    static int hassasiyet = 27;
    public static int genAdedi = hassasiyet * 9;

    public static void main(String[] args) {

        // Popülasyon Oluşturuyoruz ve Kaç Bireyden Oluşacağını Ayarlıyoruz
        Populasyon populasyon = new Populasyon(10, true);

        // Kaç İterasyon Çalışacağını Ayarlıyoruz
        for (int i = 0; i < 1000; i++) {

            // Bir uygunluk şartı belirleyerek istenilen durumda döngüyü sonlandırıyoruz
            // Şart hiç sağlanmazsa iterasyon tamamlanana kadar devam ederek son duruma ulaşıyoruz.

            // Tercihen 50 İterasyon Sayısı ve Bitler arası farkın 1000 altında olması durumunda sonlandırıyorum
            int BirSayisi = populasyon.enIyiBirey().birlerinSayisiGetir();
            int fit = Math.abs(BirSayisi - 500000);
            if(fit < 100){
                System.out.println("Generasyon: " + i
                        + "| Fitness Değeri: " + populasyon.enIyiBirey().fitnessGetir()
                        + "| A Değeri: " + populasyon.enIyiBirey().aDegeriniGetir()
                        + "| Fark :" + Math.abs(500000 - populasyon.enIyiBirey().birlerinSayisiGetir())
                        + "| Sayılar: " + Arrays.toString(populasyon.enIyiBirey().sayilarinSayisiniGetir()));
                break;
            }else{
                byte[] bireyinGeni = populasyon.enIyiBirey().genleriGetir();
                String virguldenSonra = "";
                double aDegeri = 3.5;

                for (int j = 0; j < Baslangic.hassasiyet; j++) {
                    virguldenSonra += FitnessHesapla.sayiyiGetir(
                            Arrays.toString(
                                    Arrays.copyOfRange(bireyinGeni, j * 9, (j * 9) + 10)
                            )
                    );
                }

                // Değerleri ekrana yazdırarak ilerleyişi görsel olarak takip ediyoruz.
                aDegeri = 3.5 + Double.parseDouble("0." + virguldenSonra);
                System.out.println("Generasyon: " + i
                        + "| Fitness Değeri: " + populasyon.enIyiBirey().fitnessGetir()
                        + "| A Değeri: " + populasyon.enIyiBirey().aDegeriniGetir()
                        + "| Fark :" + Math.abs(500000 - populasyon.enIyiBirey().birlerinSayisiGetir())
                        + "| Sayılar: " + Arrays.toString(populasyon.enIyiBirey().sayilarinSayisiniGetir()));

                populasyon = GenetikAlgoritma.evrimGecirt(populasyon);
            }
        }
    }

}

