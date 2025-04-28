package Br.Com.ProzEducação.SistemaCampeonatoBidCBF;

import java.util.ArrayList;

//USAR SOMENTE ARRAY OU ARRAY LIST LEMBRAR DISSO 

//O CODIGO ESTA IDENTADO DESSA FORMA PORQUE EU ACHEI MELHOR

public class Time {
    private String nome;
    private String cidade;
    private ArrayList<Jogador> jogadores;

    public Time(String nome, String cidade) {
        if(nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do time não pode ser vazio");
        }
        this.nome = nome;
        this.cidade = cidade;
        this.jogadores = new ArrayList<>();
    }

    public void adicionarJogador(Jogador jogador) {
        if(jogador == null) {
            throw new IllegalArgumentException("Jogador não pode ser nulo");
        }
        jogadores.add(jogador);
    }

    public String listarJogadores() {
        if (jogadores.isEmpty()) {
            return "Nenhum jogador cadastrado neste time!";
        }

        StringBuilder lista = new StringBuilder();
        for (Jogador jogador : jogadores) {
            lista.append(jogador.getInfoJogador()).append("\n");
        }
        return lista.toString();
    }

    public int getTotalJogadores() {
        return jogadores.size();
    }

    public double getMediaIdade() {
        if(jogadores.isEmpty()) return 0;
        int soma = 0;
        for(Jogador jogador : jogadores) {
            soma += jogador.getIdade();
        }
        return (double) soma / jogadores.size();
    }

    //AVISO: NÃO ALTERAR NADA NA CLASSE TIME, MOTIVO VAI DAR ERRO NO PRINCIPAL
    //ATENÇÃO A IDENTAÇÃO E AS CHAVES PRA NÃO FAZER BAGUNÇA
    public String getNome() { return nome; }
    public String getCidade() { return cidade; }
    public ArrayList<Jogador> getJogadores() { return new ArrayList<>(jogadores); }

    public String getInfoTime() {
        return nome + " (" + cidade + ") - " + getTotalJogadores() + " jogadores";
    }
}
