public class CuciBersih extends Service {
    public CuciBersih() {
        super("Cuci Bersih", 10.0);
    }

    @Override
    public double calculateHarga(int jumlah) {
        return harga * jumlah;
    }
}
