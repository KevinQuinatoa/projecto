import java.time.LocalDate;

public class Computadora extends Equipo {
    private String ram;
    private String procesador;

    public Computadora(String id, String nombre, double costoCompra, double precio,
                       int cantidad, LocalDate fechaIngreso, String ram, String procesador) {
        super(id, nombre, "Computadora", costoCompra, precio, cantidad, fechaIngreso);
        this.ram = ram;
        this.procesador = procesador;
    }

    @Override
    public double calcularPrecioFinal() {
        double precioFinal = getPrecio() + (getPrecio() * 0.12);
        return precioFinal;
    }

    @Override
    public String obtenerFicha() {
        return "--- FICHA COMPUTADORA ---\n" +
                "ID         : " + getId() + "\n" +
                "Nombre     : " + getNombre() + "\n" +
                "RAM        : " + ram + "\n" +
                "Procesador : " + procesador + "\n" +
                "Costo comp.: $" + getCostoCompra() + "\n" +
                "Precio vta : $" + getPrecio() + "\n" +
                "P. Final   : $" + calcularPrecioFinal() + "\n" +
                "Ganancia   : $" + calcularGanancia() + "\n" +
                "Stock      : " + getCantidad() + "\n" +
                "Fec. ingres: " + getFechaIngreso() + "\n" +
                "Estado     : " + getEstado();
    }

    public String getRam() {
        return ram;
    }
    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getProcesador() {
        return procesador;
    }
    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }
}