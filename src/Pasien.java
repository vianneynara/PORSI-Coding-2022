import java.time.LocalDateTime;

public class Pasien {
    private int nomor;
    private String tanggal;
    private String waktu;
    private String nama;
    
    public Pasien(String nama, int nomorAntrian) {
        this.nomor = nomorAntrian;
        this.nama = nama;
        LocalDateTime ct = LocalDateTime.now();
        tanggal = "%02d/%02d/%4d".formatted(ct.getDayOfMonth(), ct.getMonthValue(), ct.getYear());
        waktu= "%02d:%02d".formatted(ct.getHour(), ct.getMinute());
    }

    @Override
    public String toString() {
        return String.format("nomor: %02d | %-21s (%10s at %5s)",
                nomor, nama, tanggal, waktu);
    }

    public int getNomor() {
        return nomor;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getNama() {
        return nama;
    }
}