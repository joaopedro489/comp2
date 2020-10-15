package br.ufrj.dcc.comp2.ple.joaoPedro.lista3;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.lang.*;
/**
 * @author      João Pedro Silva
 *
 * <h1>Classe de Web para o exercicio.
 * Possuindo uma função</h1>
 */
 public class Web {
     /**
      * writeHTML
      * <p>
      * A função recebe a lista dos dados da web.
      * </p>
      *<p>
      * Primeiro a função agrupa todas as informações necessárias para aparecer no HTML em string, no qual
      * existe uma string para os dados do items, uma string da data incial e uma string data final. Após isso
      * é lido o arquivo em html e é passado para uma lista de Strings os dados, essa lista posteriormente, é
      * alterada para que receba os dados e com isso é criado um arquivo HTML com os dados e consequentemente
      * o gráfico desejado.
      *</p>
      * @param  web um lista do objeto de Estatisticas representando os dados que irao para o grafico.
      */
     public static void writeHTML(List<HashMap<String,String>> web){
        File arq = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista3/resources/template_grafico.html");
        StringBuilder elementos2 = new StringBuilder();
        String dataInicio = "";
        String dataFinal = "";
        for(int i = 0 ; i < web.size() ; i++ ){
            if(i == 0){
                dataInicio = "\'" + web.get(i).get("data") + "\'";
            }
            if(i == (web.size() - 1)){
                dataFinal = "\'" + web.get(i).get("data")+ "\'";
            }
            elementos2.append("{ x: \'").append(web.get(i).get("data")).append("\', y: ").append(web.get(i).get("confirmados")).append(", group: \"Casos\"},").append("\n");
            elementos2.append("{ x: \'").append(web.get(i).get("data")).append("\', y: ").append(web.get(i).get("mortos")).append(", group: \"Mortes\"},").append("\n");
        }
        String elementos = elementos2.toString();
        try (FileInputStream fis = new FileInputStream(arq)) {

            List<String> linhas = Files.readAllLines(arq.toPath());
            for (int i =0; i< linhas.size(); i++) {
                String linha = linhas.get(i);
                String string;
                StringBuilder str = new StringBuilder(linha);
                int index = linha.indexOf(":items:");
                int index2 = linha.indexOf(":data_inicio:");
                int index3 = linha.indexOf(":data_fim:");

                if(index != -1){
                    str.replace(index, index+7, elementos);
                    string = str.toString();
                    linhas.set(i, string);
                }
                if(index2 != -1){
                    str.replace(index2, index2+13, dataInicio);
                    string = str.toString();
                    linhas.set(i, string);
                }
                if(index3 != -1){
                    str.replace(index3, index3+10, dataFinal);
                    string = str.toString();
                    linhas.set(i, string);
            }
        }
            File arq2 = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista3/resources/graphic.html");
            try (FileOutputStream fos = new FileOutputStream(arq2)) {
                PrintStream out = new PrintStream(fos);
                for (int i = 0; i < (linhas.size()); i++) {
                    out.println(linhas.get(i));
                }
            }
            catch (IOException e) {
                System.out.println("Problema ao abrir ou fechar o arquivo: " + arq2.getAbsolutePath());
                }
       }
       catch(IOException e){
        System.out.println("Erro ao abrir o arquivo");
        System.exit(1);
     }

    }
 }
