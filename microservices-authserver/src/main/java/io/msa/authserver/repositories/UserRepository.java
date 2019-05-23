package io.msa.authserver.repositories;

import org.springframework.data.repository.CrudRepository;

import io.msa.authserver.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);

	public User findByUsernameAndActivationKey(String username, String activationKey);

}
