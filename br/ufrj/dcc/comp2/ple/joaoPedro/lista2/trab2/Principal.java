package br.ufrj.dcc.comp2.ple.joaoPedro.lista2.trab2;

import java.util.*;
import java.lang.Math.*;

public class Principal {
  /**
    * <p>Variaveis globais necessárias para o decorrer do programa. Temos 4 monstro um de cada tipo
    * Temos a posição do jogador representado por x e y. Temos o objeto heroi que represta o protagonista.
    * Temos um contadorMonstro para sabermos se existe monstro no tabuleiro. E temos o tabuleiro sendo
    * chamado de labirinto
    </p>
    */
  public static int x, y;
  public static Vampiro vampiro = new Vampiro((int)((Math.random()*((30-20)+1))+20) ,(int)((Math.random() * ((50 - 40) + 1)) + 40) , "sugar sangue");
  public static Zumbi zumbi = new Zumbi((int)((Math.random()*((20-10)+1))+10) ,(int)((Math.random() * ((60 - 50) + 1)) + 50) , "mordida");
  public static Lobisomem lobisomem = new Lobisomem((int)((Math.random()*((20-10)+1))+10) ,(int)((Math.random() * ((60 - 50) + 1)) + 40) , "arranhão");
  public static Fantasma fantasma = new Fantasma((int)((Math.random()*((20-10)+1))+10) ,(int)((Math.random() * ((60 - 50) + 1)) + 40) , "susto");
  public static Heroi heroi = new Heroi(30, 70);
  public static int contadorMonstro = 3;
  public static int[][]labirinto = {{3, 2, 0, 1, 1, 0, 1, 0, 0, 2},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 1, 4},
                                    {0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
                                    {0, 1, 0, 0, 0, 1, 0, 1, 1, 0},
                                    {0, 1, 1, 1, 0, 1, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {0, 1, 1, 1, 0, 1, 0, 0, 1, 5},
                                    {1, 1, 0, 1, 0, 0, 0, 1, 1, 2},
                                    {6, 2, 0, 0, 0, 1, 0, 0, 0, 0}};
/**
 * Função main.
 * <p>
 * Função main, primeiro se calcula a posição de maneira aletoria podendo somente quebrar o loop infinito
 * caso o local esteja vazio. E dps temos um loop infinito onde basicamente é capturado o movimento desejado pelo
 * player e depois é chamado a função andar, e o programa é fechado somente perdendo ou ganhando o jogo.</p>
 */
  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      String movimento;
     while(true){
        x = (int)(Math.random()*((9-0)+1))+0;
        y = (int)(Math.random()*((9-0)+1))+0;
        if(labirinto[y][x] == 0)
          break;
      }
     while(true){
        movimento = scanner.nextLine().toLowerCase();
        andar(heroi,movimento, scanner);
      }

  }
  /**
     * Função combate.
     * <p>
     * Função combate, precisa de um heroi e um monstro, que será determinado em outra parte do código.
     * Com o monstro e o heroi o combate acontece, o heroi pode atacar ou fugir e o monstro sempre ataca.
     * Caso o heroi seja derrotado, o programa fecha e na tela do terminal é printado "GAME OVER", caso
     * ele derrote é chamado a funçào checarVitoria para ser possivel determinar o fim de jogo ou não.
     * Caso ele fuja ele retorna para o lugar mais proximo livre.</p>
     * @param  monstro um objeto monstro.
     * @param  heroi uma objeto heroi.
     * @param  scanner necessário para ler o input.
     */
  public static void combate(Monstro monstro, Heroi heroi, Scanner scanner){
      System.out.println("Há um " + monstro.getClass().getSimpleName() +" (ATK: " + monstro.getAtaque() + ").");
      String movimento = scanner.nextLine().toLowerCase();
      int atkHeroi;
      int atkMonstro;
      while(true){
          if(movimento.equals("fugir")){
            System.out.println("Você fugiu do combate");
            if((y+1) < 10){
              if(labirinto[y+1][x] == 0){
                y++;
                break;
              }
            }
            if((y-1)>0){
              if(labirinto[y-1][x] == 0){
                y++;
                break;
              }
            }
            if((x+1)<10){
            if(labirinto[y][x+1] == 0){
              x++;
              break;
            }
          }
          if((x-1)>0){
            if(labirinto[x-1][y] == 0){
              x--;
              break;
            }
          }
          }
          if(movimento.equals("atacar")){
              atkHeroi = heroi.atacar();
              System.out.println("Você feriu o " + monstro.getClass().getSimpleName() +" (HP: - " + atkHeroi  + ").");
              monstro.setPontosDeVida(monstro.getPontosDeVida() - atkHeroi);
              if(monstro.getPontosDeVida() <= 0){
                checarVitoria();
                break;
              }
              atkMonstro = monstro.atacar();
              heroi.setPontosDeVida(heroi.getPontosDeVida() - atkMonstro);
              System.out.println(monstro.getClass().getSimpleName() + " atacou com " + monstro.getNomeDeAtaque());
              System.out.println("Você foi ferido pelo " + monstro.getClass().getSimpleName() +" (HP: " + heroi.getPontosDeVida()  + ").");
              if(heroi.getPontosDeVida() <= 0){
                System.out.println("GAME OVER");
                System.exit(0);
              }
          }
          else{
            System.out.println("Movimento não permitido");
          }
          movimento = scanner.nextLine();
      }
  }
  /**
     * Função andar.
     * <p>
     * Funçao para o heroi percorrer o mapa, a função sempre checa antes o movimento desejado
     * e se é possivel realiza-lo caso tenha um monstro ou uma poção ele chama as funções que
     * lidarão com a situação </p>
     * @param  heroi um objeto heroi.
     * @param  heroi uma String representando o movimento desejado.
     * @param  scanner necessário para passar para outra função, no caso o combate.
     */
  public static void andar(Heroi heroi, String movimento, Scanner scanner){
    if(movimento.equals("frente")){
      if(!((y-1) < 0 )){
        switch (labirinto[y-1][x]) {
            case 1:
              System.out.println("Há um muro à frente.");
              break;
            case 0:
              y--;
              break;
            case 2:
              y--;
              recuparHp(heroi);
              break;
            case 5:
              y--;
              combate(lobisomem, heroi, scanner);
              break;
        }
      }
      else{
        System.out.println("Há um muro à frente.");
      }

    }
    else if(movimento.equals("trás")){
      if(!((y+1) >= 10 )){
        switch (labirinto[y+1][x]) {
            case 1:
              System.out.println("Há um muro à trás.");
              break;
            case 0:
              y++;
              break;
            case 2:
              y++;
              recuparHp(heroi);
              break;
            case 4:
              y++;
              System.out.println("KONO DIO DA");
              combate(vampiro, heroi, scanner);
              break;
        }
      }
      else{
        System.out.println("Há um muro à trás.");
      }
    }
    else if(movimento.equals("esquerda")){
      if(!((x-1) < 0 )){
        switch (labirinto[y][x-1]) {
            case 1:
              System.out.println("Há um muro à esquerda.");
              break;
            case 0:
              x--;
              break;
            case 2:
              x--;
              recuparHp(heroi);
              break;
            case 3:
              x--;
              combate(zumbi, heroi, scanner);
              break;
            case 6:
              x--;
              combate(fantasma, heroi, scanner);
              break;
        }
      }
      else{
        System.out.println("Há um muro à esquerda.");
      }
    }
    else if(movimento.equals("direita")){
      if(!((x+1) >= 10 )){
        switch (labirinto[y][x+1]) {
            case 1:
              System.out.println("Há um muro à direita.");
              break;
            case 0:
              x++;
              break;
            case 2:
              x++;
              recuparHp(heroi);
              break;
        }
      }
    else{
      System.out.println("Há um muro à direita");
    }
  }
  else{
    System.out.println("Movimento não permitido");
  }
  }
  /**
     * Função recuparHp.
     * <p>
     * Funçao para recuperar o nivel de hp do heroi sendo a metade do hp atual.
     * Caso seja maior que 70 ele terá a vida cheia </p>
     * @param  heroi um objeto heroi.
     */
  public static void recuparHp(Heroi heroi){
      labirinto[y][x] = 0;
      int pontosDeVida = (heroi.getPontosDeVida() + (int)(0.5 * heroi.getPontosDeVida())) > 70 ? 70 : (heroi.getPontosDeVida() + (int)(0.5 * heroi.getPontosDeVida()));
      heroi.setPontosDeVida(pontosDeVida);
      System.out.println("Você recupero sua vida, agora está com " + heroi.getPontosDeVida());
  }
  /**
     * Função checarVitoria.
     * <p>
     * Funçao para checar se o heroi derrotar todos os monstros.
     * Caso ele não tenha consiguido apenas retira do contador de monstro um e
     * informa ao heroi que derrotou um monstro. Caso o heroi ganhe o jogo o programa fecha</p>
     */
  public static void checarVitoria(){
    if(contadorMonstro == 0){
      System.out.println("Você ganhou o jogo, Parabéns!");
      System.exit(0);
    }
    else{
      System.out.println("Você derrotou o monstro");
      labirinto[y][x] = 0;
      contadorMonstro--;
    }
  }

}
