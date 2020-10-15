package br.ufrj.dcc.comp2.ple.joaoPedro.lista3;

import java.util.*;
import java.io.*;
/**
 * @author      João Pedro Silva
 *
 * <h1>Classe de Ranking para o exercicio.
 * Possuindo três funções</h1>
 */
public class Ranking {
    /**
     * rankingDezCidades
     * <p>
     * A função recebe a lista dos dados  do ranking geral ordena em ordem crescente em relação
     * ao número de confirmados por 100 mil habitantes e a partir disso a função cria os arquivos
     * das dez maiores e menores cidades em relação aos confirmados a cem mil habitantes.
     * </p>
     * @param  ranking um lista do objeto de Estatisticas representando o ranking.
     */
    public static void rankingDezCidades(List<HashMap<String, String>> ranking){
        File arq = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista3/resources/menor_casos_100k.tsv");
        File arq2 = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista3/resources/maior_casos_100k.tsv");

        try (FileOutputStream fos = new FileOutputStream(arq2)) {
            PrintStream out = new PrintStream(fos);
            out.println("estado"+ "\t" + "cidade" + "\t" + "data_ultima_medicao" + "\t" +"valor");
            for (int i = (ranking.size()-1); i > (ranking.size()-11); i--) {
                out.println(ranking.get(i).get("estado") + "\t" + ranking.get(i).get("cidade")+
                "\t" + ranking.get(i).get("data") + "\t" + ranking.get(i).get("confirmados_por_100k"));
            }
        }
        catch (IOException e) {
            System.out.println("Problema ao abrir ou fechar o arquivo: " + arq2.getAbsolutePath());
            }
        try (FileOutputStream fos = new FileOutputStream(arq)) {
            PrintStream out = new PrintStream(fos);
            out.println("estado"+ "\t" + "cidade" + "\t" + "data_ultima_medicao" + "\t" +"valor");
            for (int i = 0; i < 10; i++) {
                out.println(ranking.get(i).get("estado") + "\t" + ranking.get(i).get("cidade")+
                "\t" + ranking.get(i).get("data") + "\t" + ranking.get(i).get("confirmados_por_100k"));
            }
        }
        catch (IOException e) {
            System.out.println("Problema ao abrir ou fechar o arquivo: " + arq.getAbsolutePath());
            }
        }
        /**
         * rankingMortalidade
         * <p>
         * A função recebe a lista dos dados  do ranking geral ordena em ordem crescente em relação
         * ao número da taxa mortalidade e a partir disso a função cria os arquivos
         * das dez maiores e menores cidades em relação a essa taxa.
         * </p>
         * @param  ranking um lista do objeto de Estatisticas representando o ranking.
         */
    public static void rankingMortalidade(List<HashMap<String, String>> ranking){
        File arq = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista3/resources/menor_mortalidade.tsv");
        File arq2 = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista3/resources/maior_mortalidade.tsv");

        try (FileOutputStream fos = new FileOutputStream(arq2)) {
            PrintStream out = new PrintStream(fos);
            out.println("estado"+ "\t" + "cidade" + "\t" + "data_ultima_medicao" + "\t" +"valor");
            for (int i = (ranking.size()-1); i > (ranking.size()-11); i--) {
                out.println(ranking.get(i).get("estado") + "\t" + ranking.get(i).get("cidade")+
                "\t" + ranking.get(i).get("data") + "\t" + ranking.get(i).get("mortos/casos"));
            }
        }
        catch (IOException e) {
            System.out.println("Problema ao abrir ou fechar o arquivo: " + arq2.getAbsolutePath());
            }
        try (FileOutputStream fos = new FileOutputStream(arq)) {
            PrintStream out = new PrintStream(fos);
            out.println("estado"+ "\t" + "cidade" + "\t" + "data_ultima_medicao" + "\t" +"valor");
            for (int i = 0; i < 10; i++) {
                out.println(ranking.get(i).get("estado") + "\t" + ranking.get(i).get("cidade")+
                "\t" + ranking.get(i).get("data") + "\t" + ranking.get(i).get("mortos/casos"));
            }
        }
        catch (IOException e) {
            System.out.println("Problema ao abrir ou fechar o arquivo: " + arq.getAbsolutePath());
            }
        }
        /**
         * rankingTaxaCrescimento
         * <p>
         * A função recebe a lista dos dados  do ranking da Taxa de Crescimento ordena em ordem crescente em relação
         * a taxa de crescimento e a partir disso a função cria o arquivo
         * das dez maiores cidades em relação a esta taxa.
         * </p>
         * @param  ranking um lista do objeto de Estatisticas representando o ranking.
         */
    public static void rankingTaxaCrescimento(List<HashMap<String, String>> ranking){
        File arq = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista3/resources/maior_taxa_crescimento.tsv");

        try (FileOutputStream fos = new FileOutputStream(arq)) {
            PrintStream out = new PrintStream(fos);
            out.println("estado"+ "\t" + "cidade" + "\t" + "data_ultima_medicao" + "\t" +"valor");
            for (int i = (ranking.size()-1); i > (ranking.size()-11); i--) {
                out.println(ranking.get(i).get("estado") + "\t" + ranking.get(i).get("cidade")+
                "\t" + ranking.get(i).get("data") + "\t" + ranking.get(i).get("taxaDeCrescimento"));
            }
        }
        catch (IOException e) {
            System.out.println("Problema ao abrir ou fechar o arquivo: " + arq.getAbsolutePath());
            }
        }


}
