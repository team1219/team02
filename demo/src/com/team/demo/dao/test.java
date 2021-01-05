package com.team.demo.dao;

import java.util.concurrent.ThreadLocalRandom;

public class test {

	public static void main(String [] args) {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 78 + 1);
		System.out.println(randomNum);
	}
}
