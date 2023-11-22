import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

public class DepartamentoTransito {
    List<Rodovia> rodovias = new ArrayList<>();
    List<Acidente> acidentes = new ArrayList<>();

    public void cadastrarRodovia() {
        String sigla = JOptionPane.showInputDialog("Digite a sigla da rodovia:");
        String periculosidade = JOptionPane.showInputDialog("Digite o nível de periculosidade da rodovia (alto, médio, baixo):");

        Rodovia rodovia = new Rodovia(sigla, periculosidade);
        rodovias.add(rodovia);

        JOptionPane.showMessageDialog(null, "Rodovia cadastrada com sucesso!");
    }

    public void cadastrarAcidente() {
        String siglaRodovia = JOptionPane.showInputDialog("Digite a sigla da rodovia do acidente:");
        Rodovia rodovia = null;

        for (Rodovia r : rodovias) {
            if (r.sigla.equalsIgnoreCase(siglaRodovia)) {
                rodovia = r;
                break;
            }
        }

        if (rodovia == null) {
            JOptionPane.showMessageDialog(null, "Rodovia não encontrada. Cadastre a rodovia antes de cadastrar o acidente.");
            return;
        }

        int vitimasFatais = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de vítimas fatais:"));
        int feridos = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de feridos:"));
        int mes = Integer.parseInt(JOptionPane.showInputDialog("Digite o mês do acidente:"));

        List<Veiculo> veiculosEnvolvidos = new ArrayList<>();
        int numeroVeiculos = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de veículos envolvidos no acidente:"));

        for (int i = 0; i < numeroVeiculos; i++) {
            int numeroOcupantes = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de ocupantes do veículo " + (i + 1) + ":"));
            List<Pessoa> ocupantesVeiculo = new ArrayList<>();

            for (int j = 0; j < numeroOcupantes; j++) {
                String nome = JOptionPane.showInputDialog("Digite o nome do ocupante " + (j + 1) + ":");
                int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade do ocupante " + (j + 1) + ":"));
                char sexo = JOptionPane.showInputDialog("Digite o sexo do ocupante " + (j + 1) + " (M/F):").charAt(0);
                boolean condutorEmbriagado = JOptionPane.showInputDialog("O ocupante " + (j + 1) + " era o condutor embriagado? (S/N):").equalsIgnoreCase("S");
                ocupantesVeiculo.add(new Pessoa(nome, idade, sexo, condutorEmbriagado));
            }

            boolean veiculocarga = JOptionPane.showInputDialog("O veiculo " + (i + 1) + " possuia carga? (S/N)").equalsIgnoreCase("S");
            int anoFabricacao = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de fabricação do veículo " + (i + 1) + ":"));
            boolean isBicicleta = JOptionPane.showInputDialog("O veiculo " + (i + 1) + " era uma bicicleta? (S/N)").equalsIgnoreCase("S");
            veiculosEnvolvidos.add(new Veiculo(anoFabricacao, ocupantesVeiculo, veiculocarga, isBicicleta));
        }

        Acidente acidente = new Acidente(rodovia, vitimasFatais, feridos, mes, veiculosEnvolvidos);
        acidentes.add(acidente);

        JOptionPane.showMessageDialog(null, "Acidente cadastrado com sucesso!");
    }

    public void listarAcidentesComCondutorEmbriagado() {
        StringBuilder result = new StringBuilder("Acidentes com condutor embriagado:\n");

        for (Acidente acidente : acidentes) {
            for (Veiculo veiculo : acidente.veiculosEnvolvidos) {
                for (Pessoa pessoa : veiculo.ocupantes) {
                    if (pessoa.condutorEmbriagado) {
                        result.append("Rodovia: ").append(acidente.rodovia.sigla).append("\n");
                        result.append("Mês: ").append(acidente.mes).append("\n");
                        result.append("Vítimas fatais: ").append(acidente.vitimasFatais).append("\n");
                        result.append("Feridos: ").append(acidente.feridos).append("\n\n");
                        break;
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(null, result.toString());
    }

    public void listarQuantidadeAcidentesPorPericulosidade() {
        HashMap<String, Integer> quantidadePorPericulosidade = new HashMap<>();

        for (Rodovia rodovia : rodovias) {
            quantidadePorPericulosidade.put(rodovia.periculosidade, 0);
        }

        for (Acidente acidente : acidentes) {
            String periculosidadeRodovia = acidente.rodovia.periculosidade;
            quantidadePorPericulosidade.put(periculosidadeRodovia, quantidadePorPericulosidade.get(periculosidadeRodovia) + 1);
        }

        StringBuilder result = new StringBuilder("Quantidade de acidentes por periculosidade:\n");
        for (String periculosidade : quantidadePorPericulosidade.keySet()) {
            result.append("Periculosidade: ").append(periculosidade).append(", Quantidade: ").append(quantidadePorPericulosidade.get(periculosidade)).append("\n");
        }

        JOptionPane.showMessageDialog(null, result.toString());
    }

    public void mostrarVeiculosDeCargaEnvolvidosEmAcidentes() {
        StringBuilder result = new StringBuilder("Veículos de carga envolvidos em acidentes:\n");

        for (Acidente acidente : acidentes) {
            for (Veiculo veiculo : acidente.veiculosEnvolvidos) {
                if (veiculo.isVeiculoDeCarga) {
                    result.append("Rodovia: ").append(acidente.rodovia.sigla).append("\n");
                    result.append("Mês: ").append(acidente.mes).append("\n");
                    result.append("Veículo de Carga - Ano de Fabricação: ").append(veiculo.anoFabricacao);
                }
            }
        }

        JOptionPane.showMessageDialog(null, result.toString());
    }

    public void mostrarRodoviaComMaisAcidentesComBicicletas() {
        HashMap<String, Integer> quantidadePorRodovia = new HashMap<>();

        for (Rodovia rodovia : rodovias) {
            quantidadePorRodovia.put(rodovia.sigla, 0);
        }

        for (Acidente acidente : acidentes) {
            for (Veiculo veiculo : acidente.veiculosEnvolvidos) {
                if (veiculo.isBicicleta) {
                    quantidadePorRodovia.put(acidente.rodovia.sigla, quantidadePorRodovia.get(acidente.rodovia.sigla) + 1);
                    break;
                }
            }
        }

        String rodoviaComMaisAcidentes = null;
        int quantidadeMaxima = 0;

        for (String rodovia : quantidadePorRodovia.keySet()) {
            int quantidadeAcidentes = quantidadePorRodovia.get(rodovia);
            if (quantidadeAcidentes > quantidadeMaxima) {
                quantidadeMaxima = quantidadeAcidentes;
                rodoviaComMaisAcidentes = rodovia;
            }
        }

        if (rodoviaComMaisAcidentes != null) {
            JOptionPane.showMessageDialog(null, "Rodovia com mais acidentes com bicicletas: " + rodoviaComMaisAcidentes);
        } else {
            JOptionPane.showMessageDialog(null, "Não há informações suficientes para determinar a rodovia com mais acidentes com bicicletas.");
        }
    }

    public void mostrarRodoviaComMaisAcidentesComVitimasFatais() {
        HashMap<String, Integer> vitimasFataisPorRodovia = new HashMap<>();

        for (Rodovia rodovia : rodovias) {
            vitimasFataisPorRodovia.put(rodovia.sigla, 0);
        }

        for (Acidente acidente : acidentes) {
            vitimasFataisPorRodovia.put(acidente.rodovia.sigla, vitimasFataisPorRodovia.get(acidente.rodovia.sigla) + acidente.vitimasFatais);
        }

        String rodoviaComMaisVitimasFatais = null;
        int quantidadeMaxima = 0;

        for (String rodovia : vitimasFataisPorRodovia.keySet()) {
            int quantidadeVitimasFatais = vitimasFataisPorRodovia.get(rodovia);
            if (quantidadeVitimasFatais > quantidadeMaxima) {
                quantidadeMaxima = quantidadeVitimasFatais;
                rodoviaComMaisVitimasFatais = rodovia;
            }
        }

        if (rodoviaComMaisVitimasFatais != null) {
            JOptionPane.showMessageDialog(null, "Rodovia com mais acidentes com vítimas fatais: " + rodoviaComMaisVitimasFatais);
        } else {
            JOptionPane.showMessageDialog(null, "Não há informações suficientes para determinar a rodovia com mais acidentes com vítimas fatais.");
        }
    }

    public void listarAcidentesComVeiculosNovos() {
        int quantidadeAcidentesVeiculosNovos = 0;

        for (Acidente acidente : acidentes) {
            for (Veiculo veiculo : acidente.veiculosEnvolvidos) {
                if (veiculo.anoFabricacao >= 2013) {
                    quantidadeAcidentesVeiculosNovos++;
                    break;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Quantidade de acidentes com veículos novos (ano de 2013 em diante): " + quantidadeAcidentesVeiculosNovos);
    }

    public void listarRodoviasComAcidentesNoCarnaval() {
        List<String> rodoviasNoCarnaval = new ArrayList<>();

        for (Acidente acidente : acidentes) {
            if (acidente.mes == 2) {  // Verifica se o acidente ocorreu em fevereiro (carnaval)
                rodoviasNoCarnaval.add(acidente.rodovia.sigla);
            }
        }

        if (!rodoviasNoCarnaval.isEmpty()) {
            StringBuilder result = new StringBuilder("Rodovias que registraram acidentes no carnaval (fevereiro):\n");
            for (String siglaRodovia : rodoviasNoCarnaval) {
                result.append(siglaRodovia).append("\n");
            }
            JOptionPane.showMessageDialog(null, result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Não houve registros de acidentes no carnaval.");
        }
    }
}