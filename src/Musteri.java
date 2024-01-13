import java.text.SimpleDateFormat;
import java.util.Date;
class Musteri extends Kisi {
    private Date rezervasyonTarihi;
    public Musteri(String ad, String soyad, String kimlik, String tel) {
        super(ad, soyad, kimlik, tel);
    }
    public Date getRezervasyonTarihi() {
        return rezervasyonTarihi;
    }
    public void setRezervasyonTarihi(Date rezervasyonTarihi) {
        this.rezervasyonTarihi = rezervasyonTarihi;
    }
    public void kisiBilgileri() {
        System.out.println("Ad: " + this.ad);
        System.out.println("Soyad: " + this.soyad);
        System.out.println("Kimlik No: " + this.kimlik);
        System.out.println("Telefon: " + this.tel);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Rezervasyon Tarihi: " + dateFormat.format(this.rezervasyonTarihi));
    }
}