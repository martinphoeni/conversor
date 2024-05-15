import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import java.util.List;

public class GeneradorDeArchivo {
    public void guardarJson(List<Moneda> moneda) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura = new FileWriter("historial.json");
        escritura.write(gson.toJson(moneda));
        escritura.close();
    }

    public List<Moneda> cargarJson() throws IOException {
        Gson gson = new GsonBuilder().create();
        Type tipoListaMoneda = new TypeToken<List<Moneda>>() {}.getType();
        try (Reader reader = new FileReader("historial.json")) {
            return gson.fromJson(reader, tipoListaMoneda);
        }
    }

}


