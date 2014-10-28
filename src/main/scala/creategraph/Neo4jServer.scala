package creategraph

import org.neo4j.rest.graphdb.query.RestCypherQueryEngine
import org.neo4j.rest.graphdb.RestAPIFacade
import java.util.Collections

class Neo4jGraph {
  
   	val graphDb = new RestAPIFacade("http://localhost:7474/db/data");
   	
	val engine = new RestCypherQueryEngine(graphDb); 
	
	def createNodeIfNotExist(word: String) : Unit = {
	   engine.query("MERGE (Word { value:'"+word.replaceAll("'", "")+"'})", null)
	}
	
	def deleteAllNodeAndRelation() {
	   engine.query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r", null); 
	}
	
	def createRelation(startword: String, endword: String, relation: String) {
		     engine.query("MERGE (startword:Word {value:'"+startword.replaceAll("'", "")+"'})"+
		     " MERGE (endword:Word {value:'"+endword.replaceAll("'", "")+"'}) "+
		     " MERGE (startword)-[r:"+relation+"]->(endword) ", null); 
	}
}
