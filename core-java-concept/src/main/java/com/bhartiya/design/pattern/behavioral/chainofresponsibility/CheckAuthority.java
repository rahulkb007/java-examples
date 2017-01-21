package com.bhartiya.design.pattern.behavioral.chainofresponsibility;

import java.io.*;

/**
 * Created by Rahul on 14-01-2017.
 * <p>
 * https://en.wikipedia.org/wiki/Chain-of-responsibility_pattern
 */
public class CheckAuthority {
    public static void main(String[] args) {
        ManagerPurchasePower managerPurchasePower = new ManagerPurchasePower();
        DirectorPurchasePower directorPurchasePower = new DirectorPurchasePower();
        VicePresidentPurchasePower vicePresidentPurchasePower = new VicePresidentPurchasePower();
        President president = new President();
        managerPurchasePower.setSuccessor(directorPurchasePower);
        directorPurchasePower.setSuccessor(vicePresidentPurchasePower);
        vicePresidentPurchasePower.setSuccessor(president);
        try {
            while (true) {
                System.out.println("Enter the amount to check who should approve your expenditure.");
                System.out.print(">");

                double d = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
                managerPurchasePower.processRequest(new PurchaseRequest(d, "General"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

abstract class PurchasePower {
    protected static final double BASE = 10;
    protected PurchasePower successor;

    public void setSuccessor(PurchasePower successor) {
        this.successor = successor;
    }

    abstract protected double getAllowable();

    abstract protected String getRole();

    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < this.getAllowable()) {
            System.out.format("%s will approve $ %f%n", getRole(), request.getAmount());
        } else if (successor != null) {
            successor.processRequest(request);
        }
    }
}

class ManagerPurchasePower extends PurchasePower {

    protected double getAllowable() {
        return BASE * 10;
    }

    protected String getRole() {
        return "Manager";
    }
}

class DirectorPurchasePower extends PurchasePower {

    protected double getAllowable() {
        return BASE * 20;
    }

    protected String getRole() {
        return "Director";
    }
}

class VicePresidentPurchasePower extends PurchasePower {

    protected double getAllowable() {
        return BASE * 30;
    }

    protected String getRole() {
        return "VicePresident";
    }
}

class President extends PurchasePower {

    protected double getAllowable() {
        return BASE * 40;
    }

    protected String getRole() {
        return "President";
    }
}

class PurchaseRequest {
    private double amount;
    private String purpose;

    public PurchaseRequest(double amount, String purpose) {
        this.amount = amount;
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}