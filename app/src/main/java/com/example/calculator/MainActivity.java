package com.example.calculator;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.math.*;
import java.util.ArrayList;
import java.text.DecimalFormat;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //创建Button对象   也就是activity_main.xml里所设置的ID
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    Button btn_mul, btn_div, btn_add, btn_sub, btn_asr;
    Button btn_clr, btn_del, btn_eq, btn_pt, btn_sym;
    EditText et_input;
    boolean scientific = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化对象
        //       setContentView(R.layout.activity_main);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);


        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_asr = (Button) findViewById(R.id.btn_asr);

        btn_clr = (Button) findViewById(R.id.btn_clr);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_eq = (Button) findViewById(R.id.btn_equ);
        btn_pt = (Button) findViewById(R.id.btn_pt);
        btn_sym = (Button) findViewById(R.id.btn_sym);
        et_input = (EditText) findViewById(R.id.result);

        //给按钮设置的点击事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);


        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_asr.setOnClickListener(this);

        btn_pt.setOnClickListener(this);
        btn_clr.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_eq.setOnClickListener(this);
        btn_sym.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String str_show;
        if(scientific == true){
            BigDecimal bigD = new BigDecimal(et_input.getText().toString().replace('~','-'));
            str_show = bigD.toPlainString().replace('-','~');
        }
        else
            str_show = et_input.getText().toString();
        char str_show_last = str_show.charAt(str_show.length() - 1);
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                if(scientific == true){
                    str_show ="0";
                    scientific =false;
                }

                String temp1 = "";
                int i;
                for(i = str_show.length() - 1;i >= 0;i--){
                    char a = str_show.charAt(i);
                    if(a=='+' || a== '-' || a== '*' || a== '/')
                        break;
                    else
                        temp1 = temp1 + a;
                }

//                if(temp1.length()>=13){
//                    Toast toast = Toast.makeText(MainActivity.this,"一个数不能超过13个符号",Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP,0,80);
//                    toast.show();
//                    break;
//                }
//                if(v.getId() == R.id.btn_0 && str_show_last == '/'){
//                    Toast toast = Toast.makeText(MainActivity.this,"0不能做被除数",Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP,0,80);
//                    toast.show();
//                }
                if (str_show.equals("0"))
                    str_show = "" + ((Button) v).getText();
                else
                    str_show = str_show + ((Button) v).getText();
                et_input.setText(str_show);
                break;
            case R.id.btn_pt:
                if(scientific == true){
                    str_show ="0.";
                    scientific =false;
                }
                String temp = "";
                for(i = str_show.length() - 1;i >= 0;i--){
                    char a = str_show.charAt(i);
                    if(a=='+' || a== '-' || a== '*' || a== '/')
                        break;
                    else
                        temp = temp + a;
                }
                if(!temp.contains(".")){
                    if (str_show_last == '+' || str_show_last == '-' || str_show_last == '*' ||
                            str_show_last == '/' || str_show_last == '√' || str_show_last == '~')
                        str_show = str_show + "0.";
                    else if (str_show_last != '.')
                        str_show = str_show + ".";
                }
                et_input.setText(str_show);
                break;
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:
                if(scientific == true){
                    scientific =false;
                }
                if (str_show_last >= '0' && str_show_last <= '9')
                    str_show = str_show + ((Button) v).getText();
                else if (str_show_last == '+' || str_show_last == '-' || str_show_last == '*' ||
                        str_show_last == '/')
                    str_show = str_show.substring(0, str_show.length() - 1) + ((Button) v).getText();
                else if (str_show_last == '√' || str_show_last == '.' || str_show_last == '~')
                    str_show = str_show + "0" + ((Button) v).getText();
                et_input.setText(str_show);
                break;
            case R.id.btn_asr:
                if(scientific == true){
                    str_show ="0";
                    scientific =false;
                }
                if (str_show.equals("0"))
                    str_show = "√";
                else if (str_show_last >= '0' && str_show_last <= '9')
                    str_show = str_show + "*√";
                else if(str_show_last == '+' || str_show_last == '-' || str_show_last == '*' ||
                        str_show_last == '/' || str_show_last == '√' || str_show_last == '~')
                    str_show = str_show + "√";
                else if(str_show_last == '.')
                    str_show = str_show + "0*√";
                et_input.setText(str_show);
                break;
            case R.id.btn_clr:
                if(scientific == true){
                    scientific =false;
                }
                str_show = "0";
                et_input.setText(str_show);
                break;
            case R.id.btn_del: //判断是否为空，然后再进行删除
                if(scientific == true){
                    str_show ="0";
                    scientific =false;
                }
                if (str_show.length() == 1)
                    str_show = "0";
                else
                    str_show = str_show.substring(0, str_show.length()-1);
                et_input.setText(str_show);
                break;
            case R.id.btn_sym:
                if(scientific == true){
                    str_show ="0";
                    scientific =false;
                }
                if(str_show.equals("0"))
                    str_show = "~";
                else if(str_show_last == '+' || str_show_last == '-' || str_show_last == '*' ||
                        str_show_last == '/')
                    str_show = str_show + "~";
                else if(str_show_last == '√'){
                    Toast toast = Toast.makeText(MainActivity.this,"负数没有算术平方根",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP,0,80);
                    toast.show();
                }
                et_input.setText(str_show);
                break;
            case R.id.btn_equ: //运算最后结果
                /*对算式进行处理，使算式最后一个符号为数字*/
                while(str_show_last == '+' || str_show_last == '-' || str_show_last == '*' ||
                        str_show_last == '/' || str_show_last == '√' || str_show_last == '~' || str_show_last == '.') {
                    str_show = str_show.substring(0, str_show.length() - 1);
                    str_show_last = str_show.charAt(str_show.length()-1);
                }
                /***获得运算结果***/
                String result = getResult(str_show);
                /*运算结果包含？，则存在被除数为0的情况，给出提示*/
                if(result.equals("?")){
                    Toast toast = Toast.makeText(MainActivity.this,"0不能做被除数",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP,0,80);
                    toast.show();
                    scientific = false;
                }
                /**运算结果长度>13,采用科学计数法显示结果**/
                else if(result.length()>13){
                    result = scientificNotation2String(result);
                    /*结果包含∞，则运算超过计算范围，给出提示*/
                    if(result.contains("∞")){
                        Toast toast = Toast.makeText(MainActivity.this,"超出运算范围",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP,0,80);
                        toast.show();
                        scientific = false;
                    }
                    /*结果不包含∞，显示运算结果*/
                    else {
                        str_show = result.replace('-', '~');
                        scientific = true;
                    }
                }
                /*不进行科学计数法显示的输出结果*/
                else{
                    scientific = false;
                    str_show = result.replace('-','~');
                }
                break;
        }
        et_input.setText(str_show);
        et_input.setSelection(et_input.getText().length());
    }
/*获得计算结果*/
    public static String getResult(String calculate) {
        ArrayList<String> cal = splitCal(calculate);
        String result="";
        while (cal.size()>1 && !cal.contains("?")){
            while (cal.contains("√")){  //先算根号
                for(int i = cal.size()-1;i>=0;i--){
                    String temp = cal.get(i);
                    if(temp.equals("√")){
                        String res = Asr(cal.get(i+1));
                        cal.set(i,res);
                        cal.remove(i+1);
                    }
                }
            }
            while (cal.contains("*") || cal.contains("/")){     //再算乘除
                for(int i = 1;i<cal.size()-1;i++){
                    String temp = cal.get(i);
                    if(temp.equals("*")){
                        String res = Mul(cal.get(i-1),cal.get(i+1));
                        cal.set(i-1,res);
                        cal.remove(i);
                        cal.remove(i);
                        i--;
                    }
                    else if(temp.equals("/")){
                        String res = Div(cal.get(i-1),cal.get(i+1));
                        cal.set(i-1,res);
                        cal.remove(i);
                        cal.remove(i);
                        i--;
                    }
                }
            }
            while (cal.contains("+") || cal.contains("-")){       //最后算加减
                for(int i = 1;i<cal.size()-1;i++){
                    String temp = cal.get(i);
                    if(temp.equals("+")){
                        String res = Add(cal.get(i-1),cal.get(i+1));
                        cal.set(i-1,res);
                        cal.remove(i);
                        cal.remove(i);
                        i--;
                    }
                    else if(temp.equals("-")){
                        String res = Sub(cal.get(i-1),cal.get(i+1));
                        cal.set(i-1,res);
                        cal.remove(i);
                        cal.remove(i);
                        i--;
                    }
                }
            }
        }
        if(cal.contains("?"))
            result="?";
        else {
            result = cal.get(0);
        }
        return result;
    }
    /*获得运算符与运算数*/
    public static ArrayList<String> splitCal(String calculate){
        ArrayList<String> cal = new ArrayList<String>();
        for (int i = 0;i<calculate.length();i++){
            char temp = calculate.charAt(i);
            if(temp == '+' || temp == '-' || temp == '*' || temp == '/' || temp == '√')
                cal.add(temp+"");
            else if((temp >='0' && temp <='9') || temp =='~'){
                String num = "";
                int j = 0;
                for(j = i;j<calculate.length();j++) {
                    char c = calculate.charAt(j);
                    if((c >='0' && c <='9') || c =='~' || c == '.')
                        num = num+c;
                    else
                        break;
                }
                num = num.replace('~','-');
                cal.add(num);
                i = j-1;
            }
        }
        return cal;
    }
    /*科学计数显示*/
    public static String scientificNotation2String(String d) {
        String value = null;
        DecimalFormat decimalFormat = new DecimalFormat("0.00000000000E0");//格式化设置
        value = decimalFormat.format(Double.parseDouble(d));
        return value;
    }
    /*算术平方*/
    public static String Asr(String n){
        double b1 = Double.parseDouble(n);
        double result = Math.sqrt(b1);
        return Double.toString(result);
    }
    /*乘法*/
    public static String Mul(String n1,String n2){
        BigDecimal b1=new BigDecimal(n1);
        BigDecimal b2=new BigDecimal(n2);
        return b1.multiply(b2).stripTrailingZeros().toPlainString();
    }
    /*除法*/
    public static String Div(String n1,String n2){
        BigDecimal b1=new BigDecimal(n1);
        BigDecimal b2=new BigDecimal(n2);
        if(b2.compareTo(new BigDecimal("0.00000000000"))==0){
            return "?";
        }
        if(b1.compareTo(new BigDecimal("0.00000000000"))==0){
            return "0";
        }
        else
            return b1.divide(b2,8,BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }
    /*加法*/
    public static String Add(String n1,String n2) {
        BigDecimal b1 = new BigDecimal(n1);
        BigDecimal b2 = new BigDecimal(n2);
        return b1.add(b2).stripTrailingZeros().toPlainString();
    }
    /*减法*/
    public static String Sub(String  n1,String  n2){
        BigDecimal b1=new BigDecimal(n1);
        BigDecimal b2=new BigDecimal(n2);
        return b1.subtract(b2).stripTrailingZeros().toPlainString();
    }

}