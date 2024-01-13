import java.text.SimpleDateFormat;
import java.util.Date;

class DortKisilikOda extends Oda { //oda sınıfının bir alt sınıfı olarak oluşturur
    public DortKisilikOda(int no, boolean dolu) {
        super(no, 4, dolu);
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
            this.musteri = new Musteri(ad, soyad, kimlik, tel);
            this.musteri.setRezervasyonTarihi(new Date());
            this.dolu = true;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy"); //tarih formatının düzenler
            System.out.println("Rezervasyon yapıldı. Oda numarası: " + this.no + " Rezervasyon Tarihi: " + dateFormat.format(this.musteri.getRezervasyonTarihi()));
            //rezervasyon tarihini date türünden stringe çevirerek yazdırır
        }
    }

    @Override
    public void rezervasyonIptal() { //istenen oda numarasındaki rezervasyonu iptal eden fonksiyon
        if (this.dolu) {
            this.musteri = null;
            this.dolu = false;
            System.out.println("Rezervasyon iptal edildi. Oda numarası: " + this.no);
        } else {
            System.out.println("Bu oda zaten boş!");
        }
    }
}
