package com.savdev.cdiinjection.boundary;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.savdev.cdiinjection.TestConstants;
import com.savdev.cdiinjection.bean.cdi.PojoBeanWithAccessToUi;
import com.savdev.cdiinjection.boundary.rest.InsideJaxRsInjector;
import com.savdev.cdiinjection.boundary.rest.JAXRSConfiguration;
import com.savdev.cdiinjection.boundary.web.ThreadLocalContextHolder;
import com.savdev.cdiinjection.boundary.web.WebFilterPassDataFromUiLayerToBusinessComponent;

/** We test here, that when requests comes from client,
 * the WebFilterPassDataFromUiLayerToBusinessComponent web filter adds some data from UI layer into
 * the ThreadLocalContextHolder
 * And when business component is invoked, this data is accessible there inside of the PojoBeanWithAccessToUi class
 */
@RunWith(Arquillian.class)
public class PassDataFromUiLayerToBusinessComponentTest {


    @Deployment
    public static WebArchive createDeployment() throws URISyntaxException
    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("tests");
        String baseDir = resourceBundle.getString("basedir");
        File[] files = Maven.resolver().loadPomFromFile(baseDir + File.separator + "pom.xml")
                .importDependencies(ScopeType.COMPILE, ScopeType.PROVIDED).resolve().withTransitivity().asFile();
        WebArchive war = ShrinkWrap.create(WebArchive.class, TestConstants.WEB_ARCHIVE_WITH_FILTER + ".war")
                .addClasses(InsideJaxRsInjector.class, JAXRSConfiguration.class, ThreadLocalContextHolder.class,
                        WebFilterPassDataFromUiLayerToBusinessComponent.class, PojoBeanWithAccessToUi.class)
                .addAsLibraries(files)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(war.toString(true));
        return war;
    }

    /**
     * We add header into web request, this header is attached to ThreadLocalContextHolder in web filter, and
     * information about this header is responsible in the PojoBeanWithAccessToUi business component.
     * @throws IOException
     */
    @Test
    @RunAsClient
    public void testHeaderIsPassedToBusinessComponentWhenRequestComesFromBrowser() throws IOException
    {
        Client client = ClientBuilder.newClient();
        final String testHeaderValue = "TestMe";
        Response response = client.target(TestConstants.HOST_PORT)
                .path(TestConstants.WEB_ARCHIVE_WITH_FILTER + JAXRSConfiguration.JAX_RS_ENDPOINT)
                .request().header(ThreadLocalContextHolder.UI_DATA_KEY, testHeaderValue).get();
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(PojoBeanWithAccessToUi.GREETING + testHeaderValue, response.readEntity(String.class));
    }
}
