import java.util.ArrayList;

abstract class Data {
    public ArrayList<Pakaian> listPakaian = new ArrayList<Pakaian>();

}

class Turunan extends Data implements Crud {
    @Override
    public void tambahPakaian(Pakaian pakaian) {
        this.listPakaian.add(pakaian);
    }

    @Override
    public void updatePakaian(int nomor, Pakaian pakaian) {
        this.listPakaian.set((nomor - 1), pakaian);
    }

    @Override
    public void deletePakaian(int nomor) {
        listPakaian.remove(nomor - 1);
        System.out.println("Pakaian Berhasil Dihapus");
    }
}
