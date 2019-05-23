package io.msa.content.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.msa.content.models.Content;

@Repository
public interface ContentRepository extends CrudRepository<Content, Long> {

	public List<Content> findByCtnId(Long ctnId);

	@Query(value = "SELECT * FROM content  WHERE lower(name) LIKE LOWER(CONCAT('%' , ?1 , '%'))", nativeQuery = true)
	public List<Content> findByName(String name);


}
