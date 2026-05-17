import java.time.LocalDate;

public abstract class Equipo {
    private int id;
    private String nombre;
    private String tipo;
    private double costoCompra;   // precio al que la tienda lo compró
    private double precio;        // precio de venta al público
    private int cantidad;
    private String estado;
    private LocalDate fechaIngreso; // fecha en que se registró en inventario

    // Restricción: máximo 50 productos en inventario (se controla en Sistema)
    public static final int MAX_INVENTARIO = 50;
    // Restricción: precio de venta siempre mayor al costo de compra
    // Restricción: stock mínimo para alerta = 3 unidades

    public Equipo(int id, String nombre, String tipo,
                  double costoCompra, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.costoCompra = costoCompra;
        this.precio = precio;
        this.cantidad = cantidad;
        this.estado = (cantidad > 0) ? "disponible" : "agotado";
        this.fechaIngreso = LocalDate.now(); // se registra la fecha automáticamente
    }

    public abstract double calcularPrecioFinal();
    public abstract String obtenerFicha();

    // Calcula la ganancia por unidad
    public double calcularGanancia() {
        return calcularPrecioFinal() - costoCompra;
    }

    public boolean stockBajo() {
        return cantidad < 3;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getCostoCompra() { return costoCompra; }
    public void setCostoCompra(double costoCompra) { this.costoCompra = costoCompra; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.estado = (cantidad > 0) ? "disponible" : "agotado";
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }

    @Override
    public String toString() {
        return "Tipo: " + tipo + " | Nombre: " + nombre +
                " | Precio: $" + precio + " | Stock: " + cantidad + "\n";
    }
}