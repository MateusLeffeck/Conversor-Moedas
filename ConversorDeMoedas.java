import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ConversorDeMoedas {
    private static final String API_KEY = Config.getApiKey();
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";


    public static void start(String[] args){
        Scanner scanner = new Scanner(System.in);

        String[] moedasSuportadas = {"USD", "BRL", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "ARS"};
        System.out.println("Moedas suportadas:");
        for (String m : moedasSuportadas) {
            System.out.print(m + "  ");
        }
        // Recebe os valores (melhorar essa entrada)
        System.out.println("\nDigite a moeda de origem");
        String moedaOrigem = scanner.nextLine().toUpperCase();

        System.out.println("Digite a moeda de destino");
        String moedaDestino = scanner.nextLine().toUpperCase();

        System.out.println("Digite o valor a ser convertido");
        double valor = scanner.nextDouble();

        // Aqui chama a API
        double resultado = obterTaxaCambio(moedaOrigem.toUpperCase(), moedaDestino.toUpperCase(), valor);
        
        if (resultado != -1) {
            System.out.printf("%.2f %s equivalem a %.2f %s%n", valor, moedaOrigem.toUpperCase(), resultado, moedaDestino.toUpperCase());
        } else {
            System.out.println("Erro ao obter taxa de câmbio.");
        }
        scanner.close();
    }


    public static double obterTaxaCambio(String moedaOrigem, String moedaDestino, double valor){
        try {
            String urlString = API_URL + moedaOrigem;
            URL url = new URI(urlString).toURL();
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.connect();

            BufferedReader input = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String inputResposta;
            StringBuilder retorno = new StringBuilder();
            while ((inputResposta = input.readLine()) != null) {
                retorno.append(inputResposta);
            }

            input.close();

            // Analisar o JSON
            JsonObject JsonObject = JsonParser.parseString(retorno.toString()).getAsJsonObject();
            JsonObject taxasConversao = JsonObject.getAsJsonObject("conversion_rates");
            
            if(!taxasConversao.has(moedaOrigem.toUpperCase())){
                System.out.println("Moeda de origem inexistente");
                return -1;
            }

            if(!taxasConversao.has(moedaDestino.toUpperCase())){
                System.out.println("Moeda de destino inexistente");
                return -1;
            }

            double taxa = taxasConversao.get(moedaDestino.toUpperCase()).getAsDouble();
            return taxa * valor;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        
    }
}
