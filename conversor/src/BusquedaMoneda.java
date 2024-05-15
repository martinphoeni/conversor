import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BusquedaMoneda {
    public double buscaMoneda(String moneda, String destino){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/a65fc05bbdcb80fa951f7992/latest/"+moneda);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
           JsonObject jsonResponse= new Gson().fromJson(response.body(), JsonObject.class);
            double valor = jsonResponse.getAsJsonObject("conversion_rates").get(destino).getAsDouble();
            return valor;

        } catch (Exception e) {
            throw new RuntimeException("No se encontro la moneda para convertir verifique su conexion a internet.");
        }
    }
}
