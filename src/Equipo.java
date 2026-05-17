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

    public Equipo(String id, String nombre, String tipo,
                  double costoCompra, double precio, int cantidad,
                  LocalDate fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.costoCompra = costoCompra;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaIngreso = fechaIngreso;
        if (cantidad > 0) {
            this.estado = "disponible";
        } else {
            this.estado = "agotado";
        }
    }

    public abstract double calcularPrecioFinal();

    public abstract String obtenerFicha();

    public double calcularGanancia() {
        return calcularPrecioFinal() - costoCompra;
    }

    public boolean stockBajo() {
        return cantidad < 3;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCostoCompra() {
        return costoCompra;
    }
    public void setCostoCompra(double costoCompra) {
        this.costoCompra = costoCompra;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        if (cantidad > 0) {
            this.estado = "disponible";
        } else {
            this.estado = "agotado";
        }
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + " | ID: " + id + " | Nombre: " + nombre +
                " | Precio: $" + precio + " | Stock: " + cantidad + "\n";
    }
}