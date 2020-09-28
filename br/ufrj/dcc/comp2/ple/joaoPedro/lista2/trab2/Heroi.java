public class Heroi {
  private final int atk;
  private int hp;

  public void setPontosDeVida(int hp){
    this.hp = hp;
  }
  public int getAtaque(){
    return this.atk;
  }
  public int getPontosDeVida(){
    return this.hp;
  }
  public int atacar(){
    int power = (int)(Math.random() * ((this.atk - (this.atk/2)) + 1)) + (this.atk/2);
    return power;
  }

  public Heroi(int atk, int hp){
    this.hp = hp;
    this.atk = atk;
  }
}
