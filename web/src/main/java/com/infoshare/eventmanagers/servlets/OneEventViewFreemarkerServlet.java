package com.infoshare.eventmanagers.servlets;

import com.infoshare.eventmanagers.dto.EventDto;
import com.infoshare.eventmanagers.servlet.EventService;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/oneFreemarker")
public class OneEventViewFreemarkerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String TEMPLATE_DIR = "WEB-INF/templates";
    private Configuration cfg;
    @Inject
    EventService eventService;

    /**
     * Default constructor.
     */
    public OneEventViewFreemarkerServlet() {
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestParameter = request.getParameter("id");

        if (requestParameter != null) {
            Integer id = Integer.parseInt(requestParameter);
            EventDto eventDto = eventService.get(id);
            PrintWriter writer = response.getWriter();
            writer.println(eventDto.toString());
        }

//        Map<String, Object> root = new HashMap<String, Object>();
//        root.put("title", "Home");
//        root.put("cyclists", Cyclist.getRandomBikers());
//        Template template = cfg.getTemplate("home.ftlh");
//        Writer out = response.getWriter();
//        try {
//            template.process(root, out);
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
