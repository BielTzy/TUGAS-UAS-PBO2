/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package Perkebunan;

import javax.swing.*;
//import java.sql.ResultSet;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ASUS
 */

public class Perkebunan {

    private String dataBase = "perkebunan";
    private String userName = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost/" + dataBase;
    public Connection koneksiDB;
    public boolean validasi = false;

    // Variabel dari class Perkebunan
    public String var_IdAdmin, var_nmAdmin, var_userAdmin, var_passAdmin, var_telpAdmin,
            var_emailAdmin, var_IdUsaha, var_nmUsaha, var_izinUsaha, var_alamatUsaha, var_telpUsaha,
            var_emailUsaha, var_userUsaha, var_passUsaha, var_IdKunjung, var_nmKunjung, var_IdCard,
            var_userPengunjung, var_passPengunjung, var_telpPengunjung, var_emailPengunjung, var_IdKomoditi,
            var_nmKomoditi, var_skripsKomoditi, var_lokasiKomoditi, var_produsen, var_persediaan, var_harga,
            var_IdInfo, var_berita, var_kegiatan, var_tanggalInfo, var_laporan = null;

    public Perkebunan() {
        try {
            Driver driverkoneksi = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driverkoneksi);
            koneksiDB = DriverManager.getConnection(url, userName, password);
            System.out.println("Berhasil Koneksi");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Error : \n" + e.getMessage());
        }
    }

    public void loadData(JTable tabel, String sql) {
        try {
            Statement perintah = koneksiDB.createStatement();
            ResultSet ds = perintah.executeQuery(sql);
            ResultSetMetaData data = ds.getMetaData();
            int kolom = data.getColumnCount();
            DefaultTableModel model = new DefaultTableModel();

            for (int i = 1; i <= kolom; i++) {
                model.addColumn(data.getColumnName(i));
            }

            model.getDataVector().clear();
            model.fireTableDataChanged();

            while (ds.next()) {
                Object[] baris = new Object[kolom];
                for (int j = 1; j <= kolom; j++) {
                    baris[j - 1] = ds.getObject(j);
                }
                model.addRow(baris);
            }
            tabel.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // ====================== TABEL ADMIN ======================
    public void simpanAdminPST(String id, String nama, String user, String pass, String telp, String email) {
        try {
            String sql = "INSERT INTO admin (id_admin, nama_admin, username, userpass, telp, email) VALUES (?, ?, ?, ?, ?, ?)";
            String checkPrimary = "SELECT * FROM admin WHERE id_admin = '" + id + "'";
            Statement checkData = koneksiDB.createStatement();
            ResultSet data = checkData.executeQuery(checkPrimary);

            if (data.next()) {
                String isi = "\nNama : " + data.getString("nama_admin") 
                        + "\nUsername : " + data.getString("username") 
                        + "\nPassword : " + data.getString("userpass")
                        + "\nTelp : " + data.getString("telp") 
                        + "\nEmail : " + data.getString("email");
                JOptionPane.showMessageDialog(null, "ID Admin sudah terdaftar!" + isi);

                this.validasi = true;
                this.var_nmAdmin = data.getString("nama_admin");
                this.var_userAdmin = data.getString("username");
                this.var_passAdmin = data.getString("userpass");
                this.var_telpAdmin = data.getString("telp");
                this.var_emailAdmin = data.getString("email");
            } else {
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, id);
                perintah.setString(2, nama);
                perintah.setString(3, user);
                perintah.setString(4, pass);
                perintah.setString(5, telp);
                perintah.setString(6, email);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Admin berhasil disimpan!");

                this.validasi = false;
                this.var_IdAdmin = null;
                this.var_nmAdmin = null;
                this.var_userAdmin = null;
                this.var_passAdmin = null;
                this.var_telpAdmin = null;
                this.var_emailAdmin = null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ubahAdminPST(String id, String nama, String user, String pass, String telp, String email) {
        try {
            String sqlUbah = "UPDATE admin SET nama_admin = ?, username = ?, userpass = ?, telp = ?, email = ? WHERE id_admin = ?";
            PreparedStatement ubah = koneksiDB.prepareStatement(sqlUbah);
            ubah.setString(1, nama);
            ubah.setString(2, user);
            ubah.setString(3, pass);
            ubah.setString(4, telp);
            ubah.setString(5, email);
            ubah.setString(6, id);
            ubah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Admin berhasil diubah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void hapusAdminPST(String id) {
        try {
            String sqlHapus = "DELETE FROM admin WHERE id_admin=?";
            PreparedStatement hapus = koneksiDB.prepareStatement(sqlHapus);
            hapus.setString(1, id);
            hapus.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Admin berhasil dihapus!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // ====================== TABEL USAHA ======================
    public void simpanUsahaPST(String id, String nama, String izin, String alamat, String telp, String email, String user, String pass) {
        try {
            String sql = "INSERT INTO perusahaan_perkebunan (id_usaha, nama_usaha, izin_usaha, alamat_usaha, telp, email, username, userpass) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            String checkPrimary = "SELECT * FROM perusahaan_perkebunan WHERE id_usaha = '" + id + "'";
            Statement checkData = koneksiDB.createStatement();
            ResultSet data = checkData.executeQuery(checkPrimary);

            if (data.next()) {
                String isi = "\nNama Usaha : " + data.getString("nama_usaha") 
                        + "\nAlamat : " + data.getString("alamat_usaha")
                        + "\nIzin : " + data.getString("izin_usaha") 
                        + "\nTelp : " + data.getString("telp") 
                        + "\nEmail : " + data.getString("email") 
                        + "\nUsername : " + data.getString("username") 
                        + "\nPassword : " + data.getString("userpass");
                JOptionPane.showMessageDialog(null, "ID Usaha sudah terdaftar!" + isi);

                this.validasi = true;
                this.var_nmUsaha = data.getString("nama_usaha");
                this.var_izinUsaha = data.getString("izin_usaha");
                this.var_alamatUsaha = data.getString("alamat_usaha");
                this.var_telpUsaha = data.getString("telp");
                this.var_emailUsaha = data.getString("email");
                this.var_userUsaha = data.getString("username");
                this.var_passUsaha = data.getString("userpass");
            } else {
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, id);
                perintah.setString(2, nama);
                perintah.setString(3, izin);
                perintah.setString(4, alamat);
                perintah.setString(5, telp);
                perintah.setString(6, email);
                perintah.setString(7, user);
                perintah.setString(8, pass);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Usaha berhasil disimpan!");

                this.validasi = false;
                this.var_IdUsaha = null;
                this.var_nmUsaha = null;
                this.var_izinUsaha = null;
                this.var_alamatUsaha = null;
                this.var_telpUsaha = null;
                this.var_emailUsaha = null;
                this.var_userUsaha = null;
                this.var_passUsaha = null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ubahUsahaPST(String id, String nama, String izin, String alamat, String telp, String email, String user, String pass) {
        try {
            String sqlUbah = "UPDATE perusahaan_perkebunan SET nama_usaha=?, izin_usaha=?, alamat_usaha=?, telp=?, email=?, username=?, userpass=? WHERE id_usaha=?";
            PreparedStatement ubah = koneksiDB.prepareStatement(sqlUbah);
            ubah.setString(1, nama);
            ubah.setString(2, izin);
            ubah.setString(3, alamat);
            ubah.setString(4, telp);
            ubah.setString(5, email);
            ubah.setString(6, user);
            ubah.setString(7, pass);
            ubah.setString(8, id);
            ubah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Usaha berhasil diubah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void hapusUsahaPST(String id) {
        try {
            String sqlHapus = "DELETE FROM perusahaan_perkebunan WHERE id_usaha=?";
            PreparedStatement hapus = koneksiDB.prepareStatement(sqlHapus);
            hapus.setString(1, id);
            hapus.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Usaha berhasil dihapus!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // ====================== TABEL PENGUNJUNG ======================
    public void simpanPengunjungPST(String id, String nama, String idCard, String user, String pass, String telp, String email) {
        try {
            String sql = "INSERT INTO pengunjung (id_kunjung, nama_kunjung, id_card, username, userpass, telp, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String checkPrimary = "SELECT * FROM pengunjung WHERE id_kunjung = '" + id + "'";
            Statement checkData = koneksiDB.createStatement();
            ResultSet data = checkData.executeQuery(checkPrimary);

            if (data.next()) {
                String isi = "\nNama : " + data.getString("nama_kunjung") 
                        + "\nID Card : " + data.getString("id_card")
                        + "\nUsername : " + data.getString("username") 
                        + "\nPassword : " + data.getString("userpass") 
                        + "\nTelp : " + data.getString("telp") 
                        + "\nEmail : " + data.getString("email");
                JOptionPane.showMessageDialog(null, "ID Pengunjung sudah terdaftar!" + isi);

                this.validasi = true;
                this.var_nmKunjung = data.getString("nama_kunjung");
                this.var_IdCard = data.getString("id_card");
                this.var_userPengunjung = data.getString("username");
                this.var_passPengunjung = data.getString("userpass");
                this.var_telpPengunjung = data.getString("telp");
                this.var_emailPengunjung = data.getString("email");
            } else {
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, id);
                perintah.setString(2, nama);
                perintah.setString(3, idCard);
                perintah.setString(4, user);
                perintah.setString(5, pass);
                perintah.setString(6, telp);
                perintah.setString(7, email);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Pengunjung berhasil disimpan!");

                this.validasi = false;
                this.var_IdKunjung = null;
                this.var_nmKunjung = null;
                this.var_IdCard = null;
                this.var_userPengunjung = null;
                this.var_passPengunjung = null;
                this.var_telpPengunjung = null;
                this.var_emailPengunjung = null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ubahPengunjungPST(String id, String nama, String idCard, String user, String pass, String telp, String email) {
        try {
            String sqlUbah = "UPDATE pengunjung SET nama_kunjung=?, id_card=?, username=?, userpass=?, telp=?, email=? WHERE id_kunjung=?";
            PreparedStatement ubah = koneksiDB.prepareStatement(sqlUbah);
            ubah.setString(1, nama);
            ubah.setString(2, idCard);
            ubah.setString(3, user);
            ubah.setString(4, pass);
            ubah.setString(5, telp);
            ubah.setString(6, email);
            ubah.setString(7, id);
            ubah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pengunjung berhasil diubah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void hapusPengunjungPST(String id) {
        try {
            String sqlHapus = "DELETE FROM pengunjung WHERE id_kunjung=?";
            PreparedStatement hapus = koneksiDB.prepareStatement(sqlHapus);
            hapus.setString(1, id);
            hapus.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pengunjung berhasil dihapus!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // ====================== TABEL KOMODITI ======================
    public void simpanKomoditiPST(String id, String nama, String skrips, String lokasi, String produsen, String persediaan, String harga) {
        try {
            String sql = "INSERT INTO komoditi (id_komoditi, nama_komoditi, skrips_komoditi, lokasi_komoditi, produsen, persediaan, harga) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String checkPrimary = "SELECT * FROM komoditi WHERE id_komoditi = '" + id + "'";
            Statement checkData = koneksiDB.createStatement();
            ResultSet data = checkData.executeQuery(checkPrimary);

            if (data.next()) {
                String isi = "\nNama Komoditi : " + data.getString("nama_komoditi") 
                        + "\nDeskripsi : " + data.getString("skrips_komoditi") 
                        + "\nLokasi : " + data.getString("lokasi_komoditi") 
                        + "\nProdusen : " + data.getString("produsen") 
                        + "\nPersediaan : " + data.getString("persediaan") 
                        + "\nHarga : " + data.getString("harga");
                JOptionPane.showMessageDialog(null, "ID Komoditi sudah terdaftar!" + isi);

                this.validasi = true;
                this.var_nmKomoditi = data.getString("nama_komoditi");
                this.var_skripsKomoditi = data.getString("skrips_komoditi");
                this.var_lokasiKomoditi = data.getString("lokasi_komoditi");
                this.var_produsen = data.getString("produsen");
                this.var_persediaan = data.getString("persediaan");
                this.var_harga = data.getString("harga");
            } else {
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, id);
                perintah.setString(2, nama);
                perintah.setString(3, skrips);
                perintah.setString(4, lokasi);
                perintah.setString(5, produsen);
                perintah.setString(6, persediaan);
                perintah.setString(7, harga);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Komoditi berhasil disimpan!");

                this.validasi = false;
                this.var_IdKomoditi = null;
                this.var_nmKomoditi = null;
                this.var_skripsKomoditi = null;
                this.var_lokasiKomoditi = null;
                this.var_produsen = null;
                this.var_persediaan = null;
                this.var_harga = null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ubahKomoditiPST(String id, String nama, String skrips, String lokasi, String produsen, String persediaan, String harga) {
        try {
            String sqlUbah = "UPDATE komoditi SET nama_komoditi=?, skrips_komoditi=?, lokasi_komoditi=?, produsen=?, persediaan=?, harga=? WHERE id_komoditi=?";
            PreparedStatement ubah = koneksiDB.prepareStatement(sqlUbah);
            ubah.setString(1, nama);
            ubah.setString(2, skrips);
            ubah.setString(3, lokasi);
            ubah.setString(4, produsen);
            ubah.setString(5, persediaan);
            ubah.setString(6, harga);
            ubah.setString(7, id);
            ubah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Komoditi berhasil diubah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void hapusKomoditiPST(String id) {
        try {
            String sqlHapus = "DELETE FROM komoditi WHERE id_komoditi=?";
            PreparedStatement hapus = koneksiDB.prepareStatement(sqlHapus);
            hapus.setString(1, id);
            hapus.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Komoditi berhasil dihapus!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // ====================== TABEL INFO ======================
    public void simpanInfoPST(String id, String berita, String kegiatan, String tanggal, String laporan) {
        try {
            String sql = "INSERT INTO info (id_info, berita, kegiatan, tanggal_info, laporan) VALUES (?, ?, ?, ?, ?)";
            String checkPrimary = "SELECT * FROM info WHERE id_info = '" + id + "'";
            Statement checkData = koneksiDB.createStatement();
            ResultSet data = checkData.executeQuery(checkPrimary);

            if (data.next()) {
                String isi = "\nBerita : " + data.getString("berita")
                        + "\nKegiatan : " + data.getString("kegiatan") 
                        + "\nTanggal Info : " + data.getString("tanggal_info") 
                        + "\nLaporan : " + data.getString("laporan");
                JOptionPane.showMessageDialog(null, "ID Info sudah terdaftar!" + isi);

                this.validasi = true;
                this.var_berita = data.getString("berita");
                this.var_kegiatan = data.getString("kegiatan");
                this.var_tanggalInfo = data.getString("tanggal_info");
                this.var_laporan = data.getString("laporan");
            } else {
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, id);
                perintah.setString(2, berita);
                perintah.setString(3, kegiatan);
                perintah.setString(4, tanggal);
                perintah.setString(5, laporan);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Info berhasil disimpan!");

                this.validasi = false;
                this.var_IdInfo = null;
                this.var_berita = null;
                this.var_kegiatan = null;
                this.var_tanggalInfo = null;
                this.var_laporan = null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ubahInfoPST(String id, String berita, String kegiatan, String tanggal, String laporan) {
        try {
            String sqlUbah = "UPDATE info SET berita=?, kegiatan=?, tanggal_info=?, laporan=? WHERE id_info=? ";
            PreparedStatement ubah = koneksiDB.prepareStatement(sqlUbah);
            ubah.setString(1, berita);
            ubah.setString(2, kegiatan);
            ubah.setString(3, tanggal);
            ubah.setString(4, laporan);
            ubah.setString(5, id);
            ubah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Info berhasil diubah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void hapusInfoPST(String id) {
        try {
            String sqlHapus = "DELETE FROM info WHERE id_info=?";
            PreparedStatement hapus = koneksiDB.prepareStatement(sqlHapus);
            hapus.setString(1, id);
            hapus.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Info berhasil dihapus!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void cetakLaporan(String fileLaporan, String sql){
    try {
        File laporan = new File(fileLaporan);
        JasperDesign designLaporan = JRXmlLoader.load(laporan);
        JRDesignQuery queryLaporan = new JRDesignQuery();
        queryLaporan.setText(sql);
        designLaporan.setQuery(queryLaporan);
        JasperReport objekLaporan = JasperCompileManager.compileReport(designLaporan);
        JasperPrint objekPrint = JasperFillManager.fillReport(objekLaporan, null, this.koneksiDB);
        JasperViewer.viewReport(objekPrint, false);
    } catch (Exception e) {
    }
}
}
