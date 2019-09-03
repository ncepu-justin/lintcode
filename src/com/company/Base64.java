package com.company;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Justin
 * 2019/8/26  19:56
 */
public class Base64 {


    private static final String base64Code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public String base64Encoder(String srcStr) throws UnsupportedEncodingException {
        //有效值检查
        if (srcStr == null || srcStr.length() == 0) {
            return srcStr;
        }
        //将明文的ASCII码转为二进制位字串
        char[] srcStrCh = srcStr.toCharArray();
        StringBuilder asciiBinStrB = new StringBuilder();
        String asciiBin = null;
        for (int i = 0; i < srcStrCh.length; i++) {
            asciiBin = Integer.toBinaryString((int) srcStrCh[i]);
            while (asciiBin.length() < 8) {
                asciiBin = "0" + asciiBin;
            }
            asciiBinStrB.append(asciiBin);
        }
        //跟据明文长度在二进制位字串尾部补“0”
        while (asciiBinStrB.length() % 6 != 0) {
            asciiBinStrB.append("0");
        }
        String asciiBinStr = String.valueOf(asciiBinStrB);
        //将上面得到的二进制位字串转为Value，再跟据Base64编码表将之转为Encoding
        char[] codeCh = new char[asciiBinStr.length() / 6];
        int index = 0;
        for (int i = 0; i < codeCh.length; i++) {
            index = Integer.parseInt(asciiBinStr.substring(0, 6), 2);
            asciiBinStr = asciiBinStr.substring(6);
            codeCh[i] = base64Code.charAt(index);
        }
        StringBuilder code = new StringBuilder(String.valueOf(codeCh));
        //跟据需要在尾部添加“=”
        if (srcStr.length() % 3 == 1) {
            code.append("==");
        } else if (srcStr.length() % 3 == 2) {
            code.append("=");
        }
        //每76个字符加一个回车换行符（CRLF）
        int i = 76;
        while (i < code.length()) {
            code.insert(i, "\r\n");
            i += 76;
        }
        code.append("\r\n");
        return String.valueOf(code);
    }

    //a<<b((c)<)
    public String deleteStr(String src) {
        if (src == null || src.length() == 0)
            return "";
        int len = src.length();
        List<Boolean> isValid=new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (isValid.isEmpty() && src.charAt(i) != '<' && src.charAt(i) != '(' && src.charAt(i) != ')') {
                stack.push(src.charAt(i));
            } else if (isValid.isEmpty() && src.charAt(i) == '<') {
                if (stack.isEmpty()) continue;
                stack.pop();
            } else if (src.charAt(i) == '(') {
                isValid.add(false);
            } else if(src.charAt(i) == ')') {
                isValid.remove(isValid.size()-1);
            }
        }
        StringBuilder temp = new StringBuilder();
        while (!stack.isEmpty()) {
            temp.append(stack.pop());
        }
        StringBuilder res = new StringBuilder();
        for (int i = temp.length() - 1; i >= 0; i--) {
            res.append(temp.charAt(i));
        }
        return res.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Base64 base64 = new Base64();
        String res=base64.deleteStr("a<<b((c)<)");
        System.out.println(res);
    }
}
