package Br.Com.ProzEducação.SistemaCampeonatoBidCBF;

public class Jogador {
    public enum Posicao {
        GOLEIRO, ZAGUEIRO, LATERAL, VOLANTE, MEIA, ATACANTE
    }

    private String nome;
    private int idade;
    private Posicao posicao;
    private Time time;

    public Jogador(String nome, int idade, Posicao posicao, Time time) {
        if(nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do jogador não pode ser vazio");
        }
        if(idade < 16 || idade > 50) {
            throw new IllegalArgumentException("Idade do jogador deve ser entre 16 e 50 anos");
        }
        if(posicao == null) {
            throw new IllegalArgumentException("Posição não pode ser nula");
        }
        if(time == null) {
            throw new IllegalArgumentException("Time não pode ser nulo");
        }
        
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.time = time;
    }

    public String getInfoJogador() {
        return nome + " (" + posicao + ") - " + idade + " anos";
    }

    // CUIDADO NA PARTE GET, EVITAR ALTERAR SEM A CERTEZA 
    // CODIGO ESTA IDENTADO DESSA FORMA PQ EU ACHEI MELHOR, SEI QUE E ERRADO MAS EU NÃO VEJO A NECESSIDADE DE IDENTAR JA QUE ESSA AREA NÃO VAI SER MUDADA 
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public Posicao getPosicao() { return posicao; }
    public Time getTime() { return time; }
}
