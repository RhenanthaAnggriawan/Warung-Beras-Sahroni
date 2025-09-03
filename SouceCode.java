import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class KasirWarungBeras {
    private static final BigDecimal DISKON = new BigDecimal("0.05");
    private static final NumberFormat RUPIAH = NumberFormat.getCurrencyInstance(new Locale("id","ID"));
    static {
        RUPIAH.setMaximumFractionDigits(0);
        RUPIAH.setMinimumFractionDigits(0);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=== Kasir Warung Beras ===");
            BigDecimal kg = read(sc, "Masukkan jumlah beras (kg): ");
            BigDecimal harga = read(sc, "Masukkan harga per kg (Rp): ");

            BigDecimal total = kg.multiply(harga);
            BigDecimal potongan = total.multiply(DISKON);
            BigDecimal totalAkhir = total.subtract(potongan);

            BigDecimal totalR = total.setScale(0, RoundingMode.HALF_UP);
            BigDecimal potonganR = potongan.setScale(0, RoundingMode.HALF_UP);
            BigDecimal akhirR = totalAkhir.setScale(0, RoundingMode.HALF_UP);

            System.out.println("Total sebelum diskon : " + RUPIAH.format(totalR));
            System.out.println("Diskon 5%            : " + RUPIAH.format(potonganR));
            System.out.println("Total setelah diskon : " + RUPIAH.format(akhirR));

            BigDecimal tunai = read(sc, "Jumlah uang diterima (Rp): ");
            if (tunai.compareTo(akhirR) < 0) {
                System.out.println("Uang kurang          : " + RUPIAH.format(akhirR.subtract(tunai)));
            } else {
                System.out.println("Kembalian            : " + RUPIAH.format(tunai.subtract(akhirR)));
            }
        }
    }

    private static BigDecimal read(Scanner sc, String prompt) {
        System.out.print(prompt);
        return new BigDecimal(sc.nextLine().trim().replace(",", "."));
    }
}
