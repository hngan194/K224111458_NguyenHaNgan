package com.nguyenhangan.k22411csampleproject.models;

import java.util.ArrayList;

public class ListPaymentMethod {
    ArrayList<PaymentMethod> paymentMethods;
    public ListPaymentMethod()
    {
        paymentMethods=new ArrayList<>();
    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
    public void gen_payments_method()
    {
        paymentMethods.add(new PaymentMethod(1, "Banking Account", "Chuyển khoảng ngân hàng"));
        paymentMethods.add(new PaymentMethod(2, "Momo", "Thanh toán ví Momo"));
        paymentMethods.add(new PaymentMethod(3, "Cash", "Thanh toán tiền mặt"));
        paymentMethods.add(new PaymentMethod(4, "COD", "Nhận hàng rồi thanh toán"));
    }

}
