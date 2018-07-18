package com.gag.model;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.gag.model.dao.UserDAO;

@Component
public class Checker extends Thread{

	public Checker() {
		this.setDaemon(true);
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(24*60*60*1000);
				UserDAO.USER_DAO.deleteInactiveUsers();
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	@PostConstruct
	public synchronized void start() {
		super.start();
	}
	
	@Override
	@PreDestroy
	public void interrupt() {
		super.interrupt();
	}
}
