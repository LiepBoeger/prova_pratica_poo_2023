import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        DepartamentoTransito departamentoTransito = new DepartamentoTransito();

        while (true) {
            String opcao = JOptionPane.showInputDialog("Escolha uma opção:\n1 - Cadastrar Rodovia\n2 - Cadastrar Acidente\n3 - Listar Acidentes com Condutor Embriagado\n4 - Listar Quantidade de Acidentes por Periculosidade\n5 - Mostrar Veículos de Carga Envolvidos em Acidentes\n0 - Sair");

            switch (opcao) {
                case "1":
                    departamentoTransito.cadastrarRodovia();
                    break;
                case "2":
                    departamentoTransito.cadastrarAcidente();
                    break;
                case "3":
                    departamentoTransito.listarAcidentesComCondutorEmbriagado();
                    break;
                case "4":
                    departamentoTransito.listarQuantidadeAcidentesPorPericulosidade();
                    break;
                case "5":
                    departamentoTransito.mostrarVeiculosDeCargaEnvolvidosEmAcidentes();
                    break;
                case "6":
                    departamentoTransito.mostrarRodoviaComMaisAcidentesComBicicletas();
                    break;
                case "7":
                    departamentoTransito.mostrarRodoviaComMaisAcidentesComVitimasFatais();
                    break;
                case "8":
                    departamentoTransito.listarAcidentesComVeiculosNovos();
                    break;
                case "9":
                    departamentoTransito.listarRodoviasComAcidentesNoCarnaval();
                    break;
                case "0":
                    JOptionPane.showMessageDialog(null, "Saindo do programa.");
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            }
        }
    }
}

