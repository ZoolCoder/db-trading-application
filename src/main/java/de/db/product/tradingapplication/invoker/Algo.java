package de.db.product.tradingapplication.invoker;

import org.springframework.stereotype.Component;

/**
 * Represents the Algo class provided by a third-party library.
 * This class contains mock methods for trading algorithms.
 * The println calls are placeholders for the actual algorithms implemented by the library.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
@Component
public class Algo implements AlgoInterface{

  @Override
  public void doAlgo() {
    System.out.println("doAlgo");
  }

  @Override
  public void cancelTrades() {
    System.out.println("cancelTrades");
  }

  @Override
  public void reverse() {
    System.out.println("reverse");
  }

  @Override
  public void submitToMarket() {
    System.out.println("submitToMarket");
  }

  @Override
  public void performCalc() {
    System.out.println("performCalc");
  }

  @Override
  public void setUp() {
    System.out.println("setUp");
  }

  @Override
  public void setAlgoParam(int param, int value) {
    System.out.println("setAlgoParam " + param + "," + value);
  }
}
