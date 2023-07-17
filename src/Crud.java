import java.util.ArrayList;

abstract class Crud {
	public ArrayList<Pakaian> listPakaian = new ArrayList<Pakaian>();
	
	public abstract void tambahPakaian(Pakaian pakaian);
	public abstract void updatePakaian(int nomor, Pakaian pakaian);
	public abstract void deletePakaian(int nomor);
	
	public void diskon(int totalHarga) {
		if(totalHarga >= 100000) {
			int diskon = totalHarga * 20/100;
			int hargaDiskon = totalHarga - diskon;
			
			System.out.println("============================ TOTAL PEMBAYARAN =============================");
			System.out.println("Total Harga : " + totalHarga);
			System.out.println("Selamat Anda Mendapatkan Diskon Sebesar 20%");
			
			System.out.println("\nTotal Pembayaran Anda Menjadi : Rp. " + hargaDiskon);
			System.out.println("======================== TERIMA KASIH =========================");
		} else if(totalHarga >= 50000) {
			int diskon = totalHarga * 10/100;
			int hargaDiskon = totalHarga - diskon;
			
			System.out.println("============================ TOTAL PEMBAYARAN =============================");
			System.out.println("Total Harga : " + totalHarga);
			System.out.println("Selamat Anda Mendapatkan Diskon Sebesar 10%");
			System.out.println("\nTotal Pembayaran Anda Menjadi : Rp. " + hargaDiskon);
			System.out.println("======================== TERIMA KASIH =========================");
		} else {
			System.out.println("============================ TOTAL PEMBAYARAN =============================");
			System.out.println("\nTotal Pembayaran Anda Menjadi : Rp. " + totalHarga);
			System.out.println("======================== TERIMA KASIH =========================");
		}
	}
}

class Turunan extends Crud implements Test {
	@Override
	public void tambahPakaian(Pakaian pakaian) {
		this.listPakaian.add(pakaian);
	}

	@Override
	public void updatePakaian(int nomor, Pakaian pakaian) {
		this.listPakaian.set((nomor-1), pakaian);
	}

	@Override
	public void deletePakaian(int nomor) {
		listPakaian.remove(nomor-1);
		System.out.println("Pakaian Berhasil Dihapus");
	}

	@Override
	public void test() {
		System.out.println("Ini Merupakan Teks Dari Override Method Test Pada Interface");
	}
}

interface Test {
	public void test();
}