package br.com.bradesco.cnsg.persisteposvenda.adapter.database.repository.secondary;

import br.com.bradesco.cnsg.persisteposvenda.adapter.database.entity.TabelaMongoDbEntidade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasePosVendaRepositorio extends MongoRepository<TabelaMongoDbEntidade, String> {

    Optional<TabelaMongoDbEntidade> findByNumeroContrato(String numeroContrato);

}
