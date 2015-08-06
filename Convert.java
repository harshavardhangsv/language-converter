package com.harshavardhangsv.indianscriptconverter;

import android.annotation.TargetApi;
import android.os.Build;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.*;

public class Convert {
    public static int getLangCode(String lang) {
        if (lang.equals("Hindi")) {
            return 164;
        }
        else if (lang.equals("Bengali")) {
            return 166;
        }
        else if (lang.equals("Punjabi")) {
            return 168;
        }
        else if (lang.equals("Gujarati")) {
            return 170;
        }
        else if (lang.equals("Oriya")) {
            return 172;
        }
        else if (lang.equals("Tamil")) {
            return 174;
        }
        else if (lang.equals("Telugu")) {
            return 176;
        }
        else if (lang.equals("Kannada")) {
            return 178;
        }
        else if (lang.equals("Malayalam")) {
            return 180;
        }
        return 0;
    }
    public static int checklower(int n) {
        if (n>= 164 && n<=180 && n%2 == 0) {
            return 1;
        }
        else if (n>= 165 && n<=181  && n%2 == 1) {
            return 0;
        }
        else {
            return 2;
        }
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String transliterate(String input,String langName) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        int langCode = getLangCode(langName);
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        for(int i=0;i<bytes.length;++i) {
            int character = bytes[i]&0xFF;
            if(character == 224) {

                bOut.write(character);
                //        System.out.print(character+" ");
                if (i+1 < bytes.length) {
                    character = bytes[i+1]&0xFF;
                    ++i;
                }
                int flag = checklower(character);
                if (flag == 1) {
                    character = langCode;
                }
                else if (flag == 0) {
                    character = langCode + 1;
                }

            }
            if (langCode == 166 && character == 181) {
                character = 172;
            }

            bOut.write(character);
            //System.out.print(character+" ");
        }
        return bOut.toString();
    }
    public static void main(String[] args) {
        //String input = "అ ఆ ఇ ఈ ఉ ఊ";
    	/*byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
    	//System.out.println("length is "+bytes.length);
    	//System.out.println(input);
    	for(byte b: bytes) {
    		System.out.print(b&0xFF);
    		System.out.print(" ");
    	}
    	System.out.println();*/
        //System.out.println(transliterate(input, "hnd"));
    }
}
