class TransaksiPajak {
    static int kodeGenerator = 1;
    int kode;
    long nominalBayar;
    long denda;
    int bulanBayar;
    Kendaraan kendaraan;

    public TransaksiPajak(int kode,long nominalBayar,long denda, int bulanBayar, Kendaraan kendaraan) {
        this.kode = kode;
        this.nominalBayar = nominalBayar;
        this.denda = denda;
        this.bulanBayar = bulanBayar;
        this.kendaraan = kendaraan;
    }

    public class AntrianPajak {
        ArrayList<Kendaraan> kendaraanList = new ArrayList<>();
        ArrayList<TransaksiPajak> transaksiList = new ArrayList<>();

        void tambahKendaraan(Kendaraan kendaraan) {
            kendaraanList.add(kendaraan);
        }
    
        public Kendaraan cariKendaraan(String noTNKB) {
            for (Kendaraan kendaraan : kendaraanList) {
                if (kendaraan.noTNKB.equals(noTNKB)) {
                    return kendaraan;
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
            transaksiList.add(transaksi);
    
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
            if (transaksiList.isEmpty()) {
                System.out.println("Belum ada transaksi pajak yang direkam.");
                return;
            }
    
            System.out.println("Daftar Transaksi Pajak:");
            long totalPendapatan = 0;
            for (TransaksiPajak transaksi : transaksiList) {
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
            if (transaksiList.isEmpty()) {
                System.out.println("Belum ada transaksi pajak yang direkam.");
                return;
            }

            Comparator<TransaksiPajak> comparator = new Comparator<TransaksiPajak>() {
                public int compare(TransaksiPajak t1, TransaksiPajak t2) {
                    String nama1 = t1.kendaraan.nama.split(" ")[0];
                    String nama2 = t2.kendaraan.nama.split(" ")[0];
                    return nama1.compareTo(nama2);
                }
            };
    
            Collections.sort(transaksiList, comparator);
            System.out.println("Transaksi Pajak setelah diurutkan:");
            for (TransaksiPajak transaksi : transaksiList) {
                System.out.println("Kode Transaksi: " + transaksi.kode);
                System.out.println("Nomor TNKB: " + transaksi.kendaraan.noTNKB);
                System.out.println("Pemilik: " + transaksi.kendaraan.nama);
                System.out.println("Nominal Bayar: " + transaksi.nominalBayar);
                System.out.println("Denda: " + transaksi.denda);
            }
        }
    }
}

hehehehhe