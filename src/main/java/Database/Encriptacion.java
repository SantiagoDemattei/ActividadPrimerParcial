package Database;

//importar random
import java.util.Random;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Encriptacion {

    public String encriptacion(String pass){
        try{
            return Base64.getEncoder().encodeToString(pass.getBytes());
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String desencriptacion(String passEncriptada){
        try{
            byte[] bytes = Base64.getDecoder().decode(passEncriptada);
            return new String(bytes);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
