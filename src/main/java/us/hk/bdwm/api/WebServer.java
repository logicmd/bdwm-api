package us.hk.bdwm.api;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import us.hk.bdwm.api.servlet.PostServlet;
import us.hk.bdwm.api.servlet.ThreadServlet;
import us.hk.bdwm.api.servlet.TopServlet;

import javax.annotation.Resource;
import java.util.Locale;

@Component
public class WebServer {
    private static final Logger LOG = LoggerFactory.getLogger(WebServer.class);

    @Resource
    private String jettyRequestLogPath;

    @Resource
    private int jettyHttpPort;

    @Autowired
    private PostServlet postServlet;

    @Autowired
    private ThreadServlet threadServlet;

    @Autowired
    private TopServlet topServlet;

    public static void main(String[] args) throws Exception {
        final ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{
                        "applicationContext.xml"
                });
        ((AbstractApplicationContext) context).registerShutdownHook();
        try {
            WebServer server = context.getBean(WebServer.class);
            server.start();
        } catch (Exception e) {
            LOG.warn("failed to start web server.", e);
        } finally {
            ((AbstractApplicationContext) context).close();
        }
        ((AbstractApplicationContext) context).close();
    }

    private void start() throws Exception {
        LOG.info("try to start BDWM api server.");
        Server server = new Server();
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMaxThreads(50);
        threadPool.setMinThreads(10);
        threadPool.setName("BDWMServer");
        server.setThreadPool(threadPool);

        // Setup connectors.
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(jettyHttpPort);
        server.addConnector(connector);

        // add servlets
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        ServletContextHandler context = new ServletContextHandler(contexts, "");
        ServletHolder postServletHolder = new ServletHolder(postServlet);
        context.addServlet(postServletHolder, "/post");
        ServletHolder threadServletHolder = new ServletHolder(threadServlet);
        context.addServlet(threadServletHolder, "/thread");
        ServletHolder topServletHolder = new ServletHolder(topServlet);
        context.addServlet(topServletHolder, "/top");

        HandlerCollection handlers = new HandlerCollection();
        RequestLogHandler requestLogHandler = new RequestLogHandler();
        handlers.setHandlers(new Handler[]{
                contexts, requestLogHandler
        });
        server.setHandler(handlers);

        // Setup request log.
        NCSARequestLog requestLog = new NCSARequestLog(jettyRequestLogPath
                + "/bdwm-request.log.yyyy_mm_dd");
        requestLog.setAppend(true);
        requestLog.setExtended(true);
        requestLog.setRetainDays(10);
        requestLog.setLogTimeZone("GMT+08:00");
        requestLog.setLogLocale(Locale.ENGLISH);
        requestLogHandler.setRequestLog(requestLog);
        server.start();
        LOG.info("start BDWM api server success.");
        server.join();
    }
}
