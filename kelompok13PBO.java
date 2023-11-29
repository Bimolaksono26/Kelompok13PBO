import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Buku {
    private String judul;
    private String pengarang;
    private String nomorISBN;
    private boolean ketersediaan;

    public Buku(String judul, String pengarang, String nomorISBN, boolean ketersediaan) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.nomorISBN = nomorISBN;
        this.ketersediaan = ketersediaan;
    }

    public String getJudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getNomorISBN() {
        return nomorISBN;
    }

    public boolean isKetersediaan() {
        return ketersediaan;
    }

    public void setKetersediaan(boolean ketersediaan) {
        this.ketersediaan = ketersediaan;
    }
}

class TransaksiPeminjaman {
    private Date tanggalPeminjaman;
    private Date waktuPeminjaman;
    private int durasiPeminjaman;
    private AnggotaPerpustakaan anggotaPerpustakaan;
    private Buku buku;

    public TransaksiPeminjaman(AnggotaPerpustakaan anggotaPerpustakaan, Buku buku, int durasiPeminjaman) {
        this.anggotaPerpustakaan = anggotaPerpustakaan;
        this.buku = buku;
        this.durasiPeminjaman = durasiPeminjaman;
    }

    public void tandaiBukuDipinjam() {
        buku.setKetersediaan(false);
        tanggalPeminjaman = new Date();
        waktuPeminjaman = new Date();
    }

    public void tandaiBukuDikembalikan() {
        buku.setKetersediaan(true);
    }

    public Date hitungTanggalPengembalian() {
        long waktuPeminjamanMillis = waktuPeminjaman.getTime();
        long durasiMillis = durasiPeminjaman * 24 * 60 * 60 * 1000;
        return new Date(waktuPeminjamanMillis + durasiMillis);
    }
}

class Notifikasi {
    private String jenisNotifikasi;
    private String isiNotifikasi;
    private Date tanggal;

    public Notifikasi(String jenisNotifikasi, String isiNotifikasi, Date tanggal) {
        this.jenisNotifikasi = jenisNotifikasi;
        this.isiNotifikasi = isiNotifikasi;
        this.tanggal = tanggal;
    }

    public String getJenisNotifikasi() {
        return jenisNotifikasi;
    }

    public String getIsiNotifikasi() {
        return isiNotifikasi;
    }

    public Date getTanggal() {
        return tanggal;
    }
}

class AnggotaPerpustakaan {
    private String nama;
    private int nomorAnggota;
    private String alamat;
    private List<TransaksiPeminjaman> sejarahPeminjaman;
    private List<Notifikasi> daftarNotifikasi;

    public AnggotaPerpustakaan(String nama, int nomorAnggota, String alamat) {
        this.nama = nama;
        this.nomorAnggota = nomorAnggota;
        this.alamat = alamat;
        this.sejarahPeminjaman = new ArrayList<>();
        this.daftarNotifikasi = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public int getNomorAnggota() {
        return nomorAnggota;
    }

    public String getAlamat() {
        return alamat;
    }

    public List<TransaksiPeminjaman> getSejarahPeminjaman() {
        return sejarahPeminjaman;
    }

    public List<Notifikasi> getDaftarNotifikasi() {
        return daftarNotifikasi;
    }

    public void catatPeminjaman(TransaksiPeminjaman transaksi) {
        sejarahPeminjaman.add(transaksi);
    }

    public void kirimNotifikasi(Notifikasi notifikasi) {
        daftarNotifikasi.add(notifikasi);
    }

    public void tampilkanNotifikasi() {
        System.out.println("Notifikasi untuk " + nama + ":");
        for (Notifikasi notifikasi : daftarNotifikasi) {
            System.out.println("- " + notifikasi.getJenisNotifikasi() + ": " + notifikasi.getIsiNotifikasi());
            System.out.println("  Tanggal: " + notifikasi.getTanggal());
            System.out.println("-------------");
        }
        daftarNotifikasi.clear();
    }

    public void tampilkanInformasiAnggota() {
        System.out.println("Informasi Anggota:");
        System.out.println("Nama: " + nama);
        System.out.println("Nomor Anggota: " + nomorAnggota);
        System.out.println("Alamat: " + alamat);
        System.out.println("-------------");
    }
}

public class PrakPBO12 {
    private static List<Buku> koleksiBuku = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihanMenu;
        do {
            tampilkanMenuUtama();
            System.out.print("Pilih opsi (0 untuk keluar): ");
            pilihanMenu = scanner.nextInt();
            scanner.nextLine();

            switch (pilihanMenu) {
                case 1:
                    manajemenKoleksiBuku();
                    break;
                case 2:
                    layananPeminjamanBuku();
                    break;
                case 3:
                    pelacakanAktivitasAnggota();
                    break;
                case 0:
                    System.out.println("Terima kasih. Program keluar.");
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silakan pilih lagi.");
            }

        } while (pilihanMenu != 0);

        scanner.close();
    }

    private static void tampilkanMenuUtama() {
        System.out.println("==== Menu Utama ====");
        System.out.println("1. Manajemen Koleksi Buku");
        System.out.println("2. Layanan Peminjaman Buku");
        System.out.println("3. Pelacakan Aktivitas Anggota");
        System.out.println("0. Keluar");
        System.out.println("====================");
    }

    private static void manajemenKoleksiBuku() {
        int pilihanManajemenBuku;
        do {
            tampilkanMenuManajemenBuku();
            System.out.print("Pilih opsi manajemen buku (0 untuk kembali ke menu utama): ");
            pilihanManajemenBuku = scanner.nextInt();
            scanner.nextLine();

            switch (pilihanManajemenBuku) {
                case 1:
                    tambahBuku();
                    break;
                case 2:
                    tampilkanInformasiKoleksiBuku();
                    break;
                case 0:
                    System.out.println("Kembali ke menu utama.");
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silakan pilih lagi.");
            }

        } while (pilihanManajemenBuku != 0);
    }

    private static void tampilkanMenuManajemenBuku() {
        System.out.println("==== Manajemen Koleksi Buku ====");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Tampilkan Informasi Koleksi Buku");
        System.out.println("0. Kembali ke Menu Utama");
        System.out.println("===============================");
    }

    private static void tambahBuku() {
        System.out.println("Masukkan informasi buku baru:");
        System.out.print("Judul: ");
        String judul = scanner.nextLine();
        System.out.print("Pengarang: ");
        String pengarang = scanner.nextLine();
        System.out.print("Nomor ISBN: ");
        String nomorISBN = scanner.nextLine();

        Buku bukuBaru = new Buku(judul, pengarang, nomorISBN, true);
        koleksiBuku.add(bukuBaru);

        System.out.println("Buku berhasil ditambahkan ke koleksi.");
    }

    private static void tampilkanInformasiKoleksiBuku() {
        System.out.println("Informasi Koleksi Buku:");
        for (Buku buku : koleksiBuku) {
            System.out.println("Judul: " + buku.getJudul());
            System.out.println("Pengarang: " + buku.getPengarang());
            System.out.println("Nomor ISBN: " + buku.getNomorISBN());
            System.out.println("Ketersediaan: " + (buku.isKetersediaan() ? "Tersedia" : "Dipinjam"));
            System.out.println("-------------");
        }
    }

    private static void layananPeminjamanBuku() {
        // Implementasi layanan peminjaman buku
    }

    private static void pelacakanAktivitasAnggota() {
        // Implementasi pelacakan aktivitas anggota
    }
}
