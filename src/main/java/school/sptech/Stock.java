package school.sptech;

public class Stock {
    // Referênciando elementos CSV
    private Integer idDados;
    private String dataHora;
    private Double percCPU;
    private Double tempoInativo;
    private Double percRAM;
    private Double percDisc;
    private Long usedDisc;
    private Integer fkNotebook;

    // Getters and setters for generate object

    public Integer getIdDados() { return idDados; }
    public void setIdDados(Integer idDados) { this.idDados = idDados; }

    public String getDataHora() { return dataHora;}
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }

    public Double getPercCPU() { return percCPU; }
    public void setPercCPU(double percCPU) { this.percCPU = percCPU; }

    public Double getTempoInativo() { return tempoInativo;}
    public void setTempoInativo(Double tempoInativo) { this.tempoInativo = tempoInativo; }

    public Double getPercRAM() { return percRAM; }
    public void setPercRAM(Double percRAM) { this.percRAM = percRAM; }

    public Double getPercDisc() { return percDisc; }
    public void setPercDisc(Double percDisc) { this.percDisc = percDisc; }

    public long getUsedDisc() { return usedDisc; }
    public void setUsedDisc(long usedDisc) { this.usedDisc = usedDisc; }

    public Integer getFkNotebook() { return fkNotebook; }
    public void setFkNotebook(Integer fkNotebook) { this.fkNotebook = fkNotebook; }
}