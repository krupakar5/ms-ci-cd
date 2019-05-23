package io.msa.authserver.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.msa.authserver.model.Role;

@Repository
public interface RoleRepositroy extends CrudRepository<Role, Long> {

}
