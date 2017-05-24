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

//  @Query("select distinct username from Message")
  List<Message> findAllByOrderByTimestampDesc();

  List<Message> findAllByUsername(@Param("username") String username);

//  @Query("select DISTINCT username, timestamp from Message order by timestamp desc")
//  List<Message> methodname();

  @Query("select DISTINCT username from Message order by username ASC")
  List<Message> findAllByOrderByTimestamp();

  Message findMessageById(@Param("id") Long id);

//
//  @Query(""
//      + "SELECT dataAll.username, dataAll.timestamp "
//      + "FROM message dataAll "
//      + "INNER JOIN (SELECT dataSorted.username, MAX(timestamp) AS MaxTimestamp "
//      + "FROM message "
//      + "GROUP BY username) dataSorted "
//      + "ON dataAll.username = dataSorted.username "
//      + "AND dataAll.timestamp = dataSorted.MaxTimeStamp")
//  List<Message> findAllByUsername();

//  @Query("SELECT username, timestamp FROM message WHERE timestamp = (SELECT MAX(timestamp) AS MaxTimeStamp FROM message)")
//  List<Message> findAllByUsername();

//  List<Message> findDistinctByUsername();


//  List<Message> findFirstByUsernameOrderByTimestampDesc();
//  List<Message> findDistinctByUsernameOrderByTimestampDesc(@Param("username") String username);
//  List<Message> findDistinctByUsername();

//  @Query("select distinct username from Message")
//  List<Message> findAllOrderByTimestamp();
//
//  @Query("select distinct username from Message")
//  List<Message> findAllByUsernameOrderByTimestamp();

//  List<Message> findAllByUsernameAndTimestampAfter(@Param("timestamp")Timestamp timestamp);

  List<Message> findAllByTimestampIsAfterOrderByTimestampDesc(@Param("timestamp") Timestamp timestamp);

//  List<Message> findFirstByUsernameOrderByTimestamp();

  List<Message> findAllByUsernameOrderByTimestampDesc(@Param("username") String username);

}
