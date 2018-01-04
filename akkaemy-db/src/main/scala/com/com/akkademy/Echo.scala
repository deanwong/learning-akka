package com.com.akkademy

import akka.actor.Actor
import akka.event.Logging

class Echo extends Actor {
  var lastString = Option.empty[String]
  val log = Logging(context.system, this)

  override def receive = {
    case s: String => {
      log.info("received message: {}", s)
      lastString = Option(s)
    }
    case o => log.info("received unknown message: {}", o)
  }
}
