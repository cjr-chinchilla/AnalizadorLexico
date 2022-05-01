/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo_AnalizadorLexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author crist
 */
public class Entrada_AnalisisLexico {
    public static void entrada(String cajaTxT){
    File archivo = new File("archivo.txt");
        JTextField txt = new JTextField(cajaTxT);
        PrintWriter escribir;
        try {
            escribir = new PrintWriter(archivo);
            escribir.print(cajaTxT);
            escribir.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Reader lector = new BufferedReader(new FileReader("archivo.txt"));
            Lexer lexer = new Lexer(lector);
            String resultado = ""; 
            String longitud = "";
            
            while (true) {
                Tokens tokens = lexer.yylex();
                longitud = String.valueOf(lexer.lexeme);
                if (tokens == null) {
                    resultado += "FIN";
                    cajaTxT.setText(resultado);//ENVIAR TEXTO A TXT
                    return;
                }
                
                switch (tokens) {
                    case ERROR:
                        resultado += "El simbolo definido no existe\n";
                            break;
                    case Identificador: 
                        if (longitud.length() > 10)
                            resultado += "El identificador solo puede tener 10 caracteres\n";
                        else
                        resultado += lexer.lexeme + ": Es un " + tokens + "\n";
                        
                            break;
                    default:
                        resultado += "Token: " + tokens + "\n";
                            break;
                }
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
