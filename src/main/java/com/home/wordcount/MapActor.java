package com.home.wordcount;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class MapActor extends UntypedActor {

	private ActorRef aggregateActor = null;

	public MapActor(ActorRef inAggregateActor) {
		aggregateActor = inAggregateActor;
	}

	@Override
	public void onReceive(Object message) throws Throwable {
		MapPhase mapPhase = new MapPhase();
		if (message instanceof ListPhaseData) {
			ListPhaseData mapData = (ListPhaseData) message;
			for (String word : mapData.getWordList()) {
				if (mapPhase.getWordMap().get(word) == null) {
					mapPhase.getWordMap().put(word, 1);
				} else {
					mapPhase.getWordMap().put(word, mapPhase.getWordMap().get(word) + 1);
				}
			}
			this.aggregateActor.tell(mapPhase, aggregateActor);
		} else
			unhandled(message);
	}

}
