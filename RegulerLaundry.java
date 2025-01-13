public class RegulerLaundry extends Service {
    public RegulerLaundry() {
        super("Regular Laundry", 5.0);
    }

    @Override
    public double calculateHarga(int jumlah) {
        return harga * jumlah;
    }
}
