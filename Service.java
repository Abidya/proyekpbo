abstract class Service {
    protected String serviceName;
    protected double harga;

    public Service(String serviceName, double harga) {
        this.serviceName = serviceName;
        this.harga = harga;
    }

    public abstract double calculateHarga(int jumlah);

    public String getServiceName() {
        return serviceName;
    }

    public double getHarga() {
        return harga;
    }
}
