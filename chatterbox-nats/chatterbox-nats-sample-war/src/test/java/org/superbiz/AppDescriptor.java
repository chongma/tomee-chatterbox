package org.superbiz;

import java.net.URL;

import org.apache.openejb.testing.Application;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Default;
import org.apache.openejb.testing.RandomPort;
import org.apache.openejb.testing.SimpleLog;

@Default
@SimpleLog
@Classes(cdi = true, context = "/")
@Application
public class AppDescriptor {
	@RandomPort("http")
	private URL base;
	
	public URL getBase() {
		return base;
	}
}
