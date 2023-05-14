package test;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
public class CalculatorTest {
    @Test
    public void main() {
        String sum = Calculator.Solve("55+92");
        Assert.assertEquals("55+92=147", sum);
    }
    @Test
    public void makeFormula() {
        Calculator calculator=new Calculator();
        Assert.assertNotNull(calculator.MakeFormula());
    }
    @Test
    public void solve() {
        Calculator calculator=new Calculator();
        String sum = Calculator.Solve("50*60*60-2");
        Assert.assertEquals("50*60*60-2=179998", sum);
    }
}