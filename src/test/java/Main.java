import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    public static Turunan proses = new Turunan();


    public static void main(String[] args) {
        initPakaian();
        String cobaLagi;

        do {
            showPakaian();
            int pilihan = pilihMenu();

            switch(pilihan) {
                case 1:
                    tambahPakaian();
                    break;
                case 2:
                    updatePakaian();
                    break;
                case 3:
                    deletePakaian();
                    break;
                case 4:
                    setrikaPakaian();
                    break;
                case 5:
                    laundryPakaian();
                    break;
                default:
                    System.out.println("Inputan Salah. Silakan Pilih Angka 1-4");
                    break;
            }

            System.out.print("Coba Lagi (y/n)? : ");
            while (!scan.hasNext("[yn]")) {
                System.out.println("Input harus berupa y atau n!");
                scan.next();
            }

            cobaLagi = scan.next();
        } while (cobaLagi.equals("y"));
    }

    //	Menginit  Menu Menu Ke Dalam ArrayList
    public static void initPakaian() {
        //kirim value parameter ke method Pakaian (String namaPakaian, int harga)
        Pakaian pakaian1 = new Pakaian("Kemeja Pendek", 1500);
        Pakaian pakaian2 = new Pakaian("Kemeja Panjang", 2000);
        Pakaian pakaian3 = new Pakaian("Celana Panjang", 2500);

        Pakaian pakaian4 = new Pakaian();
        pakaian4.setNamaPakaian("Celana Pendek");
        pakaian4.setHarga(1500);

        proses.listPakaian.add(pakaian1);
        proses.listPakaian.add(pakaian2);
        proses.listPakaian.add(pakaian3);
        proses.listPakaian.add(pakaian4);
    }

    public static void showPakaian() {
        System.out.println("=============== DAFTAR PAKAIAN ===============");
        System.out.println("No" + "\tNama" + "\t\t\tHarga");
        int i = 0;
        for (Pakaian pakaian : proses.listPakaian) {
            i++;
            System.out.println((i) + "\t" + pakaian.getNamaPakaian() + "\t\t" + pakaian.getHarga());
        }
    }

    public static int pilihMenu() {
        System.out.println("\nMENU");
        System.out.println("1. Tambah Pakaian");
        System.out.println("2. Update Pakaian");
        System.out.println("3. Delete Pakaian");
        System.out.println("4. Setrika Pakaian");
        System.out.println("5. Laundry Pakaian");
        System.out.print("Masukkan Opsi : ");

        boolean isValidMenu = false;
        int pilih = 0;

        while(!isValidMenu) {
            try {
                while(!scan.hasNextInt()) {
                    System.out.println("\nInput harus berupa angka!");
                    System.out.print("Masukkan Opsi : ");
                    scan.next();
                }

                pilih = scan.nextInt();

                if(pilih > 5 || pilih < 1) {
                    throw new Exception("Nomor Menu Tidak Ada");
                }

                isValidMenu = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return pilih;
    }

    public static void tambahPakaian() {
        System.out.println("Anda Memilih Pilihan Tambah Pakaian");

        System.out.print("Masukkan Nama Pakaian : ");
        scan.nextLine();
        String namaPakaian = scan.nextLine();

        // Cek apakah namaPakaian sudah ada dalam listPakaian
        boolean namaPakaianExists = false;
        for (Pakaian pakaian : proses.listPakaian) {
            if (pakaian.getNamaPakaian().equalsIgnoreCase(namaPakaian)) {
                namaPakaianExists = true;
                break;
            }
        }

        if (namaPakaianExists) {
            System.out.println("\nNama Pakaian Sudah Ada");
        } else {
            System.out.print("Masukkan Harga Pakaian : ");
            while (!scan.hasNextInt()) {
                System.out.println("\nInput Harga harus berupa angka!");
                System.out.print("Masukkan Harga Pakaian : ");
                scan.next();
            }

            int harga = scan.nextInt();

            Pakaian tambah = new Pakaian(harga, namaPakaian);
            proses.tambahPakaian(tambah);
        }
    }

    public static void updatePakaian() {
        System.out.println("Anda Memilih Pilihan Update Pakaian");

        boolean validNomor = false;

        while(!validNomor) {
            System.out.println("Masukkan Nomor Pakaian Yang Ingin di Update : ");

            try {
                while(!scan.hasNextInt()) {
                    System.out.println("\nInput Nomor Pakaian harus berupa angka!");
                    System.out.println("Masukkan Nomor Pakaian Yang Ingin di Update : ");
                    scan.next();
                }

                int nomor = scan.nextInt();

                if (nomor < 1 || nomor > proses.listPakaian.size()) {
                    throw new Exception("Nomor Pakaian Tidak Ada");
                }

                validNomor = true;

                System.out.print("Masukkan Nama Pakaian Baru : ");
                scan.nextLine();
                String namaPakaian = scan.nextLine();

                if (namaPakaian.isEmpty()) {
                    namaPakaian = proses.listPakaian.get(nomor-1).getNamaPakaian();
                }

                System.out.print("Masukkan Harga Pakaian Baru : ");
                String hargaPakaian = scan.nextLine();

                if (hargaPakaian.isEmpty()) {
                    hargaPakaian = Integer.toString(proses.listPakaian.get(nomor-1).getHarga());
                } else {
                    boolean isValid = false;

                    while(!isValid) {
                        try {
                            Integer.parseInt(hargaPakaian);
                            isValid = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Input Harga harus berupa angka!");
                            System.out.print("Masukkan Harga Pakaian Baru : ");
                            hargaPakaian = scan.nextLine();
                        }
                    }
                }

                int harga = Integer.parseInt(hargaPakaian);

                Pakaian edit = new Pakaian(namaPakaian, harga);
                proses.updatePakaian(nomor, edit);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deletePakaian() {
        System.out.println("Anda Memilih Pilihan Delete Pakaian");

        boolean validNomor = false;

        while(!validNomor) {
            System.out.println("Masukkan Nomor Pakaian Yang Ingin di Delete : ");

            try {
                while(!scan.hasNextInt()) {
                    System.out.println("\nInput harus berupa angka!");
                    System.out.println("Masukkan Nomor Pakaian Yang Ingin di Delete : ");
                    scan.next();
                }

                int nomor = scan.nextInt();

                if (nomor < 1 || nomor > proses.listPakaian.size()) {
                    throw new Exception("Nomor Pakaian Tidak Ada");
                }

                validNomor = true;

                System.out.println("Apakah Anda Ingin Menghapus Pakaian " + proses.listPakaian.get(nomor-1).getNamaPakaian() + " Dari Menu? (y/n)");

                while (!scan.hasNext("[yn]")) {
                    System.out.println("Input harus berupa y atau n!");
                    scan.next();
                }

                String validasi = scan.next();

                if(validasi.equals("y") ) {
                    proses.deletePakaian(nomor);
                }

            } catch (Exception e) {
                System.out.println("Nomor Pakaian Tidak Ada");
            }
        }
    }
    public static void setrikaPakaian() {
        System.out.println("Anda Memilih Pilihan Setrika");

        String validasi;
        int totalHarga = 0;
        int totalJumlah = 0;

        do {
            boolean isValidMenu = false;

            while(!isValidMenu) {
                try {
                    isValidMenu = true;

                    System.out.print("Masukkan Jumlah Setrikaan : ");

                    while(!scan.hasNextInt()) {
                        System.out.println("\nInput harus berupa angka!");
                        System.out.print("Masukkan Jumlah Setrikaan : ");
                        scan.next();
                    }

                    int jumlah = scan.nextInt();

                    int harga = 600;
                    int total = harga * jumlah;

                    totalHarga += total;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("Ada setrikaan lain? (y/n) : ");
            while (!scan.hasNext("[yn]")) {
                System.out.println("Input harus berupa y atau n!");
                scan.next();
            }

            validasi = scan.next();

        } while (validasi.equals("y"));

        hitung(totalHarga,totalJumlah);
    }
    public static void laundryPakaian() {
        System.out.println("Anda Memilih Pilihan Laundry");

        String validasi;
        int totalHarga = 0;
        int totalJumlah = 0;
        do {
            boolean isValidMenu = false;
            showPakaian();

            while(!isValidMenu) {
                System.out.print("Masukkan Nomor Pakaian : ");

                try {
                    while(!scan.hasNextInt()) {
                        System.out.println("\nInput harus berupa angka!");
                        System.out.print("Masukkan Nomor Pakaian : ");
                        scan.next();
                    }

                    int nomor = scan.nextInt();

                    if (nomor < 1 || nomor > proses.listPakaian.size()) {
                        throw new Exception("Nomor Pakaian Tidak Ada");
                    }

                    isValidMenu = true;

                    String namaPakaian = proses.listPakaian.get(nomor - 1).getNamaPakaian();
                    System.out.print("Masukkan Jumlah " +namaPakaian+ " : ");

                    while(!scan.hasNextInt()) {
                        System.out.println("\nInput harus berupa angka!");
                        System.out.print("Masukkan Jumlah Pakaian : ");
                        scan.next();
                    }

                    int jumlah = scan.nextInt();

                    int harga = proses.listPakaian.get(nomor - 1).getHarga();
                    int total = harga * jumlah;

                    totalJumlah += jumlah;
                    totalHarga += total;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("Ada pakaian lain? (y/n) : ");
            while (!scan.hasNext("[yn]")) {
                System.out.println("Input harus berupa y atau n!");
                scan.next();
            }

            validasi = scan.next();

        } while (validasi.equals("y"));

        hitung(totalHarga,totalJumlah);
    }
    public static void hitung(int totalHarga, int totalJumlah) {
        int j = 10;
        int diskon = 2000;
        int hargaDiskon;
        int jumlahDiskon;
        System.out.println("============================ TOTAL PEMBAYARAN =============================");
        System.out.println("Total Harga : Rp. " + totalHarga);
        while (totalJumlah >= j) {
            jumlahDiskon = diskon;
            hargaDiskon = totalHarga - jumlahDiskon;
            j += 10;
            diskon += 2000;
            if (totalJumlah < j) {
                System.out.println("Selamat Anda Mendapatkan Diskon Sebesar : Rp. " + jumlahDiskon);
                System.out.println("\nTotal Pembayaran Anda Menjadi : Rp. " + hargaDiskon);
            }
        }
        System.out.println("======================== TERIMA KASIH =========================");
    }
}