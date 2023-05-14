package test;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class CalculatorTestTest {
    @Test
    public void main() {
        File file=new File("E:\\data\\1.txt");
        Assert.assertTrue(file.exists());
    }
}