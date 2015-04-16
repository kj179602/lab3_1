package lab3_1;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.*;
import pl.com.bottega.ecommerce.sales.domain.invoicing.*;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.*;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class BookKeeperTest {

	@Test
	public void testCase1() {
		
		//given
		InvoiceFactory mockIFact = mock(InvoiceFactory.class);
		TaxPolicy mockTaxP = mock(TaxPolicy.class);
		ProductData productData = new ProductData(Id.generate(), new Money(1), "ranigast", ProductType.DRUG, new Date());
	    RequestItem requestItem = new RequestItem(productData, 1, new Money(2));
		ClientData clientData = new ClientData(Id.generate(), "lek");
		InvoiceRequest invoiceRequest = new InvoiceRequest(clientData);
        invoiceRequest.add(requestItem);
		BookKeeper bk = new BookKeeper(mockIFact);
		
		//when
		when(mockTaxP.calculateTax((ProductType) any(), (Money) any())).thenReturn(new Tax(new Money(1), ""));
		when(mockIFact.create(clientData)).thenReturn(new Invoice(Id.generate(), clientData));
		int matcher =1;
		Invoice invoice = bk.issuance(invoiceRequest, mockTaxP);
		//Then
		 Assert.assertThat(invoice.getItems().size(), is(1));
		
	}

	

}
