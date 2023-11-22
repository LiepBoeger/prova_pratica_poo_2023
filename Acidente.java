import java.util.List;

class Acidente {
    Rodovia rodovia;
    int vitimasFatais;
    int feridos;
    int mes;
    List<Veiculo> veiculosEnvolvidos;

    Acidente(Rodovia rodovia, int vitimasFatais, int feridos, int mes, List<Veiculo> veiculosEnvolvidos) {
        this.rodovia = rodovia;
        this.vitimasFatais = vitimasFatais;
        this.feridos = feridos;
        this.mes = mes;
        this.veiculosEnvolvidos = veiculosEnvolvidos;
    }
}