/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miu.onlinedictionary.controller;

import com.miu.onlinedictionary.dao.DbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Owner
 */
public class DictSevlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String att = request.getParameter("Requestd");
        Connection con = new DbConnection().connect();
        PrintWriter out = response.getWriter();
        try {
            String sql1 = "select * from entries where word like '"+att+"'";
            Statement statement = con.createStatement();
            
            ResultSet result = statement.executeQuery(sql1);
            int  counter= 1;
            while (result.next()) {
                
                out.print("<p >"+ counter+" "+ result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + "</p>");
                //out.print("<div><table><td>" + result.getString(1) + " " + result.getString(2) + " " + result.getString(3) +"</td><table><div>");
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print(e.getMessage()+att);
        }

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
