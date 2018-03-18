package loginscreen.solution.example.com.loginscreen.utils;

import android.text.Editable;
import android.util.Patterns;

/**
 * Created by daniele on 18/03/18.
 */

public class Validation {

    public static boolean validateEmail(String email){
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }
        else{
            return false;
        }

    }

    public static boolean validatePassword(String password){
        if(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{6,}$")){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean validateName(String name){
        if(name.matches("^[a-zA-Z0-9]*$")){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean validatePhone(String phone){
        if(Patterns.PHONE.matcher(phone).matches()){
            return true;
        }
        else{
            return false;
        }
    }

}
