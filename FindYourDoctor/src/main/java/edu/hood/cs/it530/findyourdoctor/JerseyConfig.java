/**
 * 
 */
package edu.hood.cs.it530.findyourdoctor;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.common.FindYourDoctorAppResource;
import edu.hood.cs.it530.findyourdoctor.physicians.PhysicianResource;
import edu.hood.cs.it530.findyourdoctor.specialities.SpecialityResource;
import edu.hood.cs.it530.lab8.customers.CustomerResource;

/**
 * @author kisna
 *
 */

@Component
public class JerseyConfig extends ResourceConfig {

    /**
     * Registering Components
     */
    public JerseyConfig() {
        register(CorsFilter.class);
        register(HelloController.class);
        register(FindYourDoctorAppResource.class);
        register(PhysicianResource.class);
        register(SpecialityResource.class);
        register(CustomerResource.class);
    }

}
