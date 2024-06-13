import java util Scanner;

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    AntrianPajak antrianPajak = new AntrianPajak();

    antrianPajak.tambahKendaraan(new Kendaraan("AB 1234 X", "Budi Setiawan", "motor", 200, 5));
    antrianPajak.tambahKendaraan(new Kendaraan("B 4321 AB", "Ani Cahyani", "mobil", 1500, 4));

    while (true) {
        System.out.println("\nMenu:");
        System.out.println("1. Daftar Kendaraan");
        System.out.println("2. Bayar Pajak");
        System.out.println("3. Tampilkan Seluruh Transaksi");
        System.out.println("4. Urutkan Transaksi berdasarkan Nama Pemilik");
        System.out.println("5. Keluar");
        System.out.print("Pilih: ");
        int pilihan = sc.nextInt();
        sc.nextLine();

        switch (pilihan) {
            case 1:
        }
    }
}