/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.scripts.DBScripts;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jramos
 */
public class DBManager {
    private static DBManager databaseManager;
    private static DBConnector connector;

    public static DBConnector getConnector() {
        return connector;
    }

    public static void setConnector(DBConnector connector) {
        DBManager.connector = connector;
    }

    public DBManager() {}
    
    // Se se quiere usar una instancia de esta clase, se debe de
    // utilizar este metodo estatito. En lugar de iniciarlizar un objeto nuevo.
    // DBManager manager = DBManager.getInstance();
    //
    public static DBManager getInstance(){
        if(databaseManager == null){
            databaseManager = new DBManager();
            connector = new DBConnector();
        }
        connector.connect();
        return databaseManager;
    }
    
    public void executeUpdate(String query) {
        connector.executeUpdate(query);
    }
    
    public void executeQuery(String query) {
        ResultSet resultados = connector.executeQuery(query);
        try {
            List<String> results = new ArrayList<>();
            while(resultados.next()) {
                System.out.println(resultados.getString(1));
                results.add(resultados.getString(1));
            }
            System.out.println(results);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    @SuppressWarnings("null")
    public boolean createDB() {
        DBScripts scripts = new DBScripts();
        File file = scripts.readFileToCreateDataBase();
        boolean isScriptExecuted = false;

        try {
            StringBuilder query = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                String linea;
                while ((linea = in.readLine()) != null) {
                    query.append(linea).append("\n");
                }
            }
            connector.executeUpdate(query.toString());
            isScriptExecuted = true;
        } catch (IOException e) {
            System.err.println("=== DBManager:CreateDB::Exception ===> " + e);
        }
        return isScriptExecuted;
    }
    
    @SuppressWarnings("null")
    public boolean createTables() {
        DBScripts scripts = new DBScripts();
        File file = scripts.readFileToCreateTables();
        boolean isScriptExecuted = false;

        try {
            StringBuilder query = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                String linea;
                while ((linea = in.readLine()) != null) {
                    query.append(linea).append("\n");
                }
            }

            PreparedStatement statement;
            
            try {
                statement = connector.getConnection().prepareCall(query.toString());
                statement.executeUpdate();
                isScriptExecuted = true;
            } catch (SQLException ex) {
                System.err.println("=== DBManager:CreateTables:PreparedStatement:Exception ===> " + ex);
                isScriptExecuted = false;
            }
            
            
        } catch (IOException e) {
            System.err.println("=== DBManager:CreateTables::Exception ===> " + e);
        }
        return isScriptExecuted;
    }
    
    // Metodo para salvar los datos de un objeto en la BD.
    public void salvarObjeto(Object objeto) {
        String nombreTabla = new StringBuilder().append("tbl_").
          append(objeto.getClass().getSimpleName().toLowerCase()).toString();
        String atributosObjeto = this.lineaDesdeArray(this.obtenerNombresDeAtributos(objeto));
        String valoresObjeto = this.lineaDesdeArray(this.obtenerValoresDeAtributos(objeto));

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(nombreTabla);
        query.append(" (").append(atributosObjeto).append(")");
        query.append(" VALUES (").append(valoresObjeto).append(");");
        
        try {
            PreparedStatement statement = connector.getConnection().prepareCall(query.toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("=== DBManager:SalvarObjeto::Exception ===> " + ex);
        }
    }

    // Metodo para actualizar la información de la BD.
    public void actualizarObjeto(Object objeto) {
        String nombreClase = objeto.getClass().getSimpleName();
        String nombreTabla = new StringBuilder().append("tbl_").
          append(nombreClase.toLowerCase()).toString();
        List<String> atributosObjeto = this.obtenerNombresDeAtributos(objeto);
        String llavePrimaria = new StringBuilder().append("codigo").append(nombreClase).toString();
        List<String> valoresObjeto = this.obtenerValoresDeAtributos(objeto);
        String valorLlavePrimaria = this.evaluarLlavePrimaria(objeto, llavePrimaria);

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(nombreTabla).append(" SET ");
        
        for (int index = 0; index < atributosObjeto.size(); index++) {
            String atributo = atributosObjeto.get(index);
            String valorAtributo =  valoresObjeto.get(index);
            query.append(atributo).append(" = ").append(valorAtributo);
            if(index != atributosObjeto.size() -1 ) {
                query.append(", ");
            } else {
                query.append(" ");
            }
        }
        
        query.append(" WHERE ").append(llavePrimaria).append(" = ");
        query.append("\"").append(valorLlavePrimaria).append("\"").append(";");

        try {
            PreparedStatement statement = connector.getConnection().prepareCall(query.toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("=== DBManager:ActualizarObjeto::Exception ===> " + ex);
        }
    }
    
    // Metodo para borrar los datos de la BD.
    public void destruirObjeto(Object objeto) {
        String nombreClase = objeto.getClass().getSimpleName();
        String nombreTabla = new StringBuilder().append("tbl_").
          append(nombreClase.toLowerCase()).toString();
        String llavePrimaria = new StringBuilder().append("codigo").append(nombreClase).toString();
        String valorLlavePrimaria = this.evaluarLlavePrimaria(objeto, llavePrimaria);

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ").append(nombreTabla);
        query.append(" WHERE ").append(llavePrimaria).append(" = ");
        query.append("\"").append(valorLlavePrimaria).append("\"").append(";");
        
        try {
            System.out.println(query.toString());
            PreparedStatement statement = connector.getConnection().prepareCall(query.toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("=== DBManager:DestruirObjeto::Exception ===> " + ex);
        }
    }
        
    //  COMO USAR ESTE METODO
    //  DBManager manager = DBManager.getInstance();
    //  ArrayList<Object> listaAreas = manager.obtenerLista("Area");
    //    for(int index = 0; index < listaAreas.size(); index++) {
    //        // El objeto que se instancia, debe de ser del mismo tipo del nombre de clase que trata de obtener
    //        Area area = (Area) listaAreas.get(index);
    //        // Aquí se agarran los valores y se despliegan en la vista.
    //        // Por ejemplo:
    //        //
    //        // input_text.setText(area.getNombre());
    //        //
    //        // Así consecutivamente con los demas campos.
    //    }
    //
    public ArrayList<Object> obtenerLista(String nombreClase) {
        ArrayList<Object> listaDeResultados = new ArrayList<>();
        String nombreTabla = new StringBuilder().append("tbl_").
          append(nombreClase.toLowerCase()).toString();
        StringBuilder query = new StringBuilder();

        String className = "logica.modelos." + StringUtils.capitalize(nombreClase);
        Class klass = null;
        List<String> atributos = null;
        try {
            klass = Class.forName(className);
            atributos = this.obtenerNombresDeAtributos(klass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String ordenColumnas = this.lineaDesdeArray(atributos);

        query.append("SELECT ").append(ordenColumnas).append(" FROM ").append(nombreTabla);
        query.append(" ORDER BY ").append(ordenColumnas).append(";");
        System.out.println(query.toString());
        
        try {
            ResultSet resultados = connector.getConnection().prepareCall(query.toString()).executeQuery();
            
            while(resultados.next()) {
                try {
                    Constructor<?> constructor = klass.getConstructor();
                    Object objeto = constructor.newInstance();

                    for (int index = 0; index < atributos.size(); index++) {
                        String nombreMetodo = "set" + StringUtils.capitalize(atributos.get(index).replace("'", ""));
                        Method metodo = objeto.getClass().getDeclaredMethod(nombreMetodo, String.class);
                        metodo.invoke(objeto, resultados.getString(atributos.get(index).toString()));
                    }
                    listaDeResultados.add(objeto);
                } catch (Exception ex) {
                    System.out.println("=== DBManager:ObtenerLista::WhileLoop ===> " + ex);
                }
           }
        } catch (SQLException ex) {
            System.out.println("=== DBManager:ObtenerLista::SQLException ===> " + ex);
        }

        return listaDeResultados;
    }
    
    public void cerrarConexion() {
        connector.close();
    }
    
    private List<String> obtenerNombresDeAtributos(Object objeto) {
        Class<?> clazz = objeto.getClass();
        List<String> atributosDeClase = new ArrayList<>();

        for(Field field : clazz.getDeclaredFields()) {
            StringBuilder atributoFormateado = new StringBuilder();
            //you can also use .toGenericString() instead of .getName(). This will
            //give you the type information as well.
            atributoFormateado.append(field.getName());
            atributosDeClase.add(atributoFormateado.toString());
        }
        
        return atributosDeClase;
    }
    
    // Sobrecarga de metodo, para obtener los valores del objetos segun la clase.
    private List<String> obtenerNombresDeAtributos(Class clazz) {
        List<String> atributosDeClase = new ArrayList<>();

        for(Field field : clazz.getDeclaredFields()) {
            StringBuilder atributoFormateado = new StringBuilder();
            //you can also use .toGenericString() instead of .getName(). This will
            //give you the type information as well.
            atributoFormateado.append(field.getName());
            atributosDeClase.add(atributoFormateado.toString());
        }
        
        return atributosDeClase;
    }
    
    public List<String> obtenerValoresDeAtributos(Object objeto) {
        List<String> atributos = this.obtenerNombresDeAtributos(objeto);
        List<String> valores = new ArrayList<>();

        try {
            for (int contador = 0; contador < atributos.size(); contador++) {
                String nombreMetodo = "get" + StringUtils.capitalize(atributos.get(contador).replace("'", ""));
                Method metodo = objeto.getClass().getMethod(nombreMetodo);
                String valorMetodo = "\"" + metodo.invoke(objeto).toString() + "\"";                
                valores.add(valorMetodo);
            }
        }
        catch(IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e){
           System.out.println(e);
        }

        return valores;
    }
    
    private String lineaDesdeArray(List<String> listaDatos) {
        List<String> listaDeDatos = new ArrayList<>(listaDatos);
        String linea = String.join(", ", listaDeDatos);
        return linea;
        
    }
    
    private String evaluarLlavePrimaria(Object objeto, String nombreLLavePrimaria) {
        String valor = new String();
        
        try {
            String nombreMetodo = "get" + StringUtils.capitalize(nombreLLavePrimaria.replace("'", ""));
            Method metodo = objeto.getClass().getMethod(nombreMetodo);
            valor = metodo.invoke(objeto).toString();
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.out.println(e);
        }
        
        return valor;
    }
}
