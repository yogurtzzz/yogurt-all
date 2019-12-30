package tiger.tooth;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WeightedRoundRobin {
	private Map<Integer,Integer> effectiveWeights;
	private Map<Integer,Integer> currentWeights;
	private Map<Integer,Integer> weights;

	public WeightedRoundRobin(int[] weights){
		this.effectiveWeights = new ConcurrentHashMap<>();
		this.currentWeights = new ConcurrentHashMap<>();
		this.weights = new ConcurrentHashMap<>();
		for (int i = 0;i < weights.length; i++){
			this.effectiveWeights.put(i,weights[i]);
			this.weights.put(i,weights[i]);
			this.currentWeights.put(i,0);
		}
	}
	public int getNextWeightedObject(){
		int totalWeight = 0;
		for (Map.Entry<Integer,Integer> entry : effectiveWeights.entrySet()){
			totalWeight += entry.getValue();
		}
		int maxWeight = 0;
		int selectedPos = 0;
		for (Map.Entry<Integer,Integer> entry : currentWeights.entrySet()){
			Integer pos = entry.getKey();
			Integer currentWeight = entry.getValue();
			currentWeight += effectiveWeights.get(pos);
			if (currentWeight > maxWeight){
				maxWeight = currentWeight;
				selectedPos = pos;
			}
			currentWeights.put(pos,currentWeight + effectiveWeights.get(pos));
		}

		currentWeights.put(selectedPos,maxWeight - totalWeight);
		return selectedPos;
	}

	public static void main(String[] args) {
		WeightedRoundRobin roundRobin = new WeightedRoundRobin(new int[]{5,4,1});
		Map<Integer,Integer> map = new HashMap<>();
		for (int i = 0; i < 10; i++){
			int selectedNode = roundRobin.getNextWeightedObject();
			if (map.get(selectedNode) == null){
				map.put(selectedNode,1);
			}else{
				int count = map.get(selectedNode);
				map.put(selectedNode,count + 1);
			}
			System.out.print(selectedNode + " ");
		}
		System.out.println();
		long sum = 0;
		for (Map.Entry<Integer,Integer> entry : map.entrySet()){
			sum += entry.getValue();
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		for (Map.Entry<Integer,Integer> entry : map.entrySet()){
			double ratio = (double)entry.getValue() / sum;
			System.out.println(entry.getKey() + " " + ratio);
		}
	}

}
