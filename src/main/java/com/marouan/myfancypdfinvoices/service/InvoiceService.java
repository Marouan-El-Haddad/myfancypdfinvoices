package com.marouan.myfancypdfinvoices.service;

import com.marouan.myfancypdfinvoices.model.Invoice;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {

    private final List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public List<Invoice> findAll() {
        return List.copyOf(invoices);
    }

    public Invoice create(String userId, Integer amount) {
        // TODO real pdf creation and storing it on network server
        var invoice = new Invoice(userId, amount,
                "https://file-examples.com/wp-content/storage/2017/10/file-sample_150kB.pdf");
        invoices.add(invoice);
        return invoice;
    }
}