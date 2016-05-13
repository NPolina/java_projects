package qa.project.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonWriter;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;
import qa.project.addressbook.model.ContactData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 13.05.2016.
 */
public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contacts count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
       generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if(format.equals("xml")){
            saveAsXml(contacts, new File(file));
        } else if(format.equals("json")){
            saveAsJson(contacts, new File(file));
        } else{
            System.out.println("Unrecognized format!" + format);
        }

    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<ContactData> generateContacts(int count){
        List<ContactData> contacts = new ArrayList<ContactData>();
        File image = new File("src/test/resources/smile.png");
        String url = image.getAbsolutePath();
        for(int i = 0; i < count; i++){
            contacts.add(new ContactData().withFirstname(String.format("Firstname %s;", i)).withLastname(String.format("Lastname %s", i))
                    .withHomePhone(String.format("+373(22) 11-22-33-%s", i)).withWorkPhone(String.format("111 222 33 %s", i))
                    .withAddress((String.format("Street-street 12/3, b.%s", i))).withSecondEmail(String.format("test.test%s@gmail.com", i))
                    .withThirdEmail(String.format("test%s@gmail.com", i)).withGroup(String.format("test %s", i)));
        }
        return contacts;
    }
}
