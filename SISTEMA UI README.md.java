# SistemaCampeonatoFutebol-Registro-BidCBF-JAVA
#Trabalho da disciplina de Programação orientada a objetos 

package Br.Com.ProzEducação.SistemaCampeonatoBidCBF;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class SistemaUI {
    private Campeonato campeonato;

    public SistemaUI() {
        this.campeonato = new Campeonato();
    }

    public void executar() {
        String mensagemMenu = criarMenu();
        
        int opcao;
        do {
            String input = JOptionPane.showInputDialog(null, mensagemMenu, 
                    "Sistema de Campeonato", JOptionPane.PLAIN_MESSAGE);
            
            opcao = processarInput(input);

            switch (opcao) {
                case 1: cadastrarTime(); break;
                case 2: cadastrarJogador(); break;
                case 3: registrarPartida(); break;
                case 4: listarTimes(); break;
                case 5: listarJogadoresTime(); break;
                case 6: listarPartidas(); break;
                case 0: mostrarMensagemSaida(); break;
                default: mostrarErro("Opção inválida! Por favor, digite um número de 0 a 6.");
            }
        } while (opcao != 0);
    }

    
    // ESSE NINHO DE BAGUNÇA É O HTML/CSS PARA JAVA PARECE BAGUNÇADO MAS TA ORGANIZADO. NÃO SEI COMO EU PODERIA LEVAR ISSO PRA OUTRAS CLASSES A NÃO SER A PRINCIPAL
    private String criarMenu() {
        return "<html><div style='text-align: center; width: 300px;'>"
                + "<h2><font color='#0066CC'>⚽ GERENCIADOR DE CAMPEONATO ⚽</font></h2>"
                + "<hr>"
                + "<p style='text-align: left; margin-left: 20px;'>"
                + "<b><font color='#333333'>1.</font></b> Cadastrar Time<br>"
                + "<b><font color='#333333'>2.</font></b> Cadastrar Jogador<br>"
                + "<b><font color='#333333'>3.</font></b> Registrar Partida<br>"
                + "<b><font color='#333333'>4.</font></b> Listar Times<br>"
                + "<b><font color='#333333'>5.</font></b> Listar Jogadores<br>"
                + "<b><font color='#333333'>6.</font></b> Listar Partidas<br>"
                + "<b><font color='#FF0000'>0.</font></b> Sair"
                + "</p>"
                + "<hr>"
                + "<p>Digite o número da opção:</p>"
                + "</div></html>";
    }

    private int processarInput(String input) {
        if (input == null) return 0;
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void cadastrarTime() {
        try {
            String nome = JOptionPane.showInputDialog("Nome do Time:");
            String cidade = JOptionPane.showInputDialog("Cidade do Time:");
            
            Time novoTime = new Time(nome, cidade);
            campeonato.adicionarTime(novoTime);
            
            mostrarSucesso("Time cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void cadastrarJogador() {
        try {
            if (campeonato.getTimes().isEmpty()) {
                mostrarErro("Cadastre um time primeiro!");
                return;
            }

            String nome = JOptionPane.showInputDialog("Nome do Jogador:");
            int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade do Jogador:"));
            
            Jogador.Posicao posicao = selecionarPosicao();
            if(posicao == null) return;
            
            Time time = selecionarTime();
            if (time == null) return;

            Jogador jogador = new Jogador(nome, idade, posicao, time);
            campeonato.adicionarJogador(jogador);
            
            mostrarSucesso("Jogador cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            mostrarErro("Idade deve ser um número válido!");
        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void registrarPartida() {
        try {
            if (campeonato.getTimes().size() < 2) {
                mostrarErro("Cadastre pelo menos 2 times!");
                return;
            }

            mostrarMensagem("Selecione o time da casa:");
            Time timeCasa = selecionarTime();
            if (timeCasa == null) return;

            mostrarMensagem("Selecione o time visitante:");
            Time timeVisitante = selecionarTime();
            if (timeVisitante == null) return;

            int golsCasa = Integer.parseInt(JOptionPane.showInputDialog("Gols do " + timeCasa.getNome() + ":"));
            int golsVisitante = Integer.parseInt(JOptionPane.showInputDialog("Gols do " + timeVisitante.getNome() + ":"));

            Partida partida = new Partida(timeCasa, timeVisitante, golsCasa, golsVisitante);
            campeonato.registrarPartida(partida);
            
            mostrarSucesso("Partida registrada:\n" + partida.getInfoPartida());
        } catch (NumberFormatException e) {
            mostrarErro("Número de gols deve ser um valor inteiro!");
        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void listarTimes() {
        mostrarMensagem(campeonato.listarTimes());
    }

    private void listarJogadoresTime() {
        Time time = selecionarTime();
        if (time != null) {
            mostrarMensagem("JOGADORES DO " + time.getNome() + ":\n\n" + 
                           campeonato.listarJogadoresTime(time));
        }
    }

    private void listarPartidas() {
        mostrarMensagem(campeonato.listarPartidas());
    }

    private Time selecionarTime() {
        ArrayList<Time> times = campeonato.getTimes();
        
        if (times.isEmpty()) {
            mostrarErro("Nenhum time cadastrado!");
            return null;
        }

        String[] opcoes = new String[times.size()];
        for (int i = 0; i < times.size(); i++) {
            opcoes[i] = times.get(i).getNome() + " - " + times.get(i).getCidade();
        }

        int escolha = JOptionPane.showOptionDialog(
            null, 
            "Selecione um time:", 
            "Times", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opcoes, 
            opcoes[0]);

        return (escolha >= 0 && escolha < times.size()) ? times.get(escolha) : null;
    }

    private Jogador.Posicao selecionarPosicao() {
        Jogador.Posicao[] posicoes = Jogador.Posicao.values();
        String[] opcoes = new String[posicoes.length];
        
        for(int i = 0; i < posicoes.length; i++) {
            opcoes[i] = posicoes[i].name();
        }

        int escolha = JOptionPane.showOptionDialog(
            null, 
            "Selecione a posição:", 
            "Posições", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opcoes, 
            opcoes[0]);

        return (escolha >= 0 && escolha < posicoes.length) ? posicoes[escolha] : null;
    }

    private void mostrarSucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

    private void mostrarMensagemSaida() {
        JOptionPane.showMessageDialog(null, 
            "<html><div style='text-align: center;'>"
            + "<h3><font color='#0066CC'>Programa encerrado</font></h3>"
            + "Obrigado por usar o sistema!"
            + "</div></html>", 
            "Sair", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new SistemaUI().executar();
    }
}
