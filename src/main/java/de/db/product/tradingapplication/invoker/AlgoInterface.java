package de.db.product.tradingapplication.invoker;

/**
 * Represents the Algo functionality provided by a third-party library.
 * This interface defines methods for trading algorithms.
 * Implement this interface when integrating with the actual library.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
public interface AlgoInterface {
  void doAlgo();

  void cancelTrades();

  void reverse();

  void submitToMarket();

  void performCalc();

  void setUp();

  void setAlgoParam(int param, int value);
}