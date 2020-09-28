public abstract class Monstro {
  private final int atk;
  private int hp;
  private String nomeDeAtaque;


  public abstract int atacar();

  public int getAtaque(){
    return this.atk;
  }
  public int getPontosDeVida(){
    return this.hp;
  }
  public void setPontosDeVida(int hp){
    this.hp = hp;
  }
  public String getNomeDeAtaque(){
    return this.nomeDeAtaque;
  }

  public Monstro(int atk, int hp, String nomeDeAtaque){
    this.atk = atk;
    this.hp = hp;
    this.nomeDeAtaque = nomeDeAtaque;
  }


}
