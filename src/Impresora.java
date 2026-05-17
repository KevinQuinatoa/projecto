public class Impresora extends Equipo {
    private String tipoImpresion;
    private boolean tonerIncluido;

    public Impresora(int id, String nombre, double costoCompra, double precio,
                     int cantidad, String tipoImpresion, boolean tonerIncluido) {
        super(id, nombre, "Impresora", costoCompra, precio, cantidad);
        this.tipoImpresion = tipoImpresion;
        this.tonerIncluido = tonerIncluido;
    }

    @Override
    public double calcularPrecioFinal() {
        double precioFinal = getPrecio() + (getPrecio() * 0.12);
        if (tonerIncluido) {
            precioFinal = precioFinal + 15;
        }
        return precioFinal;
    }

    @Override
    public String obtenerFicha() {
        return "--- FICHA IMPRESORA ---\n" +
                "ID         : " + getId() + "\n" +
                "Nombre     : " + getNombre() + "\n" +
                "Tipo       : " + tipoImpresion + "\n" +
                "Tóner inc. : " + (tonerIncluido ? "Si" : "No") + "\n" +
                "Costo comp.: $" + getCostoCompra() + "\n" +
                "Precio vta : $" + getPrecio() + "\n" +
                "P. Final   : $" + calcularPrecioFinal() + "\n" +
                "Ganancia   : $" + calcularGanancia() + "\n" +
                "Stock      : " + getCantidad() + "\n" +
                "Ingreso    : " + getFechaIngreso() + "\n" +
                "Estado     : " + getEstado();
    }

    public String getTipoImpresion() { return tipoImpresion; }
    public void setTipoImpresion(String tipoImpresion) { this.tipoImpresion = tipoImpresion; }

    public boolean isTonerIncluido() { return tonerIncluido; }
    public void setTonerIncluido(boolean tonerIncluido) { this.tonerIncluido = tonerIncluido; }
}