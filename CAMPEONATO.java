package Br.Com.ProzEducação.SistemaCampeonatoBidCBF;

import java.util.ArrayList;

//DECIDI RETIRAR A CLASSE SISTEMACAMPEONATO PORQUE NA HORA QUE EU FUI CRIAR UM DESIGNE MENOS FEIO PARA O MENU O CODIGO FUGIL DA "POO" ENTÃO DECIDI SEPARAR ISSO. A CLASSE PRINCIPAL QUE ANTES ERA SISTEMACAMPEONATO SE TORNOU SISTEMA UI

public class Campeonato {
    private ArrayList<Time> times;
    private ArrayList<Partida> partidas;

    public Campeonato() {
        this.times = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }

    public void adicionarTime(Time time) {
        if(time == null) {
            throw new IllegalArgumentException("Time não pode ser nulo");
        }
        if(times.contains(time)) {
            throw new IllegalArgumentException("Time já cadastrado");
        }
        times.add(time);
    }

    public void adicionarJogador(Jogador jogador) {
        if(jogador == null) {
            throw new IllegalArgumentException("Jogador não pode ser nulo");
        }
        if(!times.contains(jogador.getTime())) {
            throw new IllegalArgumentException("Time do jogador não está no campeonato");
        }
        jogador.getTime().adicionarJogador(jogador);
    }

    public void registrarPartida(Partida partida) {
        if(partida == null) {
            throw new IllegalArgumentException("Partida não pode ser nula");
        }
        if(!times.contains(partida.getTimeCasa()) || !times.contains(partida.getTimeVisitante())) {
            throw new IllegalArgumentException("Times da partida devem estar cadastrados");
        }
        partidas.add(partida);
    }

    public String listarTimes() {
        if (times.isEmpty()) {
            return "Nenhum time cadastrado!";
        }

        StringBuilder lista = new StringBuilder();
        for (Time time : times) {
            lista.append(time.getInfoTime()).append("\n");
        }
        return lista.toString();
    }

    public String listarJogadoresTime(Time time) {
        if(time == null) {
            throw new IllegalArgumentException("Time não pode ser nulo");
        }
        return time.listarJogadores();
    }

    public String listarPartidas() {
        if (partidas.isEmpty()) {
            return "Nenhuma partida registrada!";
        }

        StringBuilder lista = new StringBuilder();
        for (Partida partida : partidas) {
            lista.append(partida.getInfoPartida()).append("\n");
        }
        return lista.toString();
    }

    // NÃO MEXER NO GET PELAMORDEDEUS!
    public ArrayList<Time> getTimes() { return new ArrayList<>(times); }
    public ArrayList<Partida> getPartidas() { return new ArrayList<>(partidas); }
}
