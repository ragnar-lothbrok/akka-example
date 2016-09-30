package com.home.wordcount;

import java.util.ArrayList;
import java.util.List;

public class ListPhaseData {

	private List<String> wordList = new ArrayList<String>();

	public List<String> getWordList() {
		return wordList;
	}

	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}

	public ListPhaseData() {
	}

	public ListPhaseData(List<String> wordList) {
		super();
		this.wordList = wordList;
	}

}
