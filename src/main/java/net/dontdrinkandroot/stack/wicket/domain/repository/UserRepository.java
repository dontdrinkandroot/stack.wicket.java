package net.dontdrinkandroot.stack.wicket.domain.repository;

import net.dontdrinkandroot.stack.wicket.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);
}
