package com.smartholiday.test.anki;

import java.util.Scanner;

import org.apache.commons.lang.ArrayUtils;

public class UserInput {

	private Scanner scanner;

	public UserInput() {
		scanner = new Scanner(System.in);
	}

	public String read() {
		return scanner.nextLine();
	}

	public String read(String question, String[] validAnswers) {
		String answer = null;
		while (!ArrayUtils.contains(validAnswers, answer)) {
			System.out.print(question);
			answer = read();
		}
		return answer;
	}
}
