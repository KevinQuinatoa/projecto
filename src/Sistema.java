import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    private List<Equipo> listaEquipos = new ArrayList<>();
    private List<Venta>  listaVentas  = new ArrayList<>();
    private int contadorVentas = 1;

    Scanner sc;

    public Sistema() {
        sc = new Scanner(System.in);
    }

    // ── MENÚ PRINCIPAL ─────────────────────────────────────────

    public int menu() {
        System.out.println("\n===== TECNOSTORE =====");
        System.out.println("Equipos registrados: " + listaEquipos.size() + "/" + Equipo.MAX_INVENTARIO);
        System.out.println("1. Registrar equipo");
        System.out.println("2. Mostrar precio final con IVA");
        System.out.println("3. Mostrar ficha del equipo");
        System.out.println("4. Registrar venta");
        System.out.println("5. Ver historial de ventas");
        System.out.println("6. Mostrar equipos con stock bajo");
        System.out.print(">> ");
        int opc = sc.nextInt();
        return opc;
    }

    // ── SELECCIONAR EQUIPO DE LA LISTA ─────────────────────────

    public int listaEquipos() {
        System.out.println("Seleccione un equipo:");
        int i = 0;
        for (Equipo equipo : listaEquipos) {
            System.out.println(i + ". " + equipo);
            i++;
        }
        System.out.print(">> ");
        return sc.nextInt();
    }

    // ── MENÚS DE OPCIONES VÁLIDAS ──────────────────────────────

    public String seleccionarRam() {
        int opc;
        do {
            System.out.println("Seleccione la RAM:");
            System.out.println("1. 4GB");
            System.out.println("2. 8GB");
            System.out.println("3. 16GB");
            System.out.println("4. 32GB");
            System.out.print(">> ");
            opc = sc.nextInt();
            if (opc < 1 || opc > 4)
                System.out.println("Error: opcion no valida. Intente de nuevo.");
        } while (opc < 1 || opc > 4);

        switch (opc) {
            case 1: return "4GB";
            case 2: return "8GB";
            case 3: return "16GB";
            default: return "32GB";
        }
    }

    public String seleccionarProcesador() {
        int opc;
        do {
            System.out.println("Seleccione el procesador:");
            System.out.println("1. Intel i3");
            System.out.println("2. Intel i5");
            System.out.println("3. Intel i7");
            System.out.println("4. AMD Ryzen 5");
            System.out.println("5. AMD Ryzen 7");
            System.out.print(">> ");
            opc = sc.nextInt();
            if (opc < 1 || opc > 5)
                System.out.println("Error: opcion no valida. Intente de nuevo.");
        } while (opc < 1 || opc > 5);

        switch (opc) {
            case 1: return "Intel i3";
            case 2: return "Intel i5";
            case 3: return "Intel i7";
            case 4: return "AMD Ryzen 5";
            default: return "AMD Ryzen 7";
        }
    }

    public String seleccionarTipoImpresion() {
        int opc;
        do {
            System.out.println("Seleccione el tipo de impresion:");
            System.out.println("1. laser");
            System.out.println("2. inyeccion");
            System.out.print(">> ");
            opc = sc.nextInt();
            if (opc < 1 || opc > 2)
                System.out.println("Error: opcion no valida. Intente de nuevo.");
        } while (opc < 1 || opc > 2);

        switch (opc) {
            case 1: return "laser";
            default: return "inyeccion";
        }
    }

    public String seleccionarResolucion() {
        int opc;
        do {
            System.out.println("Seleccione la resolucion:");
            System.out.println("1. HD");
            System.out.println("2. Full HD");
            System.out.println("3. 4K");
            System.out.print(">> ");
            opc = sc.nextInt();
            if (opc < 1 || opc > 3)
                System.out.println("Error: opcion no valida. Intente de nuevo.");
        } while (opc < 1 || opc > 3);

        switch (opc) {
            case 1: return "HD";
            case 2: return "Full HD";
            default: return "4K";
        }
    }

    // ── VALIDACIONES NUMÉRICAS ────────────────────────────────

    public int leerEnteroPositivo(String mensaje) {
        int valor;
        do {
            System.out.print(mensaje);
            valor = sc.nextInt();
            if (valor <= 0)
                System.out.println("Error: debe ser mayor a cero. Intente de nuevo.");
        } while (valor <= 0);
        return valor;
    }

    // RESTRICCIÓN PRESUPUESTO: precio entre $500 y $2000
    public double leerPrecio(String mensaje) {
        double valor;
        do {
            System.out.print(mensaje + " (entre $" + Equipo.PRECIO_MIN + " y $" + Equipo.PRECIO_MAX + "): ");
            valor = sc.nextDouble();
            if (valor < Equipo.PRECIO_MIN || valor > Equipo.PRECIO_MAX)
                System.out.println("Error: el precio debe estar entre $"
                        + Equipo.PRECIO_MIN + " y $" + Equipo.PRECIO_MAX + ". Intente de nuevo.");
        } while (valor < Equipo.PRECIO_MIN || valor > Equipo.PRECIO_MAX);
        return valor;
    }

    // RESTRICCIÓN: costo de compra menor al precio de venta
    public double leerCostoCompra(double precioVenta) {
        double costo;
        do {
            System.out.print("Ingrese el costo de compra: ");
            costo = sc.nextDouble();
            if (costo <= 0)
                System.out.println("  ⚠ Error: debe ser mayor a cero.");
            else if (costo >= precioVenta)
                System.out.println("Error: el costo debe ser menor al precio de venta ($" + precioVenta + ").");
        } while (costo <= 0 || costo >= precioVenta);
        return costo;
    }

    // RESTRICCIÓN TIEMPO: fecha entre 2020 y hoy
    public LocalDate leerFecha() {
        LocalDate fecha = null;
        LocalDate hoy = LocalDate.now();
        LocalDate fechaMin = LocalDate.of(Equipo.ANIO_FECHA_MIN, 1, 1);
        do {
            try {
                System.out.print("Ingrese el anio (" + Equipo.ANIO_FECHA_MIN + "-" + hoy.getYear() + "): ");
                int anio = sc.nextInt();
                System.out.print("Ingrese el mes  (1-12): ");
                int mes = sc.nextInt();
                System.out.print("Ingrese el dia  (1-31): ");
                int dia = sc.nextInt();
                fecha = LocalDate.of(anio, mes, dia);
                if (fecha.isBefore(fechaMin)) {
                    System.out.println("Error: la fecha no puede ser anterior a " + fechaMin + ".");
                    fecha = null;
                } else if (fecha.isAfter(hoy)) {
                    System.out.println("Error: la fecha no puede ser futura.");
                    fecha = null;
                }
            } catch (Exception e) {
                System.out.println("Error: fecha invalida. Intente de nuevo.");
                sc.nextInt();
                fecha = null;
            }
        } while (fecha == null);
        return fecha;
    }

    // ── REGISTRAR EQUIPO ──────────────────────────────────────

    public void crearEquipo() {
        // RESTRICCIÓN ESPACIO: máximo 20 equipos
        if (listaEquipos.size() >= Equipo.MAX_INVENTARIO) {
            System.out.println("Error: inventario lleno. Maximo " + Equipo.MAX_INVENTARIO + " equipos.");
            return;
        }

        System.out.println("Escoja un tipo de equipo");
        System.out.println("1. Computadora");
        System.out.println("2. Impresora");
        System.out.println("3. Proyector");
        System.out.print(">> ");
        int opc = sc.nextInt();

        System.out.print("Ingrese el ID (ej: PC-01): ");
        String id = sc.next();

        System.out.print("Ingrese el nombre: ");
        String nombre = sc.next();

        // RESTRICCIÓN PRESUPUESTO
        double precio = leerPrecio("Ingrese el precio de venta");

        // RESTRICCIÓN: costo < precio de venta
        double costoCompra = leerCostoCompra(precio);

        int cantidad = leerEnteroPositivo("Ingrese la cantidad en stock: ");

        switch (opc) {
            case 1:
                String ram = seleccionarRam();
                String procesador = seleccionarProcesador();
                listaEquipos.add(new Computadora(id, nombre, costoCompra, precio, cantidad, ram, procesador));
                System.out.println("Computadora registrada correctamente.");
                break;
            case 2:
                String tipoImpresion = seleccionarTipoImpresion();
                System.out.print("¿Toner incluido? (true/false): ");
                boolean toner = sc.nextBoolean();
                listaEquipos.add(new Impresora(id, nombre, costoCompra, precio, cantidad, tipoImpresion, toner));
                System.out.println("Impresora registrada correctamente.");
                break;
            case 3:
                int lumenes = leerEnteroPositivo("Ingrese los lumenes: ");
                String resolucion = seleccionarResolucion();
                listaEquipos.add(new Proyector(id, nombre, costoCompra, precio, cantidad, lumenes, resolucion));
                System.out.println("Proyector registrado correctamente.");
                break;
            default:
                System.out.println("Opcion no valida.");
                break;
        }
    }

    // ── PRECIO FINAL ──────────────────────────────────────────

    public void mostrarPrecioFinal() {
        if (listaEquipos.isEmpty()) { System.out.println("No hay equipos registrados."); return; }
        int indice = listaEquipos();
        Equipo equipo = listaEquipos.get(indice);
        System.out.println("El precio final de: " + equipo + "con IVA es: $" + equipo.calcularPrecioFinal());
    }

    // ── FICHA ─────────────────────────────────────────────────

    public void mostrarFicha() {
        if (listaEquipos.isEmpty()) { System.out.println("No hay equipos registrados."); return; }
        int indice = listaEquipos();
        System.out.println(listaEquipos.get(indice).obtenerFicha());
    }

    // ── VENTAS ────────────────────────────────────────────────

    public void registrarVenta() {
        if (listaEquipos.isEmpty()) { System.out.println("No hay equipos registrados."); return; }

        int indice = listaEquipos();
        Equipo equipo = listaEquipos.get(indice);

        if (equipo.getEstado().equals("agotado")) {
            System.out.println("Error: este equipo esta agotado, no se puede vender.");
            return;
        }

        System.out.println("Stock disponible: " + equipo.getCantidad());
        int cantidadVenta = leerEnteroPositivo("Ingrese la cantidad a vender: ");

        if (cantidadVenta > equipo.getCantidad()) {
            System.out.println("Error: stock insuficiente. Solo hay "
                    + equipo.getCantidad() + " unidades.");
            return;
        }

        equipo.setCantidad(equipo.getCantidad() - cantidadVenta);
        Venta venta = new Venta(contadorVentas, equipo, cantidadVenta);
        listaVentas.add(venta);
        contadorVentas++;

        System.out.println(venta.obtenerComprobante());

        if (equipo.getEstado().equals("agotado"))
            System.out.println("Aviso: el equipo quedo sin stock.");
        else if (equipo.stockBajo())
            System.out.println("Aviso: stock bajo (menos de " + Equipo.STOCK_MINIMO + " unidades).");
    }

    // ── HISTORIAL DE VENTAS ───────────────────────────────────

    public void mostrarHistorialVentas() {
        if (listaVentas.isEmpty()) { System.out.println("No hay ventas registradas."); return; }
        System.out.println("\n--- HISTORIAL DE VENTAS ---");
        double totalGeneral = 0;
        for (Venta venta : listaVentas) {
            System.out.println(venta);
            totalGeneral += venta.getTotalVenta();
        }
        System.out.println("Total acumulado vendido: $" + totalGeneral);
    }

    // ── STOCK BAJO ────────────────────────────────────────────

    public void mostrarStockBajo() {
        System.out.println("\n--- Equipos con stock bajo ---");
        boolean hayAlguno = false;
        for (Equipo equipo : listaEquipos) {
            if (equipo.stockBajo()) {
                System.out.println(equipo);
                hayAlguno = true;
            }
        }
        if (!hayAlguno)
            System.out.println("Todos los equipos tienen stock suficiente.");
    }
}