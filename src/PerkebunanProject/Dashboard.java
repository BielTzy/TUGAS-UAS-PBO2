package PerkebunanProject;

import perkebunan.*;
import form.*;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    
    private JPanel mainPanel;
    private JMenuBar menuBar;
    
    public Dashboard() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Dashboard - Sistem Manajemen Perkebunan");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Welcome Panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        JLabel lblWelcome = new JLabel("Selamat Datang di Sistem Manajemen Perkebunan");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblSubtitle = new JLabel("Nabil Bintang Alfarizi | 2310010501 | 5E");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitle.setForeground(Color.GRAY);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(lblWelcome);
        welcomePanel.add(Box.createVerticalStrut(20));
        welcomePanel.add(lblSubtitle);
        welcomePanel.add(Box.createVerticalGlue());
        
        mainPanel.add(welcomePanel, BorderLayout.CENTER);
        
        // Menu Bar
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        
        // Menu File
        JMenu menuFile = new JMenu("File");
        menuFile.setForeground(Color.BLACK);
        JMenuItem itemExit = new JMenuItem("Keluar");
        itemExit.setForeground(Color.BLACK);
        itemExit.addActionListener(e -> System.exit(0));
        menuFile.add(itemExit);
        
        // Menu Master Data
        JMenu menuMaster = new JMenu("Master Data");
        menuMaster.setForeground(Color.BLACK);
        
        JMenuItem itemAdmin = new JMenuItem("Admin");
        itemAdmin.setForeground(Color.BLACK);
        itemAdmin.setAccelerator(KeyStroke.getKeyStroke("ctrl 1"));
        itemAdmin.addActionListener(e -> bukaFrame("Admin"));
        
        JMenuItem itemPerusahaan = new JMenuItem("Perusahaan");
        itemPerusahaan.setForeground(Color.BLACK);
        itemPerusahaan.setAccelerator(KeyStroke.getKeyStroke("ctrl 2"));
        itemPerusahaan.addActionListener(e -> bukaFrame("Perusahaan"));
        
        JMenuItem itemKomoditi = new JMenuItem("Komoditi");
        itemKomoditi.setForeground(Color.BLACK);
        itemKomoditi.setAccelerator(KeyStroke.getKeyStroke("ctrl 3"));
        itemKomoditi.addActionListener(e -> bukaFrame("Komoditi"));
        
        menuMaster.add(itemAdmin);
        menuMaster.add(itemPerusahaan);
        menuMaster.add(itemKomoditi);
        
        // Menu Transaksi
        JMenu menuTransaksi = new JMenu("Transaksi");
        menuTransaksi.setForeground(Color.BLACK);
        
        JMenuItem itemPengunjung = new JMenuItem("Pengunjung");
        itemPengunjung.setForeground(Color.BLACK);
        itemPengunjung.setAccelerator(KeyStroke.getKeyStroke("ctrl 4"));
        itemPengunjung.addActionListener(e -> bukaFrame("Pengunjung"));
        
        menuTransaksi.add(itemPengunjung);
        
        // Menu Informasi
        JMenu menuInformasi = new JMenu("Informasi");
        menuInformasi.setForeground(Color.BLACK);
        
        JMenuItem itemInfo = new JMenuItem("Info Sistem");
        itemInfo.setForeground(Color.BLACK);
        itemInfo.setAccelerator(KeyStroke.getKeyStroke("ctrl 5"));
        itemInfo.addActionListener(e -> bukaFrame("Info"));
        
        menuInformasi.add(itemInfo);
        
        // Tambahkan menu ke menu bar
        menuBar.add(menuFile);
        menuBar.add(menuMaster);
        menuBar.add(menuTransaksi);
        menuBar.add(menuInformasi);
        
        setJMenuBar(menuBar);
        
        // Panel Toolbar dengan tombol cepat
        JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        toolbarPanel.setBackground(Color.WHITE);
        
        JButton btnAdmin = createToolbarButton("Admin");
        btnAdmin.setToolTipText("Ctrl+1");
        btnAdmin.addActionListener(e -> bukaFrame("Admin"));
        
        JButton btnPerusahaan = createToolbarButton("Perusahaan");
        btnPerusahaan.setToolTipText("Ctrl+2");
        btnPerusahaan.addActionListener(e -> bukaFrame("Perusahaan"));
        
        JButton btnKomoditi = createToolbarButton("Komoditi");
        btnKomoditi.setToolTipText("Ctrl+3");
        btnKomoditi.addActionListener(e -> bukaFrame("Komoditi"));
        
        JButton btnPengunjung = createToolbarButton("Pengunjung");
        btnPengunjung.setToolTipText("Ctrl+4");
        btnPengunjung.addActionListener(e -> bukaFrame("Pengunjung"));
        
        JButton btnInfo = createToolbarButton("Info");
        btnInfo.setToolTipText("Ctrl+5");
        btnInfo.addActionListener(e -> bukaFrame("Info"));
        
        toolbarPanel.add(btnAdmin);
        toolbarPanel.add(btnPerusahaan);
        toolbarPanel.add(btnKomoditi);
        toolbarPanel.add(btnPengunjung);
        toolbarPanel.add(btnInfo);
        
        // Layout utama
        setLayout(new BorderLayout());
        add(toolbarPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        
        // Status bar
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBackground(new Color(60, 63, 65));
        JLabel lblStatus = new JLabel("Siap | Dashboard Sistem Manajemen Perkebunan");
        lblStatus.setForeground(Color.WHITE);
        statusBar.add(lblStatus);
        add(statusBar, BorderLayout.SOUTH);
    }
    
    private JButton createToolbarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(75, 110, 175));
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(50, 80, 140), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        btn.setPreferredSize(new Dimension(150, 45));
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        
        // Efek hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(95, 130, 195));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(75, 110, 175));
            }
        });
        
        return btn;
    }
    
    private void bukaFrame(String namaFrame) {
        // Buat dan tampilkan frame sesuai nama
        switch (namaFrame) {
            case "Admin":
                frameAdmin formAdmin = new frameAdmin();
                formAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                formAdmin.setVisible(true);
                break;
            case "Perusahaan":
                framePerusahaan formPerusahaan = new framePerusahaan();
                formPerusahaan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                formPerusahaan.setVisible(true);
                break;
            case "Komoditi":
                frameKomoditi formKomoditi = new frameKomoditi();
                formKomoditi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                formKomoditi.setVisible(true);
                break;
            case "Pengunjung":
                framePengunjung formPengunjung = new framePengunjung();
                formPengunjung.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                formPengunjung.setVisible(true);
                break;
            case "Info":
                frameInfo formInfo = new frameInfo();
                formInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                formInfo.setVisible(true);
                break;
        }
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
        });
    }
}