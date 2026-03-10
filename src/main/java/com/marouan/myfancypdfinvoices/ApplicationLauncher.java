package com.marouan.myfancypdfinvoices;

import com.marouan.myfancypdfinvoices.web.MyFancyPdfInvoiceServlet;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class ApplicationLauncher {

    static void main() throws LifecycleException {
        var tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        var ctx = tomcat.addContext("", null);
        var servlet = Tomcat.addServlet(ctx, "myFancyInvoiceServlet", new MyFancyPdfInvoiceServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");
        tomcat.start();
    }
}