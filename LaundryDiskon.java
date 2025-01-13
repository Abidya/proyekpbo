public class LaundryDiskon implements Diskon {
    private static final double DISCOUNT_PERCENTAGE = 0.15;

    @Override
    public double applyDiscount(double total) {
        return total - (total * DISCOUNT_PERCENTAGE);
    }
}
