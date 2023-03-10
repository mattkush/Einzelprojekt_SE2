package com.example.einzelprojekt_se2;

public class DivisorInfo {

    private int index1;
    private int index2;
    private int divisor;

    public DivisorInfo(int index1,int index2, int divisor){
        this.setIndex1(index1);
        this.setIndex2(index2);
        this.setDivisor(divisor);
    }

    public int getIndex1() {
        return index1;
    }

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    public int getIndex2() {
        return index2;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }
}
