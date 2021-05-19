package ui;

import model.Password;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static PrintStream out = System.out;
    public static void main(String[] args)throws IOException{
        int opc=1;
        while(opc!=0){
            mostrarOpciones();
            opc = readInt("el número de opción a ejecutar");
            ejecutar(opc);
        }
    }

    public static void mostrarOpciones(){
        print("");
        print("Menú:");
        print("1. Ordenar datos");
        print("2. Generar Contraseña");
        print("3. Generar Contraseña Segura");
        print("0. Salir");
        print("");
    }

    public static void ejecutar(int opc)throws IOException{
        switch (opc){
            case 1:
                ordenador();
                break;

            case 2:
                password();
                break;

            case 3:
                strongPassword();
                break;

            case 0:
                print("Hasta luego!");
                break;

            default:
                print("");
                print("Opción no valida");
                print("");
                break;
        }
    }

    public static void ordenador()throws IOException{
        int[] data;
        data = readIntArray(5);
        print("Arreglo Original:");
        printArray(data);
        print("");
        print("Arreglo Ordenado:");
        printArray(burbuja(data));
    }

    public static void password()throws IOException{
        Password p = new Password();
        if(hasCustomLength()){
            p = new Password(readInt("la longitud de la contraseña"));
        }

        p.generarPassword();
        print("");
        print("Contraseña: " + p.getPassword());
        if(!p.esFuerte()){
            print("Cuidado: La contraseña podría ser más segura!");
        }else{
            print("Excelente: La contraseña es segura!");
        }
    }

    public static void strongPassword()throws IOException{
        int length = getValidLength();
        Password p = new Password(length);
        p.generarPasswordFuerte();
        print("");
        print("Contraseña: " + p.getPassword());
    }

    public static int getValidLength()throws IOException{
        int length = readInt("la longitud de la contraseña");
        if(length<=10){
            print("Longitud no válida, debe ser mayor que 10.");
            length = getValidLength();
        }
        return length;
    }

    public static boolean hasCustomLength()throws IOException{
        print("Desea utilizar una longitud especifíca? (La predeterminada es de 8 carácteres)");
        print("1. Sí");
        print("2. No");
        int opc = readInt("una opción");
        if(opc!=1){
            return false;
        }else{
            return true;
        }
    }

    public static void print(String p){
        out.println(p);
    }

    public static void printArray(int[] data){
        String res = "";
        for (int val:
             data) {
            res += val + ",";
        }
        res = res.substring(0,res.length() -1);
        print(res);
    }

    public static String readString(String value)throws IOException{
        print("Por favor digite " + value + ":");
        String res = in.readLine();
        if(res.isBlank() || res.isEmpty()){
            print("Entrada no válida, intente de nuevo.");
            res = readString(value);
            return res;
        }else{
            return res;
        }
    }

    public static int readInt(String value)throws IOException{
        int res = 0;
        print("Por favor digite " + value + ":");
        try{
           res = Integer.parseInt(in.readLine());
        }catch(NumberFormatException e){
            print("Entrada no válida, intente de nuevo.");
            res = readInt(value);
        }

        return res;
    }

    public static int[] readIntArray(int length)throws IOException{
        int[] datos = new int[length];
        for (int i=0; i<length; i++){
            datos[i] = readInt("el valor #" + (i+1));
        }
        return datos;
    }

    public static int[] burbuja(int[]data){
        int temp;
        for(int i=1;i < data.length;i++){
            for (int j=0 ; j < data.length- 1; j++){
                if (data[j] > data[j+1]){
                    temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
            }
        }
        return data;
    }
}
