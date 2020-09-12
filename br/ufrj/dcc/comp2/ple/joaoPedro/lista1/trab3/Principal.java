package br.ufrj.dcc.comp2.ple.joaoPedro.lista1.trab3;

import java.util.*;
/**
 * @author      João Pedro Silva
 *
 * <h1> Classe de Principal para o terceiro exercicio. Programa feito testar o código do terceiro trabalho.</h1>
 */
public class Principal{
  /**
   * Função main
   * <p>
   * A função espera de entrada de uma figura geometrica 2d e após isso faz todo a operação de pegar o x e y
   * de cada vertice e depois criar o objeto da figura desejada
   *OBS.: código ficou extensos
   *</p>
   */
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       System.out.println("Digite a figura desejada");
       System.out.println("Obs.: Digite sem acento e sem espaços");
       String linha = scanner.nextLine().toLowerCase();
       switch(linha){
          case "triangulo":
            Vertice a = new Vertice();
            Vertice b = new Vertice();
            Vertice c = new Vertice();
            double x = 0.0;
            double y = 0.0;
            for(int i = 0; i <3; i++){
              boolean pass = false;
              System.out.println("Digite o x do " + (i + 1) + " vertice do triangulo");
              while(true){
                pass = false;
                try{
                  x = scanner.nextDouble();
                }
                catch(InputMismatchException e){
                    System.out.println("Não é um número");
                    scanner.next();
                    pass = true;
                }
                if(!pass){
                  break;
                }
              }
              System.out.println("Digite o y do " + (i + 1) + " vertice do triangulo");
              while(true){
                pass = false;
                try{
                  y = scanner.nextDouble();
                }
                catch(InputMismatchException e){
                    System.out.println("Não é um número");
                    scanner.next();
                    pass = true;
                }
                if(!pass){
                  break;
                }
              }
              switch(i){
                case 0:
                a.x = x;
                a.y = y;
                break;
                case 1:
                b.x = x;
                b.y = y;
                break;
                case 2:
                c.x = x;
                c.y = y;
                break;
                default:
                break;
              }
            }
            Triangulo triangulo = new Triangulo(a, b, c);
            System.out.println("Você tem o triangulo de vertices: ("
            + triangulo.a.x + " , " + triangulo.a.y + "), "+ "("
            + triangulo.b.x + " , " + triangulo.b.y + "), " + "("
            + triangulo.c.x + " , " + triangulo.c.y + "), "
            + " com área de " + triangulo.area()
            + " e seu perimetro " + triangulo.perimeter());
          break;
          case "trapezio":
          Vertice baseMaiorVertice1 = new Vertice();
          Vertice baseMaiorVertice2 = new Vertice();
          Vertice baseMenorVertice1 = new Vertice();
          Vertice baseMenorVertice2 = new Vertice();
          double xTrap = 0.0;
          double yTrap= 0.0;
          for(int i = 0; i <4; i++){
            boolean pass = false;
            System.out.println("Digite o x do " + ((i > 1? i - 1 : i + 1 )) + " vertice da base " + (i > 1? "menor" : "maior"));
            while(true){
              pass = false;
              try{
                xTrap = scanner.nextDouble();
              }
              catch(InputMismatchException e){
                  System.out.println("Não é um número");
                  scanner.next();
                  pass = true;
              }
              if(!pass){
                break;
              }
            }
            System.out.println("Digite o y do " + ((i > 1? i - 1 : i + 1 )) + " vertice da base " + (i > 1? "menor" : "maior"));
            while(true){
              pass = false;
              try{
                yTrap = scanner.nextDouble();
              }
              catch(InputMismatchException e){
                  System.out.println("Não é um número");
                  scanner.next();
                  pass = true;
              }
              if(!pass){
                break;
              }
            }
            switch(i){
              case 0:
              baseMaiorVertice1.x = xTrap;
              baseMaiorVertice1.y = yTrap;
              break;
              case 1:
              baseMaiorVertice2.x = xTrap;
              baseMaiorVertice2.y = yTrap;
              break;
              case 2:
              baseMenorVertice1.x = xTrap;
              baseMenorVertice1.y = yTrap;
              break;
              case 3:
              baseMenorVertice2.x = xTrap;
              baseMenorVertice2.y = yTrap;
              break;
              default:
              break;
            }
          }
          Trapezio trapezio = new Trapezio(baseMaiorVertice1, baseMaiorVertice2, baseMenorVertice1, baseMenorVertice2);
          System.out.println("Você tem o trapezio de vertices: ("
          + trapezio.baseMaiorVertice1.x + " , " + trapezio.baseMaiorVertice1.y + "), "+ "("
          + trapezio.baseMaiorVertice2.x + " , " + trapezio.baseMaiorVertice2.y + "), " + "("
          + trapezio.baseMenorVertice1.x + " , " + trapezio.baseMenorVertice1.y + "), " + "("
          + trapezio.baseMenorVertice2.x + " , " + trapezio.baseMenorVertice2.y + "), "
          + " com área de " + trapezio.area()
          + " e seu perimetro " + trapezio.perimeter());
            break;
          case "retangulo":
          Vertice aRet = new Vertice();
          Vertice bRet = new Vertice();
          Vertice cRet = new Vertice();
          Vertice dRet = new Vertice();
          double xRet = 0.0;
          double yRet = 0.0;
          for(int i = 0; i <4; i++){
            boolean pass = false;
            System.out.println("Digite o x do " + (i + 1) + " vertice do retangulo");
            while(true){
              pass = false;
              try{
                xRet = scanner.nextDouble();
              }
              catch(InputMismatchException e){
                  System.out.println("Não é um número");
                  scanner.next();
                  pass = true;
              }
              if(!pass){
                break;
              }
            }
            System.out.println("Digite o y do " + (i + 1) + " vertice do retangulo");
            while(true){
              pass = false;
              try{
                yRet = scanner.nextDouble();
              }
              catch(InputMismatchException e){
                  System.out.println("Não é um número");
                  scanner.next();
                  pass = true;
              }
              if(!pass){
                break;
              }
            }
            switch(i){
              case 0:
              aRet.x = xRet;
              aRet.y = yRet;
              break;
              case 1:
              bRet.x = xRet;
              bRet.y = yRet;
              break;
              case 2:
              cRet.x = xRet;
              cRet.y = yRet;
              break;
              case 3:
              dRet.x = xRet;
              dRet.y = yRet;
              break;
              default:
              break;
            }
          }
          Retangulo retangulo = new Retangulo(aRet, bRet, cRet, dRet);
          System.out.println("Você tem o retangulo de vertices: ("
          + retangulo.a.x + " , " + retangulo.a.y + "), "+ "("
          + retangulo.b.x + " , " + retangulo.b.y + "), " + "("
          + retangulo.c.x + " , " + retangulo.c.y + "), " + "("
          + retangulo.d.x + " , " + retangulo.d.y + "), "
          + " com área de " + retangulo.area()
          + " e seu perimetro " + retangulo.perimeter());
          break;
          case "circulo":
          Vertice center = new Vertice();
          double xCenter = 0.0;
          double yCenter = 0.0;
          double raio = 0.0;
          boolean pass = false;
            System.out.println("Digite o x do centro do circulo");
            while(true){
              pass = false;
              try{
                xCenter = scanner.nextDouble();
              }
              catch(InputMismatchException e){
                  System.out.println("Não é um número");
                  scanner.next();
                  pass = true;
              }
              if(!pass){
                break;
              }
            }
            System.out.println("Digite o y do centro do circulo");
            while(true){
              pass = false;
              try{
                yCenter = scanner.nextDouble();
              }
              catch(InputMismatchException e){
                  System.out.println("Não é um número");
                  scanner.next();
                  pass = true;
              }
              if(!pass){
                break;
              }
            }
            System.out.println("Digite o tamanho do raio do circulo");
            while(true){
              pass = false;
              try{
                raio = scanner.nextDouble();
              }
              catch(InputMismatchException e){
                  System.out.println("Não é um número");
                  scanner.next();
                  pass = true;
              }
              if(!pass){
                break;
              }
            }
          Circulo circulo = new Circulo(raio, center);
          System.out.println("Você tem o circulo com o centro em: ("
          + circulo.center.x + " , " + circulo.center.y + "), e raio de tamanho "
          + circulo.raio
          + " com área de " + circulo.area()
          + " e seu perimetro " + circulo.perimeter());
            break;
          default:
            System.out.println("Não se teve escolhas válidas");
       }
    }
}
