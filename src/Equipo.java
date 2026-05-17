import java.time.LocalDate;

public abstract class Equipo {
    private String id;
    private String nombre;
    private String tipo;
    private double costoCompra;
    private double precio;
    private int cantidad;
    private String estado;
    private LocalDate fechaIngreso;

    // ── RESTRICCIONES DEL SISTEMA ─────────────────────────────
    public static final int    MAX_INVENTARIO   = 20;      // espacio: máximo 20 equipos
    public static final double PRECIO_MIN       = 500.0;   // presupuesto mínimo por equipo
    public static final double PRECIO_MAX       = 2000.0;  // presupuesto máximo por equipo
    public static final int    ANIO_FECHA_MIN   = 2020;    // tiempo: fecha de ingreso desde 2020
    public static final int    STOCK_MINIMO     = 3;       // alerta de stock bajo

    public Equipo(String id, String nombre, String tipo,
                  double costoCompra, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.costoCompra = costoCompra;
        this.precio = precio;
        this.cantidad = cantidad;
        this.estado = (cantidad > 0) ? "disponible" : "agotado";
        this.fechaIngreso = LocalDate.now();
    }

    public abstract double calcularPrecioFinal();
    public abstract String obtenerFicha();

    public double calcularGanancia() {
        return calcularPrecioFinal() - costoCompra;
    }

    public boolean stockBajo() {
        return cantidad < STOCK_MINIMO;
    }

    // Getters y Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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