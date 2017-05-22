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
//TODO after login, show only the new messages since last login
//TODO only list first few messages
