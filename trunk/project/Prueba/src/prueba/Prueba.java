/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 *
 * @author Aleks
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        String service="http://dbpedia.org/sparql";
//        String query = "ASK { }";
//        QueryExecution qe = QueryExecutionFactory.sparqlService(service,query);
//        if(qe.execAsk()) System.out.println("Query Executed :D");
//        System.out.println("===============================");
//        
//        
//      
//        String q="PREFIX dbo:<http://dbpedia.org/ontology/>"
//            + "PREFIX : <http://dbpedia.org/resource/>"
//            + "select ?person where {?person dbo:birthPlace :Eindhoven.}";
//        QueryExecution qe2=QueryExecutionFactory.sparqlService(service, q);
//        ResultSet rs=qe2.execSelect();
//        while (rs.hasNext()){
//            QuerySolution s1 = rs.nextSolution();
//            System.out.println(s1.getResource("?person").toString());
//        }
        
        String service="http://dbpedia.org/sparql";
        String query="PREFIX dbo:<http://dbpedia.org/ontology/>"
            + "PREFIX : <http://dbpedia.org/resource/>"
            + "PREFIX foaf:<http://xmlns.com/foaf/0.1/>"
            + "select ?person ?name where {?person dbo:birthPlace :Eindhoven."
            + "?person foaf:name ?name}";
        QueryExecution qe=QueryExecutionFactory.sparqlService(service, query);
        ResultSet rs=qe.execSelect();
        while (rs.hasNext()){
            QuerySolution s=rs.nextSolution();
            Resource r=s.getResource("?person");
            Literal name=s.getLiteral("?name");
            System.out.println(s.getResource("?person").toString());
            System.out.println(s.getLiteral("?name").getString());
        }
        
        
//        String service="http://dbpedia.org/sparql";
//        String query="PREFIX dbo:<http://dbpedia.org/ontology/>"
//            + "PREFIX : <http://dbpedia.org/resource/>"
//            + "PREFIX foaf:<http://xmlns.com/foaf/0.1/>"
//            + "select ?type {?type a owl:Class.}";
//              
//        QueryExecution qe=QueryExecutionFactory.sparqlService(service, query);
//        ResultSet rs=qe.execSelect();
//        while (rs.hasNext()){
//            QuerySolution s=rs.nextSolution();
//            Resource r=s.getResource("?type");
//            //Literal name=s.getLiteral("?name");
//            System.out.println(s.getResource("?type").toString());
//           // System.out.println(s.getLiteral("?name").getString());
//        }
        
        
        
        
    }
    
}
