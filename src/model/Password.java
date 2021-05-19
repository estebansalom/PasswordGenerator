package model;

public class Password {
    private int longitud = 8;
    private String password;

    public Password(){
    }

    public Password(int length){
        this.longitud = length;
    }

    public int getLongitud() {
        return longitud;
    }

    public String getPassword() {
        return password;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean esFuerte() {
        int upper = 0;
        int numbers = 0;
        int lower = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                numbers++;
            } else {
                if (Character.isLowerCase(password.charAt(i))) {
                    lower++;
                } else {
                    if (Character.isUpperCase(password.charAt(i))) {
                        upper++;
                    }
                    }
                }
            }
        if(upper>2 && lower>1 && numbers>5){
            return true;
        }else{
            return false;
        }
    }

    public void generarPassword(){
        String pass="";
        for (int i=0; i<longitud; i++){
            pass+= getRandomChar();
        }
        this.password = pass;
    }

    public char getRandomChar(){
        double asciiChar = Math.floor(Math.random()*(122-46+1)+46);
        if(asciiChar==47 || (asciiChar>57&&asciiChar<65) || (asciiChar>90&&asciiChar<97)){
            asciiChar = getRandomChar();
        }
        return (char)asciiChar;
    }

    public void generarPasswordFuerte(){
        generarPassword();
        if(!this.esFuerte()){
            generarPassword();
            generarPasswordFuerte();
        }
    }
}
