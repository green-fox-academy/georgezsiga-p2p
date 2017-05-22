package com.greenfox.Repository;

import com.greenfox.Model.Message;
import java.util.List;
import org.hibernate.criterion.Distinct;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by georgezsiga on 5/18/17.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {


  List<Message> findAllByOrderByTimestampDesc();
//  List<Message> findDistinctByUsernameOrderByTimestampDesc(@Param("username") String username);
//  List<Message> findDistinctByUsername();

  @Query("select distinct username from Message")
  List<Message> findAllByUsernameOrderByTimestamp();

//  List<Message> findFirstByUsernameOrderByTimestamp();

  List<Message> findAllByUsernameOrderByTimestampDesc(@Param("username") String username);

}
