package com.example.assignment2;

public class Case {
    public static boolean isOpened;

    public static int amount;

    public static int caseId;

    public Case() {
    }

    public Case(boolean isOpened,int amount,int caseId) {
        this.isOpened = isOpened;
        this.amount=amount;
        this.caseId = caseId;
    }

    public static boolean isIsOpened() {
        return isOpened;
    }

    public static void setIsOpened(boolean isOpened) {
        Case.isOpened = isOpened;
    }

    public static int getAmount() {
        return amount;
    }

    public static void setAmount(int amount) {
        Case.amount = amount;
    }

    public static int getCaseId() {
        return caseId;
    }

    public static void setCaseId(int caseId) {
        Case.caseId = caseId;
    }
}
