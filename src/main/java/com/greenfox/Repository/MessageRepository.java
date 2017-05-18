package com.greenfox.Repository;

import com.greenfox.Model.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by georgezsiga on 5/18/17.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {

}
