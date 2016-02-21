package com.objectpartners;

public class Weather {
 public int getMinneapolis() {
  return minneapolis;
 }

 public void setMinneapolis(int minneapolis) {
  this.minneapolis = minneapolis;
 }

 public int getCancun() {
  return cancun;
 }

 public void setCancun(int cancun) {
  this.cancun = cancun;
 }

 public boolean getWarmerInCancun() {
  return warmerInCancun;
 }

 public boolean isWarmerInCancun() {
  return warmerInCancun;
 }

 public void setWarmerInCancun(boolean warmerInCancun) {
  this.warmerInCancun = warmerInCancun;
 }

 private int minneapolis;
 private int cancun;
 private boolean warmerInCancun;
}
