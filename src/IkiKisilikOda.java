import java.text.SimpleDateFormat;
import java.util.Date;
class IkiKisilikOda extends Oda {
    public IkiKisilikOda(int no, boolean dolu) {
        super(no, 2, dolu);
    }
    @Override
    public void odaBilgileri() { //oda bilgilerini ve varsa kalan müşterinin bilgilerini yazdıran fonksiyon
        System.out.println("Oda numarası: " + this.no);
        System.out.println(this.kapasite + " Kişilik Oda");
        System.out.println("Oda durumu: " + durum());
        if (this.dolu) {
            System.out.println("Odada kalan müşteri bilgileri; ");
            this.musteri.kisiBilgileri();
        }
    }

    @Override
    public void rezervasyonYap(String ad, String soyad, String kimlik, String tel) { //istenen oda numarasına rezervasyon yapıp odanın durumunu ayarlar
        if (this.dolu) {
            System.out.println("Bu oda dolu, başka bir oda seçin!");
        } else {
            this.musteri = new Musteri(ad, soyad, kimlik, tel); //odaya ait müşteri nesneni oluşturur
            this.musteri.setRezervasyonTarihi(new Date()); // Mevcut tarih ve saat bilgisini değişkene atar
            this.dolu = true;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy"); //tarih formatını düzenler
            System.out.println("Rezervasyon yapıldı. Oda numarası: " + this.no + " Rezervasyon Tarihi: " + dateFormat.format(this.musteri.getRezervasyonTarihi()));
            //rezervasyon tarihini date türünden stringe çevirerek yazdırır
        }
    }
    @Override
    public void rezervasyonIptal() { //istenen oda numarasındaki rezervasyonu iptal eden fonksiyon
        if (this.dolu) {
            this.musteri = null; //müşteriyi siler
            this.dolu = false; //odanın durumunu değiştirir
            System.out.println("Rezervasyon iptal edildi. Oda numarası: " + this.no);
        } else {
            System.out.println("Bu oda zaten boş!");
        }
    }
}