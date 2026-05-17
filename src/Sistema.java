import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    // Restricción: máximo 50 productos en inventario
    private List<Equipo> listaEquipos = new ArrayList<>();

    // Historial de ventas realizadas
    private List<Venta> listaVentas = new ArrayList<>();

    // Contador para IDs de venta
    private int contadorVentas = 1;

    Scanner sc;

    public Sistema() {
        sc = new Scanner(System.in);
    }

    // ── MENÚ ───────────────────────────────────────────────────

    public int menu() {
        System.out.println("\n===== TECNOSTORE =====");
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

    // ── SELECCIONAR DE LA LISTA ────────────────────────────────

    public int seleccionarEquipo() {
        System.out.println("Seleccione un equipo:");
        int i = 0;
        for (Equipo equipo : listaEquipos) {
            System.out.println(i + ". " + equipo);
            i++;
        }
        System.out.print(">> ");
        int opc = sc.nextInt();
        return opc;
    }

    // ── VALIDACIONES ──────────────────────────────────────────

    public double leerPositivo(String mensaje) {
        double valor;
        do {
            System.out.print(mensaje);
            valor = sc.nextDouble();
            if (valor <= 0) {
                System.out.println("  ⚠ Error: el valor no puede ser cero ni negativo. Intente de nuevo.");
            }
        } while (valor <= 0);
        return valor;
    }

    public int leerEnteroPositivo(String mensaje) {
        int valor;
        do {
            System.out.print(mensaje);
            valor = sc.nextInt();
            if (valor <= 0) {
                System.out.println("  ⚠ Error: el valor no puede ser cero ni negativo. Intente de nuevo.");
            }
        } while (valor <= 0);
        return valor;
    }

    // ── REGISTRAR EQUIPO ──────────────────────────────────────

    public void crearEquipo() {
        // Restricción: máximo 50 productos
        if (listaEquipos.size() >= Equipo.MAX_INVENTARIO) {
            System.out.println("  ⚠ Error: el inventario está lleno. Máximo " + Equipo.MAX_INVENTARIO + " productos.");
            return;
        }

        System.out.println("Escoja un tipo de equipo");
        System.out.println("1. Computadora");
        System.out.println("2. Impresora");
        System.out.println("3. Proyector");
        System.out.print(">> ");
        int opc = sc.nextInt();

        int id = leerEnteroPositivo("Ingrese el ID: ");
        sc.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();

        double costoCompra = leerPositivo("Ingrese el costo de compra: ");

        // Restricción: precio de venta debe ser mayor al costo de compra
        double precio;
        do {
            precio = leerPositivo("Ingrese el precio de venta: ");
            if (precio <= costoCompra) {
                System.out.println("  ⚠ Error: el precio de venta debe ser mayor al costo de compra ($" + costoCompra + ").");
            }
        } while (precio <= costoCompra);

        int cantidad = leerEnteroPositivo("Ingrese la cantidad en stock: ");
        sc.nextLine();

        switch (opc) {
            case 1:
                System.out.print("Ingrese la RAM: ");
                String ram = sc.nextLine();
                System.out.print("Ingrese el procesador: ");
                String procesador = sc.nextLine();
                Computadora computadora = new Computadora(id, nombre, costoCompra, precio, cantidad, ram, procesador);
                listaEquipos.add(computadora);
                System.out.println("✔ Computadora registrada correctamente.");
                break;
            case 2:
                System.out.print("Ingrese el tipo de impresion (laser/inyeccion): ");
                String tipoImpresion = sc.nextLine();
                System.out.print("¿Toner incluido? (true/false): ");
                boolean toner = sc.nextBoolean();
                Impresora impresora = new Impresora(id, nombre, costoCompra, precio, cantidad, tipoImpresion, toner);
                listaEquipos.add(impresora);
                System.out.println("✔ Impresora registrada correctamente.");
                break;
            case 3:
                int lumenes = leerEnteroPositivo("Ingrese los lumenes: ");
                sc.nextLine();
                System.out.print("Ingrese la resolucion (HD/Full HD/4K): ");
                String resolucion = sc.nextLine();
                Proyector proyector = new Proyector(id, nombre, costoCompra, precio, cantidad, lumenes, resolucion);
                listaEquipos.add(proyector);
                System.out.println("✔ Proyector registrado correctamente.");
                break;
            default:
                System.out.println("Opcion no valida.");
                break;
        }
    }

    // ── PRECIO FINAL ──────────────────────────────────────────

    public void mostrarPrecioFinal() {
        if (listaEquipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }
        int indice = seleccionarEquipo();
        Equipo equipo = listaEquipos.get(indice);
        System.out.println("El precio final de: " + equipo +
                "con IVA es: $" + equipo.calcularPrecioFinal());
    }

    // ── FICHA ─────────────────────────────────────────────────

    public void mostrarFicha() {
        if (listaEquipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }
        int indice = seleccionarEquipo();
        Equipo equipo = listaEquipos.get(indice);
        System.out.println(equipo.obtenerFicha());
    }

    // ── VENTAS ────────────────────────────────────────────────

    public void registrarVenta() {
        if (listaEquipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }

        int indice = seleccionarEquipo();
        Equipo equipo = listaEquipos.get(indice);

        if (equipo.getEstado().equals("agotado")) {
            System.out.println("  ⚠ Error: este equipo está agotado, no se puede vender.");
            return;
        }

        System.out.println("Stock disponible: " + equipo.getCantidad());
        int cantidadVenta = leerEnteroPositivo("Ingrese la cantidad a vender: ");

        // Restricción: no vender más de lo que hay en stock
        if (cantidadVenta > equipo.getCantidad()) {
            System.out.println("  ⚠ Error: no hay suficiente stock. Solo hay "
                    + equipo.getCantidad() + " unidades disponibles.");
            return;
        }

        // Descontar stock y registrar venta
        equipo.setCantidad(equipo.getCantidad() - cantidadVenta);
        Venta venta = new Venta(contadorVentas, equipo, cantidadVenta);
        listaVentas.add(venta);
        contadorVentas++;

        System.out.println(venta.obtenerComprobante());

        if (equipo.getEstado().equals("agotado")) {
            System.out.println("  ⚠ Aviso: el equipo quedó sin stock.");
        } else if (equipo.stockBajo()) {
            System.out.println("  ⚠ Aviso: el equipo tiene stock bajo.");
        }
    }

    // ── HISTORIAL DE VENTAS ───────────────────────────────────

    public void mostrarHistorialVentas() {
        if (listaVentas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        System.out.println("\n--- HISTORIAL DE VENTAS ---");
        double totalGeneral = 0;
        for (Venta venta : listaVentas) {
            System.out.println(venta);
            totalGeneral += venta.getTotalVenta();
        }
        System.out.println("Total vendido: $" + totalGeneral);
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
        if (!hayAlguno) {
            System.out.println("Todos los equipos tienen stock suficiente.");
        }
    }
}