package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //message body 내용을 바이트코드로 바로 얻을 수 있음
        ServletInputStream inputStream = request.getInputStream();
        //바이트를 문자로 바꿀 때 어떤 인코딩인지도 알려줘야 된다
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        ServletInputStream inputStream1 = request.getInputStream();
        String s = StreamUtils.copyToString(inputStream1, StandardCharsets.UTF_8);
        System.out.println("message = " + s);

        response.getWriter().write("ok");

    }
}
