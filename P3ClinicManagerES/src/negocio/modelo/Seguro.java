package negocio.modelo;

public class Seguro {
    private String aseguradora;
    private String numeroPoliza;
    private String tipoCobertura;
    private String fechaVencimiento;

    public Seguro(String aseguradora, String numeroPoliza, String tipoCobertura) {
        this.aseguradora = aseguradora;
        this.numeroPoliza = numeroPoliza;
        this.tipoCobertura = tipoCobertura;
    }

    // Getters y setters
    public String getAseguradora() { return aseguradora; }
    public void setAseguradora(String aseguradora) { this.aseguradora = aseguradora; }

    public String getNumeroPoliza() { return numeroPoliza; }
    public void setNumeroPoliza(String numeroPoliza) { this.numeroPoliza = numeroPoliza; }

    public String getTipoCobertura() { return tipoCobertura; }
    public void setTipoCobertura(String tipoCobertura) { this.tipoCobertura = tipoCobertura; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}