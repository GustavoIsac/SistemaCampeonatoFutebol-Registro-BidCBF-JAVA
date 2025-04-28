package Br.Com.ProzEducação.SistemaCampeonatoBidCBF;

public class Partida {
    private Time timeCasa;
    private Time timeVisitante;
    private int golsCasa;
    private int golsVisitante;

    public Partida(Time timeCasa, Time timeVisitante, int golsCasa, int golsVisitante) {
        if(timeCasa == null || timeVisitante == null) {
            throw new IllegalArgumentException("Times não podem ser nulos");
        }
        if(timeCasa.equals(timeVisitante)) {
            throw new IllegalArgumentException("Um time não pode jogar contra si mesmo");
        }
        if(golsCasa < 0 || golsVisitante < 0) {
            throw new IllegalArgumentException("Número de gols não pode ser negativo");
        }
        
        this.timeCasa = timeCasa;
        this.timeVisitante = timeVisitante;
        this.golsCasa = golsCasa;
        this.golsVisitante = golsVisitante;
    }

    public String getResultado() {
        if (golsCasa > golsVisitante) {
            return timeCasa.getNome() + " venceu!";
        } else if (golsVisitante > golsCasa) {
            return timeVisitante.getNome() + " venceu!";
        } else {
            return "Empate!";
        }
    }

    public String getInfoPartida() {
        return String.format("%s %d x %d %s - %s",
            timeCasa.getNome(), golsCasa, golsVisitante,
            timeVisitante.getNome(), getResultado());
    }

    // CUIDADO NA PARTE GET, EVITAR ALTERAR SEM A CERTEZA 
    // CODIGO ESTA IDENTADO DESSA FORMA PQ EU ACHEI MELHOR, SEI QUE E ERRADO MAS EU NÃO VEJO A NECESSIDADE DE IDENTAR JA QUE ESSA AREA NÃO VAI SER MUDADA
    public Time getTimeCasa() { return timeCasa; }
    public Time getTimeVisitante() { return timeVisitante; }
    public int getGolsCasa() { return golsCasa; }
    public int getGolsVisitante() { return golsVisitante; }
}
