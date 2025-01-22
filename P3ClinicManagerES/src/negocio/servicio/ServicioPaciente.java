package negocio.servicio;

import negocio.modelo.Paciente;
import negocio.modelo.Seguro;
import negocio.excepciones.ExcepcionValidacion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicioPaciente {
    private List<Paciente> pacientes;

    public ServicioPaciente() {
        this.pacientes = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) throws ExcepcionValidacion {
        // Validar campos requeridos
        ServicioValidacion.validarRequerido(paciente.getNombre(), "Nombre");
        ServicioValidacion.validarRequerido(paciente.getApellido(), "Apellido");
        ServicioValidacion.validarRequerido(paciente.getGenero(), "Género");
        ServicioValidacion.validarCorreo(paciente.getCorreo());
        ServicioValidacion.validarTelefono(paciente.getTelefono());
        ServicioValidacion.validarFecha(paciente.getFechaNac());
        ServicioValidacion.validarEdad(paciente.getEdad());
        ServicioValidacion.validarCodigoPositivo(paciente.getCodigoPaciente(), "Código de paciente");

        // Validar código único de paciente
        if (pacientes.stream().anyMatch(p -> p.getCodigoPaciente() == paciente.getCodigoPaciente())) {
            throw new ExcepcionValidacion("El código de paciente ya existe");
        }

        pacientes.add(paciente);
    }

    public Optional<Paciente> buscarPaciente(int codigo) {
        return pacientes.stream()
                .filter(p -> p.getCodigoPaciente() == codigo)
                .findFirst();
    }

    public void actualizarPaciente(Paciente paciente) throws ExcepcionValidacion {
        // Validar que el paciente existe
        Optional<Paciente> pacienteExistente = buscarPaciente(paciente.getCodigoPaciente());
        if (pacienteExistente.isEmpty()) {
            throw new ExcepcionValidacion("Paciente no encontrado");
        }

        // Validar campos requeridos
        ServicioValidacion.validarRequerido(paciente.getNombre(), "Nombre");
        ServicioValidacion.validarRequerido(paciente.getApellido(), "Apellido");
        ServicioValidacion.validarCorreo(paciente.getCorreo());
        ServicioValidacion.validarTelefono(paciente.getTelefono());

        // Actualizar paciente
        int index = pacientes.indexOf(pacienteExistente.get());
        pacientes.set(index, paciente);
    }

    public void agregarHistorialMedico(int codigoPaciente, String entrada) throws ExcepcionValidacion {
        Paciente paciente = buscarPaciente(codigoPaciente)
                .orElseThrow(() -> new ExcepcionValidacion("Paciente no encontrado"));
        ServicioValidacion.validarRequerido(entrada, "Entrada de historial");
        paciente.agregarHistorialMedico(entrada);
    }

    public void agregarCita(int codigoPaciente, String fecha, String hora) throws ExcepcionValidacion {
        Paciente paciente = buscarPaciente(codigoPaciente)
                .orElseThrow(() -> new ExcepcionValidacion("Paciente no encontrado"));
        ServicioValidacion.validarFecha(fecha);
        ServicioValidacion.validarRequerido(hora, "Hora de la cita");
        paciente.agregarCita(String.format("Cita: %s a las %s", fecha, hora));
    }

    public List<Paciente> obtenerTodosPacientes() {
        return new ArrayList<>(pacientes);
    }
}