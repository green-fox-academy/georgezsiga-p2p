package com.greenfox;

import com.greenfox.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatappApplication implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChatappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
//TODO only list first few messages
//TODO max 10 message, arrows at bottom
//TODO change timestamp to environmental time
//TODO check for already arrived message... timestamp, text..
//TODO add change send to environmental variable
//TODO links enum
//TODO filter
//TODO hide future messages

