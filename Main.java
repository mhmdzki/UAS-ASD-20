import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Membuat beberapa objek Kendaraan
        Kendaraan kendaraan1 = new Kendaraan("B 1234 AB", "John Doe", "motor", 150, 2020, 6);
        Kendaraan kendaraan2 = new Kendaraan("D 5678 CD", "Jane Smith", "mobil", 2000, 2018, 4);
        Kendaraan kendaraan3 = new Kendaraan("F 9101 EF", "Bob Johnson", "motor", 300, 2015, 8);

        // Membuat objek AntrianPajak
        TransaksiPajak.AntrianPajak antrianPajak = new TransaksiPajak.AntrianPajak();

        // Menambahkan kendaraan ke dalam antrian pajak
        antrianPajak.tambahKendaraan(kendaraan1);
        antrianPajak.tambahKendaraan(kendaraan2);
        antrianPajak.tambahKendaraan(kendaraan3);

        // Menampilkan informasi kendaraan
        System.out.println("Informasi Kendaraan:");
        System.out.println("Kendaraan 1:");
        displayKendaraanInfo(kendaraan1);
        System.out.println();
        System.out.println("Kendaraan 2:");
        displayKendaraanInfo(kendaraan2);
        System.out.println();
        System.out.println("Kendaraan 3:");
        displayKendaraanInfo(kendaraan3);
        System.out.println();

        // Simulasi pembayaran pajak
        System.out.println("Simulasi Pembayaran Pajak:");
        antrianPajak.bayarPajak("B 1234 AB", 6);
        antrianPajak.bayarPajak("D 5678 CD", 4);
        antrianPajak.bayarPajak("F 9101 EF", 9);

        // Menampilkan daftar transaksi pajak
        System.out.println();
        System.out.println("Daftar Transaksi Pajak:");
        antrianPajak.tampilkanTransaksi();

        // Urutkan transaksi berdasarkan nama pemilik
        System.out.println();
        System.out.println("Daftar Transaksi Pajak setelah diurutkan berdasarkan nama:");
        antrianPajak.urutkanTransaksiNama();
    }

    // Helper method untuk menampilkan informasi kendaraan
    private static void displayKendaraanInfo(Kendaraan kendaraan) {
        System.out.println("Nomor TNKB: " + kendaraan.noTNKB);
        System.out.println("Pemilik: " + kendaraan.nama);
        System.out.println("Jenis: " + kendaraan.jenis);
        System.out.println("CC: " + kendaraan.cc);
        System.out.println("Tahun: " + kendaraan.tahun);
        System.out.println("Bulan Harus Bayar: " + kendaraan.bulanHarusBayar);
    }
}
