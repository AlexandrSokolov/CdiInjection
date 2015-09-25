package com.savdev.cdiinjection.service;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.savdev.cdiinjection.bean.cdi.DefaultDriver;
import com.savdev.cdiinjection.bean.cdi.Driver;
import com.savdev.cdiinjection.bean.cdi.SpecializesDriver;

/**
 */
@RunWith(Arquillian.class)
public class SpecializesDriverInjectorTest
{
    @Deployment
    public static WebArchive createDeployment() throws URISyntaxException
    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("tests");
        String baseDir = resourceBundle.getString("basedir");
        File[] files = Maven.resolver().loadPomFromFile(baseDir + File.separator + "pom.xml")
                .importDependencies(ScopeType.COMPILE, ScopeType.PROVIDED).resolve().withTransitivity().asFile();
        WebArchive war = ShrinkWrap.create(WebArchive.class, "cdiinjection.war")
                .addClasses(Driver.class, DefaultDriver.class, SpecializesDriver.class, DriverInjector.class)
                .addAsLibraries(files)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(war.toString(true));
        return war;
    }

    @Inject
    DriverInjector driverInjector;

    @Test
    public void testWhatToDo()
    {
        Assert.assertNotNull(driverInjector);
        Assert.assertEquals(SpecializesDriver.DO_DRIVER, driverInjector.doDrive());
    }
}
