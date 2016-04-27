/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.util.Date;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

/**
 *
 * @author student
 */
public class BookKeeperTest {
    
    private ClientData client;
    private InvoiceRequest invoiceRequest;
    private TaxPolicy taxPolicy;
    @Before
    public void setUpForTest(){
        client = new ClientData(Id.generate(), "client1");
        invoiceRequest = new InvoiceRequest(client);
        taxPolicy = new TaxPolicy() {
		public Tax calculateTax(ProductType productType, Money net) {
                    return new Tax(new Money(2), "tax info");
		}
	};

       
    }
    
    @Test
    public void givenInvoiceRequestWithOneItem_whenIssuance_thenInvoiceWithOneItem(){
        ProductData product = new ProductData(Id.generate(),new Money(20), "product", ProductType.DRUG, new Date());
        RequestItem item = new RequestItem(product, 1, new Money(20));
        invoiceRequest.add(item);
        
        BookKeeper bk = new BookKeeper();
        Invoice result = bk.issuance(invoiceRequest, taxPolicy);
        assertThat(result.getItems().size(), equalTo(1));
    }
    @Test
    public void givenEmptyInvoiceRequest_whenIssuance_thenInvoiceWithNoItems(){
        
        BookKeeper bk = new BookKeeper();
        Invoice result = bk.issuance(invoiceRequest, taxPolicy);
        assertThat(result.getItems().size(), equalTo(0));
        
    }
    @Test
    public void givenInvoiceRequest_whenIssuance_theInvoiceShouldHaveClientInfo(){
        BookKeeper bk = new BookKeeper();
        Invoice result = bk.issuance(invoiceRequest, taxPolicy);
        assertThat(result.getClient(), equalTo(client));
    }
    
}
