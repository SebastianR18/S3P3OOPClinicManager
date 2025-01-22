package negocio.servicio;

import negocio.excepciones.ExcepcionValidacion;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ServicioValidacion {

    // Valida que el telefono y el correo ingresados cuenten con una estructura correcta a la de estos ejemplares

    private static final Pattern PATRON_CORREO = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PATRON_TELEFONO = Pattern.compile("^\\d{10}$");

    public static void validarCorreo(String correo) throws ExcepcionValidacion {
        if (correo == null || !PATRON_CORREO.matcher(correo).matches()) {
            throw new ExcepcionValidacion("Formato de correo electrónico inválido");
        }
    }

    public static void validarTelefono(String telefono) throws ExcepcionValidacion {
        if (telefono == null || !PATRON_TELEFONO.matcher(telefono).matches()) {
            throw new ExcepcionValidacion("El teléfono debe tener 10 dígitos");
        }
    }

    public static void validarFecha(String fecha) throws ExcepcionValidacion {
        try {
            LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (DateTimeParseException e) {
            throw new ExcepcionValidacion("Formato de fecha inválido (yyyy/MM/dd)");
        }
    }

    public static void validarEdad(int edad) throws ExcepcionValidacion {
        if (edad < 0 || edad > 150) {
            throw new ExcepcionValidacion("Edad inválida");
        }
    }

    public static void validarRequerido(String valor, String nombreCampo) throws ExcepcionValidacion {
        if (valor == null || valor.trim().isEmpty()) {
            throw new ExcepcionValidacion(nombreCampo + " es requerido");
        }
    }

    public static void validarCodigoPositivo(int codigo, String nombreCampo) throws ExcepcionValidacion {
        if (codigo <= 0) {
            throw new ExcepcionValidacion(nombreCampo + " debe ser un número positivo");
        }
    }
}
