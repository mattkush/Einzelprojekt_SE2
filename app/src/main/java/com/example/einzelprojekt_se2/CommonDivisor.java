package com.example.einzelprojekt_se2;

import android.util.Log;

public class CommonDivisor {

    public static DivisorInfo getCommonDivisor(int number){

        int length = (int) (Math.log10(number) + 1);
        int mod = 1;

        int div = 0;
        int num1,num2;

        for(int i = 0; i<length;i++){

            mod= (int) (Math.pow(10,i+1));

            num1 = number%mod;
            num1 = num1/(mod/10);
            Log.v("Div","Location"+i+"Idx1:"+num1+" on mod:"+mod);

            for(int j = i+1; j<length;j++){
                mod = (int)(Math.pow(10,j+1));

                num2 = number%mod;
                num2 = num2/(mod/10);
                Log.v("Div","Location"+j+"Idx2:"+num2+" on mod:"+mod);

                div = getDivisor(num1,num2);

                Log.v("Div", "Divisor:"+div+" on Idx1:"+num1+" on Idx2:"+num2);
                if(div != 0)
                    return new DivisorInfo(i,j,div);
            }
        }
        return new DivisorInfo(0,0,0);
    }




    //returns 0 if none is found
    private static int getDivisor(int num1, int num2){
        int ret = 1;
        while(ret < num1 && ret < num2){
            ret++;//start with 2
            if(num1%ret==0 && num2%ret==0)
                return ret;
        }
        return 0;
    }
}
