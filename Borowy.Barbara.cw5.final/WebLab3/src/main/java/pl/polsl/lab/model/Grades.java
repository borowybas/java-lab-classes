/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "GRADES")
@NamedQueries({
    @NamedQuery(name = "Grades.findAll", query = "SELECT g FROM Grades g"),
    @NamedQuery(name = "Grades.findByGradeId", query = "SELECT g FROM Grades g WHERE g.gradeId = :gradeId"),
    @NamedQuery(name = "Grades.findByValueOfGrade", query = "SELECT g FROM Grades g WHERE g.valueOfGrade = :valueOfGrade")})
public class Grades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GRADE_ID")
    private Long gradeId;
    @Basic(optional = false)
    @Column(name = "VALUE_OF_GRADE")
    private short valueOfGrade;
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "SUBJECT_ID")
    @ManyToOne
    private Subjects subjectId;

    public Grades() {
    }

    public Grades(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Grades(Long gradeId, short valueOfGrade) {
        this.gradeId = gradeId;
        this.valueOfGrade = valueOfGrade;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public short getValueOfGrade() {
        return valueOfGrade;
    }

    public void setValueOfGrade(short valueOfGrade) {
        this.valueOfGrade = valueOfGrade;
    }

    public Subjects getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subjects subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gradeId != null ? gradeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grades)) {
            return false;
        }
        Grades other = (Grades) object;
        if ((this.gradeId == null && other.gradeId != null) || (this.gradeId != null && !this.gradeId.equals(other.gradeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Grades[ gradeId=" + gradeId + " ]";
    }
    
}
