import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String getApiKey(){
        Properties props = new Properties();
        try (FileInputStream key = new FileInputStream("config.properties")){
            props.load(key);
            return props.getProperty("API_KEY");
        } catch (IOException e) {
            System.err.println("Erro ao pegar a chave");
            return null;
        }
    }
}
