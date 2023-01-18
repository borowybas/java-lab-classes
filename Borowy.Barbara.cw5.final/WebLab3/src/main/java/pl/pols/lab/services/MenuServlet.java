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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import pl.polsl.lab.model.StudentSingleton;
import pl.polsl.lab.model.Students;
import pl.polsl.lab.model.WrongGradeException;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

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
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) {
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
            if(name != null){
                this.studentSingleton.getStudentModel().setStudentName(request.getParameter("name"));
                //adding to database
                // make a connection to DB
                // Wysyłamy zapytanie do bazy danych

                ResultSet rs = statement.executeQuery("SELECT * FROM Student");
                //adding a new row
                rs.moveToInsertRow();
                rs.updateInt("student_id", 0);
                rs.updateString("student_name", name);
                rs.insertRow();
                rs.moveToCurrentRow();
                rs.close();
                //System.out.println("Data updated");
                
                
               // System.out.println("Data updated");

            }else if(subjectName != null && grade != null){
                this.studentSingleton.getStudentModel().addSubjectAndGrade(subjectName, Float.valueOf(grade));

                
                int numberOfRowsInSubjects = 0;
                
                //database
                //ResultSet resultSet = statement.executeQuery("SELECT * FROM Subjects WHERE name="+name);
                ResultSet rsNumberOfRowsInSubjects = statement.executeQuery("select count(*) from Subject");
                //Retrieving the result
                while(rsNumberOfRowsInSubjects.next()){
                    numberOfRowsInSubjects = rsNumberOfRowsInSubjects.getInt(1); //indeks pod który wpisać następny przedmiot (+1)
                }
                
                rsNumberOfRowsInSubjects.close();
                
                ResultSet rsNumberOfRowsInGrades = statement.executeQuery("select count(*) from Grade");
                rsNumberOfRowsInGrades.next();
                int numberOfRowsInGrades = rsNumberOfRowsInGrades.getInt(1);//indeks pod który wpisać następną ocene (+1)
                rsNumberOfRowsInGrades.close();
                
                //ResultSet rsCheckSubject = statement.executeQuery("select * from subject where subject_name="+subjectName);
                    
                //rsCheckSubject.
                //if (!rsCheckSubject.next()) {
                    //tu dodaj nowy przedmiot
                    int subjIndex = numberOfRowsInSubjects+1;
//                    ResultSet rsAddSub = statement.executeQuery("select * from Subjects");
//                    rsAddSub.moveToInsertRow();
//                    rsAddSub.updateLong("student_id", 0);
//                    rsAddSub.updateLong("subject_id", subjIndex);
//                    rsAddSub.updateString("subject_name", subjectName);
//                    
//                    rsAddSub.insertRow();
//                    rsAddSub.moveToCurrentRow();
//                    rsAddSub.close();
                    ResultSet rsAddSub = statement.executeQuery("select * from Subject");
                    rsAddSub.moveToInsertRow();
                    rsAddSub.updateInt("student_id", 0);
                    rsAddSub.updateInt("subject_id", subjIndex);
                    rsAddSub.updateString("subject_name", subjectName);

                    rsAddSub.insertRow();
                    rsAddSub.moveToCurrentRow();
                    rsAddSub.close();
                    
                    
                    //dodaj ocene
                    ResultSet rsAddGrade = statement.executeQuery("select * from Grade");
                    rsAddGrade.moveToInsertRow();
                    rsAddGrade.updateInt("grade_id", numberOfRowsInGrades+1);
                    rsAddGrade.updateInt("subject_id", subjIndex);
                    rsAddGrade.updateString("value_of_grade", grade);
                    
                    rsAddGrade.insertRow();
                    rsAddGrade.moveToCurrentRow();
                    rsAddGrade.close();
                    
//                } else {//tu dodaj tylko nową ocene
//                    //weż indeks z rsCheckSubject
//                
//                    ResultSet rsAddGrade = statement.executeQuery("select * from Grade");
//                    rsAddGrade.moveToInsertRow();
//                    rsAddGrade.updateInt("grade_id", numberOfRowsInGrades+1);
//                    rsAddGrade.updateInt("subject_id", rsCheckSubject.getInt("subject_id"));
//                    rsAddGrade.updateString("value_of_grade", grade);
//                    
//                    rsAddGrade.insertRow();
//                    rsAddGrade.moveToCurrentRow();
//                    rsAddGrade.close();
//                    
////                    do {
////                      String data = rsCheckSubject.getString("emp_name");
////                      System.out.println(data);
////                      
////                    } while (rsCheckSubject.next());
//                }

                //rsCheckSubject.close();
            }
        
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        
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
                <input type="submit" value="Add new grade" />
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
