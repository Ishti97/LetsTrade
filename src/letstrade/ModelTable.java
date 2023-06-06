/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author AvoidIshti
 */
public class ModelTable {
     Connection conn;
    ResultSet rs = null;
    PreparedStatement pst;
     public ModelTable() {
        javaconnect.Connecrdb();
    }
    
    
    String pid,pn,qis;

    public ModelTable(String pid, String pn, String qis) {
        this.pid = pid;
        this.pn = pn;
        this.qis = qis;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getQis() {
        return qis;
    }

    public void setQis(String qis) {
        this.qis = qis;
    }
    
}
