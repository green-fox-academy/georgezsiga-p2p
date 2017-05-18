package com.greenfox.Repository;

import com.greenfox.Model.Felhasznalo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by georgezsiga on 5/17/17.
 */
public interface UserRepository extends CrudRepository<Felhasznalo, Long> {

  Felhasznalo findFirstByOrderByLastActiveDesc();

  List<Felhasznalo> findByUsername(String username);

//  Felhasznalo  findFelhasznaloByUsername(String username);

  Boolean findFirstByUsernameEquals(String username);

//  Boolean findByUsernameExists(String username);

  Felhasznalo findById(long id);

  Felhasznalo findFelhasznaloByUsername(@Param("username") String username);
  Felhasznalo findFelhasznaloById(@Param("id") Long id);

}
