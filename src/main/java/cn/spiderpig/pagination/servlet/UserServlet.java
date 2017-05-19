package cn.spiderpig.pagination.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import cn.spiderpig.pagination.entity.Result;
import cn.spiderpig.pagination.entity.User;
import cn.spiderpig.pagination.service.UserService;
import cn.spiderpig.pagination.util.StreamUtil;

//@WebServlet(urlPatterns="/UserServlet")
public class UserServlet extends HttpServlet {

    UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Result result = userService.addUser(username, password);

        PrintWriter out = response.getWriter();
        // out.println(new Gson().toJson(result));
        out.println(new Gson().toJson(userService.getAll()));
        out.flush();
        out.close();
        request.setAttribute("result", result);
        // request.getRequestDispatcher("/index.jsp").forward(request,
        // response);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        // request.getRequestDispatcher("OtherServlet").forward(request,
        // response);
        request.getRequestDispatcher("OtherServlet").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 要带着请求体，否则数据为null
        ServletInputStream in = request.getInputStream();
        String json = StreamUtil.readStream(in);
        System.out.println(json);

        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        Result result = userService.findUser(Integer.parseInt(currentPage), Integer.parseInt(pageSize));

        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(result));
        out.flush();
        out.close();
        
    }

}
