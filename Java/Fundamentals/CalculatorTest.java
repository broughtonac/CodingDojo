public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setOperandOne(10);
        calculator.setOperandTwo(15);
        calculator.setOperator("+");
        calculator.performOperation();
        System.out.println(calculator.getResults());
    }
}