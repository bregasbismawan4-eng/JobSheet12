
public class percobaan4 {
    static void daftarPengunjung(String... namaPengunjung) {
        System.out.println("Daftar Nama Pengunjung:");
        for (int i = 0; i < namaPengunjung.length; i++) {
            System.out.println("- " + namaPengunjung[i]);
        }
        System.out.println("Jumlah pengunjung: " + namaPengunjung.length);
    }

    public static void main(String[] args) {
        daftarPengunjung("Ali", "Budi", "Citra");

        daftarPengunjung("Andi");
        daftarPengunjung("Doni", "Eti", "Fahmi", "Galih");
    }
}
