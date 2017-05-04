package com.smartholiday.test.anki;

public class Card implements Comparable<Card> {

	private String question;
	
	private String answer;
	
	public Card(String line) {
		prepare(line);
	}

	private void prepare(String line) {
		String[] split = line.split("[|]");
		if (split.length != 2) {
			throw new AnkiException("Invaid card: " + line);
		}
		question = split[0];
		answer = split[1];
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	@Override
	public int compareTo(Card o) {
		return question.compareTo(o.question);
	}

	public void ask() {
		System.out.println("QUESTION: " + question);
	}

	public void showAnswer() {
		System.out.println("ANSWER: " + answer);
	}
	
	@Override
	public String toString() {
		return question + "|" + answer;
	}
}
