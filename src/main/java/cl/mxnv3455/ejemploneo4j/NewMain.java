/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.mxnv3455.ejemploneo4j;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.driver.v1.*;

/**
 *
 * @author martin
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j", "gabita"));
        Session session = driver.session();

        session.run("CREATE (lampone:Usuario {nombre:'Pablo Lampone'})");
        session.run("CREATE (medina:Usuario {nombre:'Gabriel Medina'})");
        session.run("CREATE (santos:Usuario {nombre:'Mario Santos'})");
        session.run("CREATE (ravenna:Usuario {nombre:'Emilio Ravenna'})");
        session.run("CREATE (noHabra:Libro {autor:'Osvaldo Soriano', titulo: 'No habrá más penas ni olvido'})");
        session.run("CREATE (cienAnios:Libro {autor:'Gabriel García Márquez', titulo: '100 años de soledad'})");
        session.run("CREATE (novelaPeron:Libro {autor:'Tomás Eloy Martínez', titulo: 'La novela de Perón'})");
        session.run("CREATE (porQuien:Libro {autor:'Ernest Hemingway', titulo: '¿Por quién doblan las campanas?'})");
        session.run("CREATE (elAleph:Libro {autor:'Jorge Luis Borges', titulo: 'El Aleph'})");
        session.run("MATCH (santos: Usuario {nombre: 'Mario Santos'}), (elAleph: Libro {titulo: 'El Aleph'}) CREATE (santos)-[:LLEVO {fechaPrestamo: '01/05/2016'}]->(elAleph);");
        StatementResult result = session.run("MATCH (a) RETURN a; ");
        while (result.hasNext()) {
            Record record = result.next();
            List<Value> l = record.values();
            //System.out.println(record.values());
            for (int i = 0; i < l.size(); i++) {
                System.out.println(l.get(i).get("nombre"));
                
            }
            
        }

        session.close();
        driver.close();
    }

}
