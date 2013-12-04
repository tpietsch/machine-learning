package bayes_network.cpd;

import bayes_network.BNQuery;
import data.attribute.Attribute;

public class CPDLeaf extends CPDNode
{
	protected double probability;
	
	private Integer laplaceCount;

	/**
	 * Constructor 
	 * 
	 * @param probability the probability at this leaf CPDNode
	 */
	public CPDLeaf(Attribute attribute, 
				   Integer nodeValue, 
				   Integer numInstances,
				   Integer laplaceCount)
	{
		super(attribute, nodeValue, numInstances);
		
		this.laplaceCount = laplaceCount;
	}
	
	@Override
	public String toString()
	{
		//String result = "";
		String result = super.toString();
		result += "  [" + probability + "]";
		return result;
	}
	
	/**
	 * @return the probability at this leaf node
	 */
	public double getProbability()
	{
		return this.probability;
	}
	
	@Override
	public Double calculateProbability(BNQuery query)
	{
		// Get the query value for this node's attribute
		Integer queryValue = query.getValueForQueryAttribute(this.attribute);
		
		// Determine to return this leaf's probability or to return 0
		if (queryValue == null || queryValue == this.nodeValue)
		{
			return this.probability;
		}
		else 
		{
			return 0.0;
		}
	}
	
	@Override
	public void setParent(CPDNode parent)
	{
		this.parent = parent;
		
		double numerator = (double) numInstances + laplaceCount;
		double denominator = parent.numInstances 
				+ (laplaceCount * attribute.getNominalValueMap().size());
		
		this.probability = numerator / denominator;			
	}
}
