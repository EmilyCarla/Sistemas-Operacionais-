public class Processo {
    private int pid; // identificador de processos
    private int tp; // tempo de processamento
    private int cp; // contador de Programa
    private String ep; // estado do processo
    private int nes; // número de operações de E/S
    private int n_cpu; // número de vezes que usou a CPU
    private int tempoTotal; // Tempo total de execução necessário

    public Processo(int pid, int tempoTotal) {
        this.pid = pid;
        this.tp = 0;
        this.cp = this.tp + 1;
        this.ep = "Pronto";
        this.nes = 0;
        this.n_cpu = 0;
        this.tempoTotal = tempoTotal;
    }

    public int getPid() { return pid; }
    public int getTp() { return tp; }
    public int getCp() { return cp; }
    public String getEp() { return ep; }
    public int getNes() { return nes; }
    public int getNCpu() { return n_cpu; }
    public int getTempoTotal() { return tempoTotal; }
    
    public void setEp(String ep) { this.ep = ep; }
    public void incrementarTp() { this.tp++; this.cp = this.tp + 1; }
    public void incrementarNes() { this.nes++; }
    public void incrementarNCpu() { this.n_cpu++; }
    
    public boolean isConcluido() {
        return tp >= tempoTotal;
    }

    @Override
    public String toString() {
        return "PID: " + pid + ", TP: " + tp + ", CP: " + cp + ", EP: " + ep +
               ", NES: " + nes + ", N_CPU: " + n_cpu;
    }
}

