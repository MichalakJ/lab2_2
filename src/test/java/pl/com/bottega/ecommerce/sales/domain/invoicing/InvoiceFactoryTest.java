/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.bottega.ecommerce.sales.domain.invoicing;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;

/**
 *
 * @author Kuba
 */
public class InvoiceFactoryTest {
    @Test
    public void CreateShouldReturnNewInvoiceInstance(){
        InvoiceFactory factory = new InvoiceFactory();
        ClientData client = new ClientData(Id.generate(),"name");
        Invoice invoice = factory.create(client);
        assertThat(invoice.getClient().equals(client), equalTo(true));
        assertThat(invoice.getClass().toGenericString(), equalTo(Invoice.class.toGenericString()));
                
    }
}
