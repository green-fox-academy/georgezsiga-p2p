package com.greenfox.Repository;

import com.greenfox.Model.Message;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by georgezsiga on 5/18/17.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findAllByOrderByTimestampDesc();

//  @Query("SELECT DISTINCT username, timestamp FROM Message order by timestamp DESC")

  List<Message> findAllByUsername(@Param("username") String username);

//  @Query("select DISTINCT username from Message order by username ASC")
//  List<Message> findAllByOrderByTimestamp();

//  @Query("select DISTINCT username from Message order by username ASC")
//  List<Message> findTop25ByOrderByTimestamp();

  List<Message> findAllByUsernameOrderByTimestampDesc(@Param("username") String username);

  List<Message> findAllByTimestampIsAfterOrderByTimestampDesc(@Param("timestamp") Timestamp timestamp);

  List<Message> findTop10ByOrderByTimestampDesc();

  List<Message> findTop25ByOrderByTimestampDesc();
}
