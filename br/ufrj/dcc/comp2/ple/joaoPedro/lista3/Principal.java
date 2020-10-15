package br.ufrj.dcc.comp2.ple.joaoPedro.lista3;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.zip.GZIPInputStream;
import java.time.LocalDate;
/**
 * @author      João Pedro Silva
 *
 * <h1>Classe de Principal para o exercicio. Programa feito para receber uma
 * localização e com isso criar um gráfico deste lugar, ou do Brasil, e também
 * cria os arquivos de ranking</h1>
 */
public class Principal {
    public static void main(String[] args) {
        String[] entrada;
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Digite a localizaçao: ");
            String lugar = scanner.nextLine();
            entrada = lugar.split("//");
            if(entrada.length < 3)
                break;
            else{
                System.out.println("Entrada Invalida");
                scanner.next();
            }
        }
        Estatisticas web = new Estatisticas();
        Estatisticas ranking = new Estatisticas();
        Estatisticas rankingTaxa = new Estatisticas();
        if(filtrarDados(entrada, web, ranking, rankingTaxa)){
            System.out.println("Nada encontrado");
            return;
        }
        Estatisticas.rankingMortosPorConfirmados(ranking.dados);
        Ranking.rankingMortalidade(ranking.dados);
        Estatisticas.rankingConfirmadosPorCemMil(ranking.dados);
        Ranking.rankingDezCidades(ranking.dados);
        List<HashMap<String,String>> rankingTaxaFiltrada = Estatisticas.rankingTaxaCrescimento(rankingTaxa.dados);
        Ranking.rankingTaxaCrescimento(rankingTaxaFiltrada);
        if(entrada.equals("")){
            Estatisticas.ordenarWeb(web.dados);
        }
        Estatisticas.ordenarWeb(web.dados);
        if(entrada[0].equals("")){
            List<HashMap<String,String>> webFiltrada = Estatisticas.filtrarDadosWebBrasil(web.dados);
            Web.writeHTML(webFiltrada);
        }
        else{
            Web.writeHTML(web.dados);
        }
    }
    /**
     * filtrarDados
     * <p>
     * A função recebe o que é desejado filtrar para o gráfico e a recebe as instancias
     * de Estatisticas que representam cada lista de dados, um para o grafico da Web
     * e outras duas para o ranking
     * </p>
     *<p>
     *Dentro da função primeiro abrimos o arquivo  e dps começamos a ler o arquivo,
     *linha por linha, e a cada linha vão sendo adicionados os dados nas listas
     *que foram passados por parametro, e existe a filtragem de dados devido a necessidade
     *do problema, por fim temos um retorno para saber se existe uma lista fazia,
     *se tiver na main será resolvido o problema.
     *</p>
     * @param  entrada um array de String com o que será filtrado para web.
     * @param  web um objeto de Estatisticas representando a lista de dados da Web.
     * @param  ranking um objeto de Estatisticas representando o ranking.
     * @param  rankingTaxa um objeto de Estatisticas representando o ranking da Taxa de Crescimento.
     * @return um booleano para saber se alguma lista for vazia.
     */
    public static boolean filtrarDados(String[] entrada, Estatisticas web,
     Estatisticas ranking, Estatisticas rankingTaxa){
        GZIPInputStream gzipInputStream = null;
        InputStream fileInputStream;
        LocalDate currentdate = LocalDate.now();
        LocalDate earlier = currentdate.minusMonths(1);
        String[] month = currentdate.toString().split("-");
        String[] previousMonth = earlier.toString().split("-");
        try{
            fileInputStream = new FileInputStream("br/ufrj/dcc/comp2/ple/joaoPedro/lista3/resources/caso.csv.gz");
            gzipInputStream = new GZIPInputStream(fileInputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(gzipInputStream));
            String linha;
            int i = 0;
            while((linha = br.readLine()) != null){
                if(i == 0){
                    i++;
                    continue;
                }
                String[] dado = linha.split(",");
                if(Boolean.parseBoolean(dado[7]) && dado[3].equals("city")){
                    Estatisticas.saveRankingList(dado, ranking.dados);
                }
                if(entrada.length == 1 && entrada[0].equals("") && dado[3].equals("state")){
                    Estatisticas.saveWebList(dado, web.dados);
                }
                if(entrada.length == 1 && !(entrada[0].equals(""))){
                    if(entrada[0].equals(dado[1]) && dado[3].equals("state"))
                        Estatisticas.saveWebList(dado, web.dados);
                }
                if(entrada.length == 2){
                    if(entrada[0].equals(dado[2]))
                        Estatisticas.saveWebList(dado, web.dados);
                }
                String[] data = dado[0].split("-");
                if(data[2].equals("01") && dado[3].equals("city")
                 && ((data[1].equals(month[1]) && data[0].equals(month[0]))
                 || (data[1].equals(previousMonth[1]) && data[0].equals(previousMonth[0])))) {
                    Estatisticas.saveRankingRateList(dado, rankingTaxa.dados);
                }
            }
        }
        catch(IOException e){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }
        return(web.dados.isEmpty() && ranking.dados.isEmpty() && rankingTaxa.dados.isEmpty());
        }

}
