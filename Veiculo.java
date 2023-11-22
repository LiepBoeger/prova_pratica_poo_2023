import java.util.List;

class Veiculo {
    int anoFabricacao;
    List<Pessoa> ocupantes;
    boolean isVeiculoDeCarga;
    boolean isBicicleta;

    Veiculo(int anoFabricacao, List<Pessoa> ocupantes, boolean isVeiculoDeCarga, boolean isBicicleta) {
        this.anoFabricacao = anoFabricacao;
        this.ocupantes = ocupantes;
        this.isVeiculoDeCarga = isVeiculoDeCarga;
        this.isBicicleta = isBicicleta;
    }
}