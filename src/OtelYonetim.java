import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OtelYonetim {
    static int secim = 1; //ana menü için kontrol değişkeni
    public static String devam = "-1"; //menüler arası geçiş için kontrol değişkeni
    static Scanner scanner = new Scanner(System.in);

    private static void kontrol() {  //kullanıcının menüler arası geçisini sağlamak için fonksiyon
        System.out.println("Aynı işlem için 1 , ana menüye dönmek için 0 giriniz: ");
        devam = scanner.next();
        if (devam.equals("0")) {
            secim = -1; //ana menüye dönmek için girilmesi gereken değer
        }
        while (!devam.equals("1") && !devam.equals("0")) { //istenen değerler hariç birşey girilmesi durumnda tekrar giriş ister
            System.out.println("Geçerli bir değer giriniz");
            devam = scanner.next();
        }
    }
    public static void main(String[] args) {

        Otel otel = new Otel(10); // 10 odalı bir otel nesnesi oluşturur
        do {
            try {
                System.out.println("\n--- GAZİ OTEL REZERVASYON VE BİLGİ SİSTEMİ ---");
                System.out.println("1. Otel Bilgileri");
                System.out.println("2. Personel EKleme");
                System.out.println("3. Personel Listesini kaydet");
                System.out.println("4. Oda Bilgileri");
                System.out.println("5. Rezervasyon Yap");
                System.out.println("6. Rezervasyon İptal");
                System.out.println("7. Boş Odaları Listele");
                System.out.println("8. Tüm Odaları Listele");
                System.out.println("9. Oda Servisi Çağır");
                System.out.println("10.Müşteri Araması Yap");
                System.out.println("0. Çıkış");
                System.out.print("Seçiminiz: ");
                secim = scanner.nextInt();

                switch (secim) {
                    case 1:
                        do {
                            otel.otelBilgileri();
                            kontrol();
                        } while (devam.equals("1")); //kontrol değişkeni 1 olduğu sürece aynı işleme devam eder
                        break;
                    case 2:
                        do {
                            otel.personelOlustur();
                            kontrol();
                        } while (devam.equals("1"));
                        break;
                    case 3:
                        do {
                            try {
                                File file = new File("PersonelListesi.txt"); //file isminde dosya nesnesi oluşturur
                                if (!file.exists()) { //file nesnesi yok ise yeni bir tane oluşturur
                                    file.createNewFile();
                                }                                                         //file içine yazılacağını ve false olarak girilen değer
                                FileWriter fWriter = new FileWriter(file, false); //ise yazma işlmeninin dosyayı silmeden yapılcağını belirler
                                for (int i = 0; i < otel.personellistesi.size(); i++) {
                                    fWriter.write(otel.personellistesi.get(i).kisiBilgileritoString() + "\n");
                                }
                                fWriter.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e); //dosya işlemleri sırasında oluşabilecek hatalar yüzünden programın çalışmasının durmasını önler
                            }
                            System.out.println("Personel listesi metin belgesi olarak kaydedilmiştir!");
                            kontrol();
                        } while (devam.equals("1"));
                        break;
                    case 4:
                        do {
                            int odaNo = 0; //sorgualanacak odanın oda numarası çalışması için uygun bir değer
                            do {
                                try {
                                    System.out.print("Oda Numarası: ");
                                    odaNo = scanner.nextInt();
                                    otel.odaBilgileri(odaNo);
                                } catch (
                                        InputMismatchException e) { //istenen tip dışında bir değer girilmesi sonucu oluşan hatayı yakalar
                                    System.out.println("Lütfen bir sayı giriniz");
                                    scanner.nextLine(); // bir önceki scan işlemini durdurup kodun devam etmesini sağlar
                                }
                            } while (odaNo < 1 || odaNo > 10);
                            kontrol();
                        } while (devam.equals("1"));
                        break;
                    case 5:
                        do {
                            try {
                                otel.bosOdalariListele();
                                System.out.print("Oda Numarası Seçiniz: ");
                                int rezervasyonOdaNo = scanner.nextInt();
                                if (!otel.getOdalar()[rezervasyonOdaNo - 1].dolu) { //seçilen oda numarasının durumunu kontrol edip işleme devam eder
                                    System.out.print("Ad: ");
                                    String ad = scanner.next();
                                    System.out.print("Soyad: ");
                                    String soyad = scanner.next();
                                    System.out.print("Kimlik No: ");
                                    String kimlik = scanner.next();
                                    System.out.print("Telefon: ");
                                    String tel = scanner.next();
                                    otel.rezervasyonYap(rezervasyonOdaNo, ad, soyad, kimlik, tel);
                                } else System.out.println("Rezervasyon yapmak istediğiniz oda dolu");
                            } catch (
                                    ArrayIndexOutOfBoundsException e) { //dizinin index değeri dışında bir değer girildiğinde hatayı yakalar
                                System.out.println("Bu numaraya ait oda bulunmamaktadır");
                            }
                            kontrol();
                        } while (devam.equals("1"));
                        break;
                    case 6:
                        do {
                            try {
                                System.out.print("İptal Edilecek Rezervasyonun Oda Numarası: ");
                                int iptalOdaNo = scanner.nextInt();
                                otel.rezervasyonIptal(iptalOdaNo);
                            } catch (InputMismatchException e) { //istenen int türü dışında bir değer girilmesi sonucu oluşan hatayı yakalar
                                System.out.println("Lütfen bir sayı giriniz");
                                scanner.nextLine();
                            }
                            kontrol();
                        } while (devam.equals("1"));
                        break;
                    case 7:
                        do {
                            otel.bosOdalariListele();
                            kontrol();
                        } while (devam.equals("1"));
                        break;
                    case 8:
                        do {
                            otel.odalariListele();
                            kontrol();
                        } while (devam.equals("1"));
                        break;
                    case 9:
                        do {
                            try {
                                otel.personellistesi.get(0).odaServisi();
                            } catch (IndexOutOfBoundsException e) { //dizinin index değeri dışında bir değer girildiğinde hatayı yakalar
                                System.out.println("Servis yapıcak personel bulunmamaktadır");
                            }
                            kontrol();
                        } while (devam.equals("1"));
                        break;
                    case 10:
                        do {
                            try {
                                System.out.print("kimlik giriniz:");
                                String aramakimlik = scanner.next();
                                System.out.println(aramakimlik + " kimlik numaralı kişinin kaldığı oda numarası: " + otel.aramaYap(aramakimlik).getNo());
                            } catch (
                                    NullPointerException e) { //null değerinin kullanılmaya çalışılaması durumunda oluşan hatayı yakalar
                                System.out.println("Girdiğiniz kimlik numarasına sahip müşteri bulunamadı");
                            }
                            kontrol();
                        } while (devam.equals("1"));
                        break;
                    case 0:
                        System.out.println("Çıkış yapılıyor...");
                        break;
                    default:
                        System.out.println("Lütfen 0 ile 10 arasında değer giriniz");
                        secim = -1;
                }
            } catch (InputMismatchException e) { //istenen veri türü dışında bir değer girilmesi sonucu oluşan hatayı yakalar
                System.out.println("Lütfen bir sayı giriniz");
                scanner.nextLine(); // bir önceki scan işlemini durdurup kodun devam etmesini sağlar
                secim = -1; // ana menüye geri dönmesi için girilen değer
            }
        } while (secim != 0);
        scanner.close();
    }
}