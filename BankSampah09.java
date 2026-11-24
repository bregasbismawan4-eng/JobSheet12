

import java.util.Scanner;

public class BankSampah09 {

    static double[] inputSetoran(int n, Scanner sc) {
        double[] setoran = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan berat setoran nasabah ke-" + (i + 1) + " (kg): ");
            setoran[i] = sc.nextDouble();
        }
        return setoran;
    }

    static void tampilSetoran(double[] setoran) {
        System.out.println("\n===== DATA SETORAN NASABAH =====");
        for (int i = 0; i < setoran.length; i++) {
            System.out.printf("Nasabah ke-%d: %.2f kg\n", (i + 1), setoran[i]);
        }
    }

    static double totalSetoran(double[] setoran) {
        double total = 0;
        for (double berat : setoran) {
            total += berat;
        }
        return total;
    }

    static double hitungSaldo(double berat, double hargaPerKg) {
        return berat * hargaPerKg;
    }

    static int nasabahTerbanyak(double[] setoran) {
        int idxMax = 0;
        for (int i = 1; i < setoran.length; i++) {
            if (setoran[i] > setoran[idxMax]) {
                idxMax = i;
            }
        }
        return idxMax;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double hargaPerKg = 3000;

        System.out.print("Masukkan jumlah nasabah (N): ");
        int n = sc.nextInt();

        double[] dataSetoran = inputSetoran(n, sc);
        tampilSetoran(dataSetoran);

        System.out.println("\n===== SALDO NASABAH (Rp3.000/kg) =====");
        for (int i = 0; i < dataSetoran.length; i++) {
            double saldo = hitungSaldo(dataSetoran[i], hargaPerKg);
            System.out.printf("Nasabah ke-%d: Rp%,.0f\n", (i + 1), saldo);
        }

        double total = totalSetoran(dataSetoran);
        System.out.printf("\nTotal berat setoran seluruh nasabah: %.2f kg\n", total);

        int idxTerbanyak = nasabahTerbanyak(dataSetoran);
        System.out.printf("Nasabah dengan setoran terbanyak: Nasabah ke-%d (%.2f kg)\n",
                (idxTerbanyak + 1), dataSetoran[idxTerbanyak]);

        sc.close();
    }
}
