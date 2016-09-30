package com.home.wordcount;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class ListActor extends UntypedActor {

	private ActorRef mapper = null;

	public ListActor(ActorRef inReduceActor) {
		mapper = inReduceActor;
	}

	@Override
	public void onReceive(Object message) throws Throwable {
		if (message instanceof String) {
			String work = ((String) message);
			String[] splits = work.split(" ");
			List<String> wordList = new ArrayList<String>();
			for (String word : splits) {
				wordList.add(word.trim());
			}
			mapper.tell(new ListPhaseData(wordList), mapper);
		} else {
			unhandled(message);
		}
	}

}
