import java.time.LocalDate;

public class Venta {
    private int idVenta;
    private Equipo equipo;
    private int cantidadVendida;
    private double totalVenta;
    private LocalDate fechaVenta;

    public Venta(int idVenta, Equipo equipo, int cantidadVendida) {
        this.idVenta = idVenta;
        this.equipo = equipo;
        this.cantidadVendida = cantidadVendida;
        this.totalVenta = equipo.calcularPrecioFinal() * cantidadVendida;
        this.fechaVenta = LocalDate.now(); // fecha automática del sistema
    }

    public String obtenerComprobante() {
        return "--- COMPROBANTE DE VENTA ---\n" +
                "ID Venta  : " + idVenta + "\n" +
                "Fecha     : " + fechaVenta + "\n" +
                "Equipo    : " + equipo.getNombre() + "\n" +
                "Tipo      : " + equipo.getTipo() + "\n" +
                "Cantidad  : " + cantidadVendida + "\n" +
                "P. Unit.  : $" + equipo.calcularPrecioFinal() + "\n" +
                "TOTAL     : $" + totalVenta + "\n" +
                "Stock rest: " + equipo.getCantidad();
    }

    public int getIdVenta() { return idVenta; }
    public Equipo getEquipo() { return equipo; }
    public int getCantidadVendida() { return cantidadVendida; }
    public double getTotalVenta() { return totalVenta; }
    public LocalDate getFechaVenta() { return fechaVenta; }

    @Override
    public String toString() {
        return "Venta #" + idVenta + " | " + fechaVenta +
                " | " + equipo.getNombre() +
                " | Cant: " + cantidadVendida +
                " | Total: $" + totalVenta + "\n";
    }
}
