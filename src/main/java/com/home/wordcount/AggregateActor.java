package com.home.wordcount;

import java.util.HashMap;
import java.util.Map;

import akka.actor.UntypedActor;

public class AggregateActor extends UntypedActor {

	private Map<String, Integer> finalReducedMap = new HashMap<String, Integer>();

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof MapPhase) {
			MapPhase reduceData = (MapPhase) message;
			aggregateInMemoryReduce(reduceData.getWordMap());
			System.out.println("$$$$$$"+finalReducedMap);
		} else
			unhandled(message);
	}

	private void aggregateInMemoryReduce(Map<String, Integer> reducedList) {
		Integer count = null;
		for (String key : reducedList.keySet()) {
			if (finalReducedMap.containsKey(key)) {
				count = reducedList.get(key) + finalReducedMap.get(key);
				finalReducedMap.put(key, count);
			} else {
				finalReducedMap.put(key, reducedList.get(key));
			}
		}
	}

}
