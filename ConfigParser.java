import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigParser
{
 Document document = null;
 String  Path = "UnicomSP_QT" ; 

 public ConfigParser(String fileName) throws Exception
 {
   try
   {
     DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
     DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
//---------------------- in Eclispe start --------------------------------------------------
//      
//      String filepath = ConfigParser.class.getResource("/").toString();    //-- in Eclispe 
//      String fileuri = filepath + fileName;                                 //-- in Eclispe 
//      System.out.println("fileuri:"+fileuri);                             //-- in Eclispe 
//      this.document = localDocumentBuilder.parse(fileuri);                 //-- in Eclispe 
//
//---------------------- in Eclispe end ----------------------------------------------------- 
 

/**
* Jar Dir   在eclipse运行会出错 打包出来是可以的。
* 
*  /YourDirPath/
*               |---.                
*               |___config.xml
*               |___config_def.xml
*               |___config_send.xml
*               |___bin/ (Dir)
*                    |___UnicomSP2_test.jar (YourJar)
*                    |___hibernate.cfg.xml
*                  
* */    
//---------------------- in Jar start --------------------------------------------------- 
//
//        System.out.println(fileName);
       this.document = localDocumentBuilder.parse(fileName);          
//      
//---------------------- in Jar end  ----------------------------------------------------- 
   }
   catch (Exception localException)
   {
     this.document = null;
     localException.printStackTrace();
     throw new Exception("");
   }
   
  
 }

 public String getConfig(String paramString)
 {
   String str = null;
   int i = 0;
   int j = 0;
   Node localNode = null;
   int k = 1;
   StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "/");
   ArrayList<String> localArrayList = new ArrayList<String>();
   while (localStringTokenizer.hasMoreTokens())
     localArrayList.add(localStringTokenizer.nextToken());
   Element localElement = this.document.getDocumentElement();
   NodeList localNodeList = null;
   if (localElement.getNodeName().equals(localArrayList.get(i)))
   {
     k = 1;
     localNodeList = localElement.getChildNodes();
     i = 1;
   }
   else
   {
     return str;
   }
   
   label299:
   
   while ((i <= localArrayList.size()) && (k != 0))
   {
     k = 0;
     if (localNodeList.getLength() > 0)
     {
       j = 0;
       if (i == localArrayList.size())
       {
         k = 1;
         while (true)
         {
           if (j >= localNodeList.getLength())
             break label299;
           localNode = localNodeList.item(j);
           if ((localNode.getNodeType() == 3) && (localNode.getNodeValue().trim().length() > 0))
             str = localNode.getNodeValue().trim();
           ++j;
         }
       }
       while ((j < localNodeList.getLength()) && (k == 0))
       {
         localNode = localNodeList.item(j);
         if ((localNode.getNodeType() == 1) && (localNode.getNodeName().equals(localArrayList.get(i))))
         {
           k = 1;
           localNodeList = localNode.getChildNodes();
         }
         ++j;
       }
     }
      ++i;
   }
   return str;
 }

 public Hashtable<String, String> getConfigMap(String paramString)
 {
   Hashtable<String, String> localHashtable = new Hashtable<String, String>();
   
   int i = 0;
   
   int j = 0;
   
   Node localNode = null;
   
   int k = 1;
   
   StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "/");
   
   ArrayList<String> localArrayList = new ArrayList<String>();
   
   while (localStringTokenizer.hasMoreTokens())
       localArrayList.add(localStringTokenizer.nextToken());
   
   Element localElement = this.document.getDocumentElement();
   
   NodeList localNodeList = null;
   
   if (localElement.getNodeName().equals(localArrayList.get(i)))
   {
     k = 1;
     localNodeList = localElement.getChildNodes();
     i = 1;
   }
   else
   {
     return localHashtable;
   }
   
   while ((i <= localArrayList.size()) && (k != 0))
   {
     k = 0;
     if (localNodeList.getLength() > 0)
     {
       j = 0;
       if (i == localArrayList.size())
       {
         k = 1;
         NamedNodeMap localNamedNodeMap = null;
         if (localNode.hasAttributes())
           localNamedNodeMap = localNode.getAttributes();
         while (j < localNamedNodeMap.getLength())
         {
           localNode = localNamedNodeMap.item(j);
           if (localNode.getNodeType() == 2)
             localHashtable.put(localNode.getNodeName(), localNode.getNodeValue());
           ++j;
         }
       }
       else
       {
         while ((j < localNodeList.getLength()) && (k == 0))
         {
           localNode = localNodeList.item(j);
           if ((localNode.getNodeType() == 1) && (localNode.getNodeName().equals(localArrayList.get(i))))
           {
             k = 1;
             localNodeList = localNode.getChildNodes();
           }
           ++j;
         }
       }
     }
     ++i;
   }
   return localHashtable;
 }
}
 