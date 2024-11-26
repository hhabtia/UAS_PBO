/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas_pbo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Habtia Humaira
 */
@Entity
@Table(name = "mahasiswa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mahasiswa_1.findAll", query = "SELECT m FROM Mahasiswa_1 m"),
    @NamedQuery(name = "Mahasiswa_1.findByNim", query = "SELECT m FROM Mahasiswa_1 m WHERE m.nim = :nim"),
    @NamedQuery(name = "Mahasiswa_1.findByNama", query = "SELECT m FROM Mahasiswa_1 m WHERE m.nama = :nama"),
    @NamedQuery(name = "Mahasiswa_1.findByAlamat", query = "SELECT m FROM Mahasiswa_1 m WHERE m.alamat = :alamat"),
    @NamedQuery(name = "Mahasiswa_1.findByAsalSma", query = "SELECT m FROM Mahasiswa_1 m WHERE m.asalSma = :asalSma"),
    @NamedQuery(name = "Mahasiswa_1.findByNamaOrtu", query = "SELECT m FROM Mahasiswa_1 m WHERE m.namaOrtu = :namaOrtu")})
public class Mahasiswa_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nim")
    private String nim;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "asal_sma")
    private String asalSma;
    @Basic(optional = false)
    @Column(name = "nama_ortu")
    private String namaOrtu;

    public Mahasiswa_1() {
    }

    public Mahasiswa_1(String nim) {
        this.nim = nim;
    }

    public Mahasiswa_1(String nim, String nama, String alamat, String asalSma, String namaOrtu) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.asalSma = asalSma;
        this.namaOrtu = namaOrtu;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAsalSma() {
        return asalSma;
    }

    public void setAsalSma(String asalSma) {
        this.asalSma = asalSma;
    }

    public String getNamaOrtu() {
        return namaOrtu;
    }

    public void setNamaOrtu(String namaOrtu) {
        this.namaOrtu = namaOrtu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nim != null ? nim.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mahasiswa_1)) {
            return false;
        }
        Mahasiswa_1 other = (Mahasiswa_1) object;
        if ((this.nim == null && other.nim != null) || (this.nim != null && !this.nim.equals(other.nim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uas_pbo.Mahasiswa_1[ nim=" + nim + " ]";
    }
    
}
