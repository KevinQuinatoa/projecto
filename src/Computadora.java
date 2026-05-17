public class Computadora extends Equipo {
    private String ram;
    private String procesador;

    public Computadora(int id, String nombre, double costoCompra, double precio,
                       int cantidad, String ram, String procesador) {
        super(id, nombre, "Computadora", costoCompra, precio, cantidad);
        this.ram = ram;
        this.procesador = procesador;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecio() + (getPrecio() * 0.12);
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
                "Ingreso    : " + getFechaIngreso() + "\n" +
                "Estado     : " + getEstado();
    }

    public String getRam() { return ram; }
    public void setRam(String ram) { this.ram = ram; }

    public String getProcesador() { return procesador; }
    public void setProcesador(String procesador) { this.procesador = procesador; }
}