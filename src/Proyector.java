public class Proyector extends Equipo {
    private int lumenes;
    private String resolucion;

    public Proyector(int id, String nombre, double costoCompra, double precio,
                     int cantidad, int lumenes, String resolucion) {
        super(id, nombre, "Proyector", costoCompra, precio, cantidad);
        this.lumenes = lumenes;
        this.resolucion = resolucion;
    }

    @Override
    public double calcularPrecioFinal() {
        double precioFinal = getPrecio() + (getPrecio() * 0.12);
        if (resolucion.equals("4K")) {
            precioFinal = precioFinal + (getPrecio() * 0.05);
        }
        return precioFinal;
    }

    @Override
    public String obtenerFicha() {
        return "--- FICHA PROYECTOR ---\n" +
                "ID         : " + getId() + "\n" +
                "Nombre     : " + getNombre() + "\n" +
                "Lúmenes    : " + lumenes + "\n" +
                "Resolución : " + resolucion + "\n" +
                "Costo comp.: $" + getCostoCompra() + "\n" +
                "Precio vta : $" + getPrecio() + "\n" +
                "P. Final   : $" + calcularPrecioFinal() + "\n" +
                "Ganancia   : $" + calcularGanancia() + "\n" +
                "Stock      : " + getCantidad() + "\n" +
                "Ingreso    : " + getFechaIngreso() + "\n" +
                "Estado     : " + getEstado();
    }

    public int getLumenes() { return lumenes; }
    public void setLumenes(int lumenes) { this.lumenes = lumenes; }

    public String getResolucion() { return resolucion; }
    public void setResolucion(String resolucion) { this.resolucion = resolucion; }
}