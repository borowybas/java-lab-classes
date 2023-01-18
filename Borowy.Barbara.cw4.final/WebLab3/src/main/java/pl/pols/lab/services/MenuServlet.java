/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.pols.lab.services;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.polsl.lab.model.StudentSingleton;
import pl.polsl.lab.model.WrongGradeException;

/**
 *
 * @author SuperStudent.PL
 */
@WebServlet(name = "MenuServlet", urlPatterns = {"/MenuServlet"})
public class MenuServlet extends HttpServlet {

    private StudentSingleton studentSingleton;
    

    public MenuServlet(){
        this.studentSingleton = StudentSingleton.getInstance();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String name = request.getParameter("name");
        String subjectName = request.getParameter("subjectName");
        String grade = request.getParameter("grade");
        
        if(name != null){
            this.studentSingleton.getStudentModel().setStudentName(request.getParameter("name"));
        }else if(subjectName != null && grade != null){
            this.studentSingleton.getStudentModel().addSubjectAndGrade(subjectName, Float.valueOf(grade));
        }
        /*
        if(name.equals("")){
            
            try (PrintWriter out = response.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>MenuServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error</h1>");
                out.println("""
                      you forgot to enter your name
                    <form action="MenuServlet" method="GET">
                        <p>Name:<input type=text size=20 name=name></p>
                        <input type="submit" value="Enter name" />
                    </form>
                    <hr>
                        """);
                out.println("</body>");
                out.println("</html>");
            }
            
        }else if(name == null && subjectName == null || grade == null || Float.parseFloat(grade) > 5 || Float.parseFloat(grade) < 2){
            
            try (PrintWriter out = response.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>MenuServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>wrong data</h1>");
                out.println("""
                    <form action="AddGradeServlet" method="GET">
                        <input type="submit" value="Add new grade" />
                    </form>
                    <hr>
                        """);
                out.println("</body>");
                out.println("</html>");
            }
            
        }else{
             if(name != null){
            this.studentSingleton.getStudentModel().setStudentName(request.getParameter("name"));
            }else if(subjectName != null && grade != null){
            this.studentSingleton.getStudentModel().addSubjectAndGrade(subjectName, Float.valueOf(grade));
        }   */
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>MenuServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + this.studentSingleton.getStudentModel().getStudentName() + " choose an action</h1>");
                out.println("""
                    <form action="AddGradeServlet" method="GET">
                    <input type="submit" value="Try again" />
                    </form>
                    <hr>

                        """);
                out.println("""
                    <form action="PrintServlet">
                        <input type="submit" value="Print grades" />
                    </form>
                    """);
                out.println("</body>");
                out.println("</html>");
            }
        //}
        
    }

    
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /*
    <hr>
        <form action="Add" method="GET">
            <p>Subject name:<input type=text size=20 name=subjectName></p>
            <p>Grade:<input type=text size=20 name=grade></p>
            <input type="submit" value="Enter name" />
        </form>
        <form action="Print">
            <input type="submit" value="Print grades" />
        </form>
        <hr>
    */
    
}
