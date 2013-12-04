package bayes_network.builders;

import bayes_network.BayesianNetwork;
import bayes_network.BNNode;


import data.DataSet;
import data.attribute.Attribute;


public class NaiveBayesBuilder extends NetworkBuilder
{
	@Override
	public BayesianNetwork buildNetwork(DataSet data, Integer laplaceCount)
	{
		BayesianNetwork net = super.buildNetwork(data, laplaceCount);
		net.setNetInference(BayesianNetwork.NAIVE_BAYES);
		
		// Get the Node that represents the class attribute
		Attribute classAttr = data.getClassAttribute();
		BNNode classAttrNode = net.getNode(classAttr);
		
		// Create edges from the class Node to all other nodes
		for (BNNode node : net.getNodes())
		{
			if (!node.equals( classAttrNode ))
			{
				net.createEdge(classAttrNode, node);
			}
		}
		
		super.buildCPD(net, data);
		
		return net;
	}	
}
