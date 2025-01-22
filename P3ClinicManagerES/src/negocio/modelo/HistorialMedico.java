package negocio.modelo;

public class HistorialMedico {
    private String enfermedadesPrevias;
    private String alergias;
    private String intervenciones;
    private String fecha;

    public HistorialMedico(String enfermedadesPrevias, String alergias, String intervenciones, String fecha) {
        this.enfermedadesPrevias = enfermedadesPrevias;
        this.alergias = alergias;
        this.intervenciones = intervenciones;
        this.fecha = fecha;
    }

    // Getters y setters
    public String getEnfermedadesPrevias() { return enfermedadesPrevias; }
    public void setEnfermedadesPrevias(String enfermedadesPrevias) {
        this.enfermedadesPrevias = enfermedadesPrevias;
    }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getIntervenciones() { return intervenciones; }
    public void setIntervenciones(String intervenciones) { this.intervenciones = intervenciones; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}