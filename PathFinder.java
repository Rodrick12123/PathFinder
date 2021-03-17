import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class PathFinder {
  private List<List<Integer>> adj;
  public ArrayList<String> article;
  public ArrayList<String> links;
  public MysteryUnweightedGraphImplementation n;

  /**
  * Constructs a PathFinder that represents the graph with nodes (vertices) specified as in
  * nodeFile and edges specified as in edgeFile.
  * @param nodeFile name of the file with the node names
  * @param edgeFile name of the file with the edge names
  */
  public PathFinder(String nodeFile, String edgeFile){
    String splitBy = " ";  
    String inputFilePath = nodeFile;
    File inputFile = new File(inputFilePath);
    Scanner scanner = null;
    try {
        scanner = new Scanner(inputFile);
    } catch (FileNotFoundException e) {
        System.err.println(e);
        System.exit(1);
    }

    String split = "\t";
    String FilePath = edgeFile;
    File input = new File(FilePath);
    Scanner s = null;
    try {
        s = new Scanner(input);
    } catch (FileNotFoundException e) {
        System.err.println(e);
        System.exit(1);
    }
    
    n = new MysteryUnweightedGraphImplementation(true);
    int count = 0;
    article = new ArrayList<String>();
    links = new ArrayList<String>();
    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] art = line.split(splitBy);
        if(!art[0].equals("#")){
          
          
          if(!line.trim().isEmpty()){
            n.addVertex();
            article.add(line);
          }
          count++;
        }
    }

    int num = 0;
    while (s.hasNextLine()) {
        String l = s.nextLine();
        String[] data = l.split(splitBy);
        String[] link = l.split(split);

        if(!data[0].equals("#")){
         
          if(!l.trim().isEmpty()){
            int loc = 0;
            int jloc = 0;
            String con2 = "no";
            String con = "no";
            
            for(int i = 0; i < n.numVerts() ; i++) {
              

              if(link[0].equals(article.get(i))){
                
                loc = i;
                con = "yes";
              }
              if(link[1].equals(article.get(i))){
                
                jloc = i;
                con2 = "yes";
              }
              if(con2.equals("yes") && con.equals("yes")){
                
                n.addEdge(loc, jloc);
                links.add(article.get(loc) + article.get(jloc));
                loc = 0;
                jloc = 0;
                con2 = "no";
                con = "no";
              }
            }
          }
          num++;
        }
    }

    scanner.close();
  }
  
  /**
  * Returns the length of the shortest path from node1 to node2. If no path exists,
  * returns -1. If the two nodes are the same, the path length is 0.
  * @param node1 name of the starting article node
  * @param node2 name of the ending article node
  * @return length of shortest path
  */
  public int getShortestPathLength(String node1, String node2){
    int count = 0;
    int i1 = 0;
    int i2 = 0;
    int newi = 0;
    int edge = 0;

    for(int i = 0; i < n.numVerts() ; i++) {

      if(node1.equals(article.get(i))){
        i1 = i;
        newi = i;
      }
      if(node2.equals(article.get(i))){
        i2 = i;
      }
    }
    if(i2 == i1 ){
      System.out.print("They are the same");
      return count;
    }

    ArrayList<String> cList = new ArrayList<String>();
    int temp = 0;
    
    for(int i = 0; i < n.numVerts() ; i++) {
      
      if(cList.contains(article.get(i))){
        if(n.hasEdge(newi, i)){
          i = temp;
          newi = i1;
          count = 0;
          edge++;
          cList.clear();
        }
        
      }
      if(n.hasEdge(newi, i)){
        count++;
        cList.add(article.get(i));
        temp = i;
        if(n.hasEdge(newi, i2)){
          //System.out.println("The length of the shortest path is: " + count);
          return count;
        }
        
        newi = i;
        i = -1;
        
        } else if(i == n.numVerts() - 1){
          i = temp;
          newi = i1;
          count = 0;
          edge++;

          if(i == n.numVerts() && !n.hasEdge(newi, i)){
            System.out.println("N");
            return -1;
          }

        }

      if(edge == n.getDegree(i1)){
        System.out.println("There is no path");
        return -1;
      }
    }
    /*
    int x = 0;
    for(int i = 0; i < cList.size() ; i++) {
      if(cList.get(i) < x){
        cList.remove(i);
      }
      x = cList.get(i);
    }
    System.out.println(cList);
    */
    return 0;
  }
  /**
  * Returns a shortest path from node1 to node2, represented as list that has node1 at
  * position 0, node2 in the final position, and the names of each node on the path
  * (in order) in between. If the two nodes are the same, then the "path" is just a
  * single node. If no path exists, returns an empty list.
  * @param node1 name of the starting article node
  * @param node2 name of the ending article node
  * @return list of the names of nodes on the shortest path
  */
  public List<String> getShortestPath(String node1, String node2){
    int count = 0;
    int i1 = 0;
    int i2 = 0;
    int newi = 0;
    int edge = 0;
    ArrayList<String> path = new ArrayList<String>();
    for(int i = 0; i < n.numVerts() ; i++) {
      if(node1.equals(article.get(i))){
        i1 = i;
        newi = i;
      }
      if(node2.equals(article.get(i))){
        i2 = i;
      }
    }
    if(i2 == i1 || node1.equals(node2) ){
      path.add(article.get(i2));
      return path;
    }
    ArrayList<String> cList = new ArrayList<String>();
    int temp = 0;
    for(int i = 0; i < n.numVerts() ; i++) {
      if(cList.contains(article.get(i))){
        if(n.hasEdge(newi, i)){
          i = temp;
          newi = i1;
          count = 0;
          edge++;
          cList.clear();
        }
        
      }
      if(n.hasEdge(newi, i)){
        count++;
        path.add(article.get(newi));
        if(n.hasEdge(newi, i2)){
          //System.out.println("The shortest path is: " + path);
          if(count == 1){
            path.add(article.get(i2));
          }
          return path;
        }
        newi = i;
        i = -1;       
      } else if(i == n.numVerts() - 1){
          path.clear();
          i = count;
          newi = i1;
          count = 0;
          
      }
      if(edge == n.getDegree(i1)){
        path.clear();
        System.out.println("There is no path");
        return path;
      }
    }
    return path;
  }
  /**
  * Returns a shortest path from node1 to node2 that includes the node intermediateNode.
  * This may not be the absolute shortest path between node1 and node2, but should be 
  * a shortest path given the constraint that intermediateNodeAppears in the path. If all
  * three nodes are the same, the "path" is just a single node.  If no path exists, returns
  * an empty list.
  * @param node1 name of the starting article node
  * @param node2 name of the ending article node
  * @return list that has node1 at position 0, node2 in the final position, and the names of each node 
  *      on the path (in order) in between. 
  */
  public List<String> getShortestPath(String node1, String intermediateNode, String node2){
    int count = 0;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int newi = 0;
    int edge = 0;
    String check = "no";
    ArrayList<String> path = new ArrayList<String>();
    ArrayList<String> cList = new ArrayList<String>();
    int temp = 0;
    for(int i = 0; i < n.numVerts() ; i++) {
      if(node1.equals(article.get(i))){
        i1 = i;
        newi = i;
      }
      if(node2.equals(article.get(i))){
        i2 = i;
      }
      if(intermediateNode.equals(article.get(i))){
        i3 = i;
      }
    }
    if(i2 == i1 && i3 == i1 || node1.equals(node2) ){
      path.add(article.get(i2));
      return path;
    }
    
    for(int i = 0; i < n.numVerts() ; i++) {
      if(cList.contains(article.get(i))){
        if(n.hasEdge(newi, i)){
          i = temp;
          newi = i1;
          count = 0;
          edge++;
          cList.clear();
          path.clear();
        }
      }
      if(n.hasEdge(newi, i)){
        count++;
        
        path.add(article.get(newi));
        if(article.get(newi).equals(article.get(i3))){
          check = "yes";
        }
        if(n.hasEdge(newi, i2) && check.equals("yes")){
          //System.out.println("The length of the shortest path is: " + path);
          return path;
        }
        newi = i;
        i = -1;       
      } else if(i == n.numVerts() - 1){
          path.clear();
          i = count;
          newi = i1;
          edge++;
          count = 0;
          if(i == n.numVerts() && !n.hasEdge(newi, i)){
            path.clear();
            return path;
          }
      }
      if(edge == n.getDegree(i1)){
        path.clear();
        System.out.println("There is no path");
        return path;
      }
    }
    return path;
  }
  
  
  public static void main(String[] args) {  
    if(args.length == 5){
      String a = args[0];
      String b = args[1];
      String c = args[2];
      String d = args[4];
      String z = args[3];
      PathFinder n = new PathFinder(a, b);
      System.out.println("The path from " + c + " to " +  d  + " with " + z + " is: ");
      System.out.println(n.getShortestPath(c,z,d));
    }else if (args.length < 5 && args.length > 0){
      String a = args[0];
      String b = args[1];
      String c = args[2];
      String d = args[3];
      PathFinder n = new PathFinder(a, b);
      System.out.println("The path from " + c + " to " +  d + " is Length: " + n.getShortestPathLength(c, d));
      System.out.println(n.getShortestPath(c,d));
    } else{
      PathFinder n = new PathFinder("articles.tsv", "links.tsv");
      System.out.println("The path from " + "Áedán_mac_Gabráin" + " to " +  "Monarchy" + "is Length: " + n.getShortestPathLength("Áedán_mac_Gabráin", "Monarchy"));
      System.out.println(n.getShortestPath("Áedán_mac_Gabráin","Monarchy"));
      
    }
    
  }
}