package com.configuration;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;


public class PageGenerator {

    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        cfg.setClassForTemplateLoading(PageGenerator.class, "/templates/");
        Writer writer = new StringWriter();
        try {
            Template template = cfg.getTemplate(filename);
            template.process(data, writer);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    public String getPage(String filename) {
        return getPage(filename, Collections.emptyMap());
    }

    private PageGenerator() {
        cfg = new Configuration();
    }
}

