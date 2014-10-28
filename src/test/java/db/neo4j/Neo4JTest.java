package db.neo4j;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
import org.neo4j.rest.graphdb.query.QueryEngine;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;

public class Neo4JTest {
	
	RestAPI graphDb = new RestAPIFacade("http://localhost:7474/db/data");
	QueryEngine<?> engine=new RestCypherQueryEngine(graphDb); 
	
	@Test
	public  void createNodeIfNotExist() {
		     engine.query("MERGE (nn: Word { value:'nn'}) RETURN nn", null); 
	}
	
	@Test
	public  void deleteAllNodeAndRelation() {
		     engine.query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r", null); 
	}
	
	@Test
	public  void returnAll() {
		     engine.query("MATCH (n) OPTIONAL MATCH (n)-[r]-() RETURN n,r", Collections.EMPTY_MAP); 
	}
	
	@Test
	public  void createRelation() {
		     engine.query("START n=node(*), m=node(*)"+
                "where has(n.value) and has(m.value) and n.value = 'nn'"+
                "and m.value = 'vb'"+
                "create (n)-[:transform]->(m)", Collections.EMPTY_MAP); 
	}
	
	@Test
	public  void createRelation2() {
		     engine.query("MERGE (user:User {name:'John'})"+
		     " MERGE (friend:User {name:'Jane'}) "+
		     " MERGE (user)-[r:KNOWS2]->(friend) ", Collections.EMPTY_MAP); 
	}
	@Test
	public  void countAll() {
		 QueryResult<Map<String,Object>> result = (QueryResult<Map<String, Object>>) engine.query("START r=node(*) RETURN count(r)", Collections.EMPTY_MAP); 
		 Iterator<Map<String, Object>> iterator=result.iterator(); 
		 if(iterator.hasNext()) { 
		   Map<String,Object> row= iterator.next(); 
		   System.out.println("Total nodes: " + row); 
		 }
		 QueryResult<Map<String,Object>> result2 = (QueryResult<Map<String, Object>>) engine.query("START r=relationship(*) RETURN count(r)", Collections.EMPTY_MAP); 
		 Iterator<Map<String, Object>> iterator2 =result2.iterator(); 
		 if(iterator2.hasNext()) { 
		   Map<String,Object> row= iterator2.next(); 
		   System.out.println("Total relations: " + row); 
		 }
	}
}
