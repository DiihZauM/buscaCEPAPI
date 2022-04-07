import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {


    public static void main(String[] args) {


        String localidades = JOptionPane.showInputDialog("insira um cep");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder().uri(URI.create("https://brasilapi.com.br/api/cep/v1/" + localidades)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(Main::parse)
                .join();

    }


    public static String parse(String responseBody) {

            JSONObject localidade = new JSONObject(responseBody);
            String cep = localidade.getString("cep");
            String estado = localidade.getString("state");
            String cidade = localidade.getString("city");
            String bairro = localidade.getString("neighborhood");
            String rua = localidade.getString("street");
            String entregadora = localidade.getString("service");
            System.out.println(cep);
            System.out.println(estado);
            System.out.println(cidade);
            System.out.println(bairro);
            System.out.println(rua);
            System.out.println(entregadora);


        return null;

    }
}

