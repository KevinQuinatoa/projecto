import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sistema sis = new Sistema();
        Scanner sc = new Scanner(System.in);
        int opc1 = 0, opc2 = 0;

        do {
            opc1 = sis.menu();

            switch (opc1) {
                case 1:
                    sis.crearEquipo();
                    break;
                case 2:
                    sis.mostrarPrecioFinal();
                    break;
                case 3:
                    sis.mostrarFicha();
                    break;
                case 4:
                    sis.registrarVenta();
                    break;
                case 5:
                    sis.mostrarHistorialVentas();
                    break;
                case 6:
                    sis.mostrarStockBajo();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

            System.out.println("Desea seleccionar otra opcion: ");
            System.out.print("1.Si / 2.No: ");
            opc2 = sc.nextInt();

        } while (opc2 == 1);

        System.out.println("Hasta luego.");
    }
}