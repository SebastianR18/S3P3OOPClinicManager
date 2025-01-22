package negocio.modelo;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona {
    private String diagnostico;
    private String antecedentesFamiliares;
    private String grupoSanguineo;
    private int codigoPaciente;
    private List<String> historialMedico;
    private List<String> citas;
    private Seguro seguroMedico;

    public Paciente() {
        super();
        this.historialMedico = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    public Paciente(String fechaNac, int edad, String nombre, String apellido, String genero,
                    String direccion, String telefono, String correo, int codigoPaciente) {
        super(fechaNac, edad, nombre, apellido, genero, direccion, telefono, correo);
        this.codigoPaciente = codigoPaciente;
        this.historialMedico = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    // Getters y setters
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getAntecedentesFamiliares() { return antecedentesFamiliares; }
    public void setAntecedentesFamiliares(String antecedentesFamiliares) {
        this.antecedentesFamiliares = antecedentesFamiliares;
    }

    public String getGrupoSanguineo() { return grupoSanguineo; }
    public void setGrupoSanguineo(String grupoSanguineo) { this.grupoSanguineo = grupoSanguineo; }

    public int getCodigoPaciente() { return codigoPaciente; }
    public void setCodigoPaciente(int codigoPaciente) { this.codigoPaciente = codigoPaciente; }

    public List<String> getHistorialMedico() { return new ArrayList<>(historialMedico); }
    public void agregarHistorialMedico(String entrada) { historialMedico.add(entrada); }

    public List<String> getCitas() { return new ArrayList<>(citas); }
    public void agregarCita(String cita) { citas.add(cita); }

    public Seguro getSeguroMedico() { return seguroMedico; }
    public void setSeguroMedico(Seguro seguroMedico) { this.seguroMedico = seguroMedico; }
}
