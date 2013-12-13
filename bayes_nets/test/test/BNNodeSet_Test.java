package test;

import bayes_network.BNNode;
import bayes_network.BNNodeSet;
import bayes_network.BayesianNetwork;
import data.DataSet;
import data.arff.ArffReader;
import data.attribute.Attribute;

public class BNNodeSet_Test 
{
    public static void main(String[] args)
    {  
        testConstruction();
    }
    
    public static void testConstruction()
    {
        /*
         *  Read the training data from the arff file
         */
        ArffReader reader = new ArffReader();
        DataSet data = reader.readFile("./data/test_network.arff");
        data.setClassAttribute("D");
        
        /*
         *  The BNNodeSet 
         */
        BNNodeSet nodes = new BNNodeSet();
        
        
        /*
         *  Create a node corresponding to each nominal attribute in the 
         *  dataset. Continuous attributes are ignored.
         */
        for (Attribute attr : data.getAttributeList())
        {
            if (attr.getType() == Attribute.NOMINAL)
            {
                BNNode newNode = new BNNode(attr);
                nodes.addNode( newNode );
            }
        }
             
        BNNode A = nodes.getNode(data.getAttributeByName("A"));
        BNNode B = nodes.getNode(data.getAttributeByName("B"));
        BNNode C = nodes.getNode(data.getAttributeByName("C"));
        BNNode D = nodes.getNode(data.getAttributeByName("D"));
        BNNode E = nodes.getNode(data.getAttributeByName("E"));
        BNNode F = nodes.getNode(data.getAttributeByName("F"));
        BNNode G = nodes.getNode(data.getAttributeByName("G"));
        

        nodes.createEdge(B, G);
        nodes.createEdge(F, G);
        nodes.createEdge(C, F);
        nodes.createEdge(A, B);
        nodes.createEdge(A, C);
        nodes.createEdge(E, A);
        nodes.createEdge(D, A);
        
    }

}
