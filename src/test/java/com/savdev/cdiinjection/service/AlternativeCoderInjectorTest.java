package com.savdev.cdiinjection.service;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.spec.cdi.beans.BeansDescriptor;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.savdev.cdiinjection.bean.cdi.AlternativeCoder;
import com.savdev.cdiinjection.bean.cdi.Coder;

/**
 */
@RunWith(Arquillian.class)
public class AlternativeCoderInjectorTest
{
    @Deployment
    public static WebArchive createDeployment() throws URISyntaxException
    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("tests");
        String baseDir = resourceBundle.getString("basedir");
        File[] files = Maven.resolver().loadPomFromFile(baseDir + File.separator + "pom.xml")
                .importDependencies(ScopeType.COMPILE, ScopeType.PROVIDED).resolve().withTransitivity().asFile();
        BeansDescriptor beansXml = Descriptors.create(BeansDescriptor.class);
        WebArchive war = ShrinkWrap.create(WebArchive.class, "cdiinjection.war")
                .addClasses(Coder.class, AlternativeCoder.class, AlternativeCoderInjector.class)
                .addAsLibraries(files)
                .addAsWebInfResource(
                        //it adds into beans.xml:
                        // <alternatives><class>com.savdev.cdiinjection.bean.cdi.AlternativeCoder</class></alternatives>
                        new StringAsset(beansXml.alternativeClass(AlternativeCoder.class).exportAsString()),
                        beansXml.getDescriptorName());
        System.out.println(war.toString(true));
        return war;
    }

    @Inject
    AlternativeCoderInjector alternativeCoderInjector;

    @Test
    public void testWhatToDo()
    {
        Assert.assertNotNull(alternativeCoderInjector);
        Assert.assertEquals(AlternativeCoder.ALTERNATIVE_CODER, alternativeCoderInjector.whatToDo());
    }
}
