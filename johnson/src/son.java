
public class son {



	public static void main(String farts[]){
		int source = 6,size=6;
		int[][] edgeList = {{0,1,-10},{1,2,-1},{0,2,-20},{2,3,10},{3,4,12},{2,4,120},{4,5,20},{2,5,1}};
		//int[][] edgeList = {{0,1,10},{1,2,1},{0,2,20},{2,3,10},{3,4,12},{2,4,120},{4,5,20},{2,5,1}};
		son john = new son();
		edgeList=john.addImaginaryEdges(edgeList,size);
		bellman bell = new bellman(source);
		size++;
		bell.process(edgeList,size);
		/*for(int i=0;i<size;i++)
				bell.manford(i,size-1);
			System.out.println("Rec Man");
			for(int i=0;i<size;i++){
				for(int j=0;j<size;j++)
					System.out.print(" "+bell.cost[i][j]);
				System.out.println();
			}*/

		System.out.println("It Man");
		int[] nodeVals = bell.manfordIterative();
		john.process(nodeVals,bell.edgeList,bell.nodeList);
		for(int i=0;i<edgeList.length;i++)
			System.out.println("From: "+bell.edgeList[i].vertices[0]+ " to: "+bell.edgeList[i].vertices[1]+" cost: "+bell.edgeList[i].weight);
		for(int i=0;i<size-1;i++){
			dijkstra dij = new dijkstra(source);
			dij.process(bell.edgeList,bell.nodeListForDij,size,i);
			dij.getDijkstra();
			System.out.println("for vertex "+ i);
			for(int j=0;j<size;j++){
				if(dij.vertices[j].dist<Integer.MAX_VALUE)
					System.out.print(dij.vertices[j].dist- nodeVals[i] + nodeVals[j]+" ");
				else
					System.out.print(" N/A ");
			}
			System.out.println();
		}

	}

	private void process(int[] nodeVals, edge[] edgeList,node[] nodeList) {
		for(int i=0;i<nodeList.length;i++){
			int dest = nodeList[i].number;
			for(int e:nodeList[i].edges){
				int source = edgeList[e].vertices[0]==dest?edgeList[e].vertices[1]:edgeList[e].vertices[0];
				if(source!=nodeList.length-1)
					edgeList[e].weight = edgeList[e].weight + nodeVals[source] - nodeVals[dest];
				else
					edgeList[e].weight = 0;
				System.out.println("new edge weight "+edgeList[e].weight);
			}
		}

	}

	private int[][] addImaginaryEdges(int[][] edgeList, int size) {
		int[][] edgeListNew = new int[edgeList.length+size][3];
		for(int i=0;i<edgeList.length;i++)
			edgeListNew[i] = edgeList[i];
		for(int i=edgeList.length;i<edgeList.length+size;i++){
			edgeListNew[i][0]= size;
			edgeListNew[i][1] = i-edgeList.length;
			edgeListNew[i][2] = 0;
		}
		return edgeListNew;
	}


}

