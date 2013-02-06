


public class bellman {
	int source;
	int[][] cost;
	node[] nodeList;
	edge[] edgeList;
	bellman(int source){
		this.source = source;
	}



	private int manford(int destination, int maxEdges) {

		System.out.println("destination "+destination+" Maxedges "+maxEdges);
		if(cost[destination][maxEdges] != Integer.MAX_VALUE || maxEdges==0)
			return cost[destination][maxEdges] ;
		node dest = nodeList[destination];

		int tempMin = manford(destination,maxEdges-1);

		for(int e: dest.edges){
			int restPlace = edgeList[e].vertices[0] == destination? edgeList[e].vertices[1]: edgeList[e].vertices[0];
			int coster = manford(restPlace,maxEdges-1);
			if(coster<Integer.MAX_VALUE)
				tempMin = tempMin < coster+edgeList[e].weight?tempMin:coster+edgeList[e].weight;
		} 

		cost[destination][maxEdges] = tempMin;
		return tempMin;
	}
	private void process(int[][] edgelistGiven, int size,int source) {

		cost = new int[size][size];
		nodeList = new node[size];
		edgeList = new edge[edgelistGiven.length];
		for(int i=0;i<size;i++)
			nodeList[i] = new node(i);

		for(int i=0;i<edgelistGiven.length;i++){
			int vertex1 = edgelistGiven[i][0];
			int vertex2 = edgelistGiven[i][1];
			int weight =  edgelistGiven[i][2];
			edge e = new edge(vertex1,vertex2,weight);
			//edgeList[vertex1].edges.add(e);
			edgeList[i] = e;
			nodeList[vertex2].edges.add(i);


		}


		/*for(int j=0;j<size;j++)
			cost[0][j] = 0;*/

		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				cost[i][j] = Integer.MAX_VALUE;
		cost[0][0] = 0;
	}


	public static void main(String farts[]){
		int source = 0,size=6;
		//int[][] edgeList = {{0,1,-10},{1,2,-1},{2,0,-4},{0,2,-20},{2,3,10},{3,4,12},{2,4,120},{4,5,20},{2,5,1}};
		int[][] edgeList = {{0,1,10},{1,2,1},{0,2,20},{2,3,10},{3,4,12},{2,4,120},{4,5,20},{2,5,1}};
		bellman bell = new bellman(source);
		bell.process(edgeList,size,source);
		for(int i=0;i<size;i++)
			bell.manford(i,size-1);
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++)
				System.out.print(" "+bell.cost[i][j]);
			System.out.println();
		}
	}

}