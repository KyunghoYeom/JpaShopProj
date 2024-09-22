package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //status line
        response.setStatus(HttpServletResponse.SC_OK);
        //response header
       response.setHeader("Content-Type", "text/plane;charset=utf-8");
       response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
       //캐쉬를 완전 무효화 하겠다는 의미
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");
        response.getWriter().write("ok");

    }
}
