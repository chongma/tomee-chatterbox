package org.superbiz;

import java.net.URL;

import javax.annotation.Resource;

import org.apache.openejb.mockito.MockitoInjector;
import org.apache.openejb.testing.Application;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Default;
import org.apache.openejb.testing.MockInjector;
import org.apache.openejb.testing.RandomPort;
import org.apache.openejb.testing.SimpleLog;
import org.apache.tomee.chatterbox.nats.api.NATSConnectionFactory;
import org.mockito.Mock;

@Default
@SimpleLog
@Classes(cdi = true, context = "/")
@Application
public class AppDescriptor {
	@RandomPort("http")
	private URL base;		
		
//	@Mock
	@Resource
	private NATSConnectionFactory cf;
	
//	@MockInjector
//    public Class<?> mockitoInjector() {
//        return MockitoInjector.class;
//    }
	
	public URL getBase() {
		return base;
	}
}
