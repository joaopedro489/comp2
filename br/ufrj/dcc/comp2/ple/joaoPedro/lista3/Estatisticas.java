package br.ufrj.dcc.comp2.ple.joaoPedro.lista3;

import java.util.*;
/**
 * @author      João Pedro Silva
 *
 * <h1>Classe de Estatisticas para o exercicio.
 * Possuindo o seu construtor, e seis funções</h1>
 */
public class Estatisticas {
    /**
  * <p>Atributo dados. Sendo representado por um lista de HashMap de String, String.
  </p>
  */
    public List<HashMap<String, String>> dados;
    /**
     * saveWebList
     * <p>
     * A função recebe um dado de um caso de covid e a lista dos dados da web.
     * </p>
     *<p>
     *Dentro da função é criado um HashMap para armazenar os dados que serão
     *passados para lista de hashmap, não retornando nada.
     *</p>
     * @param  dado um array de String de uma linha do arquivo.
     * @param  web um lista do objeto de Estatisticas representando a lista de dados da Web.
     */
    public static void saveWebList(String[] dado, List<HashMap<String, String>> web){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("data", dado[0]);
        map.put("estado", dado[1]);
        map.put("cidade", dado[2]);
        map.put("confirmados", dado[4]);
        map.put("mortos", dado[5]);
        web.add(map);

    }
    /**
     * saveRankingList
     * <p>
     * A função recebe um dado de um caso de covid e a lista dos dados do ranking geral.
     * </p>
     *<p>
     *Dentro da função é criado um HashMap para armazenar os dados que serão
     *passados para lista de hashmap, não retornando nada.
     *</p>
     * @param  dado um array de String de uma linha do arquivo.
     * @param  ranking um lista do objeto de Estatisticas representando o ranking.
     */
    public static void saveRankingList(String[] dado, List<HashMap<String, String>> ranking){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("data", dado[0]);
        map.put("estado", dado[1]);
        map.put("cidade", dado[2]);
        map.put("confirmados_por_100k", dado[11]);
        map.put("mortos/casos", dado[12]);
        ranking.add(map);
    }
    /**
     * saveRankingRateList
     * <p>
     * A função recebe um dado de um caso de covid e a lista dos dados do ranking da taxa de crescimento.
     * </p>
     *<p>
     *Dentro da função é criado um HashMap para armazenar os dados que serão
     *passados para lista de hashmap, não retornando nada.
     *</p>
     * @param  dado um array de String de uma linha do arquivo.
     * @param  rankingTaxa um lista do objeto de Estatisticas representando o ranking da Taxa de Crescimento.
     */
    public static void saveRankingRateList(String[] dado, List<HashMap<String, String>> rankingTaxa){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("data", dado[0]);
        map.put("estado", dado[1]);
        map.put("cidade", dado[2]);
        map.put("confirmados", dado[4]);
        rankingTaxa.add(map);
    }
    /**
     * rankingConfirmadosPorCemMil
     * <p>
     * A função recebe a lista dos dados do ranking geral e ordena em ordem crescente em relação
     * ao número de confirmados por 100 mil habitantes.
     * </p>
     * @param  ranking um lista do objeto de Estatisticas representando o ranking.
     */
    public static void rankingConfirmadosPorCemMil(List<HashMap<String, String>> ranking){
        Collections.sort(ranking, new Comparator<HashMap<String, String>>(){
        public int compare(HashMap<String, String> one, HashMap<String, String> two) {
            Double confirmadosPrimeiro;
            if(one.get("confirmados_por_100k").equals("")){
                confirmadosPrimeiro = 0.0;
            }
            else{
                confirmadosPrimeiro = Double.parseDouble(one.get("confirmados_por_100k"));
            }
            Double confirmadosSegundo;
            if(two.get("confirmados_por_100k").equals("")){
             confirmadosSegundo =  0.0;
            }
            else{
                confirmadosSegundo = Double.parseDouble(two.get("confirmados_por_100k"));
            }
            return confirmadosPrimeiro.compareTo(confirmadosSegundo);
        }
        });
        for(int i = ranking.size() -1 ; i>=0; i--){
            if(ranking.get(i).get("confirmados_por_100k").equals("")){
                ranking.remove(i);
            }
        }

    }
    /**
     * rankingMortosPorConfirmados
     * <p>
     * A função recebe a lista dos dados do ranking geral e ordena em ordem crescente em relação
     * a taxa de mortalidade.
     * </p>
     * @param  ranking um lista do objeto de Estatisticas representando o ranking.
     */
    public static void rankingMortosPorConfirmados(List<HashMap<String, String>> ranking){
        Collections.sort(ranking, new Comparator<HashMap<String, String>>(){
        public int compare(HashMap<String, String> one, HashMap<String, String> two) {
            Double confirmadosMortosPrimeiro;
            if(one.get("mortos/casos").equals("")){
                confirmadosMortosPrimeiro = 0.0;
            }
            else{
                confirmadosMortosPrimeiro = Double.parseDouble(one.get("mortos/casos"));
            }
            Double confirmadosMortosSegundo;
            if(two.get("mortos/casos").equals("")){
             confirmadosMortosSegundo =  0.0;
            }
            else{
                confirmadosMortosSegundo = Double.parseDouble(two.get("mortos/casos"));
            }
            return confirmadosMortosPrimeiro.compareTo(confirmadosMortosSegundo);
        }
        });
    }
    /**
     * rankingTaxaCrescimento
     * <p>
     * A função recebe a lista dos dados do ranking da taxa de crescimento.
     * </p>
     *<p>
     * Dentro da função primeiro calculamos a taxa de crescimento de cada cidade,
     * e depois ocorre a ordenação em relação a esse resultadoe depois será retornado
     * uma lista com essa ordem.
     *</p>
     * @param  rankingTaxa um lista do objeto de Estatisticas representando o ranking da Taxa de Crescimento.
     * @return uma lista HashMap<String,String>.
     */
    public static List<HashMap<String,String>> rankingTaxaCrescimento(List<HashMap<String,String>> rankingTaxa){
        List<HashMap<String,String>> rankingTaxaFiltrada = new ArrayList<>();
        for(int i = 0; i < (rankingTaxa.size() - 1); i++){
            if(rankingTaxa.get(i+1).get("cidade").equals(rankingTaxa.get(i).get("cidade"))){
                Double confirmados1 = Double.parseDouble(rankingTaxa.get(i).get("confirmados"));
                if(rankingTaxa.get(i).get("confirmados").equals("")){
                    confirmados1 = 0.0;
                }
                else{
                    confirmados1 = Double.parseDouble(rankingTaxa.get(i).get("confirmados"));
                }
                Double confirmados2;
                if(rankingTaxa.get(i+1).get("confirmados").equals("")){
                 confirmados2 =  0.0;
                }
                else{
                    confirmados2 = Double.parseDouble(rankingTaxa.get(i+1).get("confirmados"));
                }

                Double resultado;
                if(confirmados2 == 0){
                    resultado = 0.0;
                }
                else{
                    resultado = (confirmados1/confirmados2) - 1;
                }
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("data", rankingTaxa.get(i).get("data"));
                map.put("estado", rankingTaxa.get(i).get("estado"));
                map.put("cidade", rankingTaxa.get(i).get("cidade"));
                map.put("taxaDeCrescimento", resultado.toString());
                rankingTaxaFiltrada.add(map);
            }
        }
        Collections.sort(rankingTaxaFiltrada, new Comparator<HashMap<String, String>>(){
        public int compare(HashMap<String, String> one, HashMap<String, String> two) {
            Double taxaDeCrescimento1;
            if(one.get("taxaDeCrescimento").equals("")){
                taxaDeCrescimento1 = 0.0;
            }
            else{
                taxaDeCrescimento1 = Double.parseDouble(one.get("taxaDeCrescimento"));
            }
            Double taxaDeCrescimento2;
            if(two.get("taxaDeCrescimento").equals("")){
             taxaDeCrescimento2 =  0.0;
            }
            else{
                taxaDeCrescimento2 = Double.parseDouble(two.get("taxaDeCrescimento"));
            }
            return taxaDeCrescimento1.compareTo(taxaDeCrescimento2);
        }
        });

        return rankingTaxaFiltrada;
    }
    /**
     * ordenarWeb
     * <p>
     * A função recebe a lista dos dados da web e ordena em ordem crescente em relação
     * data.
     * </p>
     * @param  web um lista do objeto de Estatisticas representando a lista de dados da Web.
     */
    public static void ordenarWeb(List<HashMap<String, String>> web){
        Collections.sort(web, new Comparator<HashMap<String, String>>(){
        public int compare(HashMap<String, String> one, HashMap<String, String> two) {
            return one.get("data").compareTo(two.get("data"));
        }
        });
    }
    /**
     * filtrarDadosWebBrasil
     * <p>
     *     Função que filtra a lista com os dados do Brasil
     *     para ter a soma dos dados por data.
     * </p>
     * @param web lista com os dados do Brasil.
     * @return lista filtrada.
     */
    public static List<HashMap<String, String>> filtrarDadosWebBrasil(List<HashMap<String, String>> web) {

            List<HashMap<String, String>> webFiltrada = new ArrayList<>();

            for(int i = 0; i < web.size()-1; i++) {

                HashMap<String, String> map = new HashMap<>();
                Integer confirmados = Integer.parseInt(web.get(i).get("confirmados"));
                Integer mortos = Integer.parseInt(web.get(i).get("mortos"));
                map.put("data", web.get(i).get("data"));

                while(i < web.size()-1 && web.get(i+1).get("data").equals(web.get(i).get("data"))) {
                    confirmados += Integer.parseInt(web.get(i+1).get("confirmados"));
                    mortos += Integer.parseInt(web.get(i+1).get("mortos"));
                    i++;
                }
                map.put("confirmados", confirmados.toString());
                map.put("mortos", mortos.toString());
                webFiltrada.add(map);
            }

            return webFiltrada;
        }
    /**
     * Construtor de Estatisticas
     * <p>
     * A função não recebe nada e cria o array list das variaveis.
     * </p>
     */
    public Estatisticas(){
        this.dados = new ArrayList<>();
    }

}
