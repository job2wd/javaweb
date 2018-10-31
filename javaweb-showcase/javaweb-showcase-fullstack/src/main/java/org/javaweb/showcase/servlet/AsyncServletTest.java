package org.javaweb.showcase.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/servlet/sync", asyncSupported = true)
public class AsyncServletTest extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -1260846330962560093L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    PrintWriter out = resp.getWriter();
    out.println("进入Servlet的时间：" + new Date() + ".");
    //out.flush();

    //在子线程中执行业务调用，并由其负责输出响应，主线程退出
    AsyncContext ctx = req.startAsync();
    
    new Thread(new Executor(ctx)).start();

    ctx.addListener(new AsyncListener() {

      @Override
      public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("onComplete");
      }

      @Override
      public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("onTimeout");
      }

      @Override
      public void onError(AsyncEvent event) throws IOException {
        System.out.println("onError");
      }

      @Override
      public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("onStartAsync");
      }
      
    });
    
    out.println("结束Servlet的时间：" + new Date() + ".");
    out.flush();
  }

}

class Executor implements Runnable {
  private AsyncContext ctx = null;
  public Executor(AsyncContext ctx){
      this.ctx = ctx;
  }

  @Override
  public void run(){
      try {
          //等待十秒钟，以模拟业务方法的执行
          Thread.sleep(10000);
          PrintWriter out = ctx.getResponse().getWriter();
          out.println("业务处理完毕的时间：" + new Date() + ".");
          out.flush();
          //ctx.complete();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}