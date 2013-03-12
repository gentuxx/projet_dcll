package tools;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.parsers.*;

import json2xml.FormatXMLMoodle;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



/**
 * Fournit une méthode statique equals permettant de comparer deux fichier XML
 * Retourne vrai si il contiennent les même valeurs 
 * même si la structure n'a pas strictement le même ordre
 * SURTOUT UTILISE POUR LES TESTS
 * @author florian et jeremy
 *
 */
public class XMLComparator {
    /**
     * Fonction retournant vrai si les chaines XML contiennent les mêmes valeurs
     * @param xml1
     * @param xml2
     * @return Si les chaines sont pareilles ou non
     */
    public static boolean compare(String xml1, String xml2){        
        try {
            //Nettoyage des chaines
            xml1 = cleanString(xml1);
            xml2 = cleanString(xml2);
            System.out.println(xml1 + "\n" +xml2);
            
            //Arbres qui vont contenir les differentes valeurs pour la comparaison
            SortedMap<String, String> map1 = new TreeMap<String, String>();
            SortedMap<String, String> map2 = new TreeMap<String, String>();

            //Initilisation des variables DOM
            InputSource is1 = new InputSource();
            is1.setCharacterStream(new StringReader(xml1));
            InputSource is2 = new InputSource();
            is2.setCharacterStream(new StringReader(xml2));
            
            Document document1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is1);
            Document document2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is2);
            
            map1 = parcour(document1.getChildNodes());
            map2 = parcour(document2.getChildNodes());
            
            //On vérifie que les deux map sont identiques
            System.out.println(map1);
            System.out.println(map2);

            return map1.equals(map2);
        
        } catch(ParserConfigurationException pce){
            System.out.println("Erreur de configuration du parseur DOM");
            System.out.println("lors de l'appel à fabrique.newDocumentBuilder();");
            return false;
        } catch(SAXException se){
            System.out.println("Erreur lors du parsing du document");
            System.out.println("lors de l'appel à construteur.parse(xml)");
            return false;
        } catch (Exception e) {
            System.out.println("Les fichiers sont différents");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Construit une map à partir d'une liste de noeuds
     * @param childNodes noueud desquels on va créer la map
     * @return La map générée
     */
    private static SortedMap<String, String> parcour(NodeList childNodes) {
        SortedMap<String, String> map = new TreeMap<String, String>();
        //Pour chaque noeuds
        for(int i = 0; i < childNodes.getLength(); i++){
            //Si le noeud a des enfants
            if(childNodes.item(i).hasChildNodes()){
                //On ajoute le noeud à la map
                map.put(childNodes.item(i).getNodeName()+":Node", "");
                //On parcour l'intérieur récursivement
                map.putAll(parcour(childNodes.item(i).getChildNodes()));
                //On ajoute la valeur du noeud à la map
                //s'il y a plusieur niveau on dit qu'il ya un array en dessous, 
                //le noeud est éclaté par la commande précédente
                if(childNodes.item(i).getChildNodes().getLength() <= 1)
                	map.put(childNodes.item(i).getNodeName()+":Value"+(childNodes.item(i).getLastChild()).toString(), (childNodes.item(i).getLastChild()).toString());
                else
                	map.put(childNodes.item(i).getNodeName()+":Value", "[array]");
            }
            
            //Si il a des attributs on les ajoutes à la map
            if(childNodes.item(i).hasAttributes()){
                for(int j = 0; j < childNodes.item(i).getAttributes().getLength(); j++)
                    map.put(childNodes.item(i).getNodeName()+":Attribute", childNodes.item(i).getAttributes().item(j).toString());
            }
        }
        return map;
    }
    
    /**
     * Construction d'une chaine XML néttoyée
     * On enleve les espaces multiples, les retour à la ligne, les tabulations
     * @param string Chaîne à nettoyer
     * @return Chaîne nettoyée
     */
    private static String cleanString(String string){
        string = string.replaceAll("\n", " ").replaceAll("\t", " ");
        while (string.contains("  ")) {
            string = string.replaceAll("  ", " ");
        }
        while (string.contains(" <")) {
            string = string.replaceAll(" <", "<");
        }
        while (string.contains("> ")) {
            string = string.replaceAll("> ", ">");
        }
        return string;
    }
}
