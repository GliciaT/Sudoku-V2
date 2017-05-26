package br.com.poli.principal;

import br.com.poli.exception.MovimentoIncorretoException;
import br.com.poli.exception.MovimentoInvalidoException;
import java.util.Date;

public class Partida {

    private Jogador jogador;
    private Tabuleiro tabuleiro = new Tabuleiro();
    private int quantidadeErros = 0;
    private boolean venceu;
    private Date tempoInicial = new Date();
    private Date tempoFinal = new Date();
    private DificuldadePartida dificuldade;
    private long score;

    public Partida(String nome, DificuldadePartida dificuldade) {
        this.tempoInicial.getTime();
        this.jogador = new Jogador(nome);
        this.dificuldade = dificuldade;
    }

    public Partida(String nome, DificuldadePartida dificuldade, int idade) {
        this.tempoInicial.getTime();
        this.jogador = new Jogador(nome, idade);
        this.dificuldade = dificuldade;
    }

    public void executaMovimento(int x, int y, int valor) throws MovimentoInvalidoException, MovimentoIncorretoException {
        try {
            boolean movimentoValido = this.tabuleiro.executaMovimento(x, y, valor);
            if (movimentoValido == true) {
                this.setScore(100);
                this.venceu = tabuleiro.isTabuleiroPreenchido();
            }
        } catch (MovimentoInvalidoException e1) {
            throw e1;
        } catch (MovimentoIncorretoException e2) {
            this.quantidadeErros += 1;
            throw e2;
        }
    }

    public boolean isFimDeJogo() {
        this.tempoFinal.getTime();
        if (this.quantidadeErros >= this.dificuldade.getQuantidadeMaximaErros()) {
            System.out.println("Errou mais do que podia. GAME OVER");
            return true;
        }
        return false;
    }

    public void iniciaPartida() {
        this.quantidadeErros = 0;
        this.tempoInicial = new Date();
        this.venceu = false;
        this.tabuleiro.geraTabuleiro(dificuldade);
        /*como a função de geraTabuleiro só tem assinatura, 
         *vou instanciar tabuleiro para ter um tabuleiro vazio*/
        this.tabuleiro = new Tabuleiro();
    }

    public String getNomeJogador() {
        return this.jogador.getNome();
    }

    public int getQuantidadeErros() {
        return this.quantidadeErros;
    }

    public int[][] getGridTabuleiro() {
        return tabuleiro.getGrid();
    }

    public int[][] getGabaritoTabuleiro() {
        return tabuleiro.getGabarito();
    }

    public int getQuantidadeMaximaErrosDificuldade() {
        return this.dificuldade.getQuantidadeMaximaErros();
    }

    public int getIdadeJogador() {
        return this.jogador.getIdade();
    }

    public void setIdadeJogador(int idade) {
        this.jogador.setIdade(idade);
    }

    public boolean getVenceu() {
        return this.venceu;
    }

    public void setVenceu(boolean venceu) {
        this.venceu = venceu;
    }

    public Date getTempoFinal() {
        return this.tempoFinal;
    }

    public Date getTempoInicial() {
        return this.tempoInicial;

    }

    public void setScore(long score) {
        this.score += score;
    }

    public long getScore() {
        return score;
    }

    public long diferencaTempo() {
        long tempo = tempoFinal.getTime() - tempoInicial.getTime();
        return tempo;
    }

}
