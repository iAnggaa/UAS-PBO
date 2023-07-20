public interface Crud {
    void tambahPakaian(Pakaian pakaian);
    void updatePakaian(int nomor, Pakaian pakaian);
    void deletePakaian(int nomor);
}