import Stream.TestStream;
import calculator.TestCalculator;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
//@SelectPackages({"calculator", "Stream"})
@SelectClasses({TestCalculator.class, TestStream.class})
public class TestSuite {
}
