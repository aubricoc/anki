package com.smartholiday.test.anki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private static final String FILE_HEADER = "card question|card answer";
	private static final String BOX_SEPARATOR = "---";

	private List<Card> redBox = new ArrayList<Card>();
	private List<Card> orangeBox = new ArrayList<Card>();
	private List<Card> greenBox = new ArrayList<Card>();
	
	public Deck(File file) {
		prepare(file);
	}

	public boolean hasMoreCards() {
		return !redBox.isEmpty();
	}
	
	public Card getNextCard() {
		Card card = redBox.get(0);
		redBox.remove(0);
		return card;
	}
	
	public void storeOkCard(Card card) {
		greenBox.add(card);
	}

	public void storePartiallyCard(Card card) {
		orangeBox.add(card);
	}

	public void storeKoCard(Card card) {
		redBox.add(card);
	}

	public boolean areAllCardsOk() {
		return redBox.isEmpty() && orangeBox.isEmpty();
	}

	public String prepareToSave() {
		String data = FILE_HEADER + System.lineSeparator();
		for (Card card : orangeBox) {
			data += card.toString() + System.lineSeparator();
		}
		data += BOX_SEPARATOR + System.lineSeparator();
		for (Card card : greenBox) {
			data += card.toString() + System.lineSeparator();
		}
		return data;
	}
	
	private void prepare(File file) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			prepareBox(redBox, bufferedReader);
			prepareBox(orangeBox, bufferedReader);
			prepareBox(greenBox, bufferedReader);
			bufferedReader.close();
		} catch (IOException e) {
			throw new AnkiException("Error reading the file.", e);
		}
	}

	private void prepareBox(List<Card> box, BufferedReader bufferedReader) throws IOException {
		String line = bufferedReader.readLine();
		if (FILE_HEADER.equals(line)) {
			line = bufferedReader.readLine();
		}
		while (line != null && line.length() > 0 && !BOX_SEPARATOR.equals(line)) {
			box.add(new Card(line));
			line = bufferedReader.readLine();
		}
		Collections.sort(box);
	}
}
