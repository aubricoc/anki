package com.smartholiday.test.anki;

import java.io.File;

public class Anki {

	private UserInput input;

	public Anki() {
		input = new UserInput();
	}

	public void run() {
		Deck deck = getDeck();
		while (deck.hasMoreCards()) {
			Card card = deck.getNextCard();
			card.ask();
			System.out.print("Press Enter to see correct answer.");
			input.read();
			card.showAnswer();
			System.out.println();
			String read = input.read("Your answer is correct? [ Y (Yes) | P (Partially) | N (No) ] : ",
					new String[] { "Y", "P", "N" });
			switch (read) {
			case "Y":
				deck.storeOkCard(card);
				break;
			case "P":
				deck.storePartiallyCard(card);
				break;
			case "N":
				deck.storeKoCard(card);
				break;
			default:
				break;
			}
		}
		if (deck.areAllCardsOk()) {
			FileLocator.getInstance().removeStorageFile();
			System.out.println("Congratulations! You answered well all questions!");
		} else {
			String data = deck.prepareToSave();
			FileLocator.getInstance().saveToStorageFile(data);
			System.out.println("Goodbye! Tomorrow more!");
		}
	}

	private Deck getDeck() {
		File file = FileLocator.getInstance().getStorageFile();
		if (file == null) {
			System.out.println("I need an input file with the deck of cards.");
			System.out.print("Where is located? -> ");
			String filePath = input.read();
			file = new File(filePath);
			if (!file.exists()) {
				throw new AnkiException("File not exists!");
			}
		}
		return new Deck(file);
	}
}
