package com.home.wordcount;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class MasterActor extends UntypedActor {

	private ActorRef aggregateRef;
	private ActorRef listRef;

	public MasterActor(ActorRef aggregateRef, ActorRef listRef) {
		super();
		this.aggregateRef = aggregateRef;
		this.listRef = listRef;
	}

	@Override
	public void onReceive(Object message) throws Throwable {
		if (message instanceof String) {
			listRef.tell(message, ActorRef.noSender());
		} else if (message instanceof ListPhaseData) {
			aggregateRef.tell(message, ActorRef.noSender());
		} else
			unhandled(message);
	}

}
