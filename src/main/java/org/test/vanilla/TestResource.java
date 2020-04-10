package org.test.vanilla;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/vanilla")
public class TestResource {

    @GET
    @Path("/base")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseClass testBaseClass() {
        final BaseClass c = new BaseClass();
        c.setBaseVariable("myBaseVariable");
        return c;
    }

    @GET
    @Path("/extended")
    @Produces(MediaType.APPLICATION_JSON)
    public ExtendedClass testExtendedClass() {
        final ExtendedClass c = new ExtendedClass();
        c.setBaseVariable("myBaseVariable");
        c.setExtendedVariable("myExtendedVariable");
        return c;
    }
}