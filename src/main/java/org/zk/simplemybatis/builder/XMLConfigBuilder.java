package org.zk.simplemybatis.builder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.zk.simplemybatis.Configuration;
import org.zk.simplemybatis.MappedStatement;
import org.zk.simplemybatis.io.Resources;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

/**
 * mybatis配置文件解析
 */
public class XMLConfigBuilder {
    private InputStream configInputStram;

    public XMLConfigBuilder(InputStream configInputStram) {
        this.configInputStram = configInputStram;
    }

    public Configuration parse() {
        Configuration configuration = new Configuration();
        parseMappers(configuration);
        // 解析其他标签...
        return configuration;
    }

    private void parseMappers(Configuration configuration) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(configInputStram);
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.evaluate("/configuration/mappers/*", doc, XPathConstants.NODESET);
            for (int i = 1; i <= nodeList.getLength(); i++) {
                String resource = (String)xPath.evaluate("/configuration/mappers/mapper[" + i + "]/@resource", doc, XPathConstants.STRING);
                InputStream mapperInputStream = Resources.getResourceAsStream(resource);
                new XMLMapperBuilder(configuration, mapperInputStream).parse();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
