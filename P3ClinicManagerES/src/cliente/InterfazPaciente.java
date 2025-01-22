package cliente;

import negocio.modelo.Paciente;
import negocio.modelo.Seguro;
import negocio.servicio.ServicioPaciente;
import negocio.servicio.ServicioValidacion;
import negocio.excepciones.ExcepcionValidacion;
import javax.swing.JOptionPane;
import java.util.Optional;
import java.util.List;


public class InterfazPaciente {
    private ServicioPaciente servicioPaciente;

    public InterfazPaciente(ServicioPaciente servicioPaciente) {
        this.servicioPaciente = servicioPaciente;
    }

    // Muestra el menú principal y maneja la interacción del usuario.

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            try {
                String menu = """
                    Sistema de Gestión de Pacientes
                    ==============================
                    1. Agregar nuevo paciente
                    2. Mostrar datos de un paciente
                    3. Editar datos de un paciente
                    4. Agregar historial médico
                    5. Mostrar historial médico
                    6. Crear una cita
                    7. Mostrar todas las citas
                    8. Mostrar todos los pacientes
                    9. Salir
                    
                    Seleccione una opción:""";

                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
                procesarOpcion(opcion);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido");
                opcion = 0;
            } catch (ExcepcionValidacion e) {
                JOptionPane.showMessageDialog(null, "Error de validación: " + e.getMessage());
                opcion = 0;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
                opcion = 0;
            }
        } while (opcion != 9);
    }

     // Procesa la opción seleccionada por el usuario y dirige a la funcionalidad correspondiente.

    private void procesarOpcion(int opcion) throws ExcepcionValidacion {
        switch (opcion) {
            case 1 -> agregarNuevoPaciente();
            case 2 -> mostrarDatosPaciente();
            case 3 -> editarDatosPaciente();
            case 4 -> agregarHistorialMedico();
            case 5 -> mostrarHistorialMedico();
            case 6 -> crearCita();
            case 7 -> mostrarCitas();
            case 8 -> mostrarTodosPacientes();
            case 9 -> JOptionPane.showMessageDialog(null, "Gracias por usar el sistema");
            default -> JOptionPane.showMessageDialog(null, "Opción no válida");
        }
    }

     // Recopila y valida la información necesaria para registrar un nuevo paciente.

    private void agregarNuevoPaciente() throws ExcepcionValidacion {
        try {
            Paciente nuevoPaciente = new Paciente();

            // Recopilación de datos básicos del paciente
            nuevoPaciente.setCodigoPaciente(Integer.parseInt(
                    JOptionPane.showInputDialog("Ingrese el código del paciente:")));
            nuevoPaciente.setNombre(
                    JOptionPane.showInputDialog("Ingrese el nombre:"));
            nuevoPaciente.setApellido(
                    JOptionPane.showInputDialog("Ingrese el apellido:"));
            nuevoPaciente.setGenero(
                    JOptionPane.showInputDialog("Ingrese el género:"));
            nuevoPaciente.setEdad(Integer.parseInt(
                    JOptionPane.showInputDialog("Ingrese la edad:")));
            nuevoPaciente.setFechaNac(
                    JOptionPane.showInputDialog("Ingrese la fecha de nacimiento (yyyy/MM/dd):"));

            // Recopilación de datos de contacto
            nuevoPaciente.setDireccion(
                    JOptionPane.showInputDialog("Ingrese la dirección:"));
            nuevoPaciente.setTelefono(
                    JOptionPane.showInputDialog("Ingrese el teléfono:"));
            nuevoPaciente.setCorreo(
                    JOptionPane.showInputDialog("Ingrese el correo electrónico:"));

            // Recopilación de datos médicos importantes
            nuevoPaciente.setGrupoSanguineo(
                    JOptionPane.showInputDialog("Ingrese el grupo sanguíneo:"));
            nuevoPaciente.setDiagnostico(
                    JOptionPane.showInputDialog("Ingrese el diagnóstico inicial:"));
            nuevoPaciente.setAntecedentesFamiliares(
                    JOptionPane.showInputDialog("Ingrese los antecedentes familiares:"));

            // Recopilación de datos del seguro médico
            String aseguradora = JOptionPane.showInputDialog("Ingrese la aseguradora:");
            String numeroPoliza = JOptionPane.showInputDialog("Ingrese el número de póliza:");
            String tipoCobertura = JOptionPane.showInputDialog("Ingrese el tipo de cobertura:");
            Seguro seguro = new Seguro(aseguradora, numeroPoliza, tipoCobertura);
            nuevoPaciente.setSeguroMedico(seguro);

            servicioPaciente.agregarPaciente(nuevoPaciente);
            JOptionPane.showMessageDialog(null, "Paciente agregado exitosamente");

        } catch (NumberFormatException e) {
            throw new ExcepcionValidacion("Por favor ingrese valores numéricos válidos");
        }
    }

    /**
     * Muestra los datos completos de un paciente específico.
     * @throws ExcepcionValidacion Si el código del paciente no es válido
     */

    private void mostrarDatosPaciente() throws ExcepcionValidacion {
        int codigo = Integer.parseInt(
                JOptionPane.showInputDialog("Ingrese el código del paciente:"));

        Optional<Paciente> pacienteOpt = servicioPaciente.buscarPaciente(codigo);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();
            String mensaje = String.format("""
                Datos del Paciente
                =================
                Código: %d
                Nombre: %s %s
                Género: %s
                Edad: %d
                Fecha de Nacimiento: %s
                Dirección: %s
                Teléfono: %s
                Correo: %s
                Grupo Sanguíneo: %s
                Diagnóstico: %s
                Antecedentes Familiares: %s""",
                    paciente.getCodigoPaciente(),
                    paciente.getNombre(),
                    paciente.getApellido(),
                    paciente.getGenero(),
                    paciente.getEdad(),
                    paciente.getFechaNac(),
                    paciente.getDireccion(),
                    paciente.getTelefono(),
                    paciente.getCorreo(),
                    paciente.getGrupoSanguineo(),
                    paciente.getDiagnostico(),
                    paciente.getAntecedentesFamiliares());

            JOptionPane.showMessageDialog(null, mensaje);
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado");
        }
    }

    /**
     * Permite modificar datos específicos de un paciente existente.
     * Muestra un submenú para seleccionar qué información se desea editar.
     * @throws ExcepcionValidacion Si el código del paciente o la opción no son válidos
     */

    private void editarDatosPaciente() throws ExcepcionValidacion {
        int codigo = Integer.parseInt(
                JOptionPane.showInputDialog("Ingrese el código del paciente a editar:"));

        Optional<Paciente> pacienteOpt = servicioPaciente.buscarPaciente(codigo);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();

            String menu = """
                Seleccione el dato a editar:
                1. Nombre
                2. Apellido
                3. Dirección
                4. Teléfono
                5. Correo
                6. Diagnóstico
                7. Antecedentes Familiares""";

            int opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1 -> paciente.setNombre(
                        JOptionPane.showInputDialog("Ingrese el nuevo nombre:"));
                case 2 -> paciente.setApellido(
                        JOptionPane.showInputDialog("Ingrese el nuevo apellido:"));
                case 3 -> paciente.setDireccion(
                        JOptionPane.showInputDialog("Ingrese la nueva dirección:"));
                case 4 -> paciente.setTelefono(
                        JOptionPane.showInputDialog("Ingrese el nuevo teléfono:"));
                case 5 -> paciente.setCorreo(
                        JOptionPane.showInputDialog("Ingrese el nuevo correo:"));
                case 6 -> paciente.setDiagnostico(
                        JOptionPane.showInputDialog("Ingrese el nuevo diagnóstico:"));
                case 7 -> paciente.setAntecedentesFamiliares(
                        JOptionPane.showInputDialog("Ingrese los nuevos antecedentes:"));
                default -> throw new ExcepcionValidacion("Opción no válida");
            }

            servicioPaciente.actualizarPaciente(paciente);
            JOptionPane.showMessageDialog(null, "Datos actualizados exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado");
        }
    }

    /**
     * Agrega una nueva entrada al historial médico de un paciente.
     * @throws ExcepcionValidacion Si el código del paciente no es válido
     */

    private void agregarHistorialMedico() throws ExcepcionValidacion {
        int codigo = Integer.parseInt(
                JOptionPane.showInputDialog("Ingrese el código del paciente:"));
        String entrada = JOptionPane.showInputDialog("Ingrese la nueva entrada del historial médico:");

        servicioPaciente.agregarHistorialMedico(codigo, entrada);
        JOptionPane.showMessageDialog(null, "Historial médico actualizado exitosamente");
    }

    /**
     * Muestra el historial médico completo de un paciente específico.
     * @throws ExcepcionValidacion Si el código del paciente no es válido
     */

    private void mostrarHistorialMedico() throws ExcepcionValidacion {
        int codigo = Integer.parseInt(
                JOptionPane.showInputDialog("Ingrese el código del paciente:"));

        Optional<Paciente> pacienteOpt = servicioPaciente.buscarPaciente(codigo);
        if (pacienteOpt.isPresent()) {
            List<String> historial = pacienteOpt.get().getHistorialMedico();
            if (historial.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El paciente no tiene historial médico registrado");
            } else {
                StringBuilder sb = new StringBuilder("Historial Médico\n================\n");
                for (int i = 0; i < historial.size(); i++) {
                    sb.append(i + 1).append(". ").append(historial.get(i)).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado");
        }
    }

    /**
     * Registra una nueva cita médica para un paciente.
     * @throws ExcepcionValidacion Si el código del paciente o el formato de fecha/hora no son válidos
     */

    private void crearCita() throws ExcepcionValidacion {
        int codigo = Integer.parseInt(
                JOptionPane.showInputDialog("Ingrese el código del paciente:"));
        String fecha = JOptionPane.showInputDialog("Ingrese la fecha de la cita (yyyy/MM/dd):");
        String hora = JOptionPane.showInputDialog("Ingrese la hora de la cita (HH:mm):");

        servicioPaciente.agregarCita(codigo, fecha, hora);
        JOptionPane.showMessageDialog(null, "Cita agendada exitosamente");
    }

    /**
     * Muestra todas las citas programadas para un paciente específico.
     * @throws ExcepcionValidacion Si el código del paciente no es válido
     */

    private void mostrarCitas() throws ExcepcionValidacion {
        int codigo = Integer.parseInt(
                JOptionPane.showInputDialog("Ingrese el código del paciente:"));

        Optional<Paciente> pacienteOpt = servicioPaciente.buscarPaciente(codigo);
        if (pacienteOpt.isPresent()) {
            List<String> citas = pacienteOpt.get().getCitas();
            if (citas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El paciente no tiene citas programadas");
            } else {
                StringBuilder sb = new StringBuilder("Citas Programadas\n================\n");
                for (int i = 0; i < citas.size(); i++) {
                    sb.append(i + 1).append(". ").append(citas.get(i)).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado");
        }
    }

    /**
     * Muestra un listado de todos los pacientes registrados en el sistema.
     * Incluye código, nombre y apellido de cada paciente.
     */

    private void mostrarTodosPacientes() {
        List<Paciente> pacientes = servicioPaciente.obtenerTodosPacientes();
        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pacientes registrados");
        } else {
            StringBuilder sb = new StringBuilder("Listado de Pacientes\n===================\n");
            for (Paciente paciente : pacientes) {
                sb.append(String.format("Código: %d - %s %s\n",
                        paciente.getCodigoPaciente(),
                        paciente.getNombre(),
                        paciente.getApellido()));
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
}