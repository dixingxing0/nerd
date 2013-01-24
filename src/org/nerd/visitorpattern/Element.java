package org.nerd.visitorpattern;
interface Element {
   // 1. accept(Visitor) interface
   public void accept( Visitor v ); // first dispatch
}