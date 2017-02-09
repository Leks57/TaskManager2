
package taskmanager2;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public abstract class Xml {

    public static void saveXml() {
        
        
    try {    

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    Document doc = factory.newDocumentBuilder().newDocument();

    Element root = doc.createElement("Задачи");
    doc.appendChild(root);
    
    for(Task t: TaskList.getTasks()){

            Element taskName = doc.createElement("Название");
            taskName.appendChild(doc.createTextNode(t.getName()));
            
            Element taskDescription = doc.createElement("Описание");
            taskDescription.appendChild(doc.createTextNode(t.getDescription()));
            
            Element taskDate = doc.createElement("Дата");
            taskDate.appendChild(doc.createTextNode(TaskManager2.DATE_FORMAT.format(t.getDate())));
            
            Element taskStatus = doc.createElement("Статус");
            taskStatus.appendChild(doc.createTextNode(String.valueOf(t.isCompleted())));
            
            Element taskNo = doc.createElement("Задача");
            
            taskNo.appendChild(taskName);
            taskNo.appendChild(taskDescription);
            taskNo.appendChild(taskDate);
            taskNo.appendChild(taskStatus);
            
            for(Contact c: t.contacts){
                
                Element taskContactName = doc.createElement("Имя");
                taskContactName.appendChild(doc.createTextNode(c.getName()));
                
                Element taskContactSurname = doc.createElement("Фамилия");
                taskContactSurname.appendChild(doc.createTextNode(c.getSurname()));
                
                Element taskContactPhone = doc.createElement("Телефон");
                taskContactPhone.appendChild(doc.createTextNode(String.valueOf(c.getPhone())));
                
                Element taskContact = doc.createElement("Контакт");
                taskContact.appendChild(taskContactName);
                taskContact.appendChild(taskContactSurname);
                taskContact.appendChild(taskContactPhone);
                taskNo.appendChild(taskContact);
                
            }
            root.appendChild(taskNo);
    }
    
    
    
    File file = new File(TaskManager2.getPathFile());
 
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.transform(new DOMSource(doc), new StreamResult(file));
    } catch (Exception e) {
        e.printStackTrace();
      }
    }


    public static void readXml() {
    
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File(TaskManager2.getPathFile()));

            // normalize text representation
            doc.getDocumentElement().normalize();
            //System.out.println ("Root element of the doc is " + 
            //     doc.getDocumentElement().getNodeName());

            NodeList listOfTasks = doc.getElementsByTagName("Задача");
            int totalTasks = listOfTasks.getLength();
            //System.out.println("Общее кол-во задач: " + totalTasks);
            
            for(int s=0; s<totalTasks; s++){
                Node taskNode = listOfTasks.item(s);
                if(taskNode.getNodeType() == Node.ELEMENT_NODE){
                    Element firstTask = (Element)taskNode;
                    
                    Task task1 = new Task();
                    if ((firstTask.getElementsByTagName("Название")).getLength() != 0){
                        task1.setName((firstTask.getElementsByTagName("Название").item(0)).getTextContent());
                    }
                    
                    if ((firstTask.getElementsByTagName("Описание")).getLength() != 0) {
                        task1.setDescription((firstTask.getElementsByTagName("Описание").item(0)).getTextContent());
                    }

                    if ((firstTask.getElementsByTagName("Дата")).getLength() != 0) {
                        task1.setDate(TaskManager2.DATE_FORMAT.parse((firstTask.getElementsByTagName("Дата").item(0)).getTextContent()));
                    }
                    
                    if ((firstTask.getElementsByTagName("Статус")).getLength() != 0) {
                        task1.setCompleted(Boolean.valueOf((firstTask.getElementsByTagName("Статус").item(0)).getTextContent()));
                    }
                    
                    NodeList listOfContacts = firstTask.getElementsByTagName("Контакт");
                    int totalContacts = listOfContacts.getLength();
                        for(int с=0; с<totalContacts; с++){
                            Node contactNode = listOfContacts.item(с);
                            Contact contact1 = new Contact();
                            
                            if(contactNode.getNodeType() == Node.ELEMENT_NODE){
                                Element firstContact = (Element)contactNode;
                                
                                if ((firstContact.getElementsByTagName("Имя")).getLength() != 0){
                                    contact1.setName((firstContact.getElementsByTagName("Имя").item(0)).getTextContent());
                                }
                                
                                if ((firstContact.getElementsByTagName("Фамилия")).getLength() != 0){
                                    contact1.setSurname((firstContact.getElementsByTagName("Фамилия").item(0)).getTextContent());
                                }
                                
                                if ((firstContact.getElementsByTagName("Телефон")).getLength() != 0){
                                    contact1.setPhone(Integer.parseInt((firstContact.getElementsByTagName("Телефон").item(0)).getTextContent()));
                                }
                                
                                task1.addContact(contact1);
                            }
                        }
                        TaskList.addTask(task1);
                }
                }
            }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
}


}
