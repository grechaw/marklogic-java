package com.marklogic.manage.pkg.servers;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Namespace;
import org.junit.Test;

import com.marklogic.test.XmlHelper;
import com.marklogic.test.jdom.Fragment;
import com.marklogic.test.jdom.MarkLogicNamespaceProvider;
import com.marklogic.test.jdom.NamespaceProvider;

public class MergeHttpServersTest extends XmlHelper {

    private HttpServerPackageMerger sut = new HttpServerPackageMerger();

    @Test
    public void test() {
        List<String> mergeFilePaths = new ArrayList<>();
        mergeFilePaths.add("src/test/resources/test-http-server.xml");

        String xml = sut.mergeHttpServerPackages(mergeFilePaths);
        Fragment pkg = parse(xml);

        pkg.assertElementExists("/srv:package-http-server");
        pkg.assertElementExists("//srv:authentication[. = 'application-level']");
        pkg.assertElementExists("//srv:error-handler[. = '/MarkLogic/rest-api/error-handler.xqy']");
        pkg.assertElementExists("//srv:url-rewriter[. = '/MarkLogic/rest-api/rewriter.xqy']");
        pkg.assertElementExists("//srv:links/srv:modules-database[. = 'test-modules']");
        pkg.assertElementExists("//srv:links/srv:default-user[. = 'testuser']");
        
        pkg.prettyPrint();
    }

    @Override
    protected NamespaceProvider getNamespaceProvider() {
        return new MarkLogicNamespaceProvider() {
            @Override
            protected List<Namespace> buildListOfNamespaces() {
                List<Namespace> list = super.buildListOfNamespaces();
                list.add(Namespace.getNamespace("srv", "http://marklogic.com/manage/package/servers"));
                return list;
            }

        };
    }
}
