package de.db.product.tradingapplication.command;

import de.db.product.tradingapplication.invoker.Algo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class Signal2CommandTest {

  @Mock
  private Algo algo;

  private Signal2Command signal2Command;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    signal2Command = new Signal2Command(algo);
  }

  @Test
  public void testExecute() {
    // Define the order in which methods should be invoked
    InOrder inOrder = Mockito.inOrder(algo);

    // Call the execute method
    signal2Command.execute();

    // Verify method invocations in the specified order
    inOrder.verify(algo).reverse();
    inOrder.verify(algo).setAlgoParam(1, 80);
    inOrder.verify(algo).submitToMarket();

    // Verify that no other methods were called on the mocked object
    verifyNoMoreInteractions(algo);
  }
}