import java.awt.*;

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {
	
	private int pontos = 0;
	private String playerId;

	/**
		Construtor da classe Score.

		@param playerId uma string que identifica o player ao qual este placar está associado.
	*/
	

	public Score(String playerId){
	this.playerId = playerId;

	}

	/**
		Método de desenho do placar.
	*/

	public void draw(){
		//desenha o placar do jogador 1
        if(this.playerId.equals("Player 1")){
		GameLib.drawText(this.playerId + ": " + this.pontos, 70, GameLib.ALIGN_LEFT);			
	    }
		//desenha o placar do jogador 2
		 if(this.playerId.equals("Player 2")){
		GameLib.drawText(this.playerId + ": " + this.pontos, 70, GameLib.ALIGN_RIGHT);			
	    }
	    }
	

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/

	public void inc(){
        this.pontos++;
	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.

		@return o valor inteiro referente ao total de pontos.
	*/

	public int getScore(){
	return this.pontos;
	}
}
