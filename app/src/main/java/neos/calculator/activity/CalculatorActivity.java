package neos.calculator.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import neos.calculator.OperationsCPU;
import neos.calculator.R;

/**
 * Created by IEvgen Boldyr on 31.03.15.
 * Project: Calculator
 */

public class CalculatorActivity extends Activity implements View.OnClickListener {

    private EditText mDisplayOperations;
    private EditText mDisplay;
    private boolean pointMarker;
    private boolean resultMarker;
    private boolean secondOperationMarker;
    private String operation;

    private Double first;
    private Double result;

    private Double onMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mDisplay = (EditText) findViewById(R.id.mDisplay);
        setPointMarker(false);
        setResultMarker(false);
        setSecondOperationMarker(false);
        setOperation(null);
        mDisplayOperations = (EditText) findViewById(R.id.mDisplayOperations);

        final Button btnZero = (Button) findViewById(R.id.btnZero);
        btnZero.setOnClickListener(this);
        final Button btnOne = (Button) findViewById(R.id.btnOne);
        btnOne.setOnClickListener(this);
        final Button btnTwo = (Button) findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(this);
        final Button btnThree = (Button) findViewById(R.id.btnThree);
        btnThree.setOnClickListener(this);
        final Button btnFour = (Button) findViewById(R.id.btnFour);
        btnFour.setOnClickListener(this);
        final Button btnFive = (Button) findViewById(R.id.btnFive);
        btnFive.setOnClickListener(this);
        final Button btnSix = (Button) findViewById(R.id.btnSix);
        btnSix.setOnClickListener(this);
        final Button btnSeven = (Button) findViewById(R.id.btnSeven);
        btnSeven.setOnClickListener(this);
        final Button btnEight = (Button) findViewById(R.id.btnEight);
        btnEight.setOnClickListener(this);
        final Button btnNine = (Button) findViewById(R.id.btnNine);
        btnNine.setOnClickListener(this);

        final Button btnAddition = (Button) findViewById(R.id.btnAddition);
        btnAddition.setOnClickListener(this);
        final Button btnSubstraction = (Button) findViewById(R.id.btnSubtraction);
        btnSubstraction.setOnClickListener(this);
        final Button btnMultiplication = (Button) findViewById(R.id.btnMultiplication);
        btnMultiplication.setOnClickListener(this);
        final Button btnDivision = (Button) findViewById(R.id.btnDivision);
        btnDivision.setOnClickListener(this);

        final Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);
        final Button btnResetAll = (Button) findViewById(R.id.btnResetAll);
        btnResetAll.setOnClickListener(this);
        final Button btnResult = (Button) findViewById(R.id.btnResult);
        btnResult.setOnClickListener(this);
        final Button btnPoint = (Button) findViewById(R.id.btnPoint);
        btnPoint.setOnClickListener(this);

        final Button btnMemoryClear = (Button) findViewById(R.id.btnMemoryClear);
        btnMemoryClear.setOnClickListener(this);
        final Button btnMemoryResult = (Button) findViewById(R.id.btnMemoryResult);
        btnMemoryResult.setOnClickListener(this);
        final Button btnMemoryAddition = (Button) findViewById(R.id.btnMemoryAddition);
        btnMemoryAddition.setOnClickListener(this);
        final Button btnMemorySubtraction = (Button) findViewById(R.id.btnMemorySubtraction);
        btnMemorySubtraction.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPoint : {
                if (mDisplay.getText().toString().equals("")
                        || mDisplay.getText().toString().equals("0.0")) {
                    break;
                } else {
                    if (!isPointMarker()) {
                        mDisplay.setText(mDisplay.getText().toString() + ".");
                        setPointMarker(true);
                    }
                    break;
                }
            }
            case R.id.btnZero : {
                if (mDisplay.getText().toString().equals("0")) {
                    mDisplay.setText("0");
                    break;
                } else {
                    mDisplay.setText(mDisplay.getText() + "0");
                    break;
                }
            }
            case R.id.btnOne : {
                addNumber("1");
                break;
            }
            case R.id.btnTwo : {
                addNumber("2");
                break;
            }
            case R.id.btnThree : {
                addNumber("3");
                break;
            }
            case R.id.btnFour : {
                addNumber("4");
                break;
            }
            case R.id.btnFive : {
                addNumber("5");
                break;
            }
            case R.id.btnSix : {
                addNumber("6");
                break;
            }
            case R.id.btnSeven : {
                addNumber("7");
                break;
            }
            case R.id.btnEight : {
                addNumber("8");
                break;
            }
            case R.id.btnNine : {
                addNumber("9");
                break;
            }
            case R.id.btnReset : {
                mDisplay.setText("0");
                setPointMarker(false);
                break;
            }
            case R.id.btnResetAll : {
                mDisplay.setText("0");
                mDisplayOperations.setText("");
                first = 0.0;
                setPointMarker(false);
                setResultMarker(false);
                setSecondOperationMarker(false);
                break;
            }
            case R.id.btnAddition : {
                operation("+");
                break;
            }
            case R.id.btnSubtraction : {
                operation("-");
                break;
            }
            case R.id.btnMultiplication : {
                operation("*");
                break;
            }
            case R.id.btnDivision : {
                operation("/");
                break;
            }
            case R.id.btnMemoryClear : {
                onMemory = null;
                Toast.makeText(this, R.string.msg_memory_clear, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnMemoryResult : {
                if (onMemory != null) {
                    mDisplay.setText(Double.toString(onMemory));
                    break;
                }
                onMemory = Double.parseDouble(mDisplay.getText().toString());
                mDisplay.setText("0");
                Toast.makeText(this, R.string.msg_memory_add, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnMemoryAddition : {
                if (onMemory != null) {
                    onMemory += Double.parseDouble(mDisplay.getText().toString());
                    break;
                }
                Toast.makeText(this, R.string.err_memory_addition, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnMemorySubtraction : {
                if (onMemory != null) {
                    onMemory -= Double.parseDouble(mDisplay.getText().toString());
                    break;
                }
                Toast.makeText(this, R.string.err_memory_subtraction, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnResult : {
                if (isResultMarker()) {
                    if (operation.equals("/")) {
                        Double second = Double.parseDouble(mDisplay.getText().toString());
                        if (second == 0) {
                            Toast.makeText(getApplicationContext(),
                                            R.string.err_div_by_zero, Toast.LENGTH_SHORT).show();
                            break;
                        }
                        mDisplayOperations.setText(mDisplayOperations.getText()
                                + mDisplay.getText().toString() + " =");
                        result = OperationsCPU.calculate(operation,
                                first, Double.parseDouble(mDisplay.getText().toString()));
                        if (result == 0) {
                            mDisplay.setText("0");
                            setResultMarker(false);
                            first = null;
                            break;
                        }
                        mDisplay.setText(Double.toString(result));
                        setResultMarker(false);
                        first = null;
                        break;
                    } else {
                        mDisplayOperations.setText(mDisplayOperations.getText()
                                + mDisplay.getText().toString() + " =");
                        result = OperationsCPU.calculate(operation,
                                first, Double.parseDouble(mDisplay.getText().toString()));
                        if (result == 0) {
                            mDisplay.setText("0");
                            setResultMarker(false);
                            first = null;
                            break;
                        }
                        mDisplay.setText(Double.toString(result));
                        setResultMarker(false);
                        setSecondOperationMarker(false);
                        first = null;
                        break;
                    }
                }
            }
        }
    }

    private void addNumber(String number) {
        if (mDisplay.getText().toString().equals("0")) {
            mDisplay.setText(number);
            return;
        }
        mDisplay.setText(mDisplay.getText() + number);
    }

    private void operation(String operation) {
        if (first == null || first == 0) {
            first = Double.parseDouble(mDisplay.getText().toString());
        }
        secondaryOperation(this.operation);
        setOperation(operation);
        setSecondOperationMarker(true);
        setPointMarker(false);
        setResultMarker(true);
        mDisplay.setText("0");
        mDisplayOperations.setText(first + " " + operation + " ");
    }

    private void secondaryOperation(String operation) {
        if (first != null && isSecondOperationMarker()) {
            first = OperationsCPU.calculate(operation,
                    first, Double.parseDouble(mDisplay.getText().toString()));
        }
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    public boolean isPointMarker() {
        return pointMarker;
    }
    public void setPointMarker(boolean pointMarker) {
        this.pointMarker = pointMarker;
    }
    public boolean isResultMarker() {
        return resultMarker;
    }
    public void setResultMarker(boolean resultMarker) {
        this.resultMarker = resultMarker;
    }
    public boolean isSecondOperationMarker() {
        return secondOperationMarker;
    }
    public void setSecondOperationMarker(boolean secondOperationMarker) {
        this.secondOperationMarker = secondOperationMarker;
    }
}
