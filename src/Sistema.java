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
        System.out.println("Equipos registrados: " + listaEquipos.size() + "/20");
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
                System.out.println("Opcion no valida. Intente de nuevo.");
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
                System.out.println("Opcion no valida. Intente de nuevo.");
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
                System.out.println("Opcion no valida. Intente de nuevo.");
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
                System.out.println("Opcion no valida. Intente de nuevo.");
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

    public double leerPrecioComputadora() {
        double valor;
        do {
            System.out.print("Ingrese el precio de venta (entre $500 y $2000): ");
            valor = sc.nextDouble();
            if (valor < 500 || valor > 2000)
                System.out.println("Error: el precio debe estar entre $500 y $2000. Intente de nuevo.");
        } while (valor < 500 || valor > 2000);
        return valor;
    }

    public double leerPrecio() {
        double valor;
        do {
            System.out.print("Ingrese el precio de venta (entre $20 y $2000): ");
            valor = sc.nextDouble();
            if (valor < 20 || valor > 2000)
                System.out.println("Error: el precio debe estar entre $20 y $2000. Intente de nuevo.");
        } while (valor < 20 || valor > 2000);
        return valor;
    }

    public double leerCostoCompra(double precioVenta) {
        double costo;
        do {
            System.out.print("Ingrese el costo de compra: ");
            costo = sc.nextDouble();
            if (costo <= 0)
                System.out.println("Error: debe ser mayor a cero.");
            else if (costo >= precioVenta)
                System.out.println("Error: el costo debe ser menor al precio de venta ($" + precioVenta + ").");
        } while (costo <= 0 || costo >= precioVenta);
        return costo;
    }

    public LocalDate leerFecha() {
        int anio, mes, dia;
        int anioActual = LocalDate.now().getYear();

        // RESTRICCIÓN TIEMPO: año entre 2020 y el año actual
        do {
            System.out.print("Ingrese el anio de ingreso (2020-" + anioActual + "): ");
            anio = sc.nextInt();
            if (anio < 2020 || anio > anioActual)
                System.out.println("Error: el anio debe estar entre 2020 y " + anioActual + ". Intente de nuevo.");
        } while (anio < 2020 || anio > anioActual);

        // mes entre 1 y 12
        do {
            System.out.print("Ingrese el mes (1-12): ");
            mes = sc.nextInt();
            if (mes < 1 || mes > 12)
                System.out.println("Error: el mes debe estar entre 1 y 12. Intente de nuevo.");
        } while (mes < 1 || mes > 12);

        // dia entre 1 y 31
        do {
            System.out.print("Ingrese el dia (1-31): ");
            dia = sc.nextInt();
            if (dia < 1 || dia > 31)
                System.out.println("Error: el dia debe estar entre 1 y 31. Intente de nuevo.");
        } while (dia < 1 || dia > 31);

        return LocalDate.of(anio, mes, dia);
    }

    // ── REGISTRAR EQUIPO ──────────────────────────────────────

    public void crearEquipo() {
        if (listaEquipos.size() >= 20) {
            System.out.println("Error: inventario lleno. Maximo 20 equipos.");
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
        sc.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();
        double precio;
        if (opc==1){
            precio = leerPrecioComputadora();
        }else {
            precio = leerPrecio();
        }
        double costoCompra = leerCostoCompra(precio);
        int cantidad = leerEnteroPositivo("Ingrese la cantidad en stock: ");

        System.out.println("Ingrese la fecha de ingreso del equipo:");
        LocalDate fechaIngreso = leerFecha();

        switch (opc) {
            case 1:
                String ram = seleccionarRam();
                String procesador = seleccionarProcesador();
                listaEquipos.add(new Computadora(id, nombre, costoCompra,
                        precio, cantidad, fechaIngreso, ram, procesador));
                System.out.println("Computadora registrada correctamente.");
                break;
            case 2:
                String tipoImpresion = seleccionarTipoImpresion();
                System.out.print("¿Toner incluido? (true/false): ");
                boolean toner = sc.nextBoolean();
                listaEquipos.add(new Impresora(id, nombre, costoCompra,
                        precio, cantidad, fechaIngreso, tipoImpresion, toner));
                System.out.println("Impresora registrada correctamente.");
                break;
            case 3:
                int lumenes = leerEnteroPositivo("Ingrese los lumenes: ");
                String resolucion = seleccionarResolucion();
                listaEquipos.add(new Proyector(id, nombre, costoCompra,
                        precio, cantidad, fechaIngreso, lumenes, resolucion));
                System.out.println("Proyector registrado correctamente.");
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
        int indice = listaEquipos();
        Equipo equipo = listaEquipos.get(indice);
        System.out.println("El precio final de: " + equipo + "con IVA es: $" + equipo.calcularPrecioFinal());
    }

    // ── FICHA ─────────────────────────────────────────────────

    public void mostrarFicha() {
        if (listaEquipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }
        int indice = listaEquipos();
        System.out.println(listaEquipos.get(indice).obtenerFicha());
    }

    // ── VENTAS ────────────────────────────────────────────────

    public void registrarVenta() {
        if (listaEquipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }

        int indice = listaEquipos();
        Equipo equipo = listaEquipos.get(indice);

        if (equipo.getEstado().equals("agotado")) {
            System.out.println("Error: este equipo esta agotado, no se puede vender.");
            return;
        }

        System.out.println("Stock disponible: " + equipo.getCantidad());
        int cantidadVenta = leerEnteroPositivo("Ingrese la cantidad a vender: ");

        if (cantidadVenta > equipo.getCantidad()) {
            System.out.println("Error: stock insuficiente. Solo hay " + equipo.getCantidad() + " unidades.");
            return;
        }

        equipo.setCantidad(equipo.getCantidad() - cantidadVenta);
        Venta venta = new Venta(contadorVentas, equipo, cantidadVenta);
        listaVentas.add(venta);
        contadorVentas++;

        System.out.println(venta.obtenerComprobante());

        if (equipo.getEstado().equals("agotado")) {
            System.out.println("Aviso: el equipo quedo sin stock.");
        } else if (equipo.stockBajo()) {
            System.out.println("Aviso: stock bajo (menos de 3 unidades).");
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
        if (!hayAlguno) {
            System.out.println("Todos los equipos tienen stock suficiente.");
        }
        System.out.println("¿Desea aumentar el stock de un equipo?");
        System.out.print("1.Si / 2.No: ");
        int opc = sc.nextInt();

        if (opc == 1) {
            int indice = listaEquipos();
            Equipo equipo = listaEquipos.get(indice);
            int cantidad = leerEnteroPositivo("Ingrese la cantidad a agregar: ");
            equipo.setCantidad(equipo.getCantidad() + cantidad);
            System.out.println("Stock actualizado. Nuevo stock de "
                    + equipo.getNombre() + ": " + equipo.getCantidad());
        }
    }

}