/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.database.DBManager;
import logica.modelos.Cliente;

/**
 *
 * @author jramos
 */
@WebServlet(
  name = "Clientes",
  urlPatterns = {"/clientes"},
  initParams = {
    @WebInitParam(name = "cedula", value = ""),
    @WebInitParam(name = "nombre", value = ""),
    @WebInitParam(name = "celular", value = ""),
    @WebInitParam(name = "email", value = ""),
    @WebInitParam(name = "direccion", value = "")
  }
)
public class Clientes extends HttpServlet {
    DBManager manager = DBManager.getInstance();
    ArrayList<Object> clientes = manager.obtenerLista("Cliente");

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
        DBManager manager = DBManager.getInstance();
        Cliente cliente = new Cliente();

        if (request.getParameter("codigoCliente") != null) {
            String codigoCliente = request.getParameter("codigoCliente");
            for(Object clt : clientes) {
                Cliente iterator = (Cliente) clt;
                if(Integer.toString(iterator.getCodigoCliente()).equals(codigoCliente)) {
                    cliente = iterator;
                }
            }
        } else {
            cliente.setCedula(request.getParameter("cedula"));
            cliente.setNombre(request.getParameter("nombre"));
            cliente.setCelular(request.getParameter("celular"));
            cliente.setEmail(request.getParameter("email"));
            cliente.setDireccion(request.getParameter("direccion"));
            // Proceso para guardar objeto en la BD.
            manager.salvarObjeto(cliente);
            // Termina proceso de guardado.            
        }
        request.setAttribute("cliente", cliente);
        System.out.print(request.getContextPath() + "/clientes/mostrar.jsp");
        request.getRequestDispatcher("clientes/mostrar.jsp").forward(request, response);
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
        if ("delete".equals(request.getParameter("action"))) {
            doDelete(request, response);
        } else {
            processRequest(request, response);            
        }

    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        DBManager manager = DBManager.getInstance();
        Cliente cliente = new Cliente();

        if (request.getParameter("codigoCliente") != null) {
            String codigoCliente = request.getParameter("codigoCliente");
            for(Object clt : clientes) {
                Cliente iterator = (Cliente) clt;
                if(Integer.toString(iterator.getCodigoCliente()).equals(codigoCliente)) {
                    cliente = iterator;
                }
            }
            manager.destruirObjeto(cliente);
        }
        System.out.print(request.getContextPath() + "/clientes/listar.jsp");
        request.getRequestDispatcher("/clientes/listar.jsp").forward(request, response);
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
