package com.greenfox.Repository;

import com.greenfox.Model.Message;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;
import org.hibernate.criterion.Distinct;
import org.hibernate.sql.Select;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by georgezsiga on 5/18/17.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {


  List<Message> findAllByOrderByTimestampDesc();

  List<Message> findAllByTimestampGreaterThan(@Param("timestamp") Timestamp timestamp);

  List<Message> findAllByUsername(@Param("username") String username);

  @Query("select DISTINCT username from Message order by username ASC")
  List<Message> findAllByOrderByTimestamp();

  Message findMessageById(@Param("id") Long id);

  List<Message> findAllByUsernameOrderByTimestampDesc(@Param("username") String username);

  List<Message> findAllByTimestampIsAfterOrderByTimestampDesc(@Param("timestamp") Timestamp timestamp);


}
