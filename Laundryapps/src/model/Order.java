package model;

import java.sql.Date;

public class Order {

    private String id;
    private String idCostumer;
    private String costumer;
    private String idService;
    private String idUser ;
    private String total;
    private String status;
    private String pembayaran;
    private String status_pembayaran;
    private Date tanggalSelesai; 
    private Date tanggal;

    public Order() {
    }

    public Order(String id, String idCostumer, String costumer, String idService, String idUser , String total, 
                 Date tanggal, Date tanggalSelesai, String status, String pembayaran, String status_pembayaran) {
        this.id = id;
        this.idCostumer = idCostumer;
        this.costumer = costumer;
        this.idService = idService;
        this.idUser  = idUser ;
        this.total = total;
        this.tanggal = tanggal;
        this.tanggalSelesai = tanggalSelesai;
        this.status = status;
        this.pembayaran = pembayaran;
        this.status_pembayaran = status_pembayaran; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCostumer() {
        return costumer;
    }

    public void setCostumer(String costumer) {
        this.costumer = costumer;
    }

    public String getIdCostumer() {
        return idCostumer;
    }

    public void setIdCostumer(String idCostumer) {
        this.idCostumer = idCostumer;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getIdUser () {
        return idUser ;
    }

    public void setIdUser (String idUser ) {
        this.idUser  = idUser ;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Date getTanggal_selesai() { 
        return tanggalSelesai;
    }

    public void setTanggal_selesai(Date tanggalSelesai) { 
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getStatus_pembayaran() { 
        return status_pembayaran;
    }

    public void setStatus_pembayaran(String status_pembayaran) { 
        this.status_pembayaran = status_pembayaran;
    }
}