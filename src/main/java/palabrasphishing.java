import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class palabrasphishing {
    public static void ordenamientoArreglos(String[] palabras, int[] apariciones) {
        for (int i = palabras.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (j + 1 <= i && apariciones[j] < apariciones[j + 1]) {
                    String aux = palabras[j];
                    int iux = apariciones[j];
                    palabras[j] = palabras[j + 1];
                    apariciones[j] = apariciones[j + 1];
                    palabras[j + 1] = aux;
                    apariciones[j + 1] = iux;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Map<String, Integer> palabras = new HashMap();
        String arrayspam [];
        String argumentos;
        String puntos[];
        String file;
        int cp;
        int pt = 0;
        if(args.length>0) {
            argumentos = args[0];
            puntos = argumentos.split(",");
            file = puntos[puntos.length-1];
            argumentos = argumentos.replaceAll("=", "").replaceAll("1", "").replaceAll("2", "").replaceAll("3", "");
            arrayspam = argumentos.split(",");
            for (int i = 0; i < puntos.length; i++) {
                String lastCharacter = puntos[i].substring(puntos[i].length() - 1);
                char[] lastChar = lastCharacter.toCharArray();
                puntos[i] = String.valueOf(lastChar[lastChar.length - 1]);
            }
            //System.out.println(arrayspam[1]);
            //System.out.println(puntos[1]);
            //System.out.println(file);

            FileReader fileReader = null;
            try {
                fileReader = new FileReader(file);
            } catch (FileNotFoundException e) {
                System.out.println("el nombre del archivo no se encuentra");
                System.exit(2);
            }
            BufferedReader in = new BufferedReader(fileReader);
            String textLine = null;


            while (true) {
                try {
                    if (!((textLine = in.readLine())   != null))

                        break;
                } catch (IOException e) {
                    System.out.println("error al leer el archivo");
                    System.exit(3);
                }
                for (String palabra : textLine.replace(",", "").replace(".", "").replace(";", "").replace(":", "").split(" ")) {

                    palabras.put(palabra, palabras.containsKey(palabra) ? palabras.get(palabra) + 1 : 1);
                }
            }
            String[] arreglo_palabras = new String[palabras.size()];
            int[] arreglo_repeticiones = new int[palabras.size()];
            palabras.keySet().toArray(arreglo_palabras);
            for (int i = 0; i < arreglo_palabras.length; i++) {
                arreglo_repeticiones[i] = palabras.get(arreglo_palabras[i]);
            }

            ordenamientoArreglos(arreglo_palabras, arreglo_repeticiones);
            System.out.println("veces repetida / palabra / puntos ");
            for (int i = 0; i < arreglo_repeticiones.length; i++) {
                for(int j = 0; j < arrayspam.length; j++){
                    if(arreglo_palabras[i] .equals(arrayspam[j])){
                        cp = Integer.parseInt(String.valueOf(arreglo_repeticiones[i]))*Integer.parseInt(puntos[j]);
                        pt = pt + cp;
                        System.out.println("   " + arreglo_repeticiones[i] + "   " + arreglo_palabras[i] + "    " +cp);
                    }
                }
            }
            System.out.println("Se acumularon " + pt + " puntos totales");



        }
    }
}




