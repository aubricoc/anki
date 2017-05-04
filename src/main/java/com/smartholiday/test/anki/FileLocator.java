package com.smartholiday.test.anki;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

public class FileLocator {

	private static final String STORAGE_FILE_NAME = "anki_storage.txt";

	private static final FileLocator INSTANCE = new FileLocator();

	private FileLocator() {
		super();
	}

	public static FileLocator getInstance() {
		return INSTANCE;
	}

	public File getStorageFile() {
		File file = new File(STORAGE_FILE_NAME);
		if (!file.exists()) {
			return null;
		}
		return file;
	}

	public void removeStorageFile() {
		File file = new File(STORAGE_FILE_NAME);
		try {
			FileUtils.forceDeleteOnExit(file);
		} catch (IOException e) {
			throw new AnkiException("Error removing file", e);
		}
	}

	public void saveToStorageFile(String data) {
		File file = new File(STORAGE_FILE_NAME);
		try {
			FileUtils.write(file, data, Charset.defaultCharset());
		} catch (IOException e) {
			throw new AnkiException("Error removing file", e);
		}
	}
}
