package io.msa.context.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.msa.context.models.Context;

@Repository
public interface ContextRepository extends MongoRepository<Context, Long> {

	public Context findById(Long id);

	public Context deleteById(Long id);

}
