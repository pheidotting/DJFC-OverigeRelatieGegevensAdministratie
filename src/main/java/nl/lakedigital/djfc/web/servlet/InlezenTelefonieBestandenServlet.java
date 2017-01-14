package nl.lakedigital.djfc.web.servlet;

import nl.lakedigital.djfc.service.InlezenTelefonieBestandenService;
import nl.lakedigital.djfc.service.TelefonieBestandService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InlezenTelefonieBestandenServlet implements ServletContextListener {
    private ScheduledExecutorService scheduler;

    @Inject
    private TelefonieBestandService telefonieBestandService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);


        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new InlezenTelefonieBestandenService(telefonieBestandService), 0, 5, TimeUnit.MINUTES);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

