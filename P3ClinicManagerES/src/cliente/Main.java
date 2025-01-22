package cliente;

import negocio.servicio.ServicioPaciente;

public class Main {
    public static void main(String[] args) {
        ServicioPaciente servicioPaciente = new ServicioPaciente();
        InterfazPaciente interfazPaciente = new InterfazPaciente(servicioPaciente);
        interfazPaciente.mostrarMenuPrincipal();
    }
}