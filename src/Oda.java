abstract class Oda {
    protected int no;
    protected int kapasite;
    protected boolean dolu;
    protected Musteri musteri;
    public Oda(int no, int kapasite, boolean dolu) {
        this.no = no;
        this.kapasite = kapasite;
        this.dolu = dolu;
    }
    public int getNo() {
        return this.no;
    }
    public boolean getDolu() {
        return this.dolu;
    }
    public Musteri getMusteri() {
        return this.musteri;
    }
    public String durum() { //odanın boş veya dolu olma durumunu string olarak döndüren fonksiyon
        if (this.dolu == true) {
            return "dolu";
        } else return "boş";
    }
    public abstract void odaBilgileri(); //alt sınıflarda kullanılmak üzere abstract fonksiyonlar
    public abstract void rezervasyonYap(String ad, String soyad, String kimlik, String tel);
    public abstract void rezervasyonIptal();
}
