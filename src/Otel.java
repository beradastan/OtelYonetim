import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Otel {
    final private String isim = "Gazi";
    final private String adres = "Beşevler";
    private Oda[] odalar;
    public List<Personel> personellistesi = new ArrayList<>(); // personel sınıfına ait nesnelerden liste oluşturur
    Scanner scanner = new Scanner(System.in);
    public Otel(int odaSayisi) { //otelin oda sayısına kadar tek sayılarla iki kişilik , çift sayılarla 4 kişilik boş oda oluşturur
        this.odalar = new Oda[odaSayisi];
        for (int i = 0; i < odaSayisi; i++) {
            if (i % 2 == 0) {
                this.odalar[i] = new IkiKisilikOda(i + 1, false); //oda no ve yeni oluşturulduğu için boş bilgisi girilir
            } else {
                this.odalar[i] = new DortKisilikOda(i + 1, false); //oda no ve yeni oluşturulduğu için boş bilgisi girilir
            }
        }
    }
    public Oda[] getOdalar() {
        return this.odalar;
    }
    public void otelBilgileri() { //otel bilgilerini yazdıran fonksiyon
        System.out.println("Otelin adı: " + this.isim);
        System.out.println("Otelin adresi: " + this.adres);
        System.out.println("Otelin oda sayısı: " + this.odalar.length);
    }
    public void odaBilgileri(int odaNo) { //oda bilgilerini ve doluysa müşteri bilgilerini fonskiyon
        if (odaNo > 0 && odaNo <= this.odalar.length) {
            this.odalar[odaNo - 1].odaBilgileri(); //alt sınıftan oluşan dizi nesnesinin fonksiyonunu çağırır
        } else {
            System.out.println("Bu numaraya ait oda bulunmamaktadır");
        }
    }

    public void rezervasyonYap(int odaNo, String ad, String soyad, String kimlik, String tel) {
        this.odalar[odaNo - 1].rezervasyonYap(ad, soyad, kimlik, tel);
        //istenen oda numarasının fonksiyonunu çağırır
    }

    public void rezervasyonIptal(int odaNo) {
        if (odaNo > 0 && odaNo <= this.odalar.length) {
            this.odalar[odaNo - 1].rezervasyonIptal();  //istenen oda numarasının fonksiyonunu çağırır
        } else {
            System.out.println("Bu numaraya ait oda bulunmamaktadır");
        }
    }

    public void bosOdalariListele() { //oteldeki boş odaların numaralarını ekrana yazdırır
        System.out.println("Boş Odalar:");
        for (Oda oda : this.odalar) { //dizideki tüm odaları geçici bir nesne kullanarak döngüye sokar
            if (!oda.getDolu()) {
                System.out.println("Oda Numarası: " + oda.getNo() + "  (" + oda.kapasite + " kişilik)");
            }
        }
    }
    public void odalariListele() { //oteldeki tüm odaların bilgilerini ekrana yazdırır
        for (Oda oda : this.odalar) {  //dizideki tüm odaları geçici bir nesne kullanarak döngüye sokar
            System.out.println("Oda Numarası: " + oda.getNo() + "  (durumu: " + oda.durum() + ")" + "  (" + oda.kapasite + " kişilik)");
        }
    }
    public Oda aramaYap(String kimlik) { //müşteri kimlik numarasına göre varsa hangi odada kaldığını döndüren fonskiyon
        for (Oda oda : odalar) { //dizideki tüm odaları geçici bir nesne kullanarak döngüye sokar
            if (oda.dolu && oda.getMusteri().getKimlik().equals(kimlik)) {
                return oda;
            }
        }
        return null;
    }
    public void personelOlustur() { //otele personel eklemesi yapar
        System.out.print("Ad: ");
        String ad = scanner.next();
        System.out.print("Soyad: ");
        String soyad = scanner.next();
        System.out.print("Kimlik No: ");
        String kimlik = scanner.next();
        System.out.print("Telefon: ");
        String tel = scanner.next();
        System.out.print("Departman: ");
        String departman = scanner.next();
        personellistesi.add(new Personel(ad, soyad, kimlik, tel, departman)); //oluşturulan listeye personel nesnesi ekler
        System.out.println("Yeni personel oluşturuldu");
    }
}