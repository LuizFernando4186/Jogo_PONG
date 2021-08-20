import java.awt.*;
import java.util.Random;


/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {
	
	private double cx;
	private double cy;
	private double width;
	private double height;
	private Color color;
	private double speed;
	private double direcaoX;
	private double direcaoY; 

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
	
	    this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.speed = speed;
		atualizaDirecao();
	}
	
	private void atualizaDirecao(){
	System.out.println("Entrou formando um angulo");
	int angulo = new Random().nextInt(60-45)+46;//gerar um angulo aleatorio entre 45 a 60.
	System.out.println("Angulo " + angulo);
	//matriz de rotação
	this.direcaoX = Math.cos(Math.toRadians(angulo)) * Math.sin(Math.toRadians(angulo));
	this.direcaoY = -Math.sin(Math.toRadians(angulo)) * Math.cos(Math.toRadians(angulo));
	}
	
	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/
	 
	public void draw(){

		GameLib.setColor(this.color);
		GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
       this.cx += delta * this.direcaoX * this.speed;
       this.cy += delta * this.direcaoY * this.speed;
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
		
    if(playerId.equals("Player 1") || playerId.equals("Player 2")){
    this.direcaoX *= -1;
	}
		
	/*
     //Posição do lado esquerdo
	if(playerId.equals("Player 1")) {
    this.direcaoX =  Math.abs(this.speed);		
	}
	//Posição do lado direito
	if(playerId.equals("Player 2")) { 
	this.direcaoX = - Math.abs(this.speed);	
	}
	*/
	}
     
	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
    
	if(wallId.equals("Top")){
	this.direcaoY = Math.abs(this.speed);
	} 
		
	if(wallId.equals("Left")){
	this.direcaoX = Math.abs(this.speed);
	} 
		
	if(wallId.equals("Bottom")){
	this.direcaoY = - Math.abs(this.speed);
	} 
	if(wallId.equals("Right")){
	this.direcaoX = - Math.abs(this.speed);
	
	}
	
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
    //F(x)  ->>  F(x)^-1
	public boolean checkCollision(Wall wall){
	String lados = wall.getId();
	
	if(lados.equals("Top")){
	//Bola sai do topo para baixo
	if(this.cy - (this.height/2) <= wall.getCy() + (wall.getHeight()/2)) return true;
	} 
	if(lados.equals("Left")){
	//Bola sai da esquerda para direita
	if(this.cx - (this.height/2) <= wall.getCx() + (wall.getWidth()/2)) return true;
	} 
	//Bola sai de baixo para cima 
	if(lados.equals("Bottom")){
	if(this.cy + (this.height/2) >= wall.getCy() - (wall.getHeight()/2)) return true;
	} 
	//Bola sai da direita para esquerda
	if(lados.equals("Right")){
	if(this.cx + (this.height/2) >= wall.getCx() - (wall.getWidth()/2)) return true;
	}
		return false;
	}
	

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
    
	//Player
    String playerId = player.getId();
    double playerAltura = player.getHeight();//Altura do player
    double playerLargura = player.getWidth();//Largura do player
	double playerEsquerda = player.getCx() - (playerLargura/2);//Parte Esquerda do player
	double playerDireita = player.getCx() + (playerLargura/2);//Parte Direita do player
	double playerCima = player.getCy() - (playerAltura/2);//Parte de Cima do player
	double playerBaixo = player.getCy() + (playerAltura/2);//Parte de Baixo do player
	
	//Bola
	double bolaIndoEsquerda = this.cx - (this.width/2);
	double bolaIndoDireita = this.cx + (this.width/2);
	double bolaIndoCima = this.cy - (this.height/2);
	double bolaIndoBaixo = this.cy + (this.height/2);
	
    //Verifica colisoes
    if(playerId.equals("Player 1") || playerId.equals("Player 2")) {
    if(bolaIndoEsquerda <= playerDireita && bolaIndoDireita >= playerEsquerda 
	&& bolaIndoBaixo >= playerCima && bolaIndoCima <= playerBaixo) {
	
    return true;		
    }
	}
	


	return false;
	}
	

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){

		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){

		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){

		return this.speed;
	}

}
