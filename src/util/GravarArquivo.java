package util;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GravarArquivo {

    public static boolean salvarArquivo(String conteudo, String destino) {
        boolean res = false;
        try {
            BufferedWriter saida = new BufferedWriter(new FileWriter(destino));
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
        return (res);
    }
}
