import java.util.ArrayList;
import java.util.Scanner;

public class RumahSakit {
    private static final int FULL_WIDTH = 70;
    private static final Scanner sc = new Scanner(System.in);

    private static void cetakMenu() {
        System.out.println("""
                [ MENU ANTRIAN PASIEN ]
                    1. Daftar antrian
                    2. Tambah antrian
                    3. Panggil antrian
                    4. Batalkan antrian
                    5. Cari antrian
                    6. Selesai""");
    }

    private static int askMenu() {
        int option;
        do {
            System.out.print("Pilih menu (1-4): ");
            option = sc.nextInt();
            sc.nextLine();  // makan trailing new line
        } while ((option < 1) || (option > 6));

        divider();
        return option;
    }

    private static void cetakAntrian(Antrian antrian) {
        if (!antrian.isEmpty()) {
            System.out.printf("%-70s%n", "DAFTAR ANTRIAN");
            System.out.println("%15s | %15s | %10s | %-21s"
                    .formatted("No. Antrian", "Tanggal", "Waktu", "Nama Pasien"));

            System.out.println("-".repeat(FULL_WIDTH));
            int counter = antrian.size();
            int pos = antrian.getFront();
            var p = antrian.getAntrian();
            while (counter != 0) {
                System.out.println("%15d | %15s | %10s | %-21s"
                        .formatted(p[pos].getNomor(), p[pos].getTanggal(), p[pos].getWaktu(), p[pos].getNama()));
                if (pos == antrian.capactiy() - 1) pos = 0;
                pos++;
                counter--;
            }
        } else {
            System.out.println("[✖] Antrian kosong!");
        }
    }

    private static Pasien ngantri(int nomor) {
        System.out.print("Masukkan nama anda: ");
        String nama = sc.nextLine().trim();
        return new Pasien(nama, nomor);
    }

    private static ArrayList<Pasien> search(String key, Antrian antrian) {
        /* Sequential Searching: No distance */
        var query = new ArrayList<Pasien>(antrian.size());
        for (Pasien p : antrian.getAntrian())
            if (p != null && p.getNama().toLowerCase().contains(key)) query.add(p);

        return query;
    }

    private static void divider() {
        System.out.println();
        System.out.println("=".repeat(FULL_WIDTH));
        System.out.println();
    }
    
    public static void main(String[] args) {
        /* Class variables */
        Antrian antrian = new Antrian(5);
        int counter = 0;

        /* Program loop */
        boolean isRunning = true;
        while (isRunning) {
            cetakMenu();
//            antrian.info();

            switch (askMenu()) {
                case 1 -> /* Daftar Antrian */ {
                    cetakAntrian(antrian);
                    divider();
                }
                case 2 -> /* Tambah Antrian */ {
                    // JIKA tidak penuh dan berhasil memasukkan: langsung dicek apakah penuh terlebih dahulu
                    if (antrian.size() != antrian.capactiy() && antrian.enqueue(ngantri(++counter))) {
                        System.out.println("[✔] Antrian terdaftar!");
                    } else {
                        System.out.println("[✖] Antrian penuh!");
                    }
                    divider();
                }
                case 3 -> /* Panggil Antrian */ {
                    if (!antrian.isEmpty()) {
                        Pasien pasien = antrian.dequeue();

                        System.out.println("🔊 Panggilan kepada nomor antrian " +
                                pasien.getNomor() + " atas nama " +
                                pasien.getNama() + ".");
                    } else {
                        System.out.println("[✖] Antrian Kosong!");
                    }
                    divider();
                }
                case 4 -> /* Batalkan Antrian */ {
                    if (!antrian.isEmpty()) {
                        Pasien pasien = antrian.cancel();
                        System.out.println("[✔] Nomor antrian " +
                                pasien.getNomor() + " atas nama " +
                                pasien.getNama() + " telah dibatalkan.");
                    } else {
                        System.out.println("[✖] Antrian Kosong!");
                    }
                    divider();
                }
                case 5 -> /* Sequential searching */ {
                    if (antrian.size() == antrian.capactiy()) {
                        System.out.print("Masukkan keyword nama pasien: ");
                        String keyWord = sc.nextLine();
                        ArrayList<Pasien> query = search(keyWord.toLowerCase(), antrian);

                        if (query.size() != 0) {
                            System.out.println("Results: ");
                            int queryCounter = 0;
                            for (Pasien p : query)
                                System.out.println("\t" + ++queryCounter + ". " + p);
                        } else {
                            System.out.println("[✖] \"" + keyWord + "\" tidak ditemukan!");
                        }
                    } else {
                        System.out.println("[✖] Antrian Kosong!");
                    }
                    divider();
                }
                default -> /* Exit */ {
                    System.out.println("Menutup program...");
                    isRunning = false;
                }
            }
        }
    }
}