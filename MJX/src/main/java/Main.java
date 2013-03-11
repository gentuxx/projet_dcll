import xml2json.*;
import json2xml.*;


public class Main {
    
    public static void print(final String string) {
        System.out.println(string);
    }
    
    public static void main(final String[] args) {
        int choice = 4;
        while (choice != 0) {
            print("Que voulez vous faire?");
            print("1 : Conversion XML -> JSON (A FAIRE)");
            print("2 : Conversion JSON -> XML (A FAIRE)");
            print("3 : Test XML -> JSON (A FAIRE)");
            print("4 : Test JSON -> XML (A FAIRE)");
            print("0 : Quitter");
            //choice = new Scanner(System.in).nextInt();
            switch(choice) {
            case 1:
            case 2:
                print("\nA FAIRE\n");
                break;
            case 3:
                String xml = "<question><name>\n"
                + "    <text>Name of question</text>\n"
                + "</name>\n"
                + "<questiontext format=\"html\">\n"
                + "    <text>Who am I?</text>\n"
                + "</questiontext>\n"
                + "<answer fraction=\"100\">\n"
                + "    <text>My Father?</text>\n"
                + "    <feedback>\n"
                + "        <text>Correct!</text>\n"
                + "    </feedback>\n"
                + "</answer>\n"
                + "<answer fraction=\"0\">\n"
                + "    <text>GOD?</text>\n"
                + "    <feedback>\n"
                + "        <text>Ooops!</text>\n"
                + "    </feedback>\n"
                + "</answer>\n"
                + "</question>\n";
                print("\n Test de conversion XML -> JSON:\n");
                print("Test XML: \n" + xml + "\n");
                print("Résultat JSON: \n" + Xml2Json.conversion(xml) + "\n");
                break;
            case 4:
                String json = "{\"question\":{\"name\":{\"text\":\"Name of question\"},\"answer\":[{\"feedback\":{\"text\":\"Correct!\"},\"text\":\"My Father?\",\"fraction\":100},{\"feedback\":{\"text\":\"Ooops!\"},\"text\":\"GOD?\",\"fraction\":0}],\"questiontext\":{\"text\":\"Who am I?\",\"format\":\"html\"}}}";
                print("\n Test de conversion JSON -> XML:\n");
                print("Test JSON: \n" + json + "\n");
                print("Résultat XML: \n" + Json2Xml.conversion(json)+ "\n");
                break;
            default:
                print("Mauvais Choix.");
                break;
            }
            choice = 0;
        }
    }
}
