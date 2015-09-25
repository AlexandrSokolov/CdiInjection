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

import com.savdev.cdiinjection.producer.ProducerService;
import com.savdev.cdiinjection.qualifier.MaxNumber;
import com.savdev.cdiinjection.qualifier.Random;

/**
 */
@RunWith(Arquillian.class)
public class RunTimeInjectorTest
{
    @Deployment
    public static WebArchive createDeployment() throws URISyntaxException
    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("tests");
        String baseDir = resourceBundle.getString("basedir");
        File[] files = Maven.resolver().loadPomFromFile(baseDir + File.separator + "pom.xml")
                .importDependencies(ScopeType.COMPILE, ScopeType.PROVIDED).resolve().withTransitivity().asFile();
        WebArchive war = ShrinkWrap.create(WebArchive.class, "cdiinjection.war")
                .addClasses(RunTimeInjector.class, ProducerService.class, MaxNumber.class, Random.class)
                .addAsLibraries(files)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(war.toString(true));
        return war;
    }

    @Inject
    RunTimeInjector runTimeInjector;

    @Test
    public void testHelloPojoBean()
    {
        Assert.assertNotNull(runTimeInjector);
        Assert.assertNotNull(runTimeInjector.randomInt);
        final int firstResult = runTimeInjector.randomInt.get().intValue();
        Assert.assertNotEquals(0, firstResult);
        final int secondResult = runTimeInjector.randomInt.get().intValue();
        Assert.assertNotEquals(0, secondResult);
        Assert.assertNotEquals(firstResult, secondResult);
    }

    /**
     * If we use objects, not primitives, the Instance.get() always return the same pointer
     */
    @Test
    public void testHelloPojoBeanViaInteger()
    {
        Assert.assertNotNull(runTimeInjector);
        Assert.assertNotNull(runTimeInjector.randomInt);
        final Integer firstResult = runTimeInjector.randomInt.get().intValue();
        final Integer secondResult = runTimeInjector.randomInt.get().intValue();
        Assert.assertSame(firstResult, secondResult);
    }
}
