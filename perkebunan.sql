-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 13 Jan 2026 pada 13.38
-- Versi server: 10.4.22-MariaDB
-- Versi PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perkebunan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `nama_admin` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `userpass` varchar(100) DEFAULT NULL,
  `telp` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id_admin`, `nama_admin`, `username`, `userpass`, `telp`, `email`) VALUES
(1, 'Nabil', 'admin01', 'admin123', '081234567890', 'nabil@admin.com'),
(2, 'Yome', 'admin02', 'admin123', '082345678901', 'yome@admin.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `info`
--

CREATE TABLE `info` (
  `id_info` int(11) NOT NULL,
  `berita` text DEFAULT NULL,
  `kegiatan` text DEFAULT NULL,
  `tanggal_info` varchar(100) DEFAULT NULL,
  `laporan` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `info`
--

INSERT INTO `info` (`id_info`, `berita`, `kegiatan`, `tanggal_info`, `laporan`) VALUES
(1, 'Harga kelapa sawit mengalami kenaikan 5% bulan ini.', 'Pelatihan pengolahan hasil perkebunan diadakan di Banjarmasin.', '2025-11-01', 'Laporan produksi Q4 2025 selesai disusun.'),
(2, 'Permintaan ekspor karet meningkat signifikan.', 'Kunjungan kerja ke perusahaan mitra di Tapin.', '2025-11-01', 'Laporan hasil panen dan ekspor 2025.');

-- --------------------------------------------------------

--
-- Struktur dari tabel `komoditi`
--

CREATE TABLE `komoditi` (
  `id_komoditi` int(11) NOT NULL,
  `nama_komoditi` varchar(100) DEFAULT NULL,
  `skrips_komoditi` text DEFAULT NULL,
  `lokasi_komoditi` varchar(100) DEFAULT NULL,
  `produsen` varchar(100) DEFAULT NULL,
  `persediaan` int(11) DEFAULT NULL,
  `harga` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `komoditi`
--

INSERT INTO `komoditi` (`id_komoditi`, `nama_komoditi`, `skrips_komoditi`, `lokasi_komoditi`, `produsen`, `persediaan`, `harga`) VALUES
(1, 'Kelapa Sawit', 'Tanaman penghasil minyak nabati utama di Indonesia.', 'Kabupaten Banjar', 'PT Sawit Lestari', 1200, '15000'),
(2, 'Karet Alam', 'Komoditi ekspor unggulan dari Kalimantan Selatan.', 'Kabupaten Tapin', 'CV Agro Jaya', 800, '20000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengunjung`
--

CREATE TABLE `pengunjung` (
  `id_kunjung` int(11) NOT NULL,
  `nama_kunjung` varchar(100) DEFAULT NULL,
  `id_card` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `userpass` varchar(100) DEFAULT NULL,
  `telp` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pengunjung`
--

INSERT INTO `pengunjung` (`id_kunjung`, `nama_kunjung`, `id_card`, `username`, `userpass`, `telp`, `email`) VALUES
(1, 'Siti Rahma', 'KJ-1001', 'siti_r', 'pass123', '085312345678', 'siti@gmail.com'),
(2, 'Ahmad Fauzi', 'KJ-1002', 'ahmadr', 'pass456', '085398765432', 'ahmad@gmail.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `perusahaan_perkebunan`
--

CREATE TABLE `perusahaan_perkebunan` (
  `id_usaha` int(11) NOT NULL,
  `nama_usaha` varchar(100) DEFAULT NULL,
  `izin_usaha` varchar(100) DEFAULT NULL,
  `alamat_usaha` text DEFAULT NULL,
  `telp` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `userpass` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `perusahaan_perkebunan`
--

INSERT INTO `perusahaan_perkebunan` (`id_usaha`, `nama_usaha`, `izin_usaha`, `alamat_usaha`, `telp`, `email`, `username`, `userpass`) VALUES
(1, 'PT Sawit Lestari', 'IUP-00123', 'Jl. Raya Banjarmasin No.12', '081355555555', 'kontak@sawitlestari.com', 'sawitlestari', 'sawit123'),
(2, 'CV Agro Jaya', 'IUP-00456', 'Jl. Perkebunan Banjar No.7', '082377777777', 'info@agrojaya.com', 'agrojaya', 'agro456');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indeks untuk tabel `info`
--
ALTER TABLE `info`
  ADD PRIMARY KEY (`id_info`);

--
-- Indeks untuk tabel `komoditi`
--
ALTER TABLE `komoditi`
  ADD PRIMARY KEY (`id_komoditi`);

--
-- Indeks untuk tabel `pengunjung`
--
ALTER TABLE `pengunjung`
  ADD PRIMARY KEY (`id_kunjung`);

--
-- Indeks untuk tabel `perusahaan_perkebunan`
--
ALTER TABLE `perusahaan_perkebunan`
  ADD PRIMARY KEY (`id_usaha`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `info`
--
ALTER TABLE `info`
  MODIFY `id_info` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `komoditi`
--
ALTER TABLE `komoditi`
  MODIFY `id_komoditi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `pengunjung`
--
ALTER TABLE `pengunjung`
  MODIFY `id_kunjung` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `perusahaan_perkebunan`
--
ALTER TABLE `perusahaan_perkebunan`
  MODIFY `id_usaha` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
