package com.github.jjagged

import rx.lang.scala.Observable

import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global

object ScalaTake extends App {

  def obs(i: Int) = Observable.just(i)
    .doOnNext(n => println(s"next $i "))
    .doOnCompleted(println(s"completed $i "))
    .doOnTerminate(println(s"terminated $i "))
    .finallyDo(println(s"finally done $i "))

  def obs2(i: Int) = Observable.from(Future(i))
    .doOnNext(n => println(s"next $i "))
    .doOnCompleted(println(s"completed $i "))
    .doOnTerminate(println(s"terminated $i "))
    .finallyDo(println(s"finally done $i "))

  val result1 = obs(1).single.toBlocking.head
  val result2 = obs(2).take(1).toBlocking.head

  val result3 = obs2(3).single.toBlocking.head
  val result4 = obs2(4).take(1).toBlocking.head
}
