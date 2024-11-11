import java.util.ArrayList;
import java.util.List;

public class SimuladorSO {
    public static void main(String[] args) {
        int[] temposExecucao = {10000, 5000, 7000, 3000, 3000, 8000, 2000, 5000, 4000, 10000};
        List<Processo> processos = new ArrayList<>();

        for (int i = 0; i < temposExecucao.length; i++) {
            processos.add(new Processo(i, temposExecucao[i]));
        }

        GerenciadorDeProcessos gerenciador = new GerenciadorDeProcessos(processos);
        gerenciador.executar();
    }
}

