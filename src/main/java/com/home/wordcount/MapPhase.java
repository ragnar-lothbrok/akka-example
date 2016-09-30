package com.home.wordcount;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapPhase {

	private Map<String, Integer> wordMap;

	public Map<String, Integer> getWordMap() {
		if(wordMap == null)
			wordMap = new HashMap<String, Integer>();
		return wordMap;
	}

	public MapPhase(Map<String, Integer> wordMap) {
		this.wordMap = Collections.unmodifiableMap(wordMap);
	}

	public MapPhase() {

	}

}
