/**
 * 
 */
package edu.hood.cs.it530.findyourdoctor;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * @author kisna
 *
 */

@Component
public class JerseyConfig extends ResourceConfig {

	/**
	 * 
	 */
	public JerseyConfig() {
		register(HelloController.class);
	}

}
