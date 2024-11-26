# UAS PBO

##  Langkah - Langkah
1.	Buat project baru dengan nama UAS_PBO
2.	Masukkan library yang diperlukan

![Screenshot 2024-11-26 133343](https://github.com/user-attachments/assets/28b0264b-a8cb-47b8-840e-4e0654a31648)

3.	Buat persistence unit dengan nama UAS_PBOPU
 
4.	Buat database dengan nama UAS_PBO, lalu buat table dengan nama mahasiswa dan login
   
![Screenshot 2024-11-26 133416](https://github.com/user-attachments/assets/3ff62d8d-066f-4d33-bdf1-d70c65042df9)

![Screenshot 2024-11-26 133526](https://github.com/user-attachments/assets/b4b258cd-4682-40b0-b531-8f61c94e13cc)
 
5.	Buat Class baru dengan nama mahasiswaa, serta buat persistence dari mahasiswaa
6.	Buat design GUI dan tuliskan source codenya

![Screenshot 2024-11-26 133701](https://github.com/user-attachments/assets/f0ccbb5d-929c-49a7-a465-9a2d3506de51)

Source Code:
> showTable
<pre>
 public void showTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UAS_PBOPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Buat model tabel dengan kolom yang sesuai
            DefaultTableModel tbnmhs = new DefaultTableModel(new String[]{"NIM", "NAMA", "ALAMAT", "ASAL SMA", "NAMA ORTU"}, 0);

            // Menggunakan NamedQuery "Matakuliah.findAll" untuk mengambil semua data
            List<Mahasiswa_1> Mahasiswa_1List = em.createNamedQuery("Mahasiswa_1.findAll", Mahasiswa_1.class).getResultList();

            // Iterasi hasil query dan tambahkan baris ke model tabel
            for (Mahasiswa_1 mh : Mahasiswa_1List) {
                tbnmhs.addRow(new Object[]{
                    mh.getNim(),
                    mh.getNama(),
                    mh.getAlamat(),
                    mh.getAsalSma(),
                    mh.getNamaOrtu()
                }
                );
            }

            // Atur model tabel ke tabel GUI
            Tabel.setModel(tbnmhs);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            em.close();  // Tutup EntityManager setelah selesai
        }
    }
              </pre>
              
> Add
<pre>
private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        if (tfNIM.getText().equals("") || tfNama.getText().equals("") || tfAlamat.getText().equals("") || tfAsal.getText().equals("") || tfNamaOrtu.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Isi semua data");
        } else {
            String nim, nama, alamat, asalsma, namaortu;
            nim = tfNIM.getText();
            nama = tfNama.getText();
            alamat = tfAlamat.getText();
            asalsma = tfAsal.getText();
            namaortu = tfNamaOrtu.getText();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("UAS_PBOPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Mahasiswa_1 mh = new Mahasiswa_1();
            mh.setNim(nim);
            mh.setNama(nama);
            mh.setAlamat(alamat);
            mh.setAsalSma(asalsma);
            mh.setNamaOrtu(namaortu);

            em.persist(mh);

            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Sukses diinput");

            //bersih();
            showTable();

            em.close();
            emf.close();
        }
    }                      
</pre>
 
> Update
<pre>
 private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        if (tfNIM.getText().equals("") || tfNama.getText().equals("") || tfAlamat.getText().equals("") || tfAsal.getText().equals("") || tfNamaOrtu.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Isi semua data");
        } else {
            String nim, nama, alamat, asalsma, namaortu;
            nim = tfNIM.getText();
            nama = tfNama.getText();
            alamat = tfAlamat.getText();
            asalsma = tfAsal.getText();
            namaortu = tfNamaOrtu.getText();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("UAS_PBOPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Mahasiswa_1 mh = em.find(Mahasiswa_1.class, nim);
            if (mh == null) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
            } else {
                mh.setNim(nim);
                mh.setNama(nama);
                mh.setAlamat(alamat);
                mh.setAsalSma(asalsma);
                mh.setNamaOrtu(namaortu);

                em.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Data berhasil diupdate");

                em.close();
                emf.close();
                // bersih();
                tfNIM.setEditable(true);
            }
        }
        showTable();
    }            
</pre>

> Delete
<pre>
private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UAS_PBOPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Validasi input
            if (tfNIM.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Masukkan NIM yang akan dihapus");
            } else {
                // Mulai transaksi
                em.getTransaction().begin();

                // Cari entitas Matakuliah berdasarkan kode mata kuliah
                String NIM = tfNIM.getText();
                Mahasiswa_1 mh = em.find(Mahasiswa_1.class, NIM);

                if (mh != null) {
                    // Hapus entitas jika ditemukan
                    em.remove(mh);

                    // Commit transaksi
                    em.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

                    // Refresh data pada tampilan
                    showTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk NIM: " + NIM);
                    em.getTransaction().rollback();
                }
            }
        } catch (Exception e) {
            // Rollback transaksi jika terjadi kesalahan
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Gagal menghapus data");
            System.out.println(e.getMessage());
        } finally {
            em.close();  // Tutup EntityManager setelah operasi
        }
</pre>

> Cetak
<pre>
 private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String driver = "org.postgresql.Driver";
        String koneksi = "jdbc:postgresql://localhost:5432/UAS_PBO";
        String user = "postgres";
        String password = "yupiburger";
        File reportFile = new File(".");
        String dirr = "";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(koneksi, user, password);
            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM Mahasiswa";
            dirr = reportFile.getCanonicalPath() + "/src/uas_pbo/";
            JasperDesign design = JRXmlLoader.load(dirr + "report1.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(design);
            ResultSet rs = stat.executeQuery(sql);
            JRResultSetDataSource rsDataSource = new JRResultSetDataSource(rs);
            JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(),
                    rsDataSource);
            JasperViewer.viewReport(jp);
        } catch (ClassNotFoundException | SQLException | IOException | JRException ex) {
            JOptionPane.showMessageDialog(null, "\nGagal Mencetak\n" + ex,
                    "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
</pre> 

> Upload
<pre>
 private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {                                          
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File filePilihan = jfc.getSelectedFile();
            System.out.println("yang dipilih : " + filePilihan.getAbsolutePath());
            try {
                BufferedReader br = new BufferedReader(new FileReader(filePilihan));
                String baris;
                String pemisah = ";";

                // EntityManager untuk database
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("UAS_PBOPU");
                EntityManager em = emf.createEntityManager();

                em.getTransaction().begin(); // Mulai transaksi

                while ((baris = br.readLine()) != null) {
                    String[] dataMhs = baris.split(pemisah);

                    if (dataMhs.length >= 5) {
                        String nim = dataMhs[0];
                        String nama = dataMhs[1];
                        String alamat = dataMhs[2];
                        String asalsma = dataMhs[3];
                        String namaortu = dataMhs[4];

                        // Buat objek MataKuliah baru
                        Mahasiswa_1 mh = new Mahasiswa_1();
                        mh.setNim(nim);
                        mh.setNama(nama);
                        mh.setAlamat(alamat);
                        mh.setAsalSma(asalsma);
                        mh.setNamaOrtu(namaortu);

                        // Simpan objek ke database
                        em.persist(mh);
                    }
                }

                em.getTransaction().commit(); // Commit transaksi
                em.close(); // Tutup EntityManager

                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan");
                showTable(); // Refresh tabel GUI

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
</pre> 

> Logout
<pre>
 private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        login g = new login();
        g.setVisible(true);
        this.dispose();
    }        
</pre> 

7.	Buat Class baru dengan nama login, jangan lupa buat persistencenya juga dan tuliskan source codenya
   
![Screenshot 2024-11-26 134757](https://github.com/user-attachments/assets/3a6290eb-b08c-4939-bcd3-6e80a279030e)

> Source Code
<pre>
   private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if (tfUser.getText().equals("") | tfPw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Isi Terlebih Dahulu");
        } else {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("UAS_PBOPU");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            String username = tfUser.getText();
            String pw = tfPw.getText();
            Login_1 g = em.find(Login_1.class, username);

            if (g == null) {
                JOptionPane.showMessageDialog(null, "Username tidak ditemukan");
            } else if (g.getPassword().equals(pw)) {
                JOptionPane.showMessageDialog(null, "Welcome!");
                Mahasiswaa m = new Mahasiswaa();
                m.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Username atau Password salah!");
            }
            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }               
</pre>

8.	Buat Class baru dengan nama create dan tuliskan source codenya
    
![Screenshot 2024-11-26 134819](https://github.com/user-attachments/assets/4177f218-5de5-489e-90f1-a95feded4554)

> Create
<pre>
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if (tfUser.getText().equals("") || tfPw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Isi username dan password");
        } else {
            String username, pw;
            username = tfUser.getText();
            pw = tfPw.getText();

            if (pw.length() < 8) {
                JOptionPane.showMessageDialog(null, "Password harus terdiri dari minimal 8 karakter.");
                return;
            }

            if (!pw.matches("[A-Za-z0-9]+")) {
                JOptionPane.showMessageDialog(null, "Password tidak boleh mengandung karakter selain huruf atau angka.");
                return;
            }

            int angka = 0;
            for (char c : pw.toCharArray()) {
                if (Character.isDigit(c)) {
                    angka++;
                }
            }

           
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("UAS_PBOPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Login_1 y = em.find(Login_1.class, username);
            if (y != null) {
                JOptionPane.showMessageDialog(null, "Username yang Anda pilih tidak tersedia, coba gunakan username lain");
                // bersih();
                tfUser.requestFocus();
            } else {
                Login_1 account = new Login_1();
                account.setUsername(username);
                account.setPassword(pw);
                em.persist(account);
                em.getTransaction().commit();

                JOptionPane.showMessageDialog(null, "Akun berhasil dibuat");
                //bersih();

                this.dispose();
                login frame = new login();
                frame.setVisible(true);
            }
            em.close();
            emf.close();
        }
</pre>

> Hide Password
<pre>
    private void cbPwActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
        if(cbPw.isSelected()){
            tfPw.setEchoChar((char)0);
        }
        else
        tfPw.setEchoChar('*');
    } 
</pre>
