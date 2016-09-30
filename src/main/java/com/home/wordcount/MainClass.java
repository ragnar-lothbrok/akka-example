package com.home.wordcount;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;

public class MainClass {

	public static void main(String[] args) throws Exception {

		ActorSystem system = ActorSystem.create("MapReduceTaks");

		
		ActorRef aggregatorRef = system.actorOf(Props.create(AggregateActor.class), "aggregatorRef");
		
		ActorRef mapperRef = system.actorOf(Props.create(MapActor.class,aggregatorRef), "mapperRef");
		
		ActorRef listRef = system.actorOf(Props.create(ListActor.class,mapperRef), "listRef");
		
		ActorRef master = system.actorOf(Props.create(MasterActor.class,aggregatorRef,mapperRef,listRef), "master");

		master.tell("Dog is man's best friend",ActorRef.noSender());
		master.tell("Dog and Fox belong to the same family",ActorRef.noSender());

		final Inbox inbox = Inbox.create(system);
		inbox.send(listRef, "The quick brown fox tried to jump over the lazy dog and fell on the dog over");
		
	
//		master.tell(new Result(), null);
		System.out.println("Java done!");
	}

}
