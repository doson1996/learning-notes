package com.ds.zk.server;

import java.io.IOException;

/**
 * @author ds
 */
public class TestServlet extends Servlet {

    @Override
    public void doPost(Request request, Response response) {

    }

    @Override
    public void doGet(Request request, Response response) {
        try {
            response.write(request.getParams(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
