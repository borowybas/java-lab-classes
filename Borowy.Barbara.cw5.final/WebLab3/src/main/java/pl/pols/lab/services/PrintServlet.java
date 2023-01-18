/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.pols.lab.services;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pl.polsl.lab.model.StudentSingleton;
import pl.polsl.lab.model.SubjectModel;

/**
 *
 * @author SuperStudent.PL
 */
public class PrintServlet extends HttpServlet {

    private StudentSingleton studentSingleton;
    

    public PrintServlet(){
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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PrintServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("""
                         <ul id="myUL">
                          <li><span class="caret">Grades</span>
                            <ul class="nested">
                        """);
            
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) {
                Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rsGrades = statement.executeQuery("select * from grade");
                

                    while(rsGrades.next()){
                        String gradeVal = rsGrades.getString("value_of_grade");
                        
                        
                        try (Connection con2 = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) {
                            Statement statement2 = con2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                            ResultSet rsSubjects = statement2.executeQuery("select * from subject where subject_id="+rsGrades.getInt("subject_id"));
                            rsSubjects.next();
                            out.println( "<li><span class=\"caret\">"+rsSubjects.getString("subject_name")+"</span>");
                            out.println("<ul class=\"nested\">");
                            rsSubjects.close();
                        } catch (SQLException sqle) {
                             System.err.println(sqle.getMessage());
                        }
                        //for(Float grade: subject.getGrades()){
                        //rsGrades.next();
                            out.println("<li>" + gradeVal + "</li>");
                            //subjectTemp.getChildren().add(gradeVal);
                        //}

                        out.println("""
                                        </ul>
                                    </li>
                                    """);
                        
                    }
                rsGrades.close();
            
            } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
            
            out.println("""
                            </ul>
                          </li>
                        </ul> 
                        """);
            
            //out.println("<h1>Servlet PrintServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

}
