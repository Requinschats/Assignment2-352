public class ArithmeticCalculators {

    public String expression;

    public ArithmeticCalculators(String path) {
        expression = IO.getExpression(path);
    }

    public class StacksCalculator {
        public Integer evaluate() {
            Stack values = new Stack(expression.length());
            Stack operations = new Stack(expression.length());
            char[] tokens = expression.toCharArray();
            for (int i = 0; i < tokens.length; i++) {
                    if (tokens[i] == ' ') {
                        continue;
                    }
                switch (tokens[i]) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        StringBuilder sbuf = new StringBuilder();
                        while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
                            sbuf.append(tokens[i++]);
                        }
                        values.push(Integer.parseInt(sbuf.toString()));
                        i--;
                        break;
                    case '(':
                        operations.push(tokens[i]);
                        break;
                    case ')':
                        while (operations.peek() != (Character)'(') {
                            values.push(applyOp((Character) operations.pop(), (Integer) values.pop(),(Integer) values.pop()));
                        }
                        operations.pop();
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                    case '=':
                    case '<':
                    case '>':
                        while (!operations.isEmpty() && precedence(tokens[i], (Character) operations.peek())) {
                            values.push(applyOp((Character) operations.pop(), (Integer) values.pop(), (Integer) values.pop()));
                        }
                        operations.push(tokens[i]);
                        break;
                    case '!':
                        operations.push(tokens[i]);
                        values.push(applyOp((Character) operations.pop(), (Integer) values.pop(), 1));
                        break;
                    case '^':
                        operations.push(tokens[i]);
                        i++;
                        StringBuilder sbuf2 = new StringBuilder();
                        while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
                            sbuf2.append(tokens[i++]);
                        }
                        values.push(Integer.parseInt(sbuf2.toString()));
                        i--;
                        values.push(applyOp((Character) operations.pop(), (Integer) values.pop(), (Integer) values.pop()));
                }
            }

            while (!operations.isEmpty()) {
                values.push(applyOp((Character) operations.pop(), (Integer) values.pop(),(Integer) values.pop()));
            }
            return (Integer) values.pop();
        }

        private boolean precedence(char operator1, char operator2) {
            if (operator2 == '(' || operator2 == ')') {
                return false;
            }else return (operator1 != '*' && operator1 != '/') || (operator2 != '+' && operator2 != '-');
        }

        private int applyOp(Character operator, Integer leftSide, Integer rightSide) {
            switch (operator.charValue()) {
                case '+':
                    return rightSide + leftSide;
                case '-':
                    return rightSide - leftSide;
                case '*':
                    return rightSide * leftSide;
                case '/':
                    if (leftSide == 0) {
                        throw new UnsupportedOperationException("Cannot divide by zero");
                    }
                    return rightSide / leftSide;
                case '!':
                    int result = 1;
                    for (int factor = 2; factor <= leftSide; factor++) {
                        result *= factor;
                    }
                    return result;
                case '^':
                    Integer result2 = 1;
                    for (int i = 1; i <= leftSide; i++) {
                        result2 *= rightSide; //inverted LHS and RHS
                    }
                    return result2;
            }
            return 0;
        }
    }

    public class recursionCalculator {

    }

    public static void main(String[] args) {
        ArithmeticCalculators.StacksCalculator assignment2Example1 = new ArithmeticCalculators("expression1.txt").new StacksCalculator();
        ArithmeticCalculators.StacksCalculator assignment2Example2 = new ArithmeticCalculators("expression2.txt").new StacksCalculator();
        ArithmeticCalculators.StacksCalculator assignment2Example3 = new ArithmeticCalculators("expression3.txt").new StacksCalculator();
        ArithmeticCalculators.StacksCalculator assignment2Example4 = new ArithmeticCalculators("expression4.txt").new StacksCalculator();
        ArithmeticCalculators.StacksCalculator assignment2Example5 = new ArithmeticCalculators("expression5.txt").new StacksCalculator();

        IO.printResult("Example1.txt", assignment2Example1.evaluate().toString());
        IO.printResult("Example2.txt", assignment2Example2.evaluate().toString());
        IO.printResult("Example3.txt", assignment2Example3.evaluate().toString());
        IO.printResult("Example4.txt", assignment2Example4.evaluate().toString());
        IO.printResult("Example5.txt", assignment2Example5.evaluate().toString());
    }
}
