class TransaksiPajak {
    static int kodeGenerator = 1;
    int kode;
    long nominalBayar;
    long denda;
    int bulanBayar;
    Kendaraan kendaraan;

    public TransaksiPajak(long nominalBayar, long denda, int bulanBayar, Kendaraan kendaraan) {
        this.kode = kodeGenerator++;
        this.nominalBayar = nominalBayar;
        this.denda = denda;
        this.bulanBayar = bulanBayar;
        this.kendaraan = kendaraan;
    }

    public static class AntrianPajak {
        private static final int MAX_KENDARAAN = 100;
        private static final int MAX_TRANSAKSI = 1000;
        private Kendaraan[] kendaraanList = new Kendaraan[MAX_KENDARAAN];
        private TransaksiPajak[] transaksiList = new TransaksiPajak[MAX_TRANSAKSI];
        private int kendaraanCount = 0;
        private int transaksiCount = 0;

        void tambahKendaraan(Kendaraan kendaraan) {
            if (kendaraanCount < MAX_KENDARAAN) {
                kendaraanList[kendaraanCount++] = kendaraan;
            } else {
                System.out.println("Daftar kendaraan sudah penuh.");
            }
        }

        public Kendaraan cariKendaraan(String noTNKB) {
            for (int i = 0; i < kendaraanCount; i++) {
                if (kendaraanList[i].noTNKB.equals(noTNKB)) {
                    return kendaraanList[i];
                }
            }
            return null;
        }

        public void bayarPajak(String noTNKB, int bulanBayar) {
            Kendaraan kendaraan = cariKendaraan(noTNKB);
            if (kendaraan == null) {
                System.out.println("Kendaraan dengan TNKB " + noTNKB + " tidak ditemukan.");
                return;
            }

            long nominalBayar = hitungNominalBayar(kendaraan, bulanBayar);
            long denda = hitungDenda(kendaraan, bulanBayar);

            TransaksiPajak transaksi = new TransaksiPajak(nominalBayar, denda, bulanBayar, kendaraan);
            if (transaksiCount < MAX_TRANSAKSI) {
                transaksiList[transaksiCount++] = transaksi;
            } else {
                System.out.println("Daftar transaksi sudah penuh.");
            }

            System.out.println("Pembayaran pajak berhasil direkam.");
            System.out.println("Kode Transaksi: " + transaksi.kode);
            System.out.println("Nomor TNKB: " + kendaraan.noTNKB);
            System.out.println("Pemilik: " + kendaraan.nama);
            System.out.println("Nominal Bayar: " + nominalBayar);
            System.out.println("Denda: " + denda);
        }

        // Method untuk menghitung nominal bayar pajak
        long hitungNominalBayar(Kendaraan kendaraan, int bulanBayar) {
            int bulanHarusBayar = kendaraan.bulanHarusBayar;
            int cc = kendaraan.cc;
            long tarifPajak = 0;

            // Menentukan tarif pajak berdasarkan jenis kendaraan dan cc
            if (kendaraan.jenis.equals("motor")) {
                if (cc < 100) {
                    tarifPajak = 100000;
                } else if (cc <= 250) {
                    tarifPajak = 250000;
                } else {
                    tarifPajak = 500000;
                }
            } else if (kendaraan.jenis.equals("mobil")) {
                if (cc < 1000) {
                    tarifPajak = 750000;
                } else if (cc <= 2500) {
                    tarifPajak = 1000000;
                } else {
                    tarifPajak = 1500000;
                }
            }

            return tarifPajak;
        }

        // Method untuk menghitung denda
        long hitungDenda(Kendaraan kendaraan, int bulanBayar) {
            int bulanHarusBayar = kendaraan.bulanHarusBayar;

            // Hitung denda jika bulanBayar > bulanHarusBayar
            long denda = 0;
            if (bulanBayar > bulanHarusBayar) {
                int selisihBulan = bulanBayar - bulanHarusBayar;
                if (selisihBulan <= 3) {
                    denda = selisihBulan * 50000;
                } else {
                    denda = 3 * 50000 + (selisihBulan - 3) * 50000;
                }
            }

            return denda;
        }

        public void tampilkanTransaksi() {
            if (transaksiCount == 0) {
                System.out.println("Belum ada transaksi pajak yang direkam.");
                return;
            }

            System.out.println("Daftar Transaksi Pajak:");
            long totalPendapatan = 0;
            for (int i = 0; i < transaksiCount; i++) {
                TransaksiPajak transaksi = transaksiList[i];
                System.out.println("Kode Transaksi: " + transaksi.kode);
                System.out.println("Nomor TNKB: " + transaksi.kendaraan.noTNKB);
                System.out.println("Pemilik: " + transaksi.kendaraan.nama);
                System.out.println("Nominal Bayar: " + transaksi.nominalBayar);
                System.out.println("Denda: " + transaksi.denda);
                totalPendapatan += transaksi.nominalBayar + transaksi.denda;
            }
            System.out.println("Total Pendapatan: " + totalPendapatan);
        }

        public void urutkanTransaksiNama() {
            if (transaksiCount == 0) {
                System.out.println("Belum ada transaksi pajak yang direkam.");
                return;
            }

            // Bubble Sort berdasarkan nama
            for (int i = 0; i < transaksiCount - 1; i++) {
                for (int j = 0; j < transaksiCount - i - 1; j++) {
                    String nama1 = transaksiList[j].kendaraan.nama.split(" ")[0];
                    String nama2 = transaksiList[j + 1].kendaraan.nama.split(" ")[0];
                    if (nama1.compareTo(nama2) > 0) {
                        TransaksiPajak temp = transaksiList[j];
                        transaksiList[j] = transaksiList[j + 1];
                        transaksiList[j + 1] = temp;
                    }
                }
            }

            System.out.println("Transaksi Pajak setelah diurutkan:");
            for (int i = 0; i < transaksiCount; i++) {
                TransaksiPajak transaksi = transaksiList[i];
                System.out.println("Kode Transaksi: " + transaksi.kode);
                System.out.println("Nomor TNKB: " + transaksi.kendaraan.noTNKB);
                System.out.println("Pemilik: " + transaksi.kendaraan.nama);
                System.out.println("Nominal Bayar: " + transaksi.nominalBayar);
                System.out.println("Denda: " + transaksi.denda);
            }
        }
    }
}