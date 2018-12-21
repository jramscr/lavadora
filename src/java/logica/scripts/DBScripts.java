/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.scripts;

import java.io.File;
import java.net.URL;

/**
 *
 * @author jramos
 */
public class DBScripts {
    // Agregar otras constantes de archivos como se necesiten.
    private static final String LOAD_DATABASE_FILE = "/logica/scripts/database.sql";
    private static final String LOAD_TABLES_FILE = "/logica/scripts/tables.sql";
    
    public File readFileToCreateDataBase() {
        URL fileUrl = getClass().getResource(LOAD_DATABASE_FILE);
        File file = new File(fileUrl.getFile());
        return file;
    }
    
    public File readFileToCreateTables() {
        URL fileUrl = getClass().getResource(LOAD_TABLES_FILE);
        File file = new File(fileUrl.getFile());
        return file;
    }
}
