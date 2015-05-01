//package com.avaje.ebean
//
//import com.avaje.ebeaninternal.util.DefaultExpressionList
//
///**
// * Created by rob on 4/11/14.
// */
//class Qry<T> {
//
//
//  fun body(init : Body<T>.() -> Unit) = {}
//}
//
//class Body<T> {
//  fun and(init: AND<T>.() -> Unit) = {}
//  fun or(init: OR<T>.() -> Unit) = {}
//}
//class AND<T>(q: Query<T>) : DefaultExpressionList<T>(q, null) {
//
//
//  fun or (property:String, value: Any) {
//
//  }
//}
//class OR<T>(q: Query<T>) : DefaultExpressionList<T>(q, null) {
//
//
////  fun or (property:String, value: Any) {
////
////  }
//}
//
//fun ExpressionList<T>.and() {
//
//}
//
//fun test() {
//
//  val qry = Qry<String>()
//  qry.body {
//    or {
//      and {
//        eq("name", 123)
//        gt("age", 12)
//        findVisit {  }
//      }
//
//    }
//  }
//
//
//}