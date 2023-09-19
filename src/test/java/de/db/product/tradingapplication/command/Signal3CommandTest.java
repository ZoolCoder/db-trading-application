package de.db.product.tradingapplication.command;

import de.db.product.tradingapplication.invoker.Algo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class Signal3CommandTest {

  @Mock
  private Algo algo;

  private Signal3Command signal3Command;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    signal3Command = new Signal3Command(algo);
  }

  @Test
  public void testExecute() {
    // Define the order in which methods should be invoked
    InOrder inOrder = Mockito.inOrder(algo);

    // Call the execute method
    signal3Command.execute();

    // Verify method invocations in the specified order
    inOrder.verify(algo).setAlgoParam(1, 90);
    inOrder.verify(algo).setAlgoParam(2, 15);
    inOrder.verify(algo).performCalc();
    inOrder.verify(algo).submitToMarket();

    // Verify that no other methods were called on the mocked object
    verifyNoMoreInteractions(algo);
  }
}


