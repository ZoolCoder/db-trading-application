package de.db.product.tradingapplication.command;

import de.db.product.tradingapplication.invoker.Algo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DefaultSignalCommandTest {

  @Mock
  private Algo algo;

  private DefaultSignalCommand defaultSignalCommand;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    defaultSignalCommand = new DefaultSignalCommand(algo);
  }

  @Test
  public void testExecute() {
    // Call the execute method
    defaultSignalCommand.execute();

    // Verify that the cancelTrades method was called on the Algo object
    verify(algo).cancelTrades();
  }
}