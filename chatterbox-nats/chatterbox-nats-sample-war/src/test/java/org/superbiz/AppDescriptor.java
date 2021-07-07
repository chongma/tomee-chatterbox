package org.superbiz;

import java.net.URL;

import org.apache.openejb.jee.ConnectionDefinition;
import org.apache.openejb.jee.Connector;
import org.apache.openejb.jee.OutboundResourceAdapter;
import org.apache.openejb.jee.ResourceAdapter;
import org.apache.openejb.testing.Application;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.ContainerProperties;
import org.apache.openejb.testing.Default;
import org.apache.openejb.testing.Module;
import org.apache.openejb.testing.RandomPort;
import org.apache.openejb.testing.SimpleLog;
import org.apache.tomee.chatterbox.nats.adapter.NATSResourceAdapter;
import org.apache.tomee.chatterbox.nats.adapter.out.NATSConnectionFactoryImpl;
import org.apache.tomee.chatterbox.nats.adapter.out.NATSConnectionImpl;
import org.apache.tomee.chatterbox.nats.adapter.out.NATSManagedConnectionFactory;
import org.apache.tomee.chatterbox.nats.api.NATSConnection;
import org.apache.tomee.chatterbox.nats.api.NATSConnectionFactory;

@Default
@SimpleLog
@Classes(cdi = true, context = "/",value = { EchoBean.class, Sender.class })
@ContainerProperties({
	  @ContainerProperties.Property(name = "NATS.baseAddress", value = "nats://localhost"),
	  @ContainerProperties.Property(name = "NATS.clusterId", value = "mycluster"),
	  @ContainerProperties.Property(name = "NATS.clientId", value = "tomee1")
	})
@Application
public class AppDescriptor {
	@RandomPort("http")
	private URL base;		
	
	public URL getBase() {
		return base;
	}
	
//	@Mock
//	private EchoBean echoBean;
			
	@Module
    public Connector connector() {
        final ConnectionDefinition connectionDefinition = new ConnectionDefinition();
        connectionDefinition.setId("NATSConnectionFactory");
        connectionDefinition.setConnectionImplClass(NATSConnectionImpl.class.getName());
        connectionDefinition.setConnectionInterface(NATSConnection.class.getName());
        connectionDefinition.setConnectionFactoryImplClass(NATSConnectionFactoryImpl.class.getName());
        connectionDefinition.setConnectionFactoryInterface(NATSConnectionFactory.class.getName());
        connectionDefinition.setManagedConnectionFactoryClass(NATSManagedConnectionFactory.class.getName());

        final OutboundResourceAdapter out = new OutboundResourceAdapter();
        out.getConnectionDefinition().add(connectionDefinition);

        final ResourceAdapter ra = new ResourceAdapter();
        ra.setId("NATS");
        ra.setResourceAdapterClass(NATSResourceAdapter.class.getName());
        ra.setOutboundResourceAdapter(out);

        final Connector connector = new Connector();
        connector.setVersion("1.7");
        connector.setResourceAdapter(ra);
        return connector;
    }
	
//	public static class MyRa extends NATSResourceAdapter {
//      @Override
//      public void start(final BootstrapContext ctx) throws ResourceAdapterInternalException {
//          // no-op
//      }
//
//      @Override
//      public void stop() {
//          // no-op
//      }
//
//      @Override
//      public void endpointActivation(final MessageEndpointFactory endpointFactory, final ActivationSpec spec) throws ResourceException {
//          // no-op
//      }
//
//      @Override
//      public void endpointDeactivation(final MessageEndpointFactory endpointFactory, final ActivationSpec spec) {
//          // no-op
//      }
//
//      @Override
//      public XAResource[] getXAResources(final ActivationSpec[] specs) throws ResourceException {
//          return new XAResource[0];
//      }
//	}	
}
