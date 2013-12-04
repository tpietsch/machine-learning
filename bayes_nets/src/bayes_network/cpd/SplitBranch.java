package bayes_network.cpd;

import data.attribute.Attribute;
import data.instance.Instance;
import data.instance.InstanceSet;

public class SplitBranch 
{

	/**
	 * The value that an instance is tested against to make this split
	 */
	private Double branchValue; 
	
	/**
	 * The attribute this branch tests
	 */
	private Attribute attribute;
	
	/**
	 * All instances that fall to this branch
	 */
	private InstanceSet instanceSet;
		
	public SplitBranch(Attribute attribute, Double branchValue)
	{
		this.instanceSet = new InstanceSet();
		this.attribute = attribute;
		this.branchValue = branchValue;
	}
	
	public InstanceSet getInstanceSet()
	{
		return instanceSet;
	}
	
	/**
	 * Attempt to add an instance to the this split branch.  The instance is 
	 * only add if it passes this branches test.
	 * 
	 * @param instance
	 */
	public void tryAddInstance(Instance instance)
	{		
		if (this.doesInstanceMakeSplit(instance))
		{
			instanceSet.addInstance(instance);
		}
	}
	
	public Attribute getAttribute() 
	{ 
		return attribute;
	} 
	
	/**
	 * @return the value that an instance is tested against to make this split
	 */
	public Double getValue()
	{
		return branchValue;
	}
	
	public Boolean doesInstanceMakeSplit(Instance instance)
	{		
		Double instanceAttrValue = 
				instance.getAttributeValue(this.attribute.getId());
				
		return (instanceAttrValue.doubleValue() == branchValue.doubleValue());
	}

}
