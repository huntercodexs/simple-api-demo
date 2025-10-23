package br.com.bradesco.cnsg.persisteposvenda.adapter.database.repository.primary;

import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.ConteudoRespostaTransacaoDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.HistoricoStatusDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.entity.TabelaMongoDbHubAverb;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HubAverbRepositorio extends MongoRepository<TabelaMongoDbHubAverb, String> {

    @Aggregation(pipeline = {
            "{ \"$match\": { \"cProtTrans\": ?0 } }",
            "{ \"$project\": { \"_id\": 0,  \"sTentvTrans\": 1, \"nPriorTrans\" : 1 , \"numContrBrad\" : \"$sContdRquisTrans.numContratoBradesco\" , \"ultimoStatus\": { \"$arrayElemAt\": [\"$aHistEstTrans\", -1]}}}"
    })
    Optional<TabelaMongoDbHubAverb> findByNumeroProtocoloAgg(String cdProtocolo);

    @Query(value= "{ 'cProtTrans': ?0 }", fields = "{ '_id' : 0, 'sContdRespTrans' : 1 }")
    Optional<TabelaMongoDbHubAverb> findContRespByNumeroProtocolo(String cdProtocolo);

    @Query("{ \"cProtTrans\" : ?0, \"sTentvTrans.qTentvRstteTrans\" : { \"$gt\" : 0 } }")
    @Update("{ \"$inc\": { \"sTentvTrans.qTentvRstteTrans\" : -1 } }")
    void subtraiTentativa(String cdProtocolo);

    @Query("{ 'cProtTrans': ?0 }")
    @Update("{ $push: { aHistEstTrans: ?1 } }")
    void registraStatusTransacaoHub(String cdProtocolo, HistoricoStatusDTO historicoStatus);

    @Query("{ 'cProtTrans': ?0 }")
    @Update("{ $set: { sContdRespTrans: ?1 } }")
    void registraRetornoSolicitacaoDesaverb(String cdProtocolo, ConteudoRespostaTransacaoDTO conteudoRespostaTransacao);

}
