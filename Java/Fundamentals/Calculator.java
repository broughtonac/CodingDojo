public class Calculator implements Operable {
    private double x;
    private double y;
    private String operator;
    private double result;
    public Calculator() {}
    public void setOperandOne(double x) {
        this.x = x;
    }
    public void setOperandTwo(double y) {
        this.y = y;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public void performOperation() {
        if (this.operator == "+") {
            this.result = this.x + this.y;
        }
        else if (this.operator == "-") {
            this.result = this.x - this.y;
        }
    }
    public double getResults() {
        return this.result;
    }
}