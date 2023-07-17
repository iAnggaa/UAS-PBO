class Pakaian {
	private String namaPakaian;
	private int harga;

	public Pakaian(String namaPakaian, int harga) {
		this.namaPakaian = namaPakaian;
		this.harga = harga;
	}
	//overloading
	public Pakaian(int harga, String namaPakaian) {
		this.namaPakaian = namaPakaian;
		this.harga = harga;
	}

	public Pakaian() {
		
	}
	
	public void setNamaPakaian(String namaPakaian) {
		this.namaPakaian = namaPakaian;
	}
	
	public void setHarga(int harga) {
		this.harga = harga;
	}
	//getter
	public String getNamaPakaian() {
		return namaPakaian;
	}

	public int getHarga() {
		return harga;
	}
}