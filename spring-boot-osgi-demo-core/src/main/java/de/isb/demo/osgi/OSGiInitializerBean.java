package de.isb.demo.osgi;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

@Component
public class OSGiInitializerBean {

    private static final Logger log = LoggerFactory.getLogger(OSGiInitializerBean.class);

    private Framework framework;

    public Framework getFramework() {
        return framework;
    }

    @PostConstruct
    public void init() {
        final Iterator<FrameworkFactory> iterator = ServiceLoader.load(FrameworkFactory.class).iterator();
        if (!iterator.hasNext()) {
            throw new IllegalStateException("Unable to locate OSGi framework factory");
        }
        final FrameworkFactory frameworkFactory = iterator.next();
        final Map<String, String> config = new HashMap<>();
        final Framework framework = frameworkFactory.newFramework(config);
        try {
            framework.start();
            Arrays.asList(framework.getBundleContext().getBundles()).forEach(b -> log.info("Bundle: {}", b.getSymbolicName()));
            Arrays.asList(framework.getRegisteredServices()).forEach(s -> log.info("Service reference: {}", s.toString()));
        } catch (BundleException e) {
            throw new IllegalStateException("Unable to start Framework");
        }
    }
}
