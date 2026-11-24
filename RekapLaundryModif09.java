import java.util.Scanner;

public class RekapLaundryModif09{

    static void inputData(int[][] data, String[] layanan, int jumlahHari, Scanner sc) {
        for (int i = 0; i < layanan.length; i++) {
            System.out.println("\nInput berat cucian (kg) untuk layanan: " + layanan[i]);
            for (int j = 0; j < jumlahHari; j++) {
                System.out.print("  Hari ke-" + (j + 1) + ": ");
                data[i][j] = sc.nextInt();
            }
        }
    }

    static void tampilTabel(int[][] data, String[] layanan, int[] harga, int jumlahHari) {
        System.out.println("\n===== REKAP BERAT CUCIAN (KG) =====");
        System.out.printf("%-15s %-10s", "Layanan", "Harga/kg");
        for (int j = 0; j < jumlahHari; j++) {
            System.out.printf("%-10s", "Hari ke-" + (j + 1));
        }
        System.out.println();
        System.out.println("-------------------------------------------------------------------------");

        for (int i = 0; i < layanan.length; i++) {
            System.out.printf("%-15s Rp%-8d", layanan[i], harga[i]);
            for (int j = 0; j < jumlahHari; j++) {
                System.out.printf("%-10d", data[i][j]);
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

    static void tampilPendapatan(int[][] data, String[] layanan, int[] harga, int jumlahHari) {
        System.out.println("\n===== PENDAPATAN LAUNDRY =====");
        long totalKeseluruhan = 0;
        for (int i = 0; i < layanan.length; i++) {
            int beratTotal = totalPerLayanan(data, i);
            long pendapatan = (long) beratTotal * harga[i];
            totalKeseluruhan += pendapatan;
            System.out.printf("%-15s : %3d kg x Rp%,d = Rp%,d\n", layanan[i], beratTotal, harga[i], pendapatan);
        }
        System.out.printf("Total Pendapatan Selama %d Hari: Rp%,d\n", jumlahHari, totalKeseluruhan);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah layanan: ");
        int jumlahLayanan = sc.nextInt();
        System.out.print("Masukkan jumlah hari: ");
        int jumlahHari = sc.nextInt();
        sc.nextLine(); // membersihkan buffer newline

        String[] layanan = new String[jumlahLayanan];
        int[] harga = new int[jumlahLayanan];
        int[][] data = new int[jumlahLayanan][jumlahHari];

        for (int i = 0; i < jumlahLayanan; i++) {
            System.out.print("Masukkan nama layanan ke-" + (i + 1) + ": ");
            layanan[i] = sc.nextLine();
            System.out.print("Masukkan harga per kg untuk " + layanan[i] + ": ");
            harga[i] = sc.nextInt();
            sc.nextLine(); // membersihkan buffer newline
        }

        inputData(data, layanan, jumlahHari, sc);
        tampilTabel(data, layanan, harga, jumlahHari);
        tampilLayananTertinggi(data, layanan);

        int idxHariSibuk = hariTersibuk(data);
        System.out.println("Hari tersibuk: Hari ke-" + (idxHariSibuk + 1));

        tampilPendapatan(data, layanan, harga, jumlahHari);

        sc.close();
    }
}
