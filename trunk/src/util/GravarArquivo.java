package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JOptionPane;
import org.jdom.Parent;

public class GravarArquivo {

    public static boolean salvarArquivo(String conteudo, String destino) {
        boolean res = false;
        boolean gravar = true;

        if (new File(destino).exists()) {
            Object[] options = {"Sim", "Não"};
            int i = JOptionPane.showOptionDialog(null, "O arquivo " + destino + " já existe. \n Deseja substituí-lo?", "Saída", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (i == JOptionPane.NO_OPTION) {
                gravar = false;
            }
        }

        if (gravar) {
            try {
//                BufferedWriter saida = new BufferedWriter(new FileWriter(destino));
                BufferedWriter saida = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destino),"UTF-8"));
                saida.write(new String(conteudo));
                // saida.newLine();
                // saida.write(new String("texto linha 2"));
                saida.close();
                // System.out.println("arquivo gravado com sucesso em "+ destino);
                res = true;
            } catch (IOException e) {
                System.out.println("Erro ao gravar o arquivo\n");
                e.printStackTrace();
            }
        }
        return (res);
    }
}
