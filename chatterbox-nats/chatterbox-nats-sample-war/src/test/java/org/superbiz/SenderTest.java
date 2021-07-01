package org.superbiz;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.openejb.testing.Application;
import org.apache.openejb.testing.SingleApplicationComposerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SingleApplicationComposerRunner.class)
public class SenderTest {

	@Application
	private AppDescriptor descriptor;
		
	@Test
	public void testNatsResourceAvailable() {
		Client client = ClientBuilder.newClient();
		Response r = client.target(descriptor.getBase().toExternalForm() + "/sender/something").request().post(Entity.entity("my message", MediaType.TEXT_PLAIN));
		assertEquals(r.getStatus(), 401);
	}
}
