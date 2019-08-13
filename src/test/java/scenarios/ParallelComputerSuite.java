package scenarios;

import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

public class ParallelComputerSuite {
  @Test
  public void runTests() {
    Class<?>[] classes = { AuthenticationTest.class };
    JUnitCore.runClasses(new ParallelComputer(true, true), classes);
  }
}
