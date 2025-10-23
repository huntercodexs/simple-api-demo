package br.com.bradesco.cnsg.persisteposvenda.adapter.database.entity;

import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.IncluirTransacaoHubAverbDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.InclusoesPosVendaDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.HistoricoStatusDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.RetentativasDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "cJorndCsndo")
public class TabelaMongoDbHubAverb {

    @Id
    private String id;

    @Field("cProtTrans")
    private String cProtTrans;

    @Field("cRetorTrans")
    private String cRetorTrans;

    @Field("nPriorTrans")
    private Integer nPriorTrans;

    @Field("aHistEstTrans")
    private List<HistoricoStatusDTO> aHistEstTrans;

    @Field("sContdRquisTrans")
    private Object sContdRquisTrans;

    @Field("sTentvTrans")
    private RetentativasDTO sTentvTrans;

    @Field("sContdRespTrans")
    private Object sContdRespTrans;

    @Field("ultimoStatus")
    private HistoricoStatusDTO ultimoStatus;

    @Field("numContrBrad")
    private String numContrBrad;

    @Field("iJorndTrans")
    private String iJorndTrans;

    @Field("iProdtTrans")
    private String iProdtTrans;

    @Field("rProcmTrans")
    private String rProcmTrans;



    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getrProcmTrans() { return rProcmTrans; }

    public void setrProcmTrans(String rProcmTrans) { this.rProcmTrans = rProcmTrans; }

    public String getiJorndTrans() { return iJorndTrans; }

    public void setiJorndTrans(String iJorndTrans) { this.iJorndTrans = iJorndTrans; }

    public String getiProdtTrans() { return iProdtTrans; }

    public void setiProdtTrans(String iProdtTrans) { this.iProdtTrans = iProdtTrans; }

    public String getNumContrBrad() { return numContrBrad; }

    public void setNumContrBrad(String numContrBrad) { this.numContrBrad = numContrBrad; }

    public String getCProtTrans() { return cProtTrans; }

    public void setCProtTrans(String cProtTrans) { this.cProtTrans = cProtTrans; }

    public String getCRetorTrans() { return cRetorTrans; }

    public void setCRetorTrans(String cRetorTrans) { this.cRetorTrans = cRetorTrans; }

    public Integer getNPriorTrans() { return nPriorTrans; }

    public void setNPriorTrans(Integer nPriorTrans) { this.nPriorTrans = nPriorTrans; }

    public List<HistoricoStatusDTO> getAHistEstTrans() { return aHistEstTrans; }

    public void setAHistEstTrans(List<HistoricoStatusDTO> aHistEstTrans) { this.aHistEstTrans = aHistEstTrans; }

    public Object getSContdRquisTrans() { return sContdRquisTrans; }

    public void setSContdRquisTrans(Object sContdRquisTrans) { this.sContdRquisTrans = sContdRquisTrans; }

    public RetentativasDTO getSTentvTrans() { return sTentvTrans; }

    public void setSTentvTrans(RetentativasDTO sTentvTrans) { this.sTentvTrans = sTentvTrans; }

    public Object getSContdRespTrans() { return sContdRespTrans; }

    public void setSContdRespTrans(Object sContdRespTrans) { this.sContdRespTrans = sContdRespTrans; }

    public HistoricoStatusDTO getUltimoStatus() { return ultimoStatus; }

    public void setUltimoStatus(HistoricoStatusDTO ultimoStatus) { this.ultimoStatus = ultimoStatus; }


    public TabelaMongoDbHubAverb(IncluirTransacaoHubAverbDTO incluirTransacaoHubAverbDTO) {
        this.sContdRquisTrans =  incluirTransacaoHubAverbDTO.sContdRquisTrans();
        this.sContdRespTrans = incluirTransacaoHubAverbDTO.sContdRespTrans();
        this.cRetorTrans = incluirTransacaoHubAverbDTO.cRetorTrans();
        this.cProtTrans = incluirTransacaoHubAverbDTO.cProtTrans();
        this.iProdtTrans = incluirTransacaoHubAverbDTO.iProdtTrans();
        this.iJorndTrans = incluirTransacaoHubAverbDTO.iJorndTrans();
        this.rProcmTrans = incluirTransacaoHubAverbDTO.rProcmTrans();
        this.sTentvTrans = incluirTransacaoHubAverbDTO.sTentvTrans();
        this.nPriorTrans = incluirTransacaoHubAverbDTO.nPriorTrans();
        this.aHistEstTrans = incluirTransacaoHubAverbDTO.aHistEstTrans();

    }


    public TabelaMongoDbHubAverb() {
        // Construtor padrão necessário para o Spring Data MongoDB
    }

}
