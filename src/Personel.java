import java.util.Scanner;
class Personel extends Kisi {
    Scanner scanner = new Scanner(System.in);
    protected String departman;
    public Personel(String ad, String soyad, String kimlik, String tel, String departman) {
        super(ad, soyad, kimlik, tel);
        this.departman = departman;
    }
    public String kisiBilgileritoString() { //metin belgesine yazdırabilmek için String döndüren fonksiyon
        return (this.ad + " " + this.soyad + " , " + this.kimlik + " , " + this.tel + " , " + this.departman);
    }

    public void odaServisi() {  //oda servisi çağırma fonksiyonu
        System.out.println("Oda servisi istediğiniz odanın numarasını giriniz:");
        int no = scanner.nextInt();
        if (no > 0 && no < 11) { //geçerli oda numarası kontrolü
            System.out.println(no + " numaralı odaya servis gönderildi");
        } else {
            System.out.println("Bu numaraya ait oda bulunmamaktadır");
        }
    }
}