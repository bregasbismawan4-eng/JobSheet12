import java.util.Scanner;

public class RekapLaundry09 {

    static void inputData(int[][] data, String[] layanan, String[] hari, Scanner sc) {
        for (int i = 0; i < layanan.length; i++) {
            System.out.println("\nInput berat cucian (kg) untuk layanan: " + layanan[i]);
            for (int j = 0; j < hari.length; j++) {
                System.out.print("  " + hari[j] + ": ");
                data[i][j] = sc.nextInt();
            }
        }
    }

    static void tampilTabel(int[][] data, String[] layanan, int[] harga, String[] hari) {
        System.out.println("\n===== REKAP BERAT CUCIAN (KG) =====");
        System.out.printf("%-15s %-10s", "Layanan", "Harga/kg");
        for (String h : hari) {
            System.out.printf("%-8s", h);
        }
        System.out.println();
        System.out.println("-------------------------------------------------------------------------");

        for (int i = 0; i < layanan.length; i++) {
            System.out.printf("%-15s Rp%-8d", layanan[i], harga[i]);
            for (int j = 0; j < hari.length; j++) {
                System.out.printf("%-8d", data[i][j]);
            }
            System.out.println();
        }
    }

    static int totalPerLayanan(int[][] data, int idxLayanan) {
        int total = 0;
        for (int j = 0; j < data[idxLayanan].length; j++) {
            total += data[idxLayanan][j];
        }
        return total;
    }

    static void tampilLayananTertinggi(int[][] data, String[] layanan) {
        int idxMax = 0;
        int maxTotal = totalPerLayanan(data, 0);

        for (int i = 1; i < layanan.length; i++) {
            int total = totalPerLayanan(data, i);
            if (total > maxTotal) {
                maxTotal = total;
                idxMax = i;
            }
        }
        System.out.println("\nLayanan dengan total berat tertinggi: " + layanan[idxMax] + " (" + maxTotal + " kg)");
    }

    static int hariTersibuk(int[][] data) {
        int jumlahHari = data[0].length;
        int idxHariMax = 0;
        int maxBeratHari = 0;

        for (int j = 0; j < jumlahHari; j++) {
            int totalHari = 0;
            for (int i = 0; i < data.length; i++) {
                totalHari += data[i][j];
            }
            if (j == 0 || totalHari > maxBeratHari) {
                maxBeratHari = totalHari;
                idxHariMax = j;
            }
        }
        return idxHariMax;
    }

    static void tampilPendapatan(int[][] data, String[] layanan, int[] harga) {
        System.out.println("\n===== PENDAPATAN LAUNDRY =====");
        long totalKeseluruhan = 0;
        for (int i = 0; i < layanan.length; i++) {
            int beratTotal = totalPerLayanan(data, i);
            long pendapatan = (long) beratTotal * harga[i];
            totalKeseluruhan += pendapatan;
            System.out.printf("%-15s : %3d kg x Rp%,d = Rp%,d\n", layanan[i], beratTotal, harga[i], pendapatan);
        }
        System.out.printf("Total Pendapatan Selama 6 Hari: Rp%,d\n", totalKeseluruhan);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] layanan = {"Cuci Kering", "Cuci Setrika", "Setrika Saja", "Express"};
        int[] harga = {6000, 8000, 5000, 12000};
        String[] hari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
        int[][] data = new int[layanan.length][hari.length];

        inputData(data, layanan, hari, sc);
        tampilTabel(data, layanan, harga, hari);
        tampilLayananTertinggi(data, layanan);

        int idxHariSibuk = hariTersibuk(data);
        System.out.println("Hari tersibuk: " + hari[idxHariSibuk]);

        tampilPendapatan(data, layanan, harga);

        sc.close();
    }
}
