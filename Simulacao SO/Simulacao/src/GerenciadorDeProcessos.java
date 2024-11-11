import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GerenciadorDeProcessos {
    private static final int QUANTUM = 1000;
    private List<Processo> processos;
    private Random random;

    public GerenciadorDeProcessos(List<Processo> processos) {
        this.processos = processos;
        this.random = new Random();
    }

    public void executar() {
        while (!processos.isEmpty()) {
            for (int i = 0; i < processos.size(); i++) {
                Processo processo = processos.get(i);

                if ("Pronto".equals(processo.getEp())) {
                    executarProcesso(processo, i);
                } else if ("Bloqueado".equals(processo.getEp())) {
                    tentarDesbloquearProcesso(processo);
                }
            }
        }
    }

    private void executarProcesso(Processo processo, int index) {
        processo.setEp("Executando");
        System.out.println("\n" + processo.getPid() + " PRONTO >>> EXECUTANDO");
        salvarTabela(processo);

        int ciclosRestantes = Math.min(QUANTUM, processo.getTempoTotal() - processo.getTp());
        for (int j = 0; j < ciclosRestantes; j++) {
            processo.incrementarTp();

            // chance de operação de E/S (1%)
            if (random.nextInt(100) < 1) {
                processo.setEp("Bloqueado");
                processo.incrementarNes();
                System.out.println(processo.getPid() + " EXECUTANDO >>> BLOQUEADO");
                salvarTabela(processo);
                return;
            }
        }

        processo.incrementarNCpu();
        if (processo.isConcluido()) {
            System.out.println(processo + "\nProcesso " + processo.getPid() + " concluído.");
            salvarTabela(processo);
            processos.remove(index);
        } else {
            processo.setEp("Pronto");
            System.out.println(processo.getPid() + " EXECUTANDO >>> PRONTO");
            salvarTabela(processo);
        }
    }

    private void tentarDesbloquearProcesso(Processo processo) {
        // 30% de chance de voltar para Pronto
        if (random.nextInt(100) < 30) {
            processo.setEp("Pronto");
            System.out.println(processo.getPid() + " BLOQUEADO >>> PRONTO");
            salvarTabela(processo);
        }
    }

    private void salvarTabela(Processo processo) {
        try (FileWriter writer = new FileWriter("tabela_processos.txt", true)) {
            writer.write(processo.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

