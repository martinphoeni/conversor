import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner lectura = new Scanner(System.in);
        int numerosOpcion;
        double monto;
        double resultado;
        double cambio;
        BusquedaMoneda consulta = new BusquedaMoneda();
        GeneradorDeArchivo generador = new GeneradorDeArchivo();
        List<Moneda> consultas = new ArrayList<>();
        String menu = "***************************\n" +
                "Bienvenido al conversor de moneda\n\n" +
                "Seleccione su opción de conversión:\n" +
                "1. Dólar => Peso Argentino\n" +
                "2. Peso Argentino => Dólar\n" +
                "3. Dólar => Real Brasileño\n" +
                "4. Real Brasileño => Dólar\n" +
                "5. Dólar => Peso Colombiano\n" +
                "6. Peso Colombiano => Dólar\n" +
                "7. Historial de consultas en esta sesion\n" +
                "8. Registro de sesion anterior \n" +
                "9. Salir";

        do {
            System.out.println(menu);
            numerosOpcion = lectura.nextInt();

            switch (numerosOpcion) {
                case 1:

                    cambio = Double.parseDouble(String.valueOf(consulta.buscaMoneda("USD", "ARS")));
                    System.out.println("Ingrese cantidad de dólares:");
                    monto = lectura.nextInt();
                    resultado = monto * cambio;
                    System.out.println(resultado + " ARS");
                    consultas.add(new Moneda("Dolares", "Pesos argentinos", monto, resultado));

                    break;
                case 2:
                    cambio = Double.parseDouble(String.valueOf(consulta.buscaMoneda("ARS", "USD")));
                    System.out.println("Ingrese cantidad de Pesos:");
                    monto = lectura.nextInt();
                    resultado = monto * cambio;
                    System.out.println(resultado + " USD");
                    consultas.add(new Moneda("Pesos Argentinos", "Dolares", monto, resultado));
                    break;
                case 3:
                    cambio = Double.parseDouble(String.valueOf(consulta.buscaMoneda("USD", "BRL")));
                    System.out.println("Ingrese cantidad de Dolares:");
                    monto = lectura.nextInt();
                    resultado = monto * cambio;
                    System.out.println(resultado + " BRL");
                    consultas.add(new Moneda("Dolares", "Reales", monto, resultado));
                    break;
                case 4:
                    cambio = Double.parseDouble(String.valueOf(consulta.buscaMoneda("BRL", "USD")));
                    System.out.println("Ingrese cantidad de Reales:");
                    monto = lectura.nextInt();
                    resultado = monto * cambio;
                    System.out.println(resultado+ " USD");
                    consultas.add(new Moneda("Reales", "Dolares", monto, resultado));
                    break;
                case 5:
                    cambio = Double.parseDouble(String.valueOf(consulta.buscaMoneda("USD", "COP")));
                    System.out.println("Ingrese cantidad de Dolares:");
                    monto = lectura.nextInt();
                    resultado = monto * cambio;
                    System.out.println(resultado+ " COP");
                    consultas.add(new Moneda("Dolares", "Pesos Colombianos", monto, resultado));
                    break;
                case 6:
                    cambio = Double.parseDouble(String.valueOf(consulta.buscaMoneda("COP", "USD")));
                    System.out.println("Ingrese cantidad de Pesos Colombianos:");
                    monto = lectura.nextInt();
                    resultado = monto * cambio;
                    System.out.println(resultado + " USD");
                    consultas.add(new Moneda("Pesos Colombianos", "Dolares", monto, resultado));
                    break;
                case 7:
                    System.out.println("Consultas realizadas en esta seccion:");
                    for (Moneda moneda : consultas) {
                        System.out.println("monto de origen " + moneda.monto() + " " +
                                moneda.moneda() +
                                ", convertido a " + moneda.destino() +
                                " = " + moneda.resultado());
                    }
                    break;
                case 8:
                    try {
                        List<Moneda> historial = generador.cargarJson();
                        System.out.println("Historial de consultas:");
                        for (Moneda moneda : historial) {
                            System.out.println("monto de origen " + moneda.monto() + " " +
                                    moneda.moneda() +
                                    ", convertido a " + moneda.destino() +
                                    " = " + moneda.resultado());

                        }
                    } catch (IOException e) {
                        System.out.println("Error al cargar el historial de consultas desde el archivo JSON.");
                        e.printStackTrace();
                    }
                    break;
                case 9:

                    try {


                        generador.guardarJson(consultas);


                    } catch (IOException e) {
                        System.out.println("Error al guardar los resultados en el archivo JSON.");
                        e.printStackTrace();
                    }
                    System.out.println("Gracias por utilizar nuestro conversor de moneda.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
            // Pausa de 3 segundos
            try {
                Thread.sleep(2000); // 3000 milisegundos = 3 segundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } while (numerosOpcion != 9);

    }

}