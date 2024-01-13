 class Kisi {
    protected String ad;
    protected String soyad;
    protected String kimlik;
    protected String tel;
    public Kisi(String ad, String soyad, String kimlik, String tel) {
        this.ad = ad;
        this.soyad = soyad;
        this.kimlik = kimlik;
        this.tel = tel;
    }
    public String getKimlik() {
        return kimlik;
    }
}