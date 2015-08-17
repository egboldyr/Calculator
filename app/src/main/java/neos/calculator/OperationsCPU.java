package neos.calculator;

/**
 * Created by IEvgen Boldyr on 31.03.15.
 * Project: CalcLite
 */

public final class OperationsCPU {

    public static Double calculate(String operation, Double first, Double second) {
        Double result = 0.0;
        switch (operation) {
            case "+" : {
                result = first + second;
                break;
            }
            case "-" : {
                result = first - second;
                break;
            }
            case "*" : {
                result = first * second;
                break;
            }
            case "/" : {
                if (second != 0.0) {
                    result = first / second;
                    break;
                }
                break;
            }
        }
        return result;
    }
}
