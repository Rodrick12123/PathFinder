import java.util.ArrayList;
import java.util.List;
import java.util.*; 
import java.util.stream.Collectors; 

public class WordCountMap{
  public String word;
  public int value;
  public Tree root;
  public List<Character> chars;
  public Tree curnode;
  public Tree next;
  public List<Tree> nextChild;
  public int count;
  public int trial;
  public int val;
  /**
  * Constructs an empty WordCountMap.
  */        

  public WordCountMap(){

    trial = 0;
    count = 0;
    
    chars = new ArrayList<>();
    root = new Tree("", 0);
    next = root;
    /*
    Default test 
    root.addChild("t", 0);
    root.addChild("c", 0);
    root.children.get(0).addChild("h", 0);
    root.children.get(1).addChild("a", 0);
    root.children.get(0).children.get(0).addChild("e", 0);
    */
  }

    
  //This class implements similar functions as 
  //WordCount with getValue and getWord
  public class Tree {
        public String word;
        public int value;
        public List<Tree> children;
        
        private Tree parent = null;
        
        
        public Tree(String word, int value) {
            this.word = word;
            this.value = value;
            children = new ArrayList<Tree>();
           
        }
        public String getWord() {
          return this.word;
        }
        
        public int getValue() {
          return this.value;
        }
        public void addChild(String word, int value) {
          Tree child = new Tree(word, value);
          child.setParent(this);
          this.children.add(child);
        }
        public void setParent(Tree parent) {
          //parent.addChild(this);
          this.parent = parent;
        }
        public Tree getParent() {
          return parent;
        }
  }
  /*
  * Adds 1 to the existing count for word, or adds word to the WordCountMap
  * with a count of 1 if it was not already present.
  * Implementation must be recursive, not iterative.
  */
  
  public void incrementCount(String word){
    
   
    if(count > next.children.size() - 1){
      //System.out.println(next.getWord());
      System.out.println("Word not found " + word);
      chars.clear();
      wordChar(word);
      int amt = 0;
      count = 0;
      next = next.getParent();
      if(next.children != null){
        for(char c : chars){
          amt++;
          String ltr = Character.toString(c);
          nxtNode(next);
        
          if( amt == chars.size()){
            next.addChild(ltr, 1);

          }else{
            next.addChild(ltr, 0);
          }
        }
      }
      next = root;
    }else{

      if(count < next.children.size() && word.length() > 0){
        //System.out.println(count);
        char cur = wordChar(word).get(0);
        String lttr = Character.toString(cur);
        
        //System.out.println(cur + " C ");
        if(nxtNode(next).get(count).getWord().equals(lttr)){
          count = 0;
          chars.remove(0);
          //System.out.println(chars + "char");
          
          word = chars.stream().map(String::valueOf).collect(Collectors.joining());
          //System.out.println(word);
          
          if(word.length() > 0){
            incrementCount(word);
            
          }else{
            
            next.value++;
            next = root;
          }
        } else{
          location(count);
          next = next.getParent();
          incrementCount(word);
        }
      }
    }
    
  }

  public List<Tree> nxtNode(Tree node){
    int temp = 0;
   
    nextChild = node.children;
    this.next = node.children.get(count);
    //count = temp;
    return nextChild;
  }

  public int location(int num){
    count++;
    return count;
  }
  public List<Character> wordChar(String word){
    chars.clear();
    for (char ch : word.toCharArray()) { 
        chars.add(ch); 
    } 
    return chars;  
  }


  /**
  * Remove 1 to the existing count for word. If word is not present, does
  * nothing. If word is present and this decreases its count to 0, removes
  * any nodes in the tree that are no longer necessary to represent the
  * remaining words.
  */
  public void decrementCount(String word){
    //System.out.println(next.getWord());
    if(count > next.children.size() - 1){
      System.out.println("Word not found");
      chars.clear();
      
    }else{
      //System.out.println("X");
      if(count < next.children.size() && word.length() > 0){
        //System.out.println(next.children.size());
        char cur = wordChar(word).get(0);
        
        String lttr = Character.toString(cur);
        
        //System.out.println(nxtNode(next).get(count).getWord() + " C ");
        if(nxtNode(next).get(count).getWord().equals(lttr)){
          count = 0;
          chars.remove(0);
          //System.out.println(chars + "char");
          
          word = chars.stream().map(String::valueOf).collect(Collectors.joining());
          //System.out.println(word);
          
          if(word.length() > 0){
            decrementCount(word);
          }else{
            
            next.value--;
            next = root;
            if(next.value <= 0){
              if(next.children == null){
                int stop = 1;
                while(stop > 0){
                  if(next.getParent().getValue() > 0){
                    if(next.getParent().children.size() >= 2){
                      next.getParent().children.remove(next);
                      //Tree.remove(next);
                      stop = 0;
                    } else{
                      next.getParent().children.remove(next);
                      //Tree.remove(next);
                    }
                  } else{
                    next.getParent().children.remove(next);
                    //Tree.remove(next);
                    stop = 0;
                  }
                }
                
              }
            }
            
          }
        } else{
          location(count);
          next = next.getParent();
          decrementCount(word);
        }
      }
    }
    
  }


  /**
  * Returns true if word is stored in this WordCountMap with
  * a count greater than 0, and false otherwise.
  * Implementation must be recursive, not iterative.
  */
  public boolean contains(String word){
    if(count > next.children.size() - 1){
      System.out.println("Word not found");
      chars.clear();
      return false;
      
    }else{
      //System.out.println("X");
      if(count < next.children.size() && word.length() > 0){
        //System.out.println(next.children.size());
        char cur = wordChar(word).get(0);
        
        String lttr = Character.toString(cur);
        
        //System.out.println(nxtNode(next).get(count).getWord() + " C ");
        if(nxtNode(next).get(count).getWord().equals(lttr)){
          count = 0;
          chars.remove(0);
          //System.out.println(chars + "char");
          
          word = chars.stream().map(String::valueOf).collect(Collectors.joining());
          //System.out.println(word);
          
          if(word.length() > 0){
            contains(word);
          }else{
            next = root;
            if(next.getValue() > 0){
              return true;
            }else{
              return false;
            }
          }
        } else{
          location(count);
          next = next.getParent();
          contains(word);
        }
      }
    }
    return false;
  }
  public String getWrd(String word){
    String temp = word;
    if(count > next.children.size() - 1){
      System.out.println("Word not found ");
      chars.clear();
      return "Error";
    }else{
      //System.out.println("X");
      if(count < next.children.size() && word.length() > 0){
        //System.out.println(next.children.size());
        char cur = wordChar(word).get(0);
        
        String lttr = Character.toString(cur);
        //System.out.println(nxtNode(next).get(count).getWord() + " C ");
        if(nxtNode(next).get(count).getWord().equals(lttr)){
          count = 0;
          
          chars.remove(0);
          //System.out.println(chars + "char");
          
          word = chars.stream().map(String::valueOf).collect(Collectors.joining());
          //System.out.println(word);
          if(word.length() > 0){
            getWrd(word);
          }else{
            next = root;
            return temp;

          }
        }else{
          location(count);
          next = next.getParent();
          getWrd(word);
        }
      }
    }
    return temp;
  }
  

  /**
  * Returns the count of word, or -1 if word is not in the WordCountMap.
  * Implementation must be recursive, not iterative.
  */
  public int getCount(String word){
    
    if(count > next.children.size() - 1){
      System.out.println("Word not found ");
      chars.clear();
      return -1;
    }else{
      //System.out.println("X");
      
      if(count < next.children.size() && word.length() > 0){
        //System.out.println(next.children.size());
        char cur = wordChar(word).get(0);
        
        String lttr = Character.toString(cur);
        
       
        if(nxtNode(next).get(count).getWord().equals(lttr)){
          count = 0;
          chars.remove(0);
          //System.out.println(chars + "char");
          
          word = chars.stream().map(String::valueOf).collect(Collectors.joining());
          //System.out.println(word);
          
          if(word.length() > 0){
            getCount(word);
          }else{
            //System.out.println(next.getWord());
            val = next.getValue();
            
            //System.out.println(next.getValue());
            next = root;
            return val;
      
          }
        } else{
          location(count);
          next = next.getParent();
          getCount(word);
        }
      }
    }
    return val;
  }
  public List<Tree> nxtNode(Tree node, int loc){
    
    nextChild = node.children;
    this.next = node.children.get(loc);
    
    return nextChild;
  }
  /** 
  * Returns a list of WordCount objects, one per word stored in this 
  * WordCountMap, sorted in decreasing order by count. 
  */
  public List<Tree> getWordCounts(){
    List<Tree> obj = new ArrayList<>();
    next = root;
    while(count < root.children.size() -1){
      for(Tree wr : next.children){
        obj.add(wr);
      }
      if(count < next.children.size() - 1 && next.children != null){
        nxtNode(next);
      }else {
        int tmp = 0;
        
        if(next.children.size() > 0){
          nxtNode(next, tmp);
        }else {
          count++;
          next = root;
        }
      }
    }
    return obj;
  }
  

  /** 
  * Returns a count of the total number of nodes in the tree. 
  * A tree with only a root is a tree with one node; it is an acceptable
  * implementation to have a tree that represents no words have either
  * 1 node (the root) or 0 nodes.
  * Implementation must be recursive, not iterative.
  */
  public int getNodeCount(){
    int total = 0;
    next = root;
    while(count < root.children.size() -1){
      
      for(Tree x : next.children){
        total++;
      }
      if(count < next.children.size() - 1 && next.children != null){
        nxtNode(next);
      }else {
        int tmp = 0;
        
        if(next.children.size() > 0){
          nxtNode(next, tmp);
        }else {
          count++;
          next = root;
        }
        
      }
    }
    return total;
  }

  public static void main(String[] args) {
    WordCountMap n = new WordCountMap();

  }
}

